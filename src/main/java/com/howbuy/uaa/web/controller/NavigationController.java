/**
 * 
 */
package com.howbuy.uaa.web.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.howbuy.uaa.common.contants.UaaContants;
import com.howbuy.uaa.dto.FundResponse;
import com.howbuy.uaa.dto.FundResponseMapping;
import com.howbuy.uaa.dto.HowbuyWebsiteData;
import com.howbuy.uaa.dto.HowbuyWebsiteDataMapping;
import com.howbuy.uaa.dto.HowbuyWebsiteFormData;
import com.howbuy.uaa.dto.PenetrateApiDataResponse;
import com.howbuy.uaa.dto.PenetrateApiResponse;
import com.howbuy.uaa.service.AnalysisHowbuyWebsiteService;
import com.howbuy.uaa.utils.DateUtils;
import com.howbuy.uaa.utils.JsonParse;
import com.howbuy.uaa.utils.UaaDataUtil;
import com.howbuy.uaa.utils.UaaReqUtil;

/**
 * @author qiankun.li 导航控制
 */
public class NavigationController extends MultiActionController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(NavigationController.class);

	private UaaReqUtil fundReqUtil;

	private AnalysisHowbuyWebsiteService analysisHowbuyWebsiteService;
	
	/**
	 * 获取每日公募统计指标明细URL
	 */
	private String custUrl;
	/**
	 * 获取每日公募统计下级指标明细URL
	 */
	private String sjhzUrl;
	
	private String apiUrl;

	private String index;
	
	private String gmView;
	
	private String webSiteView;
	
	private String perView;
	

	/**
	 * 通过spring初始化
	 */
	void init() {
		LOGGER.debug("init");
		fundReqUtil = new UaaReqUtil();
		fundReqUtil.setCustUrl(custUrl);
		fundReqUtil.setApiUrl(apiUrl);
		fundReqUtil.setSjhzUrl(sjhzUrl);
	}

	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView view = new ModelAndView(index);
		// 设置上一次保存的菜单id为null
		request.getSession().setAttribute(UaaContants.GA_MAP_KEY, null);
		String lastDay = DateUtils.getFormatedDate(DateUtils.getYesterdayDate(),
				DateUtils.FORMAT_D_YYYYMMDD);
		view.addObject("lastDay", lastDay);
		return view;
	}

	/**
	 * 获取公募数据
	 * 
	 * @param request
	 * @param response
	 */
	public ModelAndView ajaxGetFundData(HttpServletRequest request,HttpServletResponse response) {
		Map<String,Object> modelMap = new HashMap<String,Object>();
		int gid = 31;
		StringBuffer paramsSb = new StringBuffer("pageIndex=1&pageSize=1000&bizDimension=1");
		Calendar now = Calendar.getInstance();
		now.set(Calendar.DAY_OF_MONTH, now.get(Calendar.DAY_OF_MONTH) - 7);
		String end = DateUtils.getFormatedDate(DateUtils.getYesterdayDate(),
				DateUtils.FORMAT_YYYYMMDD);
		String start = DateUtils.getFormatedDate(now.getTime(),
				DateUtils.FORMAT_YYYYMMDD);// 截止到昨天,最近7天
		paramsSb.append("&statdtStart=" + start + "&statdtEnd=" + end);
		paramsSb.append("&fundTypeStat=1").append("&gid=" + gid);
		FundResponse fResponse = fundReqUtil.fundRespon(paramsSb.toString(), 0);
		List<FundResponseMapping> resList = fResponse.getList();
		FundResponseMapping summary=new FundResponseMapping();
		if(!CollectionUtils.isEmpty(resList))
			summary= resList.get(resList.size()-1);
		FundResponseMapping summaryPre=new FundResponseMapping();
		if(!CollectionUtils.isEmpty(resList) && resList.size()>=2){
			summaryPre = resList.get(resList.size()-2);
		}
		sync(summary, summaryPre, modelMap);
		modelMap.put("summary", summary);
		String jsonData = JsonParse.arrayToJsonStr(resList);
		modelMap.put("jsonData", jsonData);
		Date df = DateUtils.parseDate(start, DateUtils.FORMAT_YYYYMMDD);
		Date de = DateUtils.parseDate(end, DateUtils.FORMAT_YYYYMMDD);
		List<String> dateList = UaaDataUtil.getDateXList(df,de,DateUtils.FORMAT_D_YYYYMMDD);
		String datexJson = JsonParse.arrayToJsonStr(dateList);
		modelMap.put("datexJson", datexJson);
		String lastDay = DateUtils.getFormatedDate(DateUtils.getYesterdayDate(),
				DateUtils.FORMAT_D_YYYYMMDD);
		modelMap.put("lastDay", lastDay);
		return new ModelAndView(gmView).addAllObjects(modelMap);
		
	}
	
	/**
	 * 计算公募数据环比
	 */
	private void sync(FundResponseMapping summary,FundResponseMapping summaryPre,
			Map<String,Object> modelMap){
		long drkh1=summary.getDrkh();
		long drkh2=summaryPre.getDrkh();
		if(drkh2<=0){
			modelMap.put("drkhV", 0);
		}else{
			BigDecimal drkhV = new BigDecimal(drkh1).subtract(new BigDecimal(drkh2)).divide(new BigDecimal(drkh2),
					UaaContants.SCALE_LV,BigDecimal.ROUND_HALF_DOWN);
			modelMap.put("drkhV", drkhV);
		}
		long xzjy1 = summary.getDrxzjyrs();
		long xzjy2 = summaryPre.getDrxzjyrs();
		if(xzjy2<=0){
			modelMap.put("xzjyV", 0);
		}else{
			BigDecimal xzjyV = new BigDecimal(xzjy1).subtract(new BigDecimal(xzjy2)).divide(new BigDecimal(xzjy2),
					UaaContants.SCALE_LV,BigDecimal.ROUND_HALF_DOWN);
			modelMap.put("xzjyV", xzjyV);
		}
		
		long ljkhs1 = summary.getLjkhs();
		long ljkhs2 = summaryPre.getLjkhs();
		if(ljkhs2<=0){
			modelMap.put("ljkhsV", 0);
		}else{
			BigDecimal ljkhsV = new BigDecimal(ljkhs1).subtract(new BigDecimal(ljkhs2)).divide(new BigDecimal(ljkhs2),
					UaaContants.SCALE_LV,BigDecimal.ROUND_HALF_DOWN);
			modelMap.put("ljkhsV", ljkhsV);
		}
		
		long ljscjys1 = summary.getLjscjys();
		long ljscjys2 = summaryPre.getLjscjys();
		if(ljscjys2<=0){
			modelMap.put("ljscjysV", 0);
		}else{
			BigDecimal ljscjysV = new BigDecimal(ljscjys1).subtract(new BigDecimal(ljscjys2)).divide(new BigDecimal(ljscjys2),
					UaaContants.SCALE_LV,BigDecimal.ROUND_HALF_DOWN);
			modelMap.put("ljscjysV", ljscjysV);
		}
		
		long cys1 = summary.getCys();
		long cys2 = summaryPre.getCys();
		if(cys2<=0){
			modelMap.put("cysV", 0);
		}else{
			BigDecimal cysV = new BigDecimal(cys1).subtract(new BigDecimal(cys2)).divide(new BigDecimal(cys2),
					UaaContants.SCALE_LV,BigDecimal.ROUND_HALF_DOWN);
			modelMap.put("cysV", cysV);
		}
	}

	
	/**
	 * 异步加载网站数据趋势图
	 * @param request
	 * @param response
	 * @param ac
	 * @return
	 */
	public ModelAndView ajaxWebSiteGraph(HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> modelMap = new HashMap<String, Object>(0);
		Calendar now = Calendar.getInstance();
		now.set(Calendar.DAY_OF_MONTH, now.get(Calendar.DAY_OF_MONTH) - 7);
		String end = DateUtils.getFormatedDate(DateUtils.getYesterdayDate(),
				DateUtils.FORMAT_YYYYMMDD);
		String start = DateUtils.getFormatedDate(now.getTime(),
				DateUtils.FORMAT_YYYYMMDD);// 截止到昨天,最近7天
		int level = 1;
		String channelType = "-1";

		HowbuyWebsiteFormData websiteFormData = new HowbuyWebsiteFormData();
		websiteFormData.setBeginDate(start);
		websiteFormData.setChannelType(channelType);
		websiteFormData.setLevel(level);
		websiteFormData.setEndDate(end);
		
		Date df = DateUtils.parseDate(start, DateUtils.FORMAT_YYYYMMDD);
		Date de = DateUtils.parseDate(end, DateUtils.FORMAT_YYYYMMDD);
		List<String> dateList = UaaDataUtil.getDateXList(df, de,DateUtils.FORMAT_D_YYYYMMDD);
		websiteFormData.setDateList(dateList);
		HowbuyWebsiteData websiteCollData = analysisHowbuyWebsiteService.queryHowbuyWebsiteCollData(websiteFormData);
		String sumCollJson = "[]";
		List<HowbuyWebsiteDataMapping> sumCollList = websiteCollData.getList();
		if (!CollectionUtils.isEmpty(sumCollList)) {
			sumCollJson = JsonParse.arrayToJsonStr(sumCollList);
			int size = sumCollList.size();
			if(size>=2){
				HowbuyWebsiteDataMapping dataMapping = sumCollList.get(size-1);
				modelMap.put("dataColl", dataMapping);
				HowbuyWebsiteDataMapping dataMappingPre = sumCollList.get(size-2);
				syncWebSite(dataMapping, dataMappingPre, modelMap);
			}
			
		}
		String lastDay = DateUtils.getFormatedDate(DateUtils.getYesterdayDate(),
				DateUtils.FORMAT_D_YYYYMMDD);
		modelMap.put("lastDay", lastDay);
		modelMap.put("jsonData", sumCollJson);
		String datexJson = JsonParse.arrayToJsonStr(dateList);
		modelMap.put("datexJson", datexJson);
		return new ModelAndView(webSiteView).addAllObjects(modelMap);
	}
	
	
	
	/**计算网站数据环比
	 * @param dataMapping
	 * @param dataMappingPre
	 * @param modelMap
	 */
	private void syncWebSite(HowbuyWebsiteDataMapping dataMapping,HowbuyWebsiteDataMapping dataMappingPre,
			Map<String, Object> modelMap){
		Long uv1 = dataMapping.getUv();
		Long uv2 = dataMappingPre.getUv();
		if(uv1==null || uv2==null ||uv1.intValue()==0 ||uv2.intValue()==0){
			modelMap.put("uvV", 0);
			return;
		}
		BigDecimal uvV = new BigDecimal(uv1).subtract(new BigDecimal(uv2)).divide(new BigDecimal(uv2),
				UaaContants.SCALE_LV,BigDecimal.ROUND_HALF_DOWN);
		modelMap.put("uvV", uvV);
	}
	
	
	
	
	/**
	 * 穿透分析总表
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView ajaxPenetrateData(HttpServletRequest request,HttpServletResponse response) {
		Map<String,Object> modelMap = queryPenetrateDataMap(request, response);
		return new ModelAndView(perView).addAllObjects(modelMap);
	}
	
	private Map<String,Object> queryPenetrateDataMap(HttpServletRequest request,
			HttpServletResponse response){
		Map<String, Object> modelMap = new HashMap<String, Object>(0);
		Calendar now = Calendar.getInstance();
		Date endDate = DateUtils.addMonths(now.getTime(), -1);
		String lastMon = DateUtils.getFormatedDate(endDate, DateUtils.FORMAT_D_YYYYMMDD);
		modelMap.put("lastDate", lastMon.substring(0, 7));
		Date startDate = DateUtils.addMonths(now.getTime(), -2);
		String start = DateUtils.getFormatedDate(startDate, DateUtils.FORMAT_YYYYMMDD);
		String end = DateUtils.getFormatedDate(endDate, DateUtils.FORMAT_YYYYMMDD);
		//获取当月最后一天的日期
		start = DateUtils.getLastDay(start, DateUtils.FORMAT_YYYYMMDD);
		end = DateUtils.getLastDay(end, DateUtils.FORMAT_YYYYMMDD);
		
		//构造参数
		StringBuffer paramBuffer = new StringBuffer();
		paramBuffer.append("queryNo=%s").append("&")		
		.append("statdtEnd=").append(end);
		//部门统计数据No
		final String queryNo_Dept="REPORT_CEO_CTFXSUMMARY";
		//六象限数据No
		final String queryNo_Ctfx6r="REPORT_CEO_CTFX6R";
		String paramBaseDept = new StringBuffer(paramBuffer).append("&").append("statdtStart=").append(start).toString();
		String param_QueryNo_Dept = String.format(paramBaseDept, queryNo_Dept);
		//查询数据
		PenetrateApiResponse apiResponse_Dept = fundReqUtil.queryApi(param_QueryNo_Dept);
		List<PenetrateApiDataResponse> dataResponseList = apiResponse_Dept.getList();
		
		//查询上月的数据
		List<PenetrateApiDataResponse> dataResponseTopList = getDataTop(dataResponseList, end);
		PenetrateApiDataResponse dataResponseTop = new PenetrateApiDataResponse();
		if(!CollectionUtils.isEmpty(dataResponseTopList))
			dataResponseTop = dataResponseTopList.get(0);
		
		PenetrateApiDataResponse dataResponseTop2Befor=new PenetrateApiDataResponse();
		String end2Befor = DateUtils.getEndDate2BeforDateStr(end,DateUtils.FORMAT_YYYYMMDD);
		List<PenetrateApiDataResponse> dataResponseTop2BeforList = getDataTop(dataResponseList, end2Befor);
		if(!CollectionUtils.isEmpty(dataResponseTop2BeforList))
			dataResponseTop2Befor =dataResponseTop2BeforList.get(0);
		//计算环比
		calculateRelativeRatio(dataResponseTop, dataResponseTop2Befor, modelMap);
		modelMap.put("dataTop", dataResponseTop);
		
		//象限图数据
		String paramBaseCtx = new StringBuffer(paramBuffer).append("&").append("statdtStart=").append(end).toString();
		String param_QueryNo_Ctfx6r = String.format(paramBaseCtx, queryNo_Ctfx6r);
		PenetrateApiResponse apiResponse_Ctfx6r =fundReqUtil.queryApi(param_QueryNo_Ctfx6r);
		List<PenetrateApiDataResponse> apiResponse_Ctfx6r_List = apiResponse_Ctfx6r.getList();
		String ctfx6rJson = "[]";
		if(!CollectionUtils.isEmpty(apiResponse_Ctfx6r_List) && apiResponse_Ctfx6r_List.size()==2){
			ctfx6rJson = JsonParse.arrayToJsonStr(apiResponse_Ctfx6r_List);
		}else{
			LOGGER.error("象限图数据异常");
		}
		modelMap.put("ctfx6rJson", ctfx6rJson);
		return  modelMap;
	}
	
	
	/**获取当前最新的转换数据
	 * @param dataResponseList
	 * @param endDate
	 * @return
	 */
	List<PenetrateApiDataResponse> getDataTop(List<PenetrateApiDataResponse> dataResponseList,String end){
		List<PenetrateApiDataResponse> dataResponse = new ArrayList<PenetrateApiDataResponse>();
		if(CollectionUtils.isEmpty(dataResponseList)){
			return dataResponse;
		}
		Date endDate = DateUtils.parseDate(end, DateUtils.FORMAT_YYYYMMDD);
		Calendar endC = Calendar.getInstance();
		endC.setTime(endDate);
		int year = endC.get(Calendar.YEAR);
		int month = endC.get(Calendar.MONTH);
		
		Calendar dtC = Calendar.getInstance();
		for(PenetrateApiDataResponse pad:dataResponseList){
			String dt = pad.getStatdt();
			Date dtDate = DateUtils.parseDate(dt, DateUtils.FORMAT_YYYYMMDD);
			dtC.setTime(dtDate);
			int year_dt = dtC.get(Calendar.YEAR);
			int month_dt = dtC.get(Calendar.MONTH);
			if(year_dt==year && month==month_dt){
				dataResponse.add(pad);
			}
		}
		
		return dataResponse;
	}
	
	
	/**计算环比
	 * @param a
	 * @param b
	 * @param modelMap
	 */
	private void calculateRelativeRatio(PenetrateApiDataResponse a,PenetrateApiDataResponse b,
			Map<String, Object> modelMap){
		try {
			if(a==null||b==null){
				return;
			}
			//货转股人数环比计算
			BigDecimal CNT_C_E_1 = new BigDecimal(a.getCnt_c_e()-b.getCnt_c_e());
			BigDecimal CNT_C_E_2 = new BigDecimal(b.getCnt_c_e());
			BigDecimal CNT_C_E_r = new BigDecimal(0);
			if(CNT_C_E_2.compareTo(CNT_C_E_r)!=0){
				CNT_C_E_r = CNT_C_E_1.divide(CNT_C_E_2,UaaContants.SCALE_LV, BigDecimal.ROUND_HALF_DOWN);
			}
			modelMap.put("CNT_C_E_R", CNT_C_E_r);
			
			//货转股基数环比 
			BigDecimal CNT_C_1 = new BigDecimal(a.getCnt_c()-b.getCnt_c());
			BigDecimal CNT_C_2 = new BigDecimal(b.getCnt_c());
			BigDecimal CNT_C_r = new BigDecimal(0);
			if(CNT_C_2.compareTo(CNT_C_r)!=0){
				CNT_C_r = CNT_C_1.divide(CNT_C_2,UaaContants.SCALE_LV, BigDecimal.ROUND_HALF_DOWN);
			}
			modelMap.put("CNT_C_R", CNT_C_r);
			//公转私环比 
			BigDecimal CNT_F_1 = new BigDecimal(a.getCnt_f()-b.getCnt_f());
			BigDecimal CNT_F_2 = new BigDecimal(b.getCnt_f());
			BigDecimal CNT_F_r = new BigDecimal(0);
			if(CNT_F_2.compareTo(CNT_F_r)!=0){
				CNT_F_r = CNT_F_1.divide(CNT_F_2,UaaContants.SCALE_LV, BigDecimal.ROUND_HALF_DOWN);
			}
			modelMap.put("CNT_F_R", CNT_F_r);
			//公转私基数环比 
			BigDecimal CNT_B_1 = new BigDecimal(a.getCnt_b()-b.getCnt_b());
			BigDecimal CNT_B_2 = new BigDecimal(b.getCnt_b());
			BigDecimal CNT_B_r = new BigDecimal(0);
			if(CNT_B_2.compareTo(CNT_B_r)!=0){
				CNT_B_r = CNT_B_1.divide(CNT_B_2,UaaContants.SCALE_LV, BigDecimal.ROUND_HALF_DOWN);
			}
			modelMap.put("CNT_B_R", CNT_B_r);
		} catch (Exception e) {
			LOGGER.error("环比计算异常",e);
		}

	}
	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
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

	public String getGmView() {
		return gmView;
	}

	public void setGmView(String gmView) {
		this.gmView = gmView;
	}

	public AnalysisHowbuyWebsiteService getAnalysisHowbuyWebsiteService() {
		return analysisHowbuyWebsiteService;
	}

	public void setAnalysisHowbuyWebsiteService(
			AnalysisHowbuyWebsiteService analysisHowbuyWebsiteService) {
		this.analysisHowbuyWebsiteService = analysisHowbuyWebsiteService;
	}

	public String getWebSiteView() {
		return webSiteView;
	}

	public void setWebSiteView(String webSiteView) {
		this.webSiteView = webSiteView;
	}

	public String getPerView() {
		return perView;
	}

	public void setPerView(String perView) {
		this.perView = perView;
	}

	public String getApiUrl() {
		return apiUrl;
	}

	public void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
	}

}
