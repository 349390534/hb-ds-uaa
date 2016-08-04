/**
 * 
 */
package com.howbuy.uaa.web.controller;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertySetStrategy;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.howbuy.hadoop.mapping.MyPropertySetStrategy;
import com.howbuy.uaa.common.contants.UaaContants;
import com.howbuy.uaa.dto.DisabClientConvertCustApiResponse;
import com.howbuy.uaa.dto.DisabClientConvertCustDataApiResponse;
import com.howbuy.uaa.dto.DisabClientConvertCustDetailApiResponse;
import com.howbuy.uaa.dto.DisabClientConvertCustDetailDataApiResponse;
import com.howbuy.uaa.dto.DisabClientConvertDeptApiResponse;
import com.howbuy.uaa.dto.DisabClientConvertDeptDataApiResponse;
import com.howbuy.uaa.utils.ExportExcel;
import com.howbuy.uaa.utils.FileUtil;
import com.howbuy.uaa.utils.HttpUtil;
import com.howbuy.uaa.utils.JsonParse;

/**
 * @author qiankun.li 已分配AB客户转化表
 */
public class DisabClientConvertController extends MultiActionController {
	private static final Logger LOGGER = LoggerFactory.getLogger(DisabClientConvertController.class);
	
	/**
	 * 穿透分析API接口
	 */
	private String apiUrl;

	private String index;

	private String dataView;

	private static final String format = com.howbuy.uaa.utils.DateUtils.FORMAT_YYYYMMDD;

	public ModelAndView index(HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> modelMap = queryData(request, response);
		return new ModelAndView(index).addAllObjects(modelMap);
	}
	
	public ModelAndView loadData(HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> modelMap = queryData(request, response);
		return new ModelAndView(dataView).addAllObjects(modelMap);
	}

