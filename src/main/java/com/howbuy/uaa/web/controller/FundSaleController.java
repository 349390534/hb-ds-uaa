/**
 * 
 */
package com.howbuy.uaa.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.howbuy.uaa.service.DataxApiService;
import com.howbuy.uaa.utils.DateUtils;
import com.howbuy.uaa.utils.JsonParse;
import com.howbuy.uaa.utils.UaaDataUtil;

/**
 * @author qiankun.li 新增基金销量
 */
public class FundSaleController extends MultiActionController {

	private DataxApiService dataxApiService;

	private String index;
	private String graphViewSale;
	private String graphViewIncome;
	private String detailView;
	
	private String fundTypeTrendView;
	private String fundDetailTrendView;

	/**
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView index(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView view = new ModelAndView(index);
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("wd", 1);
		return view.addAllObjects(modelMap);
	}
	
	/**
	 * 画图方法
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView graph(HttpServletRequest request,HttpServletResponse response){
		
		ModelAndView view = new ModelAndView(graphViewSale);//销量分析
		Map<String, Object> modelMap = new HashMap<String, Object>();
		String wd =request.getParameter("wd");
		String beginDate = request.getParameter("start_date");
		String endDate = request.getParameter("end_date");
		Date begin = DateUtils.parseDate(beginDate, DateUtils.FORMAT_YYYYMMDD);
		Date end = DateUtils.parseDate(endDate, DateUtils.FORMAT_YYYYMMDD);
		List<String> dateList = UaaDataUtil.getDateXList(begin, end, DateUtils.FORMAT_D_YYYYMMDD);
		String datex = JsonParse.arrayToJsonStr(dateList);
		modelMap.put("datex", datex);
		String fundTypeAll = dataxApiService.getAllFundType(); 
		modelMap.put("fundTypeAll", fundTypeAll);
		StringBuffer paramfund=new StringBuffer("&");
		paramfund.append("tradeDtStart=").append(beginDate).append("&")
		.append("tradeDtEnd=").append(endDate);
		String paramBase = paramfund.toString();
		long startCat = System.currentTimeMillis();
		String fundTypeDataJson =dataxApiService.queryFundTypeDetailList(paramBase);
		modelMap.put("fundDataDetailJson", fundTypeDataJson);
		String fundDataJson=dataxApiService.queryFundDetailList(paramBase);
		modelMap.put("fundDataJson", fundDataJson);
		//查询基金汇总数据
		String fundSumDatajson = dataxApiService.queryFundSumDataList(paramBase);
		modelMap.put("fundSumDataJson", fundSumDatajson);
		
		//查询基金公司汇总数据
		String fundTaSumDataJson = dataxApiService.queryFundTaSumDataList(paramBase);
		modelMap.put("fundTaSumDataJson", fundTaSumDataJson);
		
		long endCat = System.currentTimeMillis();
		System.out.println("query data excute time "+(endCat-startCat)+" ms");
		if("2".equals(wd)){//收入分析
			view = new ModelAndView(graphViewIncome);
		}
		return view.addAllObjects(modelMap);
	}
	
	/**
	 * 模糊搜索基金信息
	 * @param request
	 * @param response
	 */
	public void ajaxQueryFundListJsonData(HttpServletRequest request,HttpServletResponse response){
		String term = request.getParameter("term");
		//StringUtils.isNumeric(term);
		String params = "";
		if(NumberUtils.isNumber(term)){
			//基金代码
			params="fundCode="+term;
		}else{
			//基金名称
			params="fundName="+term;
		}
		String fundJsonData = dataxApiService.queryFundJsonList(params);
		if(StringUtils.isNotBlank(fundJsonData)){
			JsonParse.writeJsonStr(response, fundJsonData);
		}
	}
	/**
	 * 模糊搜索基金公司信息
	 * @param request
	 * @param response
	 */
	public void ajaxQueryFundCompanyListJsonData(HttpServletRequest request,HttpServletResponse response){
		String term = request.getParameter("term");
		//StringUtils.isNumeric(term);
		String params = "";
		if(NumberUtils.isNumber(term)){
			//基金代码
			params="taCode="+term;
		}else{
			//基金名称
			params="taName="+term;
		}
		String fundJsonData = dataxApiService.queryCompanyJsonList(params);
		if(StringUtils.isNotBlank(fundJsonData)){
			JsonParse.writeJsonStr(response, fundJsonData);
		}
	}
	
