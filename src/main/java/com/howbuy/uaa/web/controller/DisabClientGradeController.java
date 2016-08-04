/**
 * 
 */
package com.howbuy.uaa.web.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertySetStrategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.howbuy.hadoop.mapping.MyPropertySetStrategy;
import com.howbuy.uaa.common.contants.UaaContants;
import com.howbuy.uaa.dto.DisabClientGradeDetailApiResponse;
import com.howbuy.uaa.dto.DisabClientGradeDetailDataApiResponse;
import com.howbuy.uaa.utils.ExportExcel;
import com.howbuy.uaa.utils.FileUtil;
import com.howbuy.uaa.utils.HttpUtil;
import com.howbuy.uaa.utils.JsonParse;

/**
 * @author qiankun.li 已分配AB客户评分表
 */
public class DisabClientGradeController extends MultiActionController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DisabClientGradeController.class);
	
	/**
	 * 穿透分析API接口
	 */
	private String apiUrl;

	private String index;

	public ModelAndView index(HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> modelMap = new HashMap<String, Object>(0);
		modelMap = queryData(request, response);
		return new ModelAndView(index).addAllObjects(modelMap);
	}
	
	private Map<String, Object> queryData(HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> modelMap = new HashMap<String, Object>(0);
		//构造参数
		int pageIndex = 1;
		int pageSize = 100000;
		StringBuffer paramBuffer = new StringBuffer();
		paramBuffer.append("queryNo=%s").append("&")
		.append("pageIndex=").append(pageIndex).append("&")
		.append("pageSize=").append(pageSize);
		
		String paramBase = paramBuffer.toString();
		//已分配AB客户转化表-客户类型统计数据
		final String queryNo="REPORT_CEO_KHPFCUSTTYPE";
		String param_QueryNo_Cust = String.format(paramBase, queryNo);
		//查询数据
		DisabClientGradeDetailApiResponse apiResponse_Cust = new DisabClientGradeDetailApiResponse();
		apiResponse_Cust = queryApi(apiResponse_Cust,DisabClientGradeDetailDataApiResponse.class,param_QueryNo_Cust);
		List<DisabClientGradeDetailDataApiResponse> respList = apiResponse_Cust.getList();
		if(!CollectionUtils.isEmpty(respList)){
			String custTypeJsonData = JsonParse.arrayToJsonStr(respList);
			modelMap.put("custTypeJsonData", custTypeJsonData);
		}
		//已分配AB客户转化表-部门统计数据
		final String queryNo_Dept="REPORT_CEO_KHPFOUTLET";
		String param_QueryNo_Dept = String.format(paramBase, queryNo_Dept);
		//查询数据
		DisabClientGradeDetailApiResponse apiResponse_Dept = new DisabClientGradeDetailApiResponse();
		apiResponse_Dept = queryApi(apiResponse_Dept,DisabClientGradeDetailDataApiResponse.class,param_QueryNo_Dept);
		List<DisabClientGradeDetailDataApiResponse> respListDept = apiResponse_Dept.getList();
		if(!CollectionUtils.isEmpty(respListDept)){
			String custTypeJsonData = JsonParse.arrayToJsonStr(respListDept);
			modelMap.put("deptJsonData", custTypeJsonData);
		}
		
		//已分配AB客户转化表-部门统计数据
		final String queryNo_Tg="REPORT_CEO_KHPFCONS";
		String param_QueryNo_Tg = String.format(paramBase, queryNo_Tg);
		//查询数据
		DisabClientGradeDetailApiResponse apiResponse_Tg = new DisabClientGradeDetailApiResponse();
		apiResponse_Tg = queryApi(apiResponse_Tg,DisabClientGradeDetailDataApiResponse.class,param_QueryNo_Tg);
		List<DisabClientGradeDetailDataApiResponse> respListTg = apiResponse_Tg.getList();
		if(!CollectionUtils.isEmpty(respListTg)){
			modelMap.put("respListTg", respListTg);
		}
		return modelMap;
	}
	
	
	public void exportData(HttpServletRequest request,HttpServletResponse response){
		StringBuffer paramBuffer = new StringBuffer();
		paramBuffer.append("queryNo=%s").append("&")
		.append("pageIndex=").append(1).append("&")
		.append("pageSize=").append(100000);
		
		String paramBase = paramBuffer.toString();
		//已分配AB客户转化表-部门统计数据
		final String queryNo_Tg="REPORT_CEO_KHPFCONS";
		String param_QueryNo_Tg = String.format(paramBase, queryNo_Tg);
		//查询数据
		DisabClientGradeDetailApiResponse apiResponse_Tg = new DisabClientGradeDetailApiResponse();
		apiResponse_Tg = queryApi(apiResponse_Tg,DisabClientGradeDetailDataApiResponse.class,param_QueryNo_Tg);
		List<DisabClientGradeDetailDataApiResponse> respListTg = apiResponse_Tg.getList();
		
		ExportExcel<DisabClientGradeDetailDataApiResponse> ex = new ExportExcel<DisabClientGradeDetailDataApiResponse>();
		String[] header =ExportExcel.gradeHeads;
		String rootpath = request.getSession().getServletContext().getRealPath("/");
		File dataFile = new File(rootpath+"resources/data_detail.xls");
		ex.exportExcel("客户成交数据明细", header,header, respListTg, dataFile,"");
        String fileName = "客户成交数据明细.xls";
        FileUtil.down(dataFile, fileName, response);
	}
	
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

}