	private Map<String,Object> queryData(HttpServletRequest request,HttpServletResponse response){
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
		//构造参数
		StringBuffer paramBuffer = new StringBuffer();
		paramBuffer.append("queryNo=%s").append("&")
		.append("statdtStart=").append(start).append("&")
		.append("statdtEnd=").append(end);
		//已分配AB客户转化表-客户类型统计数据
		final String queryNo="REPORT_CEO_CJBCUSTTYPE";
		String paramBase = paramBuffer.toString();
		String param_QueryNo_Dept = String.format(paramBase, queryNo);
		//查询数据
		DisabClientConvertCustApiResponse apiResponse_Dept = queryApi(new DisabClientConvertCustApiResponse(),DisabClientConvertCustDataApiResponse.class,param_QueryNo_Dept);
		List<DisabClientConvertCustDataApiResponse> respList = apiResponse_Dept.getList();
		//查询上月的数据
		List<DisabClientConvertCustDataApiResponse> dataResponseTopList = getDataTop(respList, end);
		DisabClientConvertCustDataApiResponse dataResponseTop = new DisabClientConvertCustDataApiResponse();
		if(!CollectionUtils.isEmpty(dataResponseTopList))
			dataResponseTop = dataResponseTopList.get(0);
		
		DisabClientConvertCustDataApiResponse dataResponseTop2Befor=new DisabClientConvertCustDataApiResponse();
		if(!CollectionUtils.isEmpty(respList) && respList.size()==1){
			//查询末月之前的一个月
			dataResponseTop2Befor = apiGetEndDate2Befor(end);
		}else{
			String end2Befor = com.howbuy.uaa.utils.DateUtils.getEndDate2BeforDateStr(end,format);
			List<DisabClientConvertCustDataApiResponse> dataResponseTop2BeforList = getDataTop(respList, end2Befor);
			if(!CollectionUtils.isEmpty(dataResponseTop2BeforList))
				dataResponseTop2Befor =dataResponseTop2BeforList.get(0);
		}
		//计算环比
		calculateRelativeRatio(dataResponseTop, dataResponseTop2Befor, modelMap);
		modelMap.put("dataTop", dataResponseTop);
		
		
		//已分配AB客户转化表-客户类型统计数据
		final String queryNo_REPORT_CEO_CJBOUTLET="REPORT_CEO_CJBOUTLET";
		String param_QueryNo_REPORT_CEO_CJBOUTLET = String.format(paramBase, queryNo_REPORT_CEO_CJBOUTLET);
		DisabClientConvertDeptApiResponse respDept = queryApi(new DisabClientConvertDeptApiResponse(),
		DisabClientConvertDeptDataApiResponse.class, param_QueryNo_REPORT_CEO_CJBOUTLET);
		List<DisabClientConvertDeptDataApiResponse>  resDeptList = respDept.getList();
		String dataListJson = JsonParse.arrayToJsonStr(resDeptList);
		modelMap.put("dataListJson", dataListJson);
		
		//已分配AB客户转化表-投顾明细数据
		final String queryNo_REPORT_CEO_CJBCONS = "REPORT_CEO_CJBCONS";
		StringBuffer paramBufferCJ = new StringBuffer();
		paramBufferCJ.append("queryNo=").append(queryNo_REPORT_CEO_CJBCONS);
		String param_QueryNo_REPORT_CEO_CJBCONS = paramBufferCJ.toString();
		DisabClientConvertCustDetailApiResponse respCustDetail = queryApi(new DisabClientConvertCustDetailApiResponse(),
				DisabClientConvertCustDetailDataApiResponse.class, param_QueryNo_REPORT_CEO_CJBCONS);
		List<DisabClientConvertCustDetailDataApiResponse> dataTgList =respCustDetail.getList();
		modelMap.put("dataTgList", dataTgList);
		
		String dFormat = com.howbuy.uaa.utils.DateUtils.FORMAT_D_YYYYMMDD;
		String maxEnd = com.howbuy.uaa.utils.DateUtils.getLastDay(endDate, dFormat);
		modelMap.put("maxEnd", maxEnd);
		return modelMap;
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
		final String queryNo_REPORT_CEO_CJBCONS = "REPORT_CEO_CJBCONS";
		StringBuffer paramBufferCJ = new StringBuffer();
		paramBufferCJ.append("queryNo=").append(queryNo_REPORT_CEO_CJBCONS);
		String param_QueryNo_REPORT_CEO_CJBCONS = paramBufferCJ.toString();
		DisabClientConvertCustDetailApiResponse respCustDetail = queryApi(new DisabClientConvertCustDetailApiResponse(),
				DisabClientConvertCustDetailDataApiResponse.class, param_QueryNo_REPORT_CEO_CJBCONS);
		List<DisabClientConvertCustDetailDataApiResponse> dataTgList =respCustDetail.getList();
		String rootpath = request.getSession().getServletContext().getRealPath("/");
		File dataFile = new File(rootpath + "resources/disabclientconvert_data_detail.xls");
		ExportExcel<DisabClientConvertCustDetailDataApiResponse> ex = new ExportExcel<DisabClientConvertCustDetailDataApiResponse>();
		try {
			ex.exportDisabClientConvertExcel("客户成交明细数据", dataTgList, dataFile);
			String fileName = "客户成交明细数据"+start+"-"+end+".xls";
	        FileUtil.down(dataFile, fileName, response);
		} catch (IOException e) {
			LOGGER.error("导出数据异常",e);
		}
	}
	/**计算环比
	 * @param a
	 * @param b
	 * @param modelMap
	 */
	private void calculateRelativeRatio(DisabClientConvertCustDataApiResponse a,DisabClientConvertCustDataApiResponse b,Map<String, Object> modelMap){
		try {
			if(a==null||b==null){
				return;
			}
			//一手成交客户人数环比计算
			BigDecimal trade_cnt1 = new BigDecimal(a.getTrade_cnt1()-b.getTrade_cnt1());
			BigDecimal trade_cnt12 = new BigDecimal(b.getTrade_cnt1());
			BigDecimal trade_cnt1_r = new BigDecimal(0);
			if(trade_cnt12.compareTo(trade_cnt1_r)!=0){
				trade_cnt1_r=trade_cnt1.divide(trade_cnt12,UaaContants.SCALE_LV, BigDecimal.ROUND_HALF_DOWN);
			}
			modelMap.put("trade_cnt1_r", trade_cnt1_r);
			
			//二手潜在客户成交数环比 
			BigDecimal trade_cnt2 = new BigDecimal(a.getTrade_cnt2()-b.getTrade_cnt2());
			BigDecimal trade_cnt22 = new BigDecimal(b.getTrade_cnt2());
			BigDecimal trade_cnt2_r = new BigDecimal(0);
			if(trade_cnt22.compareTo(trade_cnt2_r)!=0){
				trade_cnt2_r = trade_cnt2.divide(trade_cnt22,UaaContants.SCALE_LV, BigDecimal.ROUND_HALF_DOWN);
			}
			modelMap.put("trade_cnt2_r", trade_cnt2_r);
			
			//二手成交客户成交数环比 
			BigDecimal trade_cnt31 = new BigDecimal(a.getTrade_cnt3()-b.getTrade_cnt3());
			BigDecimal trade_cnt32 = new BigDecimal(b.getTrade_cnt3());
			BigDecimal trade_cnt3_r = new BigDecimal(0);
			if(trade_cnt32.compareTo(trade_cnt3_r)!=0){
				trade_cnt3_r = trade_cnt31.divide(trade_cnt32,UaaContants.SCALE_LV, BigDecimal.ROUND_HALF_DOWN);
			}
			modelMap.put("trade_cnt3_r", trade_cnt3_r);
			//公转私基数环比 
		} catch (Exception e) {
			LOGGER.error("环比计算异常",e);
		}

	}
	
