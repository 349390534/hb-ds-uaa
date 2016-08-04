/**
 * 
 */
package com.howbuy.uaa.utils;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertySetStrategy;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.howbuy.uaa.common.contants.UaaContants;
import com.howbuy.uaa.dto.FundResponse;
import com.howbuy.uaa.dto.FundResponseCollMapping;
import com.howbuy.uaa.dto.FundResponseMapping;
import com.howbuy.uaa.dto.Pagination;
import com.howbuy.uaa.dto.PenetrateApiDataResponse;
import com.howbuy.uaa.dto.PenetrateApiResponse;
import com.howbuy.uaa.dto.UserKeepApiResponse;

/**
 * @author qiankun.li
 * 
 */
public class UaaReqUtil {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(UaaReqUtil.class);

	private String custUrl;
	private String sjhzUrl;
	private String apiUrl;
	public UaaReqUtil() {
	}
	public UaaReqUtil(String custUrl, String sjhzUrl) {
		this.custUrl = custUrl;
		this.sjhzUrl = sjhzUrl;
	}
	
	public UaaReqUtil(String apiUrl) {
		this.apiUrl = apiUrl;
	}

	/**
	 * 
	 * @param params
	 * @param c
	 *            0：所选维度 1：所选维度的子集
	 * @return
	 */
	public FundResponse fundRespon(String params, int c) {
		FundResponse fundResponse = new FundResponse();
		LOGGER.debug("params:" + params);
		String result;
		if (c == 0) {
			// 获取每日公募统计指标明细
			result = HttpUtil.getHttpUtil().requestGet(custUrl, params);
			LOGGER.debug("URL:" + custUrl);
		} else {
			// 获取每日公募统计下级指标明细
			result = HttpUtil.getHttpUtil().requestGet(sjhzUrl, params);
			LOGGER.debug("URL:" + sjhzUrl);
		}
		LOGGER.debug(result);
		JSONObject jsonObj = JSONObject.fromObject(result);
		String status = jsonObj.getString("status");
		if (UaaContants.FAILED.equals(status)) {
			return fundResponse;
		}
		String body = jsonObj.getString("body");

		Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();
		classMap.put("list", FundResponseMapping.class);
		classMap.put("coll", FundResponseCollMapping.class);

		JsonConfig config = new JsonConfig();
		config.setRootClass(FundResponse.class);
		config.setClassMap(classMap);
		config.setPropertySetStrategy(new MyPropertySetStrategy(
				PropertySetStrategy.DEFAULT));
		fundResponse = (FundResponse) JSONObject.toBean(JSONObject.fromObject(body), config);
		return fundResponse;
	}

	
	
	/**
	 * 穿透请求查询接口
	 * @param params
	 * @return
	 */
	public PenetrateApiResponse queryApi(String params) {
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
	}
	
	
	
	/**
	 * 查询
	 * @param params
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public UserKeepApiResponse<?> queryUserKeepApi(String params,Class<?> c) {
		UserKeepApiResponse apiResponse = new UserKeepApiResponse();
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
		config.setRootClass(UserKeepApiResponse.class);
		Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();
		classMap.put("list", c);
		classMap.put("pagination", Pagination.class);
		config.setClassMap(classMap);
		config.setPropertySetStrategy(new MyPropertySetStrategy(PropertySetStrategy.DEFAULT));
		apiResponse = (UserKeepApiResponse) JSONObject.toBean(JSONObject.fromObject(body), config);
		apiResponse.setJson(body);
		return apiResponse;
	}
	
	
	/**
	 * 获取api对应的listJson对象
	 * @param url
	 * @param params
	 * @return
	 */
	public String getDataxApi(final String url,final String params){
		LOGGER.debug("URL:" + apiUrl + ",params:" + params);
		String json = null;
		String urlTmp = url;
		if(StringUtils.isBlank(urlTmp))
			urlTmp = apiUrl;
		json =HttpUtil.getHttpUtil().requestGet(urlTmp,params);
		com.alibaba.fastjson.JSONObject obj = JSON.parseObject(json);
		String disListJson =obj.getJSONObject("body").getString("list");
		return disListJson;
	}
	
	public String queryDataxApiList(String params){
		LOGGER.debug("params:" + params);
		return getDataxApi(apiUrl, params);
	}
	
	public String queryDataxApi(String params){
		LOGGER.debug("params:" + params);
		String json = null;
		json =HttpUtil.getHttpUtil().requestGet(apiUrl,params);
		return json;
	}
	
	// 请求获取数据
	private static class MyPropertySetStrategy extends PropertySetStrategy {

		public MyPropertySetStrategy(PropertySetStrategy ori) {
			this.ori = ori;
		}

		private final PropertySetStrategy ori;

		public void setProperty(Object o, String n, Object v)
				throws JSONException {
			try {
				ori.setProperty(o, n, v);
			} catch (Exception e) {

			}
		}
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
	public String getApiUrl() {
		return apiUrl;
	}
	public void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
	}
}
