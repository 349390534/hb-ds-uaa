/**
 * 
 */
package com.howbuy.uaa.web.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.howbuy.uaa.command.UserKeepCommond;
import com.howbuy.uaa.common.contants.UaaContants;
import com.howbuy.uaa.common.execption.UaaRunTimeException;
import com.howbuy.uaa.dto.UserKeepApiResponse;
import com.howbuy.uaa.dto.UserKeepDataApiMonTradeResponse;
import com.howbuy.uaa.dto.UserKeepDataApiMonUserResponse;
import com.howbuy.uaa.dto.UserKeepDataApiYearTradeResponse;
import com.howbuy.uaa.dto.UserKeepDataApiYearUserResponse;
import com.howbuy.uaa.utils.DateUtils;
import com.howbuy.uaa.utils.HttpUtil;
import com.howbuy.uaa.utils.UaaReqUtil;
import com.howbuy.uaa.utils.UaaStringUtils;

/**
 * @author qiankun.li
 * 用户留存分析
 */
public class UserAnalysisController extends MultiActionController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserAnalysisController.class);

	private String index;

	private String mindex;

	private String indexGraphDetail;
	
	private String indexGraphDetailM;

	/**
	 */
	private String queryChannelUrl;

	private String apiUrl;

	private UaaReqUtil uaaReqUtil;

	private String channelJson;

	/**
	 * 查询参数
	 */
	private final Map<String, String> queryNoMap = new HashMap<String, String>(4);

	public void init() {
		// 公募客户渠道API地址如下（渠道信息）
		try {
			uaaReqUtil = new UaaReqUtil(apiUrl);

			if (StringUtils.isBlank(queryChannelUrl))
				throw new UaaRunTimeException(
						"init channel failed,the request url is null");
			String json = HttpUtil.getHttpUtil().requestGet(queryChannelUrl);
			JSONObject jsonobj = JSONObject.fromObject(json);
			JSONArray disArrs = jsonobj.getJSONObject("body").getJSONArray(
					"list");
			channelJson = disArrs.toString();
			LOGGER.debug(channelJson);

		} catch (Exception e) {
			LOGGER.error("UserAnalysisController init error.", e);
		}
		// 接口查询信息
		queryNoMap.put("newtrade-y", "API_EC_STAT_OUTLET_PUB_BUYDNXZ");
		queryNoMap.put("newuser-y", "API_EC_STAT_OUTLET_PUB_BUY_DN");
		queryNoMap.put("newtrade-m", "API_EC_STAT_OUTLET_PUB_BUYDYXZ");
		queryNoMap.put("newuser-m", "API_EC_STAT_OUTLET_PUB_BUY_DY");
	}

	/**
	 * 整体留存首页（按年）
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView index(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView view = new ModelAndView(index);
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("gmtjqd", channelJson);
		return view.addAllObjects(modelMap);
	}

	@SuppressWarnings("unchecked")
	public ModelAndView loadData(HttpServletRequest request,
			HttpServletResponse response, UserKeepCommond ukc) {
		ModelAndView view = new ModelAndView(indexGraphDetail);
		Map<String, Object> modelMap = new HashMap<String, Object>();
		// 年份
		String year = ukc.getYear();
		// 维度
		String analysisWd = UaaStringUtils.nullToString(ukc.getAnalysisWd(), "");
		// 开户机构
		String openInst = UaaStringUtils.nullToString(ukc.getOpenInst(), "");
		// 开户平台
		String openPlatform = UaaStringUtils.nullToString(ukc.getOpenPlatform(), "");
		// 合作类型
		String cooperateType = UaaStringUtils.nullToString(ukc.getCooperateType(), "");
		// 开户网点
		String openwangdian = UaaStringUtils.nullToString(ukc.getOpenWangDian(), "");

		Integer gid = ukc.getGid();
		String queryNo = queryNoMap.get(analysisWd);
		if (StringUtils.isBlank(queryNo))
			throw new UaaRunTimeException("queryNo is null");

		StringBuffer paramsSb = new StringBuffer("queryNo=" + queryNo);
		String startM = year + "01";
		paramsSb.append("&start_date=").append(startM);
		String endM = year + "12";
		paramsSb.append("&end_date=").append(endM);

		if (StringUtils.isNotBlank(openInst)) {
			paramsSb.append("&dis_code=").append(openInst);
		}
		if (StringUtils.isNotBlank(openPlatform)) {
			paramsSb.append("&trade_chan=").append(openPlatform);
		}
		if (StringUtils.isNotBlank(cooperateType)) {
			paramsSb.append("&hzlx_code=").append(cooperateType);
		}
		if (StringUtils.isNotBlank(openwangdian)) {
			paramsSb.append("&outlet_code=").append(openwangdian);
		}
		paramsSb.append("&flag=").append(gid);
		String paramsStr = paramsSb.toString();
		if("newtrade-y".equals(analysisWd)){
			UserKeepApiResponse<UserKeepDataApiYearTradeResponse> keepApiResponse = (UserKeepApiResponse<UserKeepDataApiYearTradeResponse>) uaaReqUtil
					.queryUserKeepApi(paramsStr,UserKeepDataApiYearTradeResponse.class);
			List<UserKeepDataApiYearTradeResponse> dataList = keepApiResponse.getList();
			UserKeepDataApiYearTradeResponse top = null;
			if (!CollectionUtils.isEmpty(dataList))
				top = dataList.get(0);
			if(null!=top){
				syncRightDataY(top, modelMap);
				modelMap.put("stat_dt", top.getStat_dt());
			}
			modelMap.put("dataList", dataList);
			String json = keepApiResponse.getJson();
			modelMap.put("jsonData", json);
		}else if("newuser-y".equals(analysisWd)){
			UserKeepApiResponse<UserKeepDataApiYearUserResponse> keepApiResponse = (UserKeepApiResponse<UserKeepDataApiYearUserResponse>) uaaReqUtil
					.queryUserKeepApi(paramsStr,UserKeepDataApiYearUserResponse.class);
			List<UserKeepDataApiYearUserResponse> dataList = keepApiResponse.getList();
			UserKeepDataApiYearUserResponse top = null;
			if (!CollectionUtils.isEmpty(dataList))
				top = dataList.get(0);
			if(null!=top){
				syncRightDataY(top, modelMap);
				modelMap.put("stat_dt", top.getStat_dt());
			}
			modelMap.put("dataList", dataList);
			String json = keepApiResponse.getJson();
			modelMap.put("jsonData", json);
		}
		modelMap.put("wd", analysisWd);
		return view.addAllObjects(modelMap);
	}

	/**
	 * 计算年化指标数据-交易维度
	 * 
	 * @param top
	 * @param modelMap
	 */
	private void syncRightDataY(UserKeepDataApiYearTradeResponse top,
			Map<String, Object> modelMap) {
		if (null != top) {
			// 新增交易人数
			Long dnxzjy_rs_total = top.getDnxzjy_rs_total();
			modelMap.put("dnxzjy_rs_total", dnxzjy_rs_total);
			// 复购人数-交易次数大于等于2
			Long dnxzjy_rs_2 = top.getDnxzjy_rs_2();
			Long dnxzjy_rs_3 = top.getDnxzjy_rs_3();
			Long dnxzjy_rs_4 = top.getDnxzjy_rs_4();
			Long dnxzjy_rs_5 = top.getDnxzjy_rs_5();
			Long dnxzjy_rs_gt5 = top.getDnxzjy_rs_gt5();
			Long fgrs = 0l;
			if (null != dnxzjy_rs_2)
				fgrs += dnxzjy_rs_2;
			if (null != dnxzjy_rs_3)
				fgrs += dnxzjy_rs_3;
			if (null != dnxzjy_rs_4)
				fgrs += dnxzjy_rs_4;
			if (null != dnxzjy_rs_5)
				fgrs += dnxzjy_rs_5;
			if (null != dnxzjy_rs_gt5)
				fgrs += dnxzjy_rs_gt5;
			modelMap.put("dnxzjy_fgrs", fgrs);
			// 复购人数占比=复购人数/新增交易人数
			BigDecimal fgrszb = new BigDecimal(0);
			if (null != dnxzjy_rs_total && dnxzjy_rs_total.longValue() != 0)
				fgrszb = new BigDecimal(fgrs).divide(new BigDecimal(
						dnxzjy_rs_total), UaaContants.SCALE_LV,
						BigDecimal.ROUND_HALF_DOWN);
			modelMap.put("dnxzjy_fgrszb", fgrszb);
			// 存量人数
			Long clrs = top.getClrs();
			modelMap.put("clrs", clrs);
			// 存量人数占比
			BigDecimal clrsRate = top.getClrs_rate();
			modelMap.put("clrsRate", clrsRate);

		}
	}
	
	/**
	 * 计算年化指标数据-开户维度
	 * @param top
	 * @param modelMap
	 */
	private void syncRightDataY(UserKeepDataApiYearUserResponse top,
			Map<String, Object> modelMap) {
		//新开户人数
		Long dnkh_zrs = top.getDnkh_zrs();
		modelMap.put("dnkh_zrs", dnkh_zrs);
		//新增交易人数	
		Long dnkhdnjy_rs_total = top.getDnkhdnjy_rs_total();
		modelMap.put("dnkhdnjy_rs_total", dnkhdnjy_rs_total);
		//占比
		BigDecimal dnkhdnjy_rs_total_zb = new BigDecimal(0);
		if(null!=dnkh_zrs)
			dnkhdnjy_rs_total_zb = new BigDecimal(dnkhdnjy_rs_total).divide(new BigDecimal(dnkh_zrs)
			,UaaContants.SCALE_LV,BigDecimal.ROUND_HALF_DOWN);
		modelMap.put("dnkhdnjy_rs_total_zb", dnkhdnjy_rs_total_zb);
		
		//复购人数
		Long fgrs = 0l;
		Long djrs2= top.getDnkhdnjy_rs_2();
		if(null!=djrs2)
			fgrs+=djrs2;
		Long djrs3= top.getDnkhdnjy_rs_3();
		if(null!=djrs3)
			fgrs+=djrs3;
		Long djrs4= top.getDnkhdnjy_rs_4();
		if(null!=djrs4)
			fgrs+=djrs4;
		Long djrs5= top.getDnkhdnjy_rs_5();
		if(null!=djrs5)
			fgrs+=djrs5;
		Long djrsgt5= top.getDnkhdnjy_rs_gt5();
		if(null!=djrsgt5)
			fgrs+=djrsgt5;
		modelMap.put("fgrs", fgrs);
		//占比
		BigDecimal fgrs_zb = new BigDecimal(0);
		if(null!=dnkh_zrs)
			fgrs_zb = new BigDecimal(fgrs).divide(new BigDecimal(dnkh_zrs)
			,UaaContants.SCALE_LV,BigDecimal.ROUND_HALF_DOWN);
		modelMap.put("fgrs_zb", fgrs_zb);
		//有存量人数
		Long clrs = top.getClrs();
		modelMap.put("clrs", clrs);
		//占比
		BigDecimal clrs_zb = new BigDecimal(0);
		if(null!=dnkh_zrs)
			clrs_zb = new BigDecimal(clrs).divide(new BigDecimal(dnkh_zrs)
			,UaaContants.SCALE_LV,BigDecimal.ROUND_HALF_DOWN);
		modelMap.put("clrs_zb", clrs_zb);
		
	}

	/**
	 * 按月留存首页
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView mindex(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView view = new ModelAndView(mindex);
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("gmtjqd", channelJson);
		return view.addAllObjects(modelMap);
	}

	@SuppressWarnings("unchecked")
	public ModelAndView loadDataM(HttpServletRequest request,HttpServletResponse response, UserKeepCommond ukc) {
		ModelAndView view = new ModelAndView(indexGraphDetailM);
		Map<String, Object> modelMap = new HashMap<String, Object>();
		// 年份
		String month = ukc.getMonth();
		// 维度
		String analysisWd = UaaStringUtils.nullToString(ukc.getAnalysisWd(), "");
		// 开户机构
		String openInst = UaaStringUtils.nullToString(ukc.getOpenInst(), "");
		// 开户平台
		String openPlatform = UaaStringUtils.nullToString(ukc.getOpenPlatform(), "");
		// 合作类型
		String cooperateType = UaaStringUtils.nullToString(ukc.getCooperateType(), "");
		// 开户网点
		String openwangdian = UaaStringUtils.nullToString(ukc.getOpenWangDian(), "");

		Integer gid = ukc.getGid();
		String queryNo = queryNoMap.get(analysisWd);
		if (StringUtils.isBlank(queryNo))
			throw new UaaRunTimeException("queryNo is null");

		StringBuffer paramsSb = new StringBuffer("queryNo=" + queryNo);
		Date date = DateUtils.parseDate(month, "yyyyMM");
		Date lastDate = DateUtils.addMonths(date, 11);
		
		String startM = month;
		paramsSb.append("&start_date=").append(startM);
		String endM = DateUtils.getFormatedDate(lastDate, "yyyyMM");
		paramsSb.append("&end_date=").append(endM);

		if (StringUtils.isNotBlank(openInst)) {
			paramsSb.append("&dis_code=").append(openInst);
		}
		if (StringUtils.isNotBlank(openPlatform)) {
			paramsSb.append("&trade_chan=").append(openPlatform);
		}
		if (StringUtils.isNotBlank(cooperateType)) {
			paramsSb.append("&hzlx_code=").append(cooperateType);
		}
		if (StringUtils.isNotBlank(openwangdian)) {
			paramsSb.append("&outlet_code=").append(openwangdian);
		}
		paramsSb.append("&flag=").append(gid);
		String paramsStr = paramsSb.toString();
		if("newtrade-m".equals(analysisWd)){
			UserKeepApiResponse<UserKeepDataApiMonTradeResponse> keepApiResponse = (UserKeepApiResponse<UserKeepDataApiMonTradeResponse>) uaaReqUtil
					.queryUserKeepApi(paramsStr,UserKeepDataApiMonTradeResponse.class);
			List<UserKeepDataApiMonTradeResponse> dataList = keepApiResponse.getList();
			UserKeepDataApiMonTradeResponse top = null;
			if (!CollectionUtils.isEmpty(dataList)){
				top = dataList.get(dataList.size()-1);
				modelMap.put("nowdata", top);
				List<UserKeepDataApiMonTradeResponse> revertDataList = new ArrayList<UserKeepDataApiMonTradeResponse>(dataList);
				Collections.reverse(revertDataList);
				modelMap.put("dataList",revertDataList );
			}
			String json = keepApiResponse.getJson();
			modelMap.put("jsonData", json);
		}else if("newuser-m".equals(analysisWd)){
			UserKeepApiResponse<UserKeepDataApiMonUserResponse> keepApiResponse = (UserKeepApiResponse<UserKeepDataApiMonUserResponse>) uaaReqUtil
					.queryUserKeepApi(paramsStr,UserKeepDataApiMonUserResponse.class);
			List<UserKeepDataApiMonUserResponse> dataList = keepApiResponse.getList();
			UserKeepDataApiMonUserResponse top = null;
			if (!CollectionUtils.isEmpty(dataList)){
				top = dataList.get(dataList.size()-1);
				modelMap.put("nowdata", top);
				List<UserKeepDataApiMonUserResponse> revertDataList = new ArrayList<UserKeepDataApiMonUserResponse>(dataList);
				Collections.reverse(revertDataList);
				modelMap.put("dataList",revertDataList );
			}
			String json = keepApiResponse.getJson();
			modelMap.put("jsonData", json);
		}
		modelMap.put("wd", analysisWd);
		modelMap.put("startM", startM);
		return view.addAllObjects(modelMap);
	}
	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getMindex() {
		return mindex;
	}

	public void setMindex(String mindex) {
		this.mindex = mindex;
	}

	public String getQueryChannelUrl() {
		return queryChannelUrl;
	}

	public void setQueryChannelUrl(String queryChannelUrl) {
		this.queryChannelUrl = queryChannelUrl;
	}

	public String getApiUrl() {
		return apiUrl;
	}

	public void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
	}

	public String getIndexGraphDetail() {
		return indexGraphDetail;
	}

	public void setIndexGraphDetail(String indexGraphDetail) {
		this.indexGraphDetail = indexGraphDetail;
	}

	public String getIndexGraphDetailM() {
		return indexGraphDetailM;
	}

	public void setIndexGraphDetailM(String indexGraphDetailM) {
		this.indexGraphDetailM = indexGraphDetailM;
	}

}