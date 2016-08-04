/**
 * 
 */
package com.howbuy.uaa.service;

import static com.howbuy.uaa.common.contants.DataxQueryNo.API_JOB_FUNDTYPE_FEE;
import static com.howbuy.uaa.common.contants.DataxQueryNo.API_JOB_FUND_FEE;
import static com.howbuy.uaa.common.contants.DataxQueryNo.API_JOB_FUND_FEE_SUM;
import static com.howbuy.uaa.common.contants.DataxQueryNo.API_JOB_FUND_TA;
import static com.howbuy.uaa.common.contants.DataxQueryNo.API_JOB_FUND_TYPE;
import static com.howbuy.uaa.common.contants.DataxQueryNo.API_JOB_TA;
import static com.howbuy.uaa.common.contants.DataxQueryNo.API_JOB_TA_FEE;
import static com.howbuy.uaa.common.contants.DataxQueryNo.API_JOB_TA_FEE_SUM;
import static com.howbuy.uaa.common.contants.UaaContants.PARAM_QUERYNO;
import static com.howbuy.uaa.common.contants.UaaContants.PARAM_QUERYNON;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertySetStrategy;

import org.apache.commons.lang.StringUtils;

import com.howbuy.hadoop.mapping.MyPropertySetStrategy;
import com.howbuy.uaa.common.contants.UaaContants;
import com.howbuy.uaa.dto.fundsale.FundDataDetailDto;
import com.howbuy.uaa.dto.fundsale.FundDataResponse;
import com.howbuy.uaa.dto.fundsale.FundTypeDataDetailDto;
import com.howbuy.uaa.dto.fundsale.FundTypeDataResponse;
import com.howbuy.uaa.utils.ExportExcel;
import com.howbuy.uaa.utils.FileUtil;
import com.howbuy.uaa.utils.UaaReqUtil;

/**
 * @author qiankun.li
 * 提供对datax的数据查询服务
 */
public class DataxApiService {

	private UaaReqUtil fundReqUtil;
	
	/**
	 * 获取每日公募统计指标明细URL
	 */
	private String custUrl;
	/**
	 * 获取每日公募统计下级指标明细URL
	 */
	private String sjhzUrl;
	
	/**
	 * 获取渠道关系数据URL
	 */
	private String channelUrl;
	
	/**
	 * datax接口查询地址
	 */
	private String apiAdminQuery;
	
	void init() {
		fundReqUtil = new UaaReqUtil();
		fundReqUtil.setApiUrl(getApiAdminQuery());
		fundReqUtil.setCustUrl(getCustUrl());
		fundReqUtil.setSjhzUrl(getSjhzUrl());
	}

	/**
	 * 获取所有的基金类型
	 * @return
	 */
	public String getAllFundType(){
		StringBuffer url= new StringBuffer(apiAdminQuery);
		String queryNo=API_JOB_FUND_TYPE;
		url.append(PARAM_QUERYNO);
		String reqUrl = String.format(url.toString(),queryNo);
		String resJson = fundReqUtil.getDataxApi(reqUrl,null);
		return resJson;
	}
	
	private String reqJson(String params,String queryNo){
		StringBuffer url= new StringBuffer(apiAdminQuery);
		url.append(PARAM_QUERYNO);
		if(StringUtils.isNotBlank(params))
			url.append("&").append(params);
		String reqUrl = String.format(url.toString(),queryNo);
		String resJson = fundReqUtil.getDataxApi(reqUrl,null);
		return resJson;
	}
	/**
	 * 获取基金列表
	 * @return
	 */
	public String queryFundJsonList(String params){
		String queryNo=API_JOB_FUND_TA;
		return reqJson(params, queryNo);
	}
	
	
	/**
	 * 获取基金公司列表
	 * @return
	 */
	public String queryCompanyJsonList(String params){
		String queryNo=API_JOB_TA;
		return reqJson(params, queryNo);
	}
	
	/**
	 * 查询基金明细数据明细
	 * @return
	 */
	public String queryFundDetailList(String params){
		String queryNo=API_JOB_FUND_FEE;
		return getReqJson(params, queryNo);
	}
	
	private String getReqJson(String params,String queryNo){
		StringBuffer paramfund=new StringBuffer(PARAM_QUERYNON);
		if(StringUtils.isNotBlank(params))
			paramfund.append("&").append(params);
		String parambase = paramfund.toString();
		parambase=String.format(parambase, queryNo);
		return fundReqUtil.queryDataxApiList(parambase);
	}
	
	/**
	 * 获取基金公司明细数据
	 * @param params
	 * @return
	 */
	public String getFundCompanyDetailData(String params){
		String queryNo=API_JOB_TA_FEE;
		return getReqJson(params, queryNo);
	}
	
	
	private String getReqApiJson(String params,String queryNo){
		StringBuffer paramfund=new StringBuffer(PARAM_QUERYNON);
		if(StringUtils.isNotBlank(params))
			paramfund.append("&").append(params);
		String parambase = paramfund.toString();
		parambase=String.format(parambase, queryNo);
		return fundReqUtil.queryDataxApi(parambase);
	}
	
	public String getFundTypeApiData(String params){
		String queryNo=API_JOB_FUNDTYPE_FEE;
		return getReqApiJson(params, queryNo);
	}
	
	public String getFundApiData(String params){
		String queryNo=API_JOB_FUND_FEE;
		return getReqApiJson(params, queryNo);
	}
	/**
	 * 查询基金类型明细数据
	 * @param params
	 * @param fund
	 * @return
	 */
	public String queryFundTypeDetailList(String params){
		StringBuffer paramfund=new StringBuffer(PARAM_QUERYNON);
		paramfund.append(params);
		String queryNo=API_JOB_FUNDTYPE_FEE;
		String parambase = paramfund.toString();
		parambase=String.format(parambase, queryNo);
		return fundReqUtil.queryDataxApiList(parambase);
	}
	
