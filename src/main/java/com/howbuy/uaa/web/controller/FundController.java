package com.howbuy.uaa.web.controller;

import java.io.File;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.howbuy.uaa.command.FundStatisticsCommand;
import com.howbuy.uaa.common.AppChannelSingleData;
import com.howbuy.uaa.common.contants.UaaContants;
import com.howbuy.uaa.common.execption.UaaRunTimeException;
import com.howbuy.uaa.dto.FundResponse;
import com.howbuy.uaa.dto.FundResponseCollMapping;
import com.howbuy.uaa.dto.FundResponseMapping;
import com.howbuy.uaa.service.AnalysisCoreDataService;
import com.howbuy.uaa.utils.DateUtils;
import com.howbuy.uaa.utils.ExportExcel;
import com.howbuy.uaa.utils.FileUtil;
import com.howbuy.uaa.utils.HttpUtil;
import com.howbuy.uaa.utils.JsonParse;
import com.howbuy.uaa.utils.SortUtil;
import com.howbuy.uaa.utils.UaaDataUtil;
import com.howbuy.uaa.utils.UaaReqUtil;
import com.howbuy.uaa.utils.UaaStringUtils;


/**
 * 公募统计
 * @author qiankun.li
 *
 */
public class FundController extends MultiActionController{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FundController.class);
	
	private AnalysisCoreDataService analysisCoreDataService;
	
	private String index;
	private String wirelessWd;
	private String fundGraph;
	private String fundChannelDetail;
	private String fundTrendDetail;
	private String fundTrendDetailTbody;
	private String fundTypeDetail;
	
	/**
	 * 获取渠道关系数据URL
	 */
	private String channelUrl;
	/**
	 * 获取每日公募统计指标明细URL
	 */
	private String custUrl;
	/**
	 * 获取每日公募统计下级指标明细URL
	 */
	private String sjhzUrl;
	
	private UaaReqUtil fundReqUtil;
	
	private volatile String titile_jsonString ="";
	//排序字段：人均下单金额、人均成交金额、人家支付金额、当日开户绑卡率、当日开户验卡率、当日开户鉴权率、当日开户交易率、下单转化率、成交转化率
	public static List<String> orderByList = Arrays.asList("rjxdje","rjcjje","rjzfje","drkhbkl","drkhykl","drkhjql","drkhjyl","xdzhl","cjzhl");
	
	static Map<String,String> orderByMap = new HashMap<String,String>(){
		/**
		 * 
		 */
		private static final long serialVersionUID = 1232541431991171543L;

		{
			//PS：排序字段对应的值，作为CRM的查询SQL的拼接变量 如：order by drxdje/drxdrs
			//其中drxdje/drxdrs代表两个变量相除得到的值
			
			put("rjxdje", "drxdje/drxdrs");
			put("rjcjje", "drqrjycjje/drqrjycjrs");
			put("rjzjje", "drzfje/drzfrs");
			put("drkhbkl", "drkhdrbk/drkh");
			put("drkhykl", "drkhdryk/drkh");
			put("drkhjql", "drkhdrjq/drkh");
			put("drkhjyl", "drkhdrjyrs/drkh");
			put("xdzhl", "");
			put("cjzhl", "");
			
		}
	};
	
	private static Map<String,String> orderByMappingMap = new HashMap<String,String>(){
		/**
		 * 
		 */
		private static final long serialVersionUID = 5201198145235173098L;

		{
			put("rjxdje", "rjxdzje");
			put("rjcjje", "rjcjzje");
			put("rjzfje", "rjzzfje");
			put("drkhbkl", "drkhzbkl");
			put("drkhykl", "drkhzykl");
			put("drkhjql", "drkhzjql");
			put("drkhjyl", "drkhzjyl");
			put("xdzhl", "xdzzhl");
			put("cjzhl", "cjzzhl");
		}
		
	};
	
	public void init(){
		//公募客户渠道API地址如下（渠道信息）
		try {
			fundReqUtil = new UaaReqUtil(getCustUrl(),getSjhzUrl());
			
			if(StringUtils.isBlank(channelUrl)){
				throw new UaaRunTimeException("init channel failed,the request url is null");
			}
			String json =HttpUtil.getHttpUtil().requestGet(channelUrl);
			JSONObject jsonobj = JSONObject.fromObject(json);
			JSONArray disArrs = jsonobj.getJSONObject("body").getJSONArray("list");
			titile_jsonString = disArrs.toString();
			
			LOGGER.debug(titile_jsonString);
			
		} catch (Exception e) {
			LOGGER.error("FundController init error.",e);
		}
	}
	
	private static Map<String,String> fundMap = new HashMap<String,String>(){
		/**
		 * 
		 */
		private static final long serialVersionUID = 5201198145235173098L;

		{
			/*drkh			当日开户人数
			drscbk			当日首次绑卡人数
			drscyk			当日首次验卡人数
			drscjq			当日首次鉴权人数
			drkhdrbk		当日开户当日绑卡人数
			drkhdryk		当日开户当日验卡人数
			drkhdrjq		当日开户当日鉴权人数
			drxzjyrs		当日新增交易人数
			drxzjyje		当日新增交易金额
			drkhdrjybs		当日开户当日交易笔数
			drkhdrjyje		当日开户当日交易金额
			drxdbs			当日下单笔数
			drxdje			当日下单金额
			drqrjycjbs		当日确认交易的成交笔数
			drqrjycjje		当日确认交易的成交金额
			drkhdrjyrs		当日开户当日交易人数
			drxdrs			当日下单人数
			drqrjycjrs		当日确认交易的成交人数
			drzfrs				支付人数
							支付金额
							支付笔数

			*/
			/*zkh			总开户数
			sczbk			首次总绑卡数
			sczyk			首次总验卡数
			sczjq			首次总鉴权数
			drkhdrzbk		当日开户当日总绑卡数
			drkhdrzyk		当日开户当日总验卡数
			drkhdrzjq		当日开户当日总鉴权数
			xzjyzrs			新增交易总人数
			xzjyzje			新增交易总金额
			drkhdrjyzbs		当日开户当日交易总笔数
			drkhdrjyzje		当日开户当日交易总金额
			xdzbs			下单总笔数
			xdzje			下单总金额
			qrjycjzbs		确认交易成交总笔数
			qrjycjzje		确认交易成交总金额
			drkhdrjyzrs		当日开户当日交易总人数
			xdzrs			下单总人数
			qrjycjzrs		确认交易成交总人数*/
			
			//key 指标字段，value：指标汇总字段
			put("drkh","zkh");
			put("drscbk","sczbk");
			put("drscyk","sczyk");
			put("drscjq","sczjq");
			put("drkhdrbk","drkhdrzbk");
			put("drkhdryk","drkhdrzyk");
			put("drkhdrjq","drkhdrzjq");
			put("drxzjyrs","xzjyzrs");
			put("drxzjyje","xzjyzje");
			put("drkhdrjybs","drkhdrjyzbs");
			put("drkhdrjyje","drkhdrjyzje");
			put("drxdbs","xdzbs");
			put("drxdje","xdzje");
			put("drqrjycjbs","qrjycjzbs");
			put("drqrjycjje","qrjycjzje");
			put("drkhdrjyrs","drkhdrjyzrs");
			put("drxdrs","xdzrs");
			put("drqrjycjrs","qrjycjzrs");
			//人均下单金额-人均总金额
			put("rjxdje","rjxdzje");
			//人均成交金额-人均成交总金额
			put("rjcjje","rjcjzje");
			//当日开户绑卡率-选择的时间范围内的开户总绑卡率
			put("drkhbkl","drkhzbkl");
			//当日开户验卡率-选择的时间范围内的开户验卡率
			put("drkhykl","drkhzykl");
			//当日开户鉴权率-选择的时间范围内的开户鉴权率
			put("drkhjql","drkhzjql");
			//当日开户交易率-选择的时间范围内的开户交易率
			put("drkhjyl","drkhzjyl");
			//当日支付人数-总支付人数
			put("drzfrs","zzfrs");
			//当日支付笔数-总支付人数
			put("drzfbs","zzfbs");
			//当日支付金额-总支付人数
			put("drzfje","zzfje");
			//人均支付金额
			put("rjzfje","rjzzfje");
			
			
			
			//下单转化率-下单总转化率 TODO 未统计
			put("xdzhl","xdzzhl");
			//成交转化率-成交总转化率 TODO 未统计
			put("cjzhl","cjzzhl");
			
			put("childname", "childname");
		}
	};
	
	Map<Integer, String> gidMap = new HashMap<Integer, String>(4);
	{
		gidMap.put(31,"discode");
		gidMap.put(30,"discode");
		gidMap.put(15,"tradechan");
		gidMap.put(14,"tradechan");
		gidMap.put(7,"hzlxcode");
		gidMap.put(6,"hzlxcode");
		gidMap.put(3,"outletcode");
		gidMap.put(2,"outletcode");
		gidMap.put(1,"fundtype");
		gidMap.put(0,"fundtype");
		
	}
	List<String> pathcode =Arrays.asList("discode","tradechan","hzlxcode","outletcode");
	Map<String,String> pathName = new HashMap<String,String>();
	{
		pathName.put("discode1", "开户机构");
		pathName.put("tradechan1", "开户平台");
		pathName.put("hzlxcode1", "合作类型");
		pathName.put("outletcode1", "开户网点(渠道)");
		pathName.put("discode2", "分销机构");
		pathName.put("tradechan2", "交易平台");
		pathName.put("hzlxcode2", "合作类型");
		pathName.put("outletcode2", "交易网点");
	}
	
	public ModelAndView index(HttpServletRequest request,HttpServletResponse response) {
		Map<String,Object> modelMap = new HashMap<String, Object>();
		String channelData = AppChannelSingleData.getSingleData().getChannelData();
		if(StringUtils.isNotBlank(channelData))
			modelMap.put("gmtjqd", channelData);//渠道数据
		else
			modelMap.put("gmtjqd", titile_jsonString);//渠道数据
		
		modelMap.put("wd", "1");//默认为开户维度
		return new ModelAndView(index).addAllObjects(modelMap);
		
	}
	
	/** 
	 * 生成图表
	 * @param request
	 * @param response
	 * @param fs
	 * @return
	 * @throws ParseException
	 * @throws ServletRequestBindingException 
	 */
	public ModelAndView graph(HttpServletRequest request,HttpServletResponse response,FundStatisticsCommand fs) throws ParseException, ServletRequestBindingException{

		String start = fs.getStartDate();
		String end = fs.getEndDate();
		//维度 1开户、2交易 默认1
		String analysisWd = UaaStringUtils.nullToString(fs.getAnalysisWd(),"1");
		//开户机构
		String openInst = UaaStringUtils.nullToString(fs.getOpenInst(), "-1");
		//开户平台
		String openPlatform = UaaStringUtils.nullToString(fs.getOpenPlatform(),"-1");
		//合作类型
		String cooperateType =UaaStringUtils.nullToString(fs.getCooperateType(),"-1");
		//开户网点
		String openwangdian = UaaStringUtils.nullToString(fs.getOpenWangDian(),"-1");
		//基金类型
		String fundType = UaaStringUtils.nullToString(fs.getFundType(),"-1");
		
		int gid=31;
		StringBuffer paramsSb= new StringBuffer("bizDimension="+analysisWd);
		start = start.replace("-", "");
		end = end.replace("-", "");
		paramsSb.append("&statdtStart="+start+"&statdtEnd="+end);
		
		if(!openInst.equals("-1"))
		{
			gid -= 16;
			paramsSb.append("&disCode="+openInst);
		}
		if(!openPlatform.equals("-1"))
		{
			gid -= 8;
			if(!UaaContants.A.equals(openPlatform)){
				paramsSb.append("&tradeChan="+openPlatform);
			}
		}
		if(!cooperateType.equals("-1")){
			gid -= 4;
			if(!UaaContants.A.equals(cooperateType)){
				paramsSb.append("&hzlxCode="+cooperateType);
			}
		}
		if(!openwangdian.equals("-1")){
			gid -= 2;
			paramsSb.append("&outletCode="+openwangdian);
		}
		boolean isFundType = false;
		if(!fundType.equals("-1")){
			gid -= 1;
			paramsSb.append("&fundType="+fundType);
			isFundType =true;
		}else{
			//基金类型为空 fundTypeStat生效
			paramsSb.append("&fundTypeStat=%s");
		}
		paramsSb.append("&gid="+gid);
		String params1 = null;
		if(!isFundType){
			params1 = String.format(paramsSb.toString(),"1");
		}else{
			params1 = paramsSb.toString();
		}
		Map<String, Object> modelMap = new HashMap<String, Object>();
		//获取当前维度日期明细数据与汇总数据
		FundResponse fResponse = fundReqUtil.fundRespon(params1,0);
		List<FundResponseCollMapping> sumColllist = fResponse.getColl();
		List<FundResponseMapping> datalistOne=null;
		List<FundResponseMapping> datalistSum=null;
		if(gid<=1){//无细分渠道
			datalistOne = fResponse.getList();
			if(!CollectionUtils.isEmpty(datalistOne)){
				datalistOne = UaaDataUtil.calExtraQuotaData(datalistOne);
				String dataJsonOne = JsonParse.arrayToJsonStr(datalistOne);
				LOGGER.debug("dataJsonOne:"+dataJsonOne);
				modelMap.put("dataJsonOne", dataJsonOne);
			}
		}else{
			datalistSum = fResponse.getList();
			if(!CollectionUtils.isEmpty(datalistSum)){
				datalistSum = UaaDataUtil.calExtraQuotaData(datalistSum);
				String dataJsonSum = JsonParse.arrayToJsonStr(datalistSum);
				LOGGER.debug("dataJsonSum:"+dataJsonSum);
				modelMap.put("dataJsonSum", dataJsonSum);
			}
		}
		
		//获取当前维度下级明细和汇总
		FundResponse childResponse = fundReqUtil.fundRespon(params1, 1);
		List<FundResponseCollMapping> collList = childResponse.getColl();
		summaryCalculate(collList);
		List<FundResponseMapping> datalist = childResponse.getList();
		datalist = UaaDataUtil.calExtraQuotaData(datalist);
		FundResponseCollMapping summary=null;
		//当前维度汇总数据处理
		if(!CollectionUtils.isEmpty(sumColllist)){
			summaryCalculate(sumColllist);
			summary= sumColllist.get(0);
		}
		if(!isFundType){
			String params0 = String.format(paramsSb.toString(),"0");
			//获取当前维度下级明细和汇总 对基金类型汇总的数据
			FundResponse childResponseFundType = fundReqUtil.fundRespon(params0, 1);
			List<FundResponseCollMapping> collListFundType = childResponseFundType.getColl();
			summaryCalculate(collListFundType);
			List<FundResponseMapping> datalistFundType = childResponseFundType.getList();
			datalistFundType = UaaDataUtil.calExtraQuotaData(datalistFundType);
			String collJsonFundType = JsonParse.arrayToJsonStr(collListFundType);
			String dataJsonFundType = JsonParse.arrayToJsonStr(datalistFundType);
			modelMap.put("collJsonFundType", collJsonFundType);
			modelMap.put("dataJsonFundType", dataJsonFundType);
		}
		
		//横坐标
		Date df = DateUtils.parseDate(start, DateUtils.FORMAT_YYYYMMDD);
		Date de = DateUtils.parseDate(end, DateUtils.FORMAT_YYYYMMDD);
		List<String> dateList = UaaDataUtil.getDateXList(df,de,DateUtils.FORMAT_D_YYYYMMDD);
		
		modelMap.put("startTime", start);
		modelMap.put("endTime",end);
		modelMap.put("gid",gid);
		modelMap.put("fundType",fundType);
		modelMap.put("wd",analysisWd);
		modelMap.put("summary", summary);
		modelMap.put("datelist", dateList);
		modelMap.put("fundMap", fundMap);
		if(!CollectionUtils.isEmpty(collList)){
			String collJson = JsonParse.arrayToJsonStr(collList);
			modelMap.put("collJson", collJson);
			LOGGER.debug("collJson:"+collJson);
		}
		
		if(!CollectionUtils.isEmpty(datalist)){
			String dataJson = JsonParse.arrayToJsonStr(datalist);
			modelMap.put("dataJson", dataJson);
			LOGGER.debug("dataJson:"+dataJson);
		}
		return new ModelAndView(fundGraph).addAllObjects(modelMap);
	}
	
	
	/**
	 * 渠道明细查询
	 * @param request
	 * @param response
	 * @param fs
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ModelAndView channelDetail(HttpServletRequest request,HttpServletResponse response,FundStatisticsCommand fs){
		String childname = "discode";
		int cnt = 0;
		String orderby = UaaStringUtils.nullToString(fs.getOrderBy(),"");
		String order = UaaStringUtils.nullToString(fs.getOrder(),"");
		Map<String, String > path = new HashMap<String ,String>();
		if(fs.getOpenInst() == null || fs.getOpenInst().equals("-1")){
			path.put("discode", "-1");
			childname = "discode";
		}
		else {
			path.put("discode", fs.getOpenInst());
			if(fs.getOpenInst().equals("HB000A001"))
				// 好买显示旗下平台
				childname = "tradechan";//平台
			else {
				//其他显示旗下网点
				childname = "outletcode";//网点
			}
			cnt++;
		}
		String openPlatform = fs.getOpenPlatform();
		if(openPlatform== null || openPlatform.equals("-1")){
			path.put("tradechan", "-1");
		}
		else {
			if(!UaaContants.A.equals(openPlatform)){
				path.put("tradechan", openPlatform);
			}
			childname = "hzlxcode";
			cnt++;
		}
		String cooperateType = fs.getCooperateType();
		if(fs.getCooperateType() == null || "-1".equals(cooperateType)){
			path.put("hzlxcode", "-1");
		}
		else {
			if(!UaaContants.A.equals(cooperateType)){
				path.put("hzlxcode", cooperateType);
			}
			childname = "outletcode";
			cnt++;
		}
		if(fs.getOpenWangDian() == null || fs.getOpenWangDian().equals("-1")){
			path.put("outletcode", "-1");
		}
		else {
			path.put("outletcode", fs.getOpenWangDian());
			childname = "none";
			cnt++;
		}
		List<FundResponseCollMapping> showList = new ArrayList<FundResponseCollMapping>();
		Map<String,Object> resMap = GetParams(fs);
		String params = resMap.get("params").toString();
		FundResponse fResponse = fundReqUtil.fundRespon(params,0);
		List<FundResponseCollMapping> colllist = fResponse.getColl();
		if(colllist != null)
			summaryCalculate(colllist);
		FundResponse fResponseChild = fundReqUtil.fundRespon(params,1);
		List<FundResponseCollMapping> childCollList = fResponseChild.getColl();
		if(childCollList != null){
			summaryCalculate(childCollList);
			if(orderByList.contains(orderby)){
				SortUtil<FundResponseCollMapping> sort= new SortUtil<FundResponseCollMapping>();
				String orderMap = orderByMappingMap.get(orderby);
				String method = "get"+orderMap.replaceFirst(orderMap.substring(0, 1),orderMap.substring(0, 1).toUpperCase())  ; 
				sort.sort(childCollList, method, order);
				
				for(int i = 0;i<childCollList.size();i++){
					if(childCollList.get(i)!=null){
						FundResponseCollMapping fund = (FundResponseCollMapping)childCollList.get(i);
						showList.add(fund);
					}
					
				}
			}else{
				showList = childCollList;
			}
		}
			
		
		List<String> theadList = (List<String>) resMap.get("theadlist");
		
		Map<String,Object> modelMap = new HashMap<String, Object>();
		modelMap.put("coll", colllist);//全部数据
		modelMap.put("theadlist",theadList);//所选指标
		modelMap.put("colllist", showList);//子集数据
		modelMap.put("wd", fs.getAnalysisWd());//分析维度
		modelMap.put("discode", fs.getOpenInst());//开户机构
		modelMap.put("childname", childname);//子渠道维度
		modelMap.put("pathcode", pathcode);
		modelMap.put("pathname", pathName);
		modelMap.put("cnt",cnt);
		
		return new ModelAndView(fundChannelDetail).addAllObjects(modelMap);
	}
	
	/**
	 * 渠道明细导出
	 * @param request
	 * @param response
	 */
	public void channelDetailExport(HttpServletRequest request,HttpServletResponse response){
		String start = request.getParameter("startDate");
		String end = request.getParameter("endDate");
		String analysisWd = request.getParameter("analysisWd");
		
		//根据以下维度计算gid
		String openInst = UaaStringUtils.nullToString(request.getParameter("openInst"), "-1");
		String openPlatform  = UaaStringUtils.nullToString(request.getParameter("openPlatform"), "-1");
		String cooperateType = UaaStringUtils.nullToString(request.getParameter("cooperateType"), "-1");
		String openwangdian = UaaStringUtils.nullToString(request.getParameter("openWangDian"), "-1");
		String fundType = UaaStringUtils.nullToString( request.getParameter("fundType"), "-1");
		String wdname = UaaStringUtils.nullToString( request.getParameter("wdname"), "");
		
		//页面参数
		String openNorm = request.getParameter("openNorm");
		String tradeNorm = request.getParameter("tradeNorm");
		
		String currentParam = request.getParameter("currentParam");
		int gid=31;
		StringBuffer params= new StringBuffer("pageIndex=1&bizDimension="+analysisWd);
		if(!openInst.equals("-1"))
		{
			gid -= 16;
			params.append("&disCode="+openInst);
		}
		if(!openPlatform.equals("-1"))
		{
			gid -= 8;
			if(!UaaContants.A.equals(openPlatform)){
				params.append("&tradeChan="+openPlatform);
			}
		}
		if(!cooperateType.equals("-1")){
			gid -= 4;
			if(!UaaContants.A.equals(cooperateType)){
				params.append("&hzlxCode="+cooperateType);
			}
		}
		if(!openwangdian.equals("-1")){
			gid -= 2;
			params.append("&outletCode="+openwangdian);
		}
		if(!fundType.equals("-1")){
			gid -= 1;
			params.append("&fundType="+fundType);
		}
		params.append("&statdtStart="+start.replace("-", "")+"&statdtEnd="+end.replace("-", ""));
		params.append("&gid="+gid);
		
		String paramsStr = params.toString();
		FundResponse fcollResponse=null;
		List<FundResponseCollMapping> collchildlist = null;
		if(gid>1){
			fcollResponse= fundReqUtil.fundRespon(paramsStr, 1);
		}else{
			fcollResponse = fundReqUtil.fundRespon(paramsStr,0);
		}
		if(null!=fcollResponse){
			collchildlist= fcollResponse.getColl();
		}
		//List<FundResponseCollMapping> colllist = fResponse.getColl();
		//List<FundResponseCollMapping> collchildlist = fcollResponse.getColl(); 
		for(FundResponseCollMapping coll:collchildlist)
		{
			if(coll.getDisname() != null)coll.setChildname(coll.getDisname());
			if(coll.getChanname() != null)coll.setChildname(coll.getChanname());
			if(coll.getHzlx() != null)coll.setChildname(coll.getHzlx());
			if(coll.getOutletname() != null)coll.setChildname(coll.getOutletname());
			
			if(coll.getChildname() == null){
				if(StringUtils.isNotBlank(wdname)){
					coll.setChildname(wdname);
				}else{
					coll.setChildname("No Name");
				}
			}
		}
		/*if(colllist != null)
			summaryCalculate(colllist);*/
		if(collchildlist != null)
			summaryCalculate(collchildlist);
		
		List<String> theadlist = new ArrayList<String>();
		theadlist.add("childname");
		List<String> remove = new ArrayList<String>();
		remove.add("-1");
		if(openNorm.length() != 0)
		{
			String[] openarr = openNorm.split("\\$");
			List<String> openList = Arrays.asList(openarr);
			theadlist.addAll(openList);
		}
		if(tradeNorm.length() != 0)
		{
			String[] tradearr =  tradeNorm.split("\\$");
			List<String> tradeList = Arrays.asList(tradearr);
			theadlist.addAll(tradeList);
		}
		theadlist.removeAll(remove);
		int theadlength= theadlist.size();
		for(int i=0; i<theadlength ; i++)
		{
			theadlist.set(i, fundMap.get(theadlist.get(i)).toString());
		}
		
		ExportExcel<FundResponseCollMapping> ex = new ExportExcel<FundResponseCollMapping>();
		String[] header = (String[])theadlist.toArray(new String[theadlist.size()]);
		String rootpath = request.getSession().getServletContext().getRealPath("/");
		File dataFile = new File(rootpath+"resources/data_detail.xls");
		String th = gidMap.get(gid);
		String tag = pathName.get(th+analysisWd);
		ex.exportExcel("渠道明细", header,header, collchildlist, dataFile,tag);
        //String fileName = "data_detail_"+DateUtils.getFormatedDate(new Date(),DateUtils.FORMAT_YYYYMMDD_HHMMSS)+".xls";
        String fileName = currentParam+".xls";
        FileUtil.down(dataFile, fileName, response);
		
	}
	
	/**趋势明细
	 * @param request
	 * @param response
	 * @param fs
	 * @return
	 */
	public ModelAndView trendDetail(HttpServletRequest request,HttpServletResponse response,FundStatisticsCommand fs){
		String analysisWd = fs.getAnalysisWd();
		
		String pagerows = fs.getPageRows();
		int rows = Integer.parseInt(pagerows);
		
		String orderby = UaaStringUtils.nullToString(fs.getOrderBy(),"");
		String order = UaaStringUtils.nullToString(fs.getOrder(),"");
		String curpage = UaaStringUtils.nullToString(fs.getCurPage(),"1");
		int current = Integer.parseInt(curpage);
//		if(orderByList.contains(orderby))
//		{
//			//orderByMap中value为空的参数
//			if("xdzhl".equals(orderby) || "cjzhl".equals(orderby)){}
//			else{
//				fs.setOrderBy("trendDetail");
//			}
//		}
		List<FundResponseMapping> showList = new ArrayList<FundResponseMapping>();
		Map<String, Object> modelMap = new HashMap<String, Object>(0);
		Map<String,Object> resMap = GetParams(fs);
		modelMap.put("theadlist",resMap.get("theadlist"));
		FundResponse fResponse = fundReqUtil.fundRespon(resMap.get("params").toString(),0);
		List<FundResponseMapping> datalist = fResponse.getList();
		List<FundResponseCollMapping> colllist = fResponse.getColl();
		int pageCount = fResponse.getPageCount();
		
		if(!CollectionUtils.isEmpty(datalist)){
			calculateLv(datalist);//计算率、平均值
			if(orderByList.contains(orderby)){
				SortUtil<FundResponseMapping> sort= new SortUtil<FundResponseMapping>();
				String method = "get"+orderby.replaceFirst(orderby.substring(0, 1),orderby.substring(0, 1).toUpperCase())  ; 
				sort.sort(datalist, method, order);
				int maxIndex = current*rows;
				if(maxIndex>=pageCount){
					maxIndex = pageCount; 
				}
				for(int i = (current-1)*rows;i<maxIndex;i++){
					if(datalist.get(i)!=null){
						FundResponseMapping fund = (FundResponseMapping)datalist.get(i);
						showList.add(fund);
					}
					
				}
				modelMap.put("datalist", showList);
			}else
				modelMap.put("datalist", datalist);
		}
		if(!CollectionUtils.isEmpty(colllist)){
			summaryCalculate(colllist);
			modelMap.put("coll", colllist);
		}
		
		
		int pages = pageCount % rows == 0?pageCount/rows:pageCount/rows+1;
		modelMap.put("rows", pagerows);
		modelMap.put("pages", pages);
		modelMap.put("maxrows", pageCount);
		modelMap.put("wd", analysisWd);
		return new ModelAndView(fundTrendDetail).addAllObjects(modelMap);
	}
	
	/*
	 * 趋势明细导出
	 */
	public void trendDetailExport(HttpServletRequest request,HttpServletResponse response){
		
		String start = request.getParameter("startDate");
		String end = request.getParameter("endDate");
		String analysisWd = request.getParameter("analysisWd");
		
		//根据以下维度计算gid
		String openInst = request.getParameter("openInst")==null?"-1":request.getParameter("openInst");
		String openPlatform =request.getParameter("openPlatform")==null?"-1":request.getParameter("openPlatform");
		String cooperateType =request.getParameter("cooperateType")==null?"-1":request.getParameter("cooperateType");
		String openwangdian = request.getParameter("openWangDian")==null?"-1":request.getParameter("openWangDian");
		String fundType = request.getParameter("fundType")==null?"-1":request.getParameter("fundType");
		
		//页面参数
		String openNorm = request.getParameter("openNorm");
		String tradeNorm = request.getParameter("tradeNorm");
/*		String orderby = request.getParameter("orderBy");
		String order = request.getParameter("order");
		String pagerows = request.getParameter("pageRows");*/
		String curpage = request.getParameter("curPage");
		
		String currentParam = request.getParameter("currentParam");
		
		int gid=31;
		String params="pageIndex="+curpage+"&bizDimension="+analysisWd+"&statdtStart="+start.replace("-", "")+"&statdtEnd="+end.replace("-", "");
		//String params="pageIndex=1&pageSize=10&bizDimension="+analysisWd+"&statdtStart=20150417&statdtEnd=20150418";
		if(!openInst.equals("-1"))
		{
			gid -= 16;
			params += "&disCode="+openInst;
		}
		if(!openPlatform.equals("-1"))
		{
			gid -= 8;
			if(!UaaContants.A.equals(openPlatform)){
				params += "&tradeChan="+openPlatform;
			}
		}
		if(!cooperateType.equals("-1")){
			gid -= 4;
			if(!UaaContants.A.equals(cooperateType)){
				params += "&hzlxCode="+cooperateType;
			}
		}
		if(!openwangdian.equals("-1")){
			gid -= 2;
			params += "&outletCode="+openwangdian;
		}
		if(!fundType.equals("-1")){
			gid -= 1;
			params += "&fundType="+fundType;
		}
		
		params += "&gid="+gid;
		
		
		FundResponse fResponse = fundReqUtil.fundRespon(params,0);
		List<FundResponseMapping> datalist = fResponse.getList();
		if(datalist != null){
			calculateLv(datalist);//计算率、平均值
		}
		List<String> theadlist = new ArrayList<String>();
		theadlist.add("statdt");
		List<String> remove = new ArrayList<String>();
		remove.add("-1");
		if(openNorm.length() != 0)
		{
			String[] openarr = openNorm.split("\\$");
			List<String> openList = Arrays.asList(openarr);
			theadlist.addAll(openList);
		}
		if(tradeNorm.length() != 0)
		{
			String[] tradearr =  tradeNorm.split("\\$");
			List<String> tradeList = Arrays.asList(tradearr);
			theadlist.addAll(tradeList);
		}
		theadlist.removeAll(remove);
		LOGGER.debug("theadlist"+theadlist);
		
		ExportExcel<FundResponseMapping> ex = new ExportExcel<FundResponseMapping>();
		String[] header = (String[])theadlist.toArray(new String[theadlist.size()]);
		String rootpath = request.getSession().getServletContext().getRealPath("/");
		File dataFile = new File(rootpath+"resources/data_detail.xls");
		ex.exportExcel("趋势明细", header,header, datalist, dataFile,"");
        String fileName = currentParam+".xls";
        FileUtil.down(dataFile, fileName, response);
		
	}
	
	/**tbody显示 分页|页面行数|排序
	 * @param request
	 * @param response
	 * @param fs
	 * @return
	 */
	public ModelAndView trendDetailTbody(HttpServletRequest request,HttpServletResponse response,FundStatisticsCommand fs){
		String analysisWd = fs.getAnalysisWd();
		
		String pagerows = fs.getPageRows();
		int rows = Integer.parseInt(pagerows);
		
		String orderby = UaaStringUtils.nullToString(fs.getOrderBy(),"");
		String order = UaaStringUtils.nullToString(fs.getOrder(),"");
		String curpage = UaaStringUtils.nullToString(fs.getCurPage(),"1");
		int current = Integer.parseInt(curpage);
		
		List<FundResponseMapping> showList = new ArrayList<FundResponseMapping>();
		Map<String,Object> resMap = GetParams(fs);
		FundResponse fResponse = fundReqUtil.fundRespon(resMap.get("params").toString(),0);
		List<FundResponseMapping> datalist = fResponse.getList();
		if(!CollectionUtils.isEmpty(datalist)){
			calculateLv(datalist);
			if(orderByList.contains(orderby)){
				SortUtil<FundResponseMapping> sort= new SortUtil<FundResponseMapping>();
				String method = "get"+orderby.replaceFirst(orderby.substring(0, 1),orderby.substring(0, 1).toUpperCase())  ; 
				sort.sort(datalist, method, order);
				int pageCount = fResponse.getPageCount();
				int maxIndex = current*rows;
				if(maxIndex>=pageCount){
					maxIndex = pageCount ; 
				}
				for(int i = (current-1)*rows;i<maxIndex;i++){
					if(datalist.get(i)!=null){
						FundResponseMapping fund = (FundResponseMapping)datalist.get(i);
						showList.add(fund);
					}
					
				}
			}else{
				showList = datalist;
			}		
			
		}
		
		
		return new ModelAndView(fundTrendDetailTbody)
		.addObject("datalist", showList)
		.addObject("theadlist", resMap.get("theadlist"))
		.addObject("wd", analysisWd);
		
	}
	
	/**基金类型明细
	 * @param request
	 * @param response
	 * @param fs
	 * @return
	 */
	public ModelAndView fundDetail(HttpServletRequest request,HttpServletResponse response,FundStatisticsCommand fs){
		String analysisWd = fs.getAnalysisWd();
		/*String[] fundlist = {"0","1","2","3","4","6","7","a"};
		int listlen = fundlist.length;*/
		String[] fundnamelist  = {"股票型","混合型","债券型","货币型","QDII","结构型","公募专户","储蓄罐"};
		Map<String, Object> modelMap = new HashMap<String, Object>();
		Map<String,Object> resMap = GetParams(fs);
		modelMap.put("theadlist", resMap.get("theadlist"));
		//List<FundResponseCollMapping> fundcolllist = new ArrayList<FundResponseCollMapping>();
		/*String para = resMap.get("params").toString() + "&fundTypeStat=1";
		int st = para.indexOf("gid");
		int gid = Integer.parseInt(para.substring(st+4)) - 1;
		for(int i =0 ;i<listlen;i++)
		{
			String param = para.substring(0, st+4);
			param = param + gid + "&fundType=" + fundlist[i];
			LOGGER.debug(param);
			FundResponse fResponse = fundReqUtil.fundRespon(param,0);
			List<FundResponseCollMapping> colllist = fResponse.getColl();
			if(colllist != null){
				SummaryCalculate(colllist);
			}
			if(colllist!=null)
				fundcolllist.add(colllist.get(0));
			else {
				fundcolllist.add(null);
			}
		}*/
		FundResponse fResponse = fundReqUtil.fundRespon(resMap.get("params").toString(),0);
		List<FundResponseCollMapping> coll = fResponse.getColl();
		if(!CollectionUtils.isEmpty(coll)){
			summaryCalculate(coll);
			modelMap.put("coll", coll.get(0));
		}
		FundResponse fCollRespon = fundReqUtil.fundRespon(resMap.get("params").toString()+"&fundTypeStat=0", 1);
		List<FundResponseCollMapping> fundcolllist = fCollRespon.getColl();
		if(!CollectionUtils.isEmpty(fundcolllist))
		{
			summaryCalculate(fundcolllist);
			modelMap.put("colllist", fundcolllist);
		}
		modelMap.put("fundlist", fundnamelist);
		modelMap.put("wd", analysisWd);
		return new ModelAndView(fundTypeDetail).addAllObjects(modelMap);
	}
	
	/**
	 * 
	 * 基金类型明细导出
	 * @param request
	 * @param response
	 */
	public void fundDetailExport(HttpServletRequest request,HttpServletResponse response){
		String start = request.getParameter("startDate");
		String end = request.getParameter("endDate");
		String analysisWd = request.getParameter("analysisWd");
		
		//根据以下维度计算gid
		String openInst = UaaStringUtils.nullToString(request.getParameter("openInst"),"-1");
		String openPlatform =UaaStringUtils.nullToString(request.getParameter("openPlatform"),"-1");
		String cooperateType =UaaStringUtils.nullToString(request.getParameter("cooperateType"),"-1");
		String openwangdian = UaaStringUtils.nullToString(request.getParameter("openWangDian"),"-1");
		//String fundType = UaaStringUtils.nullToString(request.getParameter("fundType"),"-1");
		
		//界面参数
		String openNorm = request.getParameter("openNorm");
		String tradeNorm = request.getParameter("tradeNorm");
		String currentParam = request.getParameter("currentParam");
		
		int gid=31;
		String params="pageIndex=1&bizDimension="+analysisWd+"&statdtStart="+start.replace("-", "")+"&statdtEnd="+end.replace("-", "");
		
		if(!openInst.equals("-1"))
		{
			gid -= 16;
			params += "&disCode="+openInst;
		}
		if(!openPlatform.equals("-1"))
		{
			gid -= 8;
			if(!UaaContants.A.equals(openPlatform)){
				params += "&tradeChan="+openPlatform;
			}
		}
		if(!cooperateType.equals("-1")){
			gid -= 4;
			if(!UaaContants.A.equals(cooperateType)){
				params += "&hzlxCode="+cooperateType;
			}
		}
		if(!openwangdian.equals("-1")){
			gid -= 2;
			params += "&outletCode="+openwangdian;
		}
		/*if(!fundType.equals("-1")){
			gid -= 1;
			params += "&fundType="+fundType;
		}*/
		String sumParam = params + "&gid="+gid + "&fundTypeStat=0";
		FundResponse fResponse = fundReqUtil.fundRespon(sumParam, 1);
		List<FundResponseCollMapping> fundcolllist = fResponse.getColl();
		if(fundcolllist != null)
		{
			summaryCalculate(fundcolllist);
		}
		
		
		List<String> theadlist = new ArrayList<String>();
		List<String> collList = new ArrayList<String>();
		collList.add("fundtypename");
		List<String> remove = new ArrayList<String>();
		remove.add("-1");
		if(openNorm.length() != 0)
		{
			String[] openarr = openNorm.split("\\$");
			List<String> openList = Arrays.asList(openarr);
			theadlist.addAll(openList);
		}
		if(tradeNorm.length() != 0)
		{
			String[] tradearr =  tradeNorm.split("\\$");
			List<String> tradeList = Arrays.asList(tradearr);
			theadlist.addAll(tradeList);
		}
		theadlist.removeAll(remove);
		int tlistlen = theadlist.size();
		for(int i = 0;i<tlistlen;i++)
		{
			collList.add(fundMap.get(theadlist.get(i)).toString());
		}
		ExportExcel<FundResponseCollMapping> ex = new ExportExcel<FundResponseCollMapping>();
		String[] header = (String[])collList.toArray(new String[collList.size()]);
		String rootpath = request.getSession().getServletContext().getRealPath("/");
		File dataFile = new File(rootpath+"resources/data_detail.xls");
		ex.exportExcel("基金类型明细", header,header, fundcolllist, dataFile,"");
        String fileName = currentParam+".xls";
        FileUtil.down(dataFile, fileName, response);
		
	}
	
	/**
	 * 请求参数拼接
	 * @param fs
	 * @return
	 */
	public Map<String,Object> GetParams(FundStatisticsCommand fs){
		String start = fs.getStartDate();
		String end = fs.getEndDate();
		String analysisWd = fs.getAnalysisWd();
		
		//根据以下维度计算gid
		String openInst = UaaStringUtils.nullToString(fs.getOpenInst(),"-1");
		String openPlatform = UaaStringUtils.nullToString(fs.getOpenPlatform(),"-1");
		String cooperateType =UaaStringUtils.nullToString(fs.getCooperateType(),"-1");
		String openwangdian = UaaStringUtils.nullToString(fs.getOpenWangDian(),"-1");
		String fundType = UaaStringUtils.nullToString(fs.getFundType(),"-1");
		
		//页面参数
		String openNorm = UaaStringUtils.nullToString(fs.getOpenNorm(),"");
		String tradeNorm = UaaStringUtils.nullToString(fs.getTradeNorm(),"");
		String orderby = UaaStringUtils.nullToString(fs.getOrderBy(),"");
		String order = UaaStringUtils.nullToString(fs.getOrder(),"");
		String pagerows = UaaStringUtils.nullToString(fs.getPageRows(),"");
		String curpage = UaaStringUtils.nullToString(fs.getCurPage(),"1");
		
		if(orderByList.contains(orderby))
		{
//			orderby = (String) orderByMap.get(orderby);
			orderby= "";
			pagerows = "";
			curpage = "";
		}
		int gid=31;
		StringBuffer params= new StringBuffer("pageIndex="+curpage+"&pageSize="+pagerows+"&bizDimension="+analysisWd+"&statdtStart="+start.replace("-", "")+"&statdtEnd="+end.replace("-", "")+"&orderBy="+orderby+"&order="+order);
		if(!openInst.equals("-1"))
		{
			gid -= 16;
			params.append("&disCode="+openInst);
		}
		if(!openPlatform.equals("-1"))
		{
			gid -= 8;
			if(!UaaContants.A.equals(openPlatform)){
				params.append("&tradeChan="+openPlatform);
			}
		}
		if(!cooperateType.equals("-1")){
			gid -= 4;
			if(!UaaContants.A.equals(cooperateType)){
				params.append("&hzlxCode="+cooperateType);
			}
		}
		if(!openwangdian.equals("-1")){
			gid -= 2;
			params.append("&outletCode="+openwangdian);
		}
		if(!fundType.equals("-1")){
			gid -= 1;
			params.append("&fundType="+fundType);
		}
		
		params.append("&gid="+gid);
		
		List<String> theadlist = new ArrayList<String>();
		if(openNorm.length() != 0)
		{
			String[] openarr = openNorm.split("\\$");
			List<String> openList = Arrays.asList(openarr);
			theadlist.addAll(openList);
		}
		if(tradeNorm.length() != 0)
		{
			String[] tradearr =  tradeNorm.split("\\$");
			List<String> tradeList = Arrays.asList(tradearr);
			theadlist.addAll(tradeList);
		}
		LOGGER.debug("params:"+params);
		LOGGER.debug("theadlist"+theadlist);
		
		Map<String,Object> response = new HashMap<String,Object>();
		response.put("params", params.toString());
		response.put("theadlist", theadlist);
		return response;
	}
	
	/**
	 * 计算平均值 率
	 * @param datalist
	 */
	private void calculateLv(List<FundResponseMapping> datalist) {
		if (!CollectionUtils.isEmpty(datalist)) {

			// 获取格式化对象
			NumberFormat nt = NumberFormat.getPercentInstance();
			// 设置百分数精确度2即保留两位小数
			nt.setMinimumFractionDigits(2);

			for (int i = 0; i < datalist.size(); i++) {
				FundResponseMapping mapping = datalist.get(i);
				BigDecimal xdje = mapping.getDrxdje();// 当日下单金额
				long xdrs = mapping.getDrxdrs();// 当日下单人数
				if (xdje != null && xdrs != 0) {
					BigDecimal rjxdje = xdje.divide(new BigDecimal(xdrs),
							UaaContants.SCALE, BigDecimal.ROUND_HALF_DOWN);
					mapping.setRjxdje(rjxdje);// 人均下单金额
				}

				BigDecimal cjje = mapping.getDrqrjycjje();// 当日确认交易成交金额
				long cjrs = mapping.getDrqrjycjrs();// 当日确认交易成交人数
				if (cjje != null && cjrs != 0) {
					BigDecimal rjcjje = cjje.divide(new BigDecimal(cjrs),UaaContants.SCALE, BigDecimal.ROUND_HALF_DOWN);
					// 人均成交金额 Drqrjycjje/Drqrjycjrs
					mapping.setRjcjje(rjcjje);
				}
				
				//人均支付金额
				BigDecimal drzje = mapping.getDrzfje();
				long drzzfrs = mapping.getDrzfrs();
				if (drzje != null && drzzfrs != 0) {
					BigDecimal rjzfje = drzje.divide(new BigDecimal(drzzfrs),
							UaaContants.SCALE, BigDecimal.ROUND_HALF_DOWN);
					mapping.setRjzfje(rjzfje);// 
				}

				long drkhdrbk = mapping.getDrkhdrbk();// 当日开户绑卡人数
				long drkhdryk = mapping.getDrkhdryk();// 当日开户验卡人数
				long drkhdrjq = mapping.getDrkhdrjq();// 当日开户鉴权人数
				long drkhdrjyrs = mapping.getDrkhdrjyrs();// 当日开户当日交易人数
				long drkh = mapping.getDrkh();// 开户人数
				if (drkh != 0) {
					if (drkhdrbk != 0) {
						// 绑卡率 Drkhdrbk/Drkh
						BigDecimal num =new  BigDecimal((double)drkhdrbk / drkh);
						BigDecimal bkl = num.setScale(UaaContants.SCALE_LV, BigDecimal.ROUND_HALF_UP);
						mapping.setDrkhbkl(bkl);
					}
					if (drkhdryk != 0) {
						BigDecimal num =new  BigDecimal((double)drkhdryk / drkh);
						BigDecimal ykl = num.setScale(UaaContants.SCALE_LV, BigDecimal.ROUND_HALF_UP);// 验卡率 Drkhdryk/Drkh
						mapping.setDrkhykl(ykl);
					}
					if (drkhdrjq != 0) {
						BigDecimal num =new  BigDecimal((double)drkhdrjq / drkh);
						BigDecimal jql = num.setScale(UaaContants.SCALE_LV, BigDecimal.ROUND_HALF_UP);// 鉴权率 Drkhdrjq/drkh
						mapping.setDrkhjql(jql);
					}
					if (drkhdrjyrs != 0) {
						BigDecimal num =new  BigDecimal((double)drkhdrjyrs / drkh);
						BigDecimal jyl = num.setScale(UaaContants.SCALE_LV, BigDecimal.ROUND_HALF_UP);// 交易率 Drkhdrjyrs/drkh
						mapping.setDrkhjyl(jyl);
					}
				}
			}

		}

	}
	
	/**
	 * 计算总均值  总率
	 * @param colllist
	 */
	private void summaryCalculate(List<FundResponseCollMapping> colllist) {
		if (!CollectionUtils.isEmpty(colllist)) {

			NumberFormat nt = NumberFormat.getPercentInstance();
			// 设置百分数精确度2即保留两位小数
			nt.setMinimumFractionDigits(2);
			//DecimalFormat df = new java.text.DecimalFormat("#.00");
			for (FundResponseCollMapping fundRes : colllist) {
				BigDecimal xdje = fundRes.getXdzje();// 当日下单金额
				long xdrs = fundRes.getXdzrs();// 当日下单人数
				if (null != xdje && xdrs != 0) {
					BigDecimal rjxdje = xdje.divide(new BigDecimal(xdrs),
							UaaContants.SCALE, BigDecimal.ROUND_HALF_DOWN);
					fundRes.setRjxdzje(rjxdje);// 人均下单金额
				}

				BigDecimal cjje = fundRes.getQrjycjzje();// 当日确认交易成交金额
				long cjrs = fundRes.getQrjycjzrs();// 当日确认交易成交人数
				if (cjje != null && cjrs != 0) {
					BigDecimal rjcjje = cjje.divide(new BigDecimal(cjrs),
							UaaContants.SCALE, BigDecimal.ROUND_HALF_DOWN);
					fundRes.setRjcjzje(rjcjje);// 人均成交金额 Drqrjycjje/Drqrjycjrs
				}
				//人均支付金额	当日支付金额/当日支付人数
				BigDecimal drzje = fundRes.getZzfje();
				long drzzfrs = fundRes.getZzfrs();
				if (drzje != null && drzzfrs != 0) {
					BigDecimal rjzfje = drzje.divide(new BigDecimal(drzzfrs),
							UaaContants.SCALE, BigDecimal.ROUND_HALF_DOWN);
					fundRes.setRjzzfje(rjzfje);// 人均成交金额 Drqrjycjje/Drqrjycjrs
				}

				long drkhdrbk = fundRes.getDrkhdrzbk();// 当日开户绑卡人数
				long drkhdryk = fundRes.getDrkhdrzyk();// 当日开户验卡人数
				long drkhdrjq = fundRes.getDrkhdrzjq();// 当日开户鉴权人数
				long drkhdrjyrs = fundRes.getDrkhdrjyzrs();// 当日开户当日交易人数
				long drkh = fundRes.getZkh();// 开户人数

				if (drkh != 0) {
					if (drkhdrbk != 0) {
						BigDecimal num =new  BigDecimal((float)drkhdrbk / drkh);
						BigDecimal bkl = num.setScale(UaaContants.SCALE_LV, BigDecimal.ROUND_HALF_UP);// 绑卡率 Drkhdrbk/Drkh
						fundRes.setDrkhzbkl(bkl);
					}
					if (drkhdryk != 0) {
						BigDecimal num =new  BigDecimal((float)drkhdryk / drkh);
						BigDecimal ykl = num.setScale(UaaContants.SCALE_LV, BigDecimal.ROUND_HALF_UP);// 验卡率 Drkhdryk/Drkh
						fundRes.setDrkhzykl(ykl);
					}
					if (drkhdrjq != 0) {
						BigDecimal num =new  BigDecimal((float)drkhdrjq / drkh);
						BigDecimal jql = num.setScale(UaaContants.SCALE_LV, BigDecimal.ROUND_HALF_UP);// 鉴权率 Drkhdrjq/drkh
						fundRes.setDrkhzjql(jql);
					}
					if (drkhdrjyrs != 0) {
						BigDecimal num =new  BigDecimal((float)drkhdrjyrs / drkh);
						BigDecimal jyl = num.setScale(UaaContants.SCALE_LV, BigDecimal.ROUND_HALF_UP);// 交易率 Drkhdrjyrs/drkh
						fundRes.setDrkhzjyl(jyl);
					}
				}
			}

		}
	}
	
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getFundGraph() {
		return fundGraph;
	}
	public void setFundGraph(String fundGraph) {
		this.fundGraph = fundGraph;
	}

	public String getFundChannelDetail() {
		return fundChannelDetail;
	}

	public void setFundChannelDetail(String fundChannelDetail) {
		this.fundChannelDetail = fundChannelDetail;
	}

	public String getFundTrendDetail() {
		return fundTrendDetail;
	}

	public void setFundTrendDetail(String fundTrendDetail) {
		this.fundTrendDetail = fundTrendDetail;
	}

	public String getFundTrendDetailTbody() {
		return fundTrendDetailTbody;
	}

	public void setFundTrendDetailTbody(String fundTrendDetailTbody) {
		this.fundTrendDetailTbody = fundTrendDetailTbody;
	}


	public AnalysisCoreDataService getAnalysisCoreDataService() {
		return analysisCoreDataService;
	}

	public void setAnalysisCoreDataService(
			AnalysisCoreDataService analysisCoreDataService) {
		this.analysisCoreDataService = analysisCoreDataService;
	}

	public String getWirelessWd() {
		return wirelessWd;
	}

	public void setWirelessWd(String wirelessWd) {
		this.wirelessWd = wirelessWd;
	}

	public String getFundTypeDetail() {
		return fundTypeDetail;
	}

	public void setFundTypeDetail(String fundTypeDetail) {
		this.fundTypeDetail = fundTypeDetail;
	}

	public String getChannelUrl() {
		return channelUrl;
	}

	public void setChannelUrl(String channelUrl) {
		this.channelUrl = channelUrl;
	}

	public String getCustUrl() {
		return custUrl;
	}

	public void setCustUrl(String custUrl) {
		this.custUrl = custUrl;
	}

	public String getSjhzUrl() {
		return sjhzUrl;
	}

	public void setSjhzUrl(String sjhzUrl) {
		this.sjhzUrl = sjhzUrl;
	}

	
	
}
