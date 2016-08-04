/**
 * 
 */
package com.howbuy.uaa.web.controller;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.howbuy.uaa.common.contants.UaaContants;
import com.howbuy.uaa.dto.PenetrateApiDataResponse;
import com.howbuy.uaa.dto.PenetrateApiResponse;
import com.howbuy.uaa.utils.ExportExcel;
import com.howbuy.uaa.utils.FileUtil;
import com.howbuy.uaa.utils.JsonParse;
import com.howbuy.uaa.utils.UaaDataUtil;
import com.howbuy.uaa.utils.UaaReqUtil;

/**
 * @author qiankun.li 穿透分析
 */
public class PenetrateAnalysisController extends MultiActionController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PenetrateAnalysisController.class);

	/**
	 * 穿透分析API接口
	 */
	private String apiUrl;

	/**
	 * 首页
	 */
	private String index;
	
	private String dataView;
	
	private static final String format = com.howbuy.uaa.utils.DateUtils.FORMAT_YYYYMMDD;

	private UaaReqUtil reqUtil;
	
	void init(){
		reqUtil = new UaaReqUtil(apiUrl);
	}
	
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView view = new ModelAndView(index);
		Map<String,Object> modelMap = queryDataMap(request, response);
		return view.addAllObjects(modelMap);
	}

	
	public ModelAndView loadApiData(HttpServletRequest request,
			HttpServletResponse response){
		ModelAndView view = new ModelAndView(dataView);
		Map<String,Object> modelMap = queryDataMap(request, response);
		return view.addAllObjects(modelMap);
	}
	
	
	/**
	 * 导出数据
	 * @param request
	 * @param response
	 */
	public void exportData(HttpServletRequest request,HttpServletResponse response){
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		Date endDate = DateUtils.addMonths(new Date(), -1);
		if (StringUtils.isBlank(start) || StringUtils.isBlank(end)) {
			//默认查询6个月的数据
			Date startDate = DateUtils.addMonths(new Date(), -6);
			start = com.howbuy.uaa.utils.DateUtils.getFormatedDate(startDate, format);
			end = com.howbuy.uaa.utils.DateUtils.getFormatedDate(endDate, format);
		}else{
			start = start.replaceAll("\\-", "");
			end = end.replaceAll("\\-", "");
		}
		//获取当月最后一天的日期
		end = com.howbuy.uaa.utils.DateUtils.getLastDay(end, format);
		//构造参数
		StringBuffer paramBuffer = new StringBuffer();
		paramBuffer.append("queryNo=%s").append("&")
		.append("statdtStart=").append(start).append("&")
		.append("statdtEnd=").append(end);
		//部门统计数据No
		final String queryNo_Dept="REPORT_CEO_CTFXSUMMARY";
		String paramBase = paramBuffer.toString();
		String param_QueryNo_Dept = String.format(paramBase, queryNo_Dept);
		PenetrateApiResponse apiResponse_Dept = reqUtil.queryApi(param_QueryNo_Dept);
		List<PenetrateApiDataResponse> dataResponseList = apiResponse_Dept.getList();
		List<PenetrateApiDataResponse> dataResponseListSort = new ArrayList<PenetrateApiDataResponse>(dataResponseList);
		String rootpath = request.getSession().getServletContext().getRealPath("/");
		File dataFile = new File(rootpath + "resources/penetrate_data_detail.xls");
		ExportExcel<PenetrateApiDataResponse> ex = new ExportExcel<PenetrateApiDataResponse>();
		try {
			ex.exportPenetrateExcel("高端用户数据", dataResponseListSort, dataFile);
			String fileName = "高端用户数据"+start+"-"+end+".xls";
	        FileUtil.down(dataFile, fileName, response);
		} catch (IOException e) {
			LOGGER.error("导出数据异常",e);
		}
	}
	
	private Map<String,Object> queryDataMap(HttpServletRequest request,
			HttpServletResponse response){
		Map<String, Object> modelMap = new HashMap<String, Object>(0);
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		Date endDate = DateUtils.addMonths(new Date(), -1);
		if (StringUtils.isBlank(start) || StringUtils.isBlank(end)) {
			//默认查询6个月的数据
			Date startDate = DateUtils.addMonths(new Date(), -6);
			start = com.howbuy.uaa.utils.DateUtils.getFormatedDate(startDate, format);
			end = com.howbuy.uaa.utils.DateUtils.getFormatedDate(endDate, format);
		}else{
			start = start.replaceAll("\\-", "");
			end = end.replaceAll("\\-", "");
		}
		//获取当月最后一天的日期
		start = com.howbuy.uaa.utils.DateUtils.getLastDay(start, format);
		end = com.howbuy.uaa.utils.DateUtils.getLastDay(end, format);
		modelMap.put("lastDate", end.substring(0, 6));
		modelMap.put("start", start.substring(0, 4)+"-"+start.substring(4,6)+"-01");
		modelMap.put("end", end.substring(0, 4)+"-"+end.substring(4,6)+"-"+end.substring(6));
		
		//构造日期集合
		Date df = com.howbuy.uaa.utils.DateUtils.parseDate(start, com.howbuy.uaa.utils.DateUtils.FORMAT_YYYYMMDD);
		Date de = com.howbuy.uaa.utils.DateUtils.parseDate(end, com.howbuy.uaa.utils.DateUtils.FORMAT_YYYYMMDD);
		List<String> dateList = UaaDataUtil.getDateXMonthList(df,de,com.howbuy.uaa.utils.DateUtils.FORMAT_YYYYMMDD);
		List<String> dateMList = convertMonthCh(dateList);
		modelMap.put("rangeDate", JsonParse.arrayToJsonStr(dateMList));
		//构造参数
		StringBuffer paramBuffer = new StringBuffer();
		paramBuffer.append("queryNo=%s").append("&")
		.append("statdtStart=").append(start).append("&")
		.append("statdtEnd=").append(end);
		//部门统计数据No
		final String queryNo_Dept="REPORT_CEO_CTFXSUMMARY";
		//六象限数据No
		final String queryNo_Ctfx6r="REPORT_CEO_CTFX6R";
		String paramBase = paramBuffer.toString();
		String param_QueryNo_Dept = String.format(paramBase, queryNo_Dept);
		String param_QueryNo_Ctfx6r = String.format(paramBase, queryNo_Ctfx6r);
		//查询数据
		PenetrateApiResponse apiResponse_Dept = reqUtil.queryApi(param_QueryNo_Dept);
		List<PenetrateApiDataResponse> dataResponseList = apiResponse_Dept.getList();
		List<PenetrateApiDataResponse> dataResponseListSort = new ArrayList<PenetrateApiDataResponse>(dataResponseList);
		modelMap.put("dataList", dataResponseListSort);
		Collections.reverse(dataResponseList);//日期升序
		String dataListJson = JsonParse.arrayToJsonStr(dataResponseList);
		modelMap.put("dataListJson", dataListJson);
		//查询上月的数据
		List<PenetrateApiDataResponse> dataResponseTopList = getDataTop(dataResponseList, end);
		PenetrateApiDataResponse dataResponseTop = new PenetrateApiDataResponse();
		if(!CollectionUtils.isEmpty(dataResponseTopList))
			dataResponseTop = dataResponseTopList.get(0);
		
		PenetrateApiDataResponse dataResponseTop2Befor=new PenetrateApiDataResponse();
		if(!CollectionUtils.isEmpty(dataResponseList) && dataResponseList.size()==1){
			//查询末月之前的一个月
			dataResponseTop2Befor = apiGetEndDate2Befor(end);
		}else{
			String end2Befor = com.howbuy.uaa.utils.DateUtils.getEndDate2BeforDateStr(end,format);
			List<PenetrateApiDataResponse> dataResponseTop2BeforList = getDataTop(dataResponseList, end2Befor);
			if(!CollectionUtils.isEmpty(dataResponseTop2BeforList))
				dataResponseTop2Befor =dataResponseTop2BeforList.get(0);
		}
		//计算环比
		calculateRelativeRatio(dataResponseTop, dataResponseTop2Befor, modelMap);
		
		modelMap.put("dataTop", dataResponseTop);
		String dataTopJson = JsonParse.objToJsonStr(dataResponseTop);
		modelMap.put("dataTopJson", dataTopJson);
		
		//象限图数据
		PenetrateApiResponse apiResponse_Ctfx6r = reqUtil.queryApi(param_QueryNo_Ctfx6r);
		List<PenetrateApiDataResponse> apiResponse_Ctfx6r_List = apiResponse_Ctfx6r.getList();
		Collections.reverse(apiResponse_Ctfx6r_List);//日期升序
		String ctfx6rListJson = "[]";
		if(!CollectionUtils.isEmpty(apiResponse_Ctfx6r_List)){
			ctfx6rListJson = JsonParse.arrayToJsonStr(apiResponse_Ctfx6r_List);
		}
		modelMap.put("ctfx6rListJson", ctfx6rListJson);
		List<PenetrateApiDataResponse> apiResponse_Ctfx6r_TopList = getDataTop(apiResponse_Ctfx6r_List, end);
		String ctfx6rJson = "[]";
		if(!CollectionUtils.isEmpty(apiResponse_Ctfx6r_TopList) && apiResponse_Ctfx6r_TopList.size()==2){
			ctfx6rJson = JsonParse.arrayToJsonStr(apiResponse_Ctfx6r_TopList);
		}else{
			LOGGER.error("象限图数据异常");
		}
		modelMap.put("ctfx6rJson", ctfx6rJson);
		String dFormat = com.howbuy.uaa.utils.DateUtils.FORMAT_D_YYYYMMDD;
		String maxEnd = com.howbuy.uaa.utils.DateUtils.getLastDay(endDate, dFormat);
		modelMap.put("maxEnd", maxEnd);
		return  modelMap;
	}
	
	/**获取月份
	 * @param dateList
	 * @return
	 */
	private List<String> convertMonthCh(List<String> dateList){
		List<String> dateMList = new ArrayList<String>(0);
		if(dateList.isEmpty()){
			return dateMList;
		}
		for(String ymd : dateList){
			String m = ymd.substring(4,6);
			dateMList.add(m);
		}
		return dateMList;
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
	
	
	/**获取结束日期前一个月的数据
	 * @param end
	 * @return
	 */
	private PenetrateApiDataResponse apiGetEndDate2Befor(String end){
		//选择同一个月
		String date2BeforStr = com.howbuy.uaa.utils.DateUtils.getEndDate2BeforDateStr(end,format);
		StringBuffer paramBufferBefor2M = new StringBuffer(); 
		paramBufferBefor2M.append("queryNo=%s").append("&")
		.append("statdtStart=").append(date2BeforStr).append("&")
		.append("statdtEnd=").append(date2BeforStr);
		//部门统计数据No
		final String queryNo_Dept="REPORT_CEO_CTFXSUMMARY";
		String param = String.format(paramBufferBefor2M.toString(), queryNo_Dept);
		PenetrateApiResponse apiResponse_Dept = reqUtil.queryApi(param);
		List<PenetrateApiDataResponse> dataResponseList = apiResponse_Dept.getList();
		if(CollectionUtils.isEmpty(dataResponseList)){
			return new PenetrateApiDataResponse();
		}
		return dataResponseList.get(0);
	
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
		Date endDate = com.howbuy.uaa.utils.DateUtils.parseDate(end, format);
		Calendar endC = Calendar.getInstance();
		endC.setTime(endDate);
		int year = endC.get(Calendar.YEAR);
		int month = endC.get(Calendar.MONTH);
		
		Calendar dtC = Calendar.getInstance();
		for(PenetrateApiDataResponse pad:dataResponseList){
			String dt = pad.getStatdt();
			Date dtDate = com.howbuy.uaa.utils.DateUtils.parseDate(dt, format);
			dtC.setTime(dtDate);
			int year_dt = dtC.get(Calendar.YEAR);
			int month_dt = dtC.get(Calendar.MONTH);
			if(year_dt==year && month==month_dt){
				dataResponse.add(pad);
			}
		}
		
		return dataResponse;
	}
	
	/**
	 * 请求查询接口
	 * 
	 * @param params
	 * @return
	 */
	/*PenetrateApiResponse queryApi(String params) {
		PenetrateApiResponse apiResponse = new PenetrateApiResponse();
		LOGGER.debug("params:" + params);
		LOGGER.debug("URL:" + apiUrl + ",params:" + params);
		String result = HttpUtil.getHttpUtil().requestGet(apiUrl, params);
		LOGGER.debug(result);
		JSONObject jsonObj = JSONObject.fromObject(result);
		String status = jsonObj.getString("status");
		if (UaaContants.FAILED.equals(status)) {
			return apiResponse;
		}
		String body = jsonObj.getString("body");
		body = body.toLowerCase();
		JsonConfig config = new JsonConfig();
		config.setRootClass(PenetrateApiResponse.class);
		Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();
		classMap.put("list", PenetrateApiDataResponse.class);
		config.setClassMap(classMap);
		config.setPropertySetStrategy(new MyPropertySetStrategy(
				PropertySetStrategy.DEFAULT));
		apiResponse = (PenetrateApiResponse) JSONObject.toBean(
				JSONObject.fromObject(body), config);
		return apiResponse;
	}*/

	public String getApiUrl() {
		return apiUrl;
	}

	public void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}


	public String getDataView() {
		return dataView;
	}


	public void setDataView(String dataView) {
		this.dataView = dataView;
	}

}