	/**
	 * 查询基金汇总数据
	 * @param params
	 * @return
	 */
	public String queryFundSumDataList(String params){
		String queryNo=API_JOB_FUND_FEE_SUM;
		return getReqJson(params, queryNo);
	}
	
	public String queryFundSumDataAPIList(String params){
		String queryNo=API_JOB_FUND_FEE_SUM;
		return getReqApiJson(params, queryNo);
	}
	
	
	private FundTypeDataResponse getFundTypeData(String fundDataJson){
		FundTypeDataResponse dataResponse = new FundTypeDataResponse();
		JSONObject jsonObj = JSONObject.fromObject(fundDataJson);
		String status = jsonObj.getString("status");
		if(UaaContants.FAILED.equals(status)){
			return dataResponse;
		}
		String body = jsonObj.getString("body");
		
		Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();
		classMap.put("list",FundTypeDataDetailDto.class);
		
		JsonConfig config = new JsonConfig();
		config.setRootClass(FundTypeDataResponse.class);
		config.setClassMap(classMap);
		config.setPropertySetStrategy(new MyPropertySetStrategy(PropertySetStrategy.DEFAULT));
		dataResponse = (FundTypeDataResponse)JSONObject.toBean(JSONObject.fromObject(body), config);
		return dataResponse;
	}
	private FundDataResponse getFundData(String fundDataJson){
		FundDataResponse dataResponse = new FundDataResponse();
		JSONObject jsonObj = JSONObject.fromObject(fundDataJson);
		String status = jsonObj.getString("status");
		if(UaaContants.FAILED.equals(status)){
			return dataResponse;
		}
		String body = jsonObj.getString("body");
		
		Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();
		classMap.put("list",FundDataDetailDto.class);
		
		JsonConfig config = new JsonConfig();
		config.setRootClass(FundDataResponse.class);
		config.setClassMap(classMap);
		config.setPropertySetStrategy(new MyPropertySetStrategy(PropertySetStrategy.DEFAULT));
		dataResponse = (FundDataResponse)JSONObject.toBean(JSONObject.fromObject(body), config);
		return dataResponse;
	}
	
	/**
	 * 构造下载文件名称
	 * @param qs tab名称
	 * @param request
	 * @return
	 */
	private String buildFileName (String qs,HttpServletRequest request){
		StringBuffer fileName = new StringBuffer();
		String wd = request.getParameter("wd");
		if("1".equals(wd)){
			fileName.append("销量");
		}else if("2".equals(wd)){
			fileName.append("收入");
		}
		fileName.append(qs);
		String start_date = request.getParameter("start_date");
		String end_date = request.getParameter("end_date");
		fileName.append(start_date).append("至").append(end_date);
		return fileName.toString();
	}
	/**
	 * 下载基金类型明细数据
	 * @param params
	 */
	public void downLoadFundTypeData(final String params,HttpServletRequest request,
			HttpServletResponse response){
		//基金数据
		//基金类型数据
		String fundDataJson= getFundTypeApiData(params);
		FundTypeDataResponse fundTypeData = getFundTypeData(fundDataJson);
		Collection<FundTypeDataDetailDto> fundTypeList=fundTypeData.getList();
		
		ExportExcel<FundTypeDataDetailDto> ex = new ExportExcel<FundTypeDataDetailDto>();
		String[] header = ExportExcel.fundsaleFundTypeHeads;
		String wd = request.getParameter("wd");
		if("2".equals(wd)){
			//收入数据
			header = ExportExcel.fundsaleFundTypeHeadsIncome;
		}
		String rootpath = request.getSession().getServletContext().getRealPath("/");
		File dataFile = new File(rootpath+"resources/data_detail.xls");
		ex.exportExcel("基金类型趋势明细", header,header, fundTypeList, dataFile,"");
		String fileName = buildFileName("趋势明细", request)+".xls";
        FileUtil.down(dataFile, fileName, response);
        
	}
	/**
	 * 下载基金明细数据
	 * @param params
	 */
	public void downLoadFundData(final String params,HttpServletRequest request,
			HttpServletResponse response){
		//基金数据
		String fundDataJson= queryFundSumDataAPIList(params);
		FundDataResponse fundData = getFundData(fundDataJson);
		Collection<FundDataDetailDto> fundTypeList=fundData.getList();
		
		ExportExcel<FundDataDetailDto> ex = new ExportExcel<FundDataDetailDto>();
		String[] header = ExportExcel.fundsaleFundHeads;
		String wd = request.getParameter("wd");
		if("2".equals(wd)){
			//收入数据
			header = ExportExcel.fundsaleFundHeadsIncome;
		}
		String rootpath = request.getSession().getServletContext().getRealPath("/");
		File dataFile = new File(rootpath+"resources/data_detail.xls");
		ex.exportExcel("基金趋势明细", header,header, fundTypeList, dataFile,"");
		String fileName = buildFileName("基金明细", request)+".xls";
        FileUtil.down(dataFile, fileName, response);
		
	}
	
	/**
	 * 查询基金公司汇总数据
	 * @param params
	 * @return
	 */
	public String queryFundTaSumDataList(String params){
		String queryNo=API_JOB_TA_FEE_SUM;
		return getReqJson(params, queryNo);
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

	public String getChannelUrl() {
		return channelUrl;
	}

	public void setChannelUrl(String channelUrl) {
		this.channelUrl = channelUrl;
	}

	public String getApiAdminQuery() {
		return apiAdminQuery;
	}

	public void setApiAdminQuery(String apiAdminQuery) {
		this.apiAdminQuery = apiAdminQuery;
	}
	
	
}