	/**
	 * 异步获取基金数据
	 * @param request
	 * @param response
	 */
	public void ajaxGetFundData(HttpServletRequest request,HttpServletResponse response){
		String beginDate = request.getParameter("beginDate");
		String endDate = request.getParameter("endDate");
		String fundCode = request.getParameter("fundCode");
		String params = "fundCode="+fundCode+"&tradeDtStart="+beginDate+"&tradeDtEnd="+endDate;
		if(StringUtils.isNotBlank(fundCode)){
			String dataJson = dataxApiService.queryFundDetailList(params);
			JsonParse.writeJsonStr(response, dataJson);
		}
	}
	
	
	/**
	 * 异步获取基金公司数据
	 * @param request
	 * @param response
	 */
	public void ajaxGetFundCompanyData(HttpServletRequest request,HttpServletResponse response){
		String beginDate = request.getParameter("beginDate");
		String endDate = request.getParameter("endDate");
		String fundCode = request.getParameter("taCode");
		String params = "taCode="+fundCode+"&tradeDtStart="+beginDate+"&tradeDtEnd="+endDate;
		if(StringUtils.isNotBlank(fundCode)){
			String dataJson = dataxApiService.getFundCompanyDetailData(params);
			JsonParse.writeJsonStr(response, dataJson);
		}
	}
	
	private String buildParam(HttpServletRequest request,HttpServletResponse response){
		String beginDate = request.getParameter("start_date");
		String endDate = request.getParameter("end_date");
		String pageIndex = request.getParameter("pageindex");
		if(StringUtils.isBlank(pageIndex))
			pageIndex="1";
		String pageSize = request.getParameter("pagesize");
		if(StringUtils.isBlank(pageSize))
			pageSize="10";
		String params = "tradeDtStart="+beginDate+"&tradeDtEnd="+endDate+"&pageSize="+pageSize+"&pageIndex="+pageIndex;
		return params;
	}
	/**
	 * 异步查询基金类型明细数据
	 * @param request
	 * @param response
	 * @return
	 */
	public void ajaxQueryFundTypeList(HttpServletRequest request,HttpServletResponse response){
		String params = buildParam(request, response);
		String dataJson = dataxApiService.getFundTypeApiData(params);
		JsonParse.writeJsonStr(response, dataJson);
		
	}
	
	/**
	 * 分页查询基金明细数据
	 * @param request
	 * @param response
	 * @return
	 */
	public void ajaxQueryFundDetailList(HttpServletRequest request,HttpServletResponse response){
		String params = buildParam(request, response);
		//String dataJson = dataxApiService.getFundApiData(params);
		String dataJson = dataxApiService.queryFundSumDataAPIList(params);
		JsonParse.writeJsonStr(response, dataJson);
	}
	
	/**
	 * 下载明细
	 * @param request
	 * @param response
	 */
	public void downLoadData(HttpServletRequest request,HttpServletResponse response){
		String type = request.getParameter("dtype");
		String params = buildParam(request, response);
		if("1".equals(type)){
			//fundType下载
			dataxApiService.downLoadFundTypeData(params, request, response);
		}else if("2".equals(type)){
			dataxApiService.downLoadFundData(params, request, response);
		}
		
		
		
	}
	
	
	
	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}
 

	public String getGraphViewSale() {
		return graphViewSale;
	}

	public void setGraphViewSale(String graphViewSale) {
		this.graphViewSale = graphViewSale;
	}

	public String getGraphViewIncome() {
		return graphViewIncome;
	}

	public void setGraphViewIncome(String graphViewIncome) {
		this.graphViewIncome = graphViewIncome;
	}

	public String getDetailView() {
		return detailView;
	}

	public void setDetailView(String detailView) {
		this.detailView = detailView;
	}

	public DataxApiService getDataxApiService() {
		return dataxApiService;
	}

	public void setDataxApiService(DataxApiService dataxApiService) {
		this.dataxApiService = dataxApiService;
	}

	public String getFundTypeTrendView() {
		return fundTypeTrendView;
	}

	public void setFundTypeTrendView(String fundTypeTrendView) {
		this.fundTypeTrendView = fundTypeTrendView;
	}

	public String getFundDetailTrendView() {
		return fundDetailTrendView;
	}

	public void setFundDetailTrendView(String fundDetailTrendView) {
		this.fundDetailTrendView = fundDetailTrendView;
	}

}