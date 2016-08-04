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
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertySetStrategy;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.howbuy.hadoop.mapping.MyPropertySetStrategy;
import com.howbuy.uaa.command.FundStatisticsCommand;
import com.howbuy.uaa.common.contants.UaaContants;
import com.howbuy.uaa.common.execption.UaaRunTimeException;
import com.howbuy.uaa.dto.CustomerResponse;
import com.howbuy.uaa.dto.CustomerResponseCollMapping;
import com.howbuy.uaa.dto.CustomerResponseMapping;
import com.howbuy.uaa.utils.DateUtils;
import com.howbuy.uaa.utils.ExportExcel;
import com.howbuy.uaa.utils.FileUtil;
import com.howbuy.uaa.utils.HttpUtil;
import com.howbuy.uaa.utils.JsonParse;
import com.howbuy.uaa.utils.SortUtil;
import com.howbuy.uaa.utils.UaaDataUtil;
import com.howbuy.uaa.utils.UaaStringUtils;

public class CustomerDataController extends MultiActionController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerDataController.class);
	//排序字段：总验卡率、总鉴权率、总交易率、总鉴权交易率
	public static List<String> orderByList = Arrays.asList("ljzykl","ljzjql","ljzjyl","ljzjqjyl");
	private static Map<String,String> custMap = new HashMap<String,String>(){
		
		/**
		 * 
		 */
		private static final long serialVersionUID = -293894964998759987L;

		{
			
			//key 指标字段，value：指标汇总字段
			put("ljkhs","ljzkhs");
			put("ljbks","ljzbks");
			put("ljyks","ljzyks");
			put("ljjqs","ljzjqs");
			put("ljscjys","ljscjyzs");
			put("cys","zcys");
			put("ljzykl","ljzykl");
			put("ljzjql","ljzjql");
			put("ljzjyl","ljzjyl");
			put("ljzjqjyl","ljzjqjyl");
			
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
	private static Map<String,String> orderByMappingMap = new HashMap<String,String>(){
		/**
		 * 
		 */
		private static final long serialVersionUID = -293894964998759987L;

		{
			put("ljzykl", "ljzykl");
			put("ljzjql", "ljzjql");
			put("ljzjyl", "ljzjyl");
			put("ljzjqjyl", "ljzjqjyl");
		}
		
	};
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
	private String index;
	private String channelUrl;
	private volatile String titile_jsonString ="";
	private String customerGraph;
	private String custchannelDetail;
	private String custUrl;
	private String sjhzUrl;
	private String custchannelDetailTbody;
	private String custtrendDetail;
	private String custTrendDetailTbody;
	public void init(){
		//公募客户渠道API地址如下（渠道信息）
		try {
			if(StringUtils.isBlank(channelUrl)){
				throw new UaaRunTimeException("init channel failed,the request url is null");
			}
			String json = HttpUtil.getHttpUtil().requestGet(channelUrl);
			JSONObject jsonobj = JSONObject.fromObject(json);
			JSONArray disArrs = jsonobj.getJSONObject("body").getJSONArray("list");
			titile_jsonString = disArrs.toString();
			
			LOGGER.debug(titile_jsonString);
			
		} catch (Exception e) {
			LOGGER.error("CustomerDataController init error.",e);
		}
	}
	public ModelAndView index(HttpServletRequest request,HttpServletResponse response) {
		Map<String,Object> modelMap = new HashMap<String, Object>();
		modelMap.put("zkhsjqd", titile_jsonString);//渠道数据
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
	public ModelAndView customerGraph(HttpServletRequest request,HttpServletResponse response,FundStatisticsCommand fs) throws ParseException, ServletRequestBindingException{

		String start = fs.getStartDate();
		String end = fs.getEndDate();
		//维度 1开户  默认1
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
		//基金类型为空 fundTypeStat生效
		paramsSb.append("&fundTypeStat=1");
		paramsSb.append("&gid="+gid);
		String params1  = paramsSb.toString();
		Map<String, Object> modelMap = new HashMap<String, Object>();
		//获取当前维度日期明细数据与汇总数据
		CustomerResponse fResponse = custRespon(params1,0);
		List<CustomerResponseMapping> frList = fResponse.getList();
		List<CustomerResponseMapping> datalistOne=null;
		List<CustomerResponseMapping> datalistSum=null;
		if(gid<=1){//无细分渠道
			datalistOne = frList;
			if(!CollectionUtils.isEmpty(datalistOne)){
				datalistOne = UaaDataUtil.calCustExtraQuotaData(datalistOne);
				String dataJsonOne = JsonParse.arrayToJsonStr(datalistOne);
				LOGGER.debug("dataJsonOne:"+dataJsonOne);
				modelMap.put("dataJsonOne", dataJsonOne);
			}
		}else{
			datalistSum = frList;
			if(!CollectionUtils.isEmpty(datalistSum)){
				datalistSum = UaaDataUtil.calCustExtraQuotaData(datalistSum);
				String dataJsonSum = JsonParse.arrayToJsonStr(datalistSum);
				LOGGER.debug("dataJsonSum:"+dataJsonSum);
				modelMap.put("dataJsonSum", dataJsonSum);
			}
		}
		
		//获取当前维度下级明细和汇总
		CustomerResponse childResponse = custRespon(params1, 1);
		List<CustomerResponseMapping> datalist = childResponse.getList();
		List<CustomerResponseCollMapping> collListVar = calAllCol(datalist, end);
		summaryCalculate(collListVar);
		
		datalist = UaaDataUtil.calCustExtraQuotaData(datalist);
		CustomerResponseCollMapping summary=null;
		//当前维度汇总数据处理
		if(!CollectionUtils.isEmpty(frList)){
			List<CustomerResponseCollMapping> cdList =calAllCol(frList, end);
			if(cdList.size()>=1){
				summary= cdList.get(0);
				summaryCalculate(cdList);
			}
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
		modelMap.put("fundMap", custMap);
		if(!CollectionUtils.isEmpty(collListVar)){
			String collJson = JsonParse.arrayToJsonStr(collListVar);
			modelMap.put("collJson", collJson);
			LOGGER.debug("collJson:"+collJson);
		}
		
		if(!CollectionUtils.isEmpty(datalist)){
			String dataJson = JsonParse.arrayToJsonStr(datalist);
			modelMap.put("dataJson", dataJson);
			LOGGER.debug("dataJson:"+dataJson);
		}
		return new ModelAndView(customerGraph).addAllObjects(modelMap);
	}
	
	
	@SuppressWarnings("unchecked")
	public Map<String,Object> channelDetail(HttpServletRequest request,HttpServletResponse response,FundStatisticsCommand fs){
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
		List<CustomerResponseCollMapping> showList = new ArrayList<CustomerResponseCollMapping>();
		Map<String,Object> resMap = buildReqParams(fs);
		String params = resMap.get("params").toString();
		CustomerResponse fResponse = custRespon(params,0);
		List<CustomerResponseMapping> frList= fResponse.getList();
		String end = fs.getEndDate();
		List<CustomerResponseCollMapping> summaryList = calAllCol(frList, end);
		if(summaryList.size()>=1)
			summaryCalculate(summaryList);
		CustomerResponse fResponseChild = custRespon(params,1);
		List<CustomerResponseMapping> cdList= fResponseChild.getList();
		List<CustomerResponseCollMapping> childCollList = calAllCol(cdList, end);
		if(childCollList.size()>=1){
			summaryCalculate(childCollList);
			if(orderByList.contains(orderby)){
				SortUtil<CustomerResponseCollMapping> sort= new SortUtil<CustomerResponseCollMapping>();
				String orderMap = orderByMappingMap.get(orderby);
				String method = "get"+orderMap.replaceFirst(orderMap.substring(0, 1),orderMap.substring(0, 1).toUpperCase())  ; 
				sort.sort(childCollList, method, order);
				
				for(int i = 0;i<childCollList.size();i++){
					if(childCollList.get(i)!=null){
						CustomerResponseCollMapping fund = (CustomerResponseCollMapping)childCollList.get(i);
						showList.add(fund);
					}
					
				}
			}else{
				showList = childCollList;
			}
		}
			
		List<String> theadList = (List<String>) resMap.get("theadlist");
		
		Map<String,Object> modelMap = new HashMap<String, Object>();
		modelMap.put("coll", summaryList);//全部数据
		modelMap.put("theadlist",theadList);//所选指标
		modelMap.put("colllist", showList);//子集数据
		modelMap.put("wd", fs.getAnalysisWd());//分析维度
		modelMap.put("discode", fs.getOpenInst());//开户机构
		modelMap.put("childname", childname);//子渠道维度
		modelMap.put("pathcode", pathcode);
		modelMap.put("pathname", pathName);
		modelMap.put("cnt",cnt);
		return modelMap;
	}
	
	/**
	 * 渠道明细
	 * @param request
	 * @param response
	 * @param fs
	 * @return
	 */
	public ModelAndView custChannelDetail(HttpServletRequest request,HttpServletResponse response,FundStatisticsCommand fs){
		
		Map<String,Object> modelMap = channelDetail(request,response,fs);
		return new ModelAndView(custchannelDetail).addAllObjects(modelMap);
	}
	/**
	 * 渠道明细body
	 * @param request
	 * @param response
	 * @param fs
	 * @return
	 */
	public ModelAndView custChannelDetailTbody(HttpServletRequest request,HttpServletResponse response,FundStatisticsCommand fs){
		
		Map<String,Object> modelMap = channelDetail(request,response,fs);
		return new ModelAndView(custchannelDetailTbody).addAllObjects(modelMap);
	}
	/**趋势明细
	 * @param request
	 * @param response
	 * @param fs
	 * @return
	 */
	public ModelAndView custtrendDetail(HttpServletRequest request,HttpServletResponse response,FundStatisticsCommand fs){
		String analysisWd = fs.getAnalysisWd();
		
		String pagerows = fs.getPageRows();
		int rows = Integer.parseInt(pagerows);
		
		String orderby = UaaStringUtils.nullToString(fs.getOrderBy(),"");
		String order = UaaStringUtils.nullToString(fs.getOrder(),"");
		String curpage = UaaStringUtils.nullToString(fs.getCurPage(),"1");
		int current = Integer.parseInt(curpage);
		List<CustomerResponseMapping> showList = new ArrayList<CustomerResponseMapping>();
		Map<String, Object> modelMap = new HashMap<String, Object>(0);
		Map<String,Object> resMap = buildReqParams(fs);
		modelMap.put("theadlist",resMap.get("theadlist"));
		CustomerResponse fResponse = custRespon(resMap.get("params").toString(),0);
		List<CustomerResponseMapping> datalist = fResponse.getList();
		int pageCount = fResponse.getPageCount();
		if(!CollectionUtils.isEmpty(datalist)){
			calculateLv(datalist);//计算率、平均值
			if(orderByList.contains(orderby)){
				SortUtil<CustomerResponseMapping> sort= new SortUtil<CustomerResponseMapping>();
				String method = "get"+orderby.replaceFirst(orderby.substring(0, 1),orderby.substring(0, 1).toUpperCase())  ; 
				sort.sort(datalist, method, order);
				int maxIndex = current*rows;
				if(maxIndex>=pageCount){
					maxIndex = pageCount; 
				}
				for(int i = (current-1)*rows;i<maxIndex;i++){
					if(datalist.get(i)!=null){
						CustomerResponseMapping fund = (CustomerResponseMapping)datalist.get(i);
						showList.add(fund);
					}
					
				}
				modelMap.put("datalist", showList);
			}else
				modelMap.put("datalist", datalist);
		}
		
		
		int pages = pageCount % rows == 0?pageCount/rows:pageCount/rows+1;
		modelMap.put("rows", pagerows);
		modelMap.put("pages", pages);
		modelMap.put("maxrows", pageCount);
		modelMap.put("wd", analysisWd);
		return new ModelAndView(custtrendDetail).addAllObjects(modelMap);
	}
	/**tbody显示 分页|页面行数|排序
	 * @param request
	 * @param response
	 * @param fs
	 * @return
	 */
	public ModelAndView custtrendDetailTbody(HttpServletRequest request,HttpServletResponse response,FundStatisticsCommand fs){
		String analysisWd = fs.getAnalysisWd();
		
		String pagerows = fs.getPageRows();
		int rows = Integer.parseInt(pagerows);
		
		String orderby = UaaStringUtils.nullToString(fs.getOrderBy(),"");
		String order = UaaStringUtils.nullToString(fs.getOrder(),"");
		String curpage = UaaStringUtils.nullToString(fs.getCurPage(),"1");
		int current = Integer.parseInt(curpage);
		
		List<CustomerResponseMapping> showList = new ArrayList<CustomerResponseMapping>();
		Map<String,Object> resMap = buildReqParams(fs);
		CustomerResponse fResponse = custRespon(resMap.get("params").toString(),0);
		List<CustomerResponseMapping> datalist = fResponse.getList();
		if(!CollectionUtils.isEmpty(datalist)){
			calculateLv(datalist);
			if(orderByList.contains(orderby)){
				SortUtil<CustomerResponseMapping> sort= new SortUtil<CustomerResponseMapping>();
				String method = "get"+orderby.replaceFirst(orderby.substring(0, 1),orderby.substring(0, 1).toUpperCase())  ; 
				sort.sort(datalist, method, order);
				int pageCount = fResponse.getPageCount();
				int maxIndex = current*rows;
				if(maxIndex>=pageCount){
					maxIndex = pageCount ; 
				}
				for(int i = (current-1)*rows;i<maxIndex;i++){
					if(datalist.get(i)!=null){
						CustomerResponseMapping fund = (CustomerResponseMapping)datalist.get(i);
						showList.add(fund);
					}
					
				}
			}else{
				showList = datalist;
			}		
			
		}
		
		
		return new ModelAndView(custTrendDetailTbody)
		.addObject("datalist", showList)
		.addObject("theadlist", resMap.get("theadlist"))
		.addObject("wd", analysisWd);
		
	}
	/**
	 * 渠道明细导出
	 * @param request
	 * @param response
	 */
	public void custchannelDetailExport(HttpServletRequest request,HttpServletResponse response){
		String start = request.getParameter("startDate");
		String end = request.getParameter("endDate");
		String analysisWd = request.getParameter("analysisWd");
		
		//根据以下维度计算gid
		String openInst = UaaStringUtils.nullToString(request.getParameter("openInst"), "-1");
		String openPlatform  = UaaStringUtils.nullToString(request.getParameter("openPlatform"), "-1");
		String cooperateType = UaaStringUtils.nullToString(request.getParameter("cooperateType"), "-1");
		String openwangdian = UaaStringUtils.nullToString(request.getParameter("openWangDian"), "-1");
		String fundType = UaaStringUtils.nullToString( request.getParameter("fundType"), "-1");
		
		//页面参数
		String openNorm = request.getParameter("openNorm");
		
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
		CustomerResponse fResponse = custRespon(paramsStr,0);
		List<CustomerResponseCollMapping> colllist = calAllCol(fResponse.getList(),end);
		List<CustomerResponseCollMapping> collchildlist=null;
		if(gid>1){
			CustomerResponse fcollResponse = custRespon(paramsStr, 1);
			
			collchildlist = calAllCol(fcollResponse.getList(),end); 
			for(CustomerResponseCollMapping coll:collchildlist)
			{
				if(coll.getDisname() != null)coll.setChildname(coll.getDisname());
				if(coll.getChanname() != null)coll.setChildname(coll.getChanname());
				if(coll.getHzlx() != null)coll.setChildname(coll.getHzlx());
				if(coll.getOutletname() != null)coll.setChildname(coll.getOutletname());
				
				if(coll.getChildname() == null)
					coll.setChildname("No Name");
			}
		}else{
			collchildlist = colllist;
			for(CustomerResponseCollMapping coll:collchildlist)
			{
				if(coll.getChildname() == null)
					coll.setChildname(coll.getOutletname());
			}
			
		}
		
		if(colllist != null)
			summaryCalculate(colllist);
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
		theadlist.removeAll(remove);
		int theadlength= theadlist.size();
		for(int i=0; i<theadlength ; i++)
		{
			String th = theadlist.get(i);
			String thcol = custMap.get(th);
			if(StringUtils.isNotBlank(thcol))
				theadlist.set(i, thcol);
		}
		
		ExportExcel<CustomerResponseCollMapping> ex = new ExportExcel<CustomerResponseCollMapping>();
		String[] header = (String[])theadlist.toArray(new String[theadlist.size()]);
		String rootpath = request.getSession().getServletContext().getRealPath("/");
		File dataFile = new File(rootpath+"resources/data_detail.xls");
		String th = gidMap.get(gid);
		String tag = pathName.get(th+analysisWd);
		ex.exportExcel("渠道明细", header,header, collchildlist, dataFile,tag);
		String fileName = "";
		if("".equals(currentParam) || currentParam == null){
			fileName = "custChannel"+".xls";
		}else
			fileName = currentParam+".xls";
        FileUtil.down(dataFile, fileName, response);
		
	}
	/*
	 * 趋势明细导出
	 */
	public void custtrendDetailExport(HttpServletRequest request,HttpServletResponse response){
		
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
		String curpage = request.getParameter("curPage");
		
		String currentParam = request.getParameter("currentParam");
		
		int gid=31;
		String params="pageIndex="+curpage+"&bizDimension="+analysisWd+"&statdtStart="+start.replace("-", "")+"&statdtEnd="+end.replace("-", "");
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
		
		
		CustomerResponse fResponse = custRespon(params,0);
		List<CustomerResponseMapping> datalist = fResponse.getList();
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
		theadlist.removeAll(remove);
		LOGGER.debug("theadlist"+theadlist);
		
		ExportExcel<CustomerResponseMapping> ex = new ExportExcel<CustomerResponseMapping>();
		String[] header = (String[])theadlist.toArray(new String[theadlist.size()]);
		String rootpath = request.getSession().getServletContext().getRealPath("/");
		File dataFile = new File(rootpath+"resources/data_detail.xls");
		ex.exportExcel("趋势明细", header,header, datalist, dataFile,"");
		String fileName = "";
		if("".equals(currentParam) || currentParam == null){
			fileName = "custtrendDetail"+".xls";
		}else
			fileName = currentParam+".xls";
        FileUtil.down(dataFile, fileName, response);
		
	}
	/**
	 * 
	 * @param params
	 * @param c 0：所选维度  1：所选维度的子集
	 * @return
	 */
	private CustomerResponse custRespon(String params,int c){
		CustomerResponse custResponse = new CustomerResponse();
		LOGGER.debug("params:"+params);
		String result;
		if(c == 0)
		{
			//获取每日公募统计指标明细
			result = HttpUtil.getHttpUtil().requestGet(custUrl, params);
			LOGGER.debug("URL:"+custUrl);
		}
		else {
			//获取每日公募统计下级指标明细
			result = HttpUtil.getHttpUtil().requestGet(sjhzUrl, params);
			LOGGER.debug("URL:"+sjhzUrl);
		}
		LOGGER.debug(result);
		JSONObject jsonObj = JSONObject.fromObject(result);
		String status = jsonObj.getString("status");
		if(UaaContants.FAILED.equals(status)){
			return custResponse;
		}
		String body = jsonObj.getString("body");
		
		Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();
		classMap.put("list",CustomerResponseMapping.class);
		classMap.put("coll", CustomerResponseCollMapping.class);
		
		JsonConfig config = new JsonConfig();
		config.setRootClass(CustomerResponse.class);
		config.setClassMap(classMap);
		config.setPropertySetStrategy(new MyPropertySetStrategy(PropertySetStrategy.DEFAULT));
		custResponse = (CustomerResponse)JSONObject.toBean(JSONObject.fromObject(body), config);
		
		return custResponse;
	}
	/**
	 * 请求参数拼接
	 * @param fs
	 * @return
	 */
	public Map<String,Object> buildReqParams(FundStatisticsCommand fs){
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
	 * 计算总均值  总率
	 * @param colllist
	 */
	private void summaryCalculate(List<CustomerResponseCollMapping> list) {
		NumberFormat nt = NumberFormat.getPercentInstance();
		// 设置百分数精确度2即保留两位小数
		nt.setMinimumFractionDigits(2);
		//DecimalFormat df = new java.text.DecimalFormat("#.00");
		if(!CollectionUtils.isEmpty(list)){
			for(CustomerResponseCollMapping cust:list){
				long zkhs = cust.getLjzkhs();// 总客户数
				long ykrs = cust.getLjzyks();// 总验卡人数
				long jqrs = cust.getLjzjqs();//总鉴权人数
				long jyrs = cust.getLjscjyzs();//首次交易总人数
				if(zkhs!=0){
					BigDecimal zkhsNum = new BigDecimal(zkhs);
					if(ykrs!=0){
						BigDecimal num =new  BigDecimal(ykrs);
						BigDecimal zykl = num.divide(zkhsNum,UaaContants.SCALE_LV, BigDecimal.ROUND_HALF_UP);
						cust.setLjzykl(zykl);//总验卡率
					}
					if(jqrs!=0){
						BigDecimal num =new  BigDecimal(jqrs);
						BigDecimal zjql = num.divide(zkhsNum,UaaContants.SCALE_LV, BigDecimal.ROUND_HALF_UP);
						cust.setLjzjql(zjql);//总鉴权率
					}
					if(jyrs!=0){
						BigDecimal num =new  BigDecimal(jyrs);
						BigDecimal zjyl = num.divide(zkhsNum,UaaContants.SCALE_LV, BigDecimal.ROUND_HALF_UP);
						cust.setLjzjyl(zjyl);//总交易率
					}
				}
				if(jqrs!=0){
					if(jyrs!=0){
						BigDecimal num =new  BigDecimal(jyrs);
						BigDecimal jqrsNum =new  BigDecimal(jqrs);
						BigDecimal zjqjyl = num.divide(jqrsNum,UaaContants.SCALE_LV, BigDecimal.ROUND_HALF_UP);
						cust.setLjzjqjyl(zjqjyl);//总鉴权交易率
					}
				}
			}
		}
		
	

	
	}
	/**
	 * 计算平均值 率
	 * @param datalist
	 */
	private void calculateLv(List<CustomerResponseMapping> datalist) {
		if (!CollectionUtils.isEmpty(datalist)) {

			// 获取格式化对象
			NumberFormat nt = NumberFormat.getPercentInstance();
			// 设置百分数精确度2即保留两位小数
			nt.setMinimumFractionDigits(2);

			for (int i = 0; i < datalist.size(); i++) {
				CustomerResponseMapping cust = datalist.get(i);
				long zkhs = cust.getLjkhs();// 总客户数
				long ykrs = cust.getLjyks();// 总验卡人数
				long jqrs = cust.getLjjqs();//总鉴权人数
				long jyrs = cust.getLjscjys();//首次交易总人数
				if(zkhs!=0){
					BigDecimal zkhsNum = new BigDecimal(zkhs);
					if(ykrs!=0){
						BigDecimal num =new  BigDecimal(ykrs);
						BigDecimal zykl = num.divide(zkhsNum,UaaContants.SCALE_LV, BigDecimal.ROUND_HALF_UP);
						cust.setLjykl(zykl);//总验卡率
						cust.setLjzykl(zykl);//总验卡率
					}
					if(jqrs!=0){
						BigDecimal num =new  BigDecimal(jqrs);
						BigDecimal zjql = num.divide(zkhsNum,UaaContants.SCALE_LV, BigDecimal.ROUND_HALF_UP);
						cust.setLjjql(zjql);//总鉴权率
						cust.setLjzjql(zjql);//总鉴权率
					}
					if(jyrs!=0){
						BigDecimal num =new  BigDecimal(jyrs);
						BigDecimal zjyl = num.divide(zkhsNum,UaaContants.SCALE_LV, BigDecimal.ROUND_HALF_UP);
						cust.setLjjyl(zjyl);//总交易率
						cust.setLjzjyl(zjyl);//总交易率
					}
				}
				if(jqrs!=0){
					if(jyrs!=0){
						BigDecimal num =new  BigDecimal(jyrs);
						BigDecimal jqrsNum =new  BigDecimal(jqrs);
						BigDecimal zjqjyl = num.divide(jqrsNum,UaaContants.SCALE_LV, BigDecimal.ROUND_HALF_UP);
						cust.setLjjqjyl(zjqjyl);//总鉴权交易率
						cust.setLjzjqjyl(zjqjyl);//总鉴权交易率
					}
				}
			}

		}

	}
	
	
	/**
	 * 通过提前最后一天的数据，返回累计之和
	 * @param list
	 * @param date
	 * @return
	 */
	private List<CustomerResponseCollMapping> calAllCol(List<CustomerResponseMapping> list,String date){
		List<CustomerResponseCollMapping> collMappingList = new ArrayList<CustomerResponseCollMapping>();
		
		
		if(CollectionUtils.isEmpty(list)||StringUtils.isBlank(date)){
			return collMappingList;
		}
		String dt="";
		if(date.contains("-")){
			date = date.replaceAll("-", "");
		}
		dt = date;
		for(CustomerResponseMapping crm:list){
			String statDt = crm.getStatdt();
			if(!statDt.equals(dt)){
				continue;
			}
			CustomerResponseCollMapping collMapping = new CustomerResponseCollMapping();
			BeanUtils.copyProperties(crm, collMapping);
			//总开户数
			long khs = crm.getLjkhs();
			collMapping.setLjzkhs(khs);
			//总验卡人数
			long ljyks = crm.getLjyks();
			collMapping.setLjzyks(ljyks);
			// 总鉴权人数
			long ljjqs = crm.getLjjqs();
			collMapping.setLjzjqs(ljjqs);
			// 首次交易总人数
			long ljscjys = crm.getLjscjys();
			collMapping.setLjscjyzs(ljscjys);
			// 总持有人数
			long cys = crm.getCys();
			collMapping.setZcys(cys);
			// 总绑卡人数
			long ljbks = crm.getLjbks();
			collMapping.setLjzbks(ljbks);
			collMappingList.add(collMapping);
		}
		return collMappingList;
	}
	
	
	
	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}
	public String getChannelUrl() {
		return channelUrl;
	}
	public void setChannelUrl(String channelUrl) {
		this.channelUrl = channelUrl;
	}
	public String getCustomerGraph() {
		return customerGraph;
	}
	public void setCustomerGraph(String customerGraph) {
		this.customerGraph = customerGraph;
	}
	public String getCustchannelDetail() {
		return custchannelDetail;
	}
	public void setCustchannelDetail(String custchannelDetail) {
		this.custchannelDetail = custchannelDetail;
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
	public String getCustchannelDetailTbody() {
		return custchannelDetailTbody;
	}
	public void setCustchannelDetailTbody(String custchannelDetailTbody) {
		this.custchannelDetailTbody = custchannelDetailTbody;
	}
	public String getCusttrendDetail() {
		return custtrendDetail;
	}
	public void setCusttrendDetail(String custtrendDetail) {
		this.custtrendDetail = custtrendDetail;
	}
	public String getCustTrendDetailTbody() {
		return custTrendDetailTbody;
	}
	public void setCustTrendDetailTbody(String custTrendDetailTbody) {
		this.custTrendDetailTbody = custTrendDetailTbody;
	}
	

}