	/**获取结束日期前一个月的数据
	 * @param end
	 * @return
	 */
	private DisabClientConvertCustDataApiResponse apiGetEndDate2Befor(String end){
		//选择同一个月
		String date2BeforStr = com.howbuy.uaa.utils.DateUtils.getEndDate2BeforDateStr(end,format);
		StringBuffer paramBufferBefor2M = new StringBuffer(); 
		paramBufferBefor2M.append("queryNo=%s").append("&")
		.append("statdtStart=").append(date2BeforStr).append("&")
		.append("statdtEnd=").append(date2BeforStr);
		//已分配AB客户转化表-客户类型统计数据
		final String queryNo="REPORT_CEO_CJBCUSTTYPE";
		String param = String.format(paramBufferBefor2M.toString(), queryNo);
		DisabClientConvertCustApiResponse apiResponse_Dept = queryApi(new DisabClientConvertCustApiResponse(),DisabClientConvertCustDataApiResponse.class,param);
		List<DisabClientConvertCustDataApiResponse> dataResponseList = apiResponse_Dept.getList();
		if(CollectionUtils.isEmpty(dataResponseList)){
			return new DisabClientConvertCustDataApiResponse();
		}
		return dataResponseList.get(0);
	
	}
	/**获取当前最新的转换数据
	 * @param dataResponseList
	 * @param endDate
	 * @return
	 */
	List<DisabClientConvertCustDataApiResponse> getDataTop(List<DisabClientConvertCustDataApiResponse> dataResponseList,String end){
		List<DisabClientConvertCustDataApiResponse> dataResponse = new ArrayList<DisabClientConvertCustDataApiResponse>();
		if(CollectionUtils.isEmpty(dataResponseList)){
			return dataResponse;
		}
		Date endDate = com.howbuy.uaa.utils.DateUtils.parseDate(end, format);
		Calendar endC = Calendar.getInstance();
		endC.setTime(endDate);
		int year = endC.get(Calendar.YEAR);
		int month = endC.get(Calendar.MONTH);
		
		Calendar dtC = Calendar.getInstance();
		for(DisabClientConvertCustDataApiResponse pad:dataResponseList){
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
	@SuppressWarnings("unchecked")
	<T> T queryApi(T t,Class<?> dataClass,String params) {
		LOGGER.debug("URL:" + apiUrl + ",params:" + params);
		String result = HttpUtil.getHttpUtil().requestGet(apiUrl, params);
		LOGGER.debug(result);
		JSONObject jsonObj = JSONObject.fromObject(result);
		String status = jsonObj.getString("status");
		if (UaaContants.FAILED.equals(status)) {
			return t;
		}
		String body = jsonObj.getString("body");
		body = body.toLowerCase();
		JsonConfig config = new JsonConfig();
		config.setRootClass(t.getClass());
		Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();
		classMap.put("list", dataClass);
		config.setClassMap(classMap);
		config.setPropertySetStrategy(new MyPropertySetStrategy(PropertySetStrategy.DEFAULT));
		t = (T)JSONObject.toBean(JSONObject.fromObject(body), config);
		return t;
	}
	
	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getApiUrl() {
		return apiUrl;
	}

	public void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
	}

	public String getDataView() {
		return dataView;
	}

	public void setDataView(String dataView) {
		this.dataView = dataView;
	}

}
