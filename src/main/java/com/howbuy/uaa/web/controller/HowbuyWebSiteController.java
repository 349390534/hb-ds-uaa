package com.howbuy.uaa.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.howbuy.common.util.NumberUtil;
import com.howbuy.uaa.command.AccessChannelCommand;
import com.howbuy.uaa.command.RouteManageCommand;
import com.howbuy.uaa.dto.HowbuyWebsiteCollDataMapping;
import com.howbuy.uaa.dto.HowbuyWebsiteData;
import com.howbuy.uaa.dto.HowbuyWebsiteDataMapping;
import com.howbuy.uaa.dto.HowbuyWebsiteFormData;
import com.howbuy.uaa.persistence.HowbuyRootChannel;
import com.howbuy.uaa.persistence.ZeroChannelTag;
import com.howbuy.uaa.service.AnalysisDataToolService;
import com.howbuy.uaa.service.AnalysisHowbuyWebsiteService;
import com.howbuy.uaa.utils.DateUtils;
import com.howbuy.uaa.utils.ExportExcel;
import com.howbuy.uaa.utils.FileUtil;
import com.howbuy.uaa.utils.JsonParse;
import com.howbuy.uaa.utils.UaaDataUtil;

/**
 * @author qiankun.li 好买网站 数据分析
 */
public class HowbuyWebSiteController extends MultiActionController {

	private String index;
	private String trendGraph;
	private String trendDetail;
	private String trendDetailTable;
	private String channelDetail;

	private String indexQueryRouteOne;
	private String indexQueryRouteTwo;
	private String indexQueryRouteThree;

	private AnalysisHowbuyWebsiteService analysisHowbuyWebsiteService;
	private AnalysisDataToolService analysisDataToolService;

	/**
	 * 根渠道json对象
	 */
	private String rootChannelJson;
	/**
	 * 搜索渠道json对象
	 */
	private String zeroChannelTagJson;

	void init() {
		List<HowbuyRootChannel> rootChannelList = analysisHowbuyWebsiteService
				.queryHowbuyRootChannelList();
		rootChannelJson = JsonParse.arrayToJsonStr(rootChannelList);
		List<ZeroChannelTag> channelTagList = analysisHowbuyWebsiteService
				.queryZeroChannelTagList();
		zeroChannelTagJson = JsonParse.arrayToJsonStr(channelTagList);
	}

	/**
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> modelMap = new java.util.HashMap<String, Object>();
		modelMap.put("rootChannelJson", rootChannelJson);
		modelMap.put("zeroChannelTagJson", zeroChannelTagJson);
		return new ModelAndView(index).addAllObjects(modelMap);
	}

	/**
	 * 推广渠道列表初始化
	 * 
	 * @param request
	 * @param response
	 * @param ri
	 * @return
	 */
	public ModelAndView initailQueryQd(HttpServletRequest request,
			HttpServletResponse response, RouteManageCommand ri) {
		int level = ri.getLevel();
		String parent = ri.getParentId();
		String channelType = ri.getChannelType();
		int chType = 1;// 默认为网站
		if (StringUtils.isNotBlank(channelType)
				&& NumberUtil.isInteger(channelType)) {
			chType = Integer.valueOf(channelType);
		}
		List<RouteManageCommand> list = analysisDataToolService.queryAllRoute(level,chType,parent);
		if (level == 1)
			return new ModelAndView(indexQueryRouteOne).addObject("routeOne",
					list);
		else if (level == 2) {
			return new ModelAndView(indexQueryRouteTwo).addObject("routeTwo",
					list);
		} else {
			return new ModelAndView(indexQueryRouteThree).addObject(
					"routeThree", list);
		}
	}

	/**
	 * 异步加载分布图/趋势图
	 * 
	 * @param request
	 * @param response
	 * @param ac
	 * @return
	 */
	public ModelAndView trendGraph(HttpServletRequest request,HttpServletResponse response, AccessChannelCommand ac) {
		Map<String, Object> modelMap = new HashMap<String, Object>(0);
		String start = ac.getBeginDate();
		String end = ac.getEndDate();
		int level = ac.getLevel();
		String channelType = ac.getChannelType();

		HowbuyWebsiteFormData websiteFormData = new HowbuyWebsiteFormData();
		BeanUtils.copyProperties(ac, websiteFormData);

		Date df = DateUtils.parseDate(start, DateUtils.FORMAT_D_YYYYMMDD);
		Date de = DateUtils.parseDate(end, DateUtils.FORMAT_D_YYYYMMDD);
		List<String> dateList = UaaDataUtil.getDateXList(df, de,DateUtils.FORMAT_D_YYYYMMDD);
		websiteFormData.setDateList(dateList);
		HowbuyWebsiteData websiteCollData = analysisHowbuyWebsiteService.queryHowbuyWebsiteCollData(websiteFormData);
		List<HowbuyWebsiteCollDataMapping> collData = websiteCollData.getColl();
		if (!CollectionUtils.isEmpty(collData)) {
			HowbuyWebsiteCollDataMapping collDataMapping = collData.get(0);
			modelMap.put("collData", collDataMapping);
		}
		String sumCollJson = "[]";
		List<HowbuyWebsiteDataMapping> sumCollList = websiteCollData.getList();
		if (!CollectionUtils.isEmpty(sumCollList)) {
			sumCollJson = JsonParse.arrayToJsonStr(sumCollList);
		}
		
		HowbuyWebsiteData websiteData = analysisHowbuyWebsiteService.queryHowbuyWebsiteDataDetail(websiteFormData);
		List<HowbuyWebsiteCollDataMapping> childCollList = websiteData.getColl();
		List<HowbuyWebsiteDataMapping> childList = websiteData.getList();
		String childListJson = "[]";
		if (!CollectionUtils.isEmpty(childList)) {
			childListJson = JsonParse.arrayToJsonStr(childList);
		}
		String childCollJson = "[]";
		if (!CollectionUtils.isEmpty(childCollList)) {
			childCollJson = JsonParse.arrayToJsonStr(childCollList);
		}
		modelMap.put("beginDate", start);
		modelMap.put("endDate", end);
		modelMap.put("level", level);
		modelMap.put("channelType", channelType);
		modelMap.put("dataJson", childListJson);
		modelMap.put("collJson", childCollJson);
		modelMap.put("childCollList", childCollList);
		modelMap.put("sumListJson", sumCollJson);
		modelMap.put("dateList", dateList);

		return new ModelAndView(trendGraph).addAllObjects(modelMap);
	}

	/**
	 * 异步加载渠道明细
	 * @param request
	 * @param response
	 * @param ac
	 * @return
	 */
	public ModelAndView channelDetail(HttpServletRequest request,HttpServletResponse response, AccessChannelCommand ac) {

		Map<String, Object> modelMap = new HashMap<String, Object>(0);
		String start = ac.getBeginDate();
		String end = ac.getEndDate();
		int level = ac.getLevel();
		String channelType = ac.getChannelType();

		HowbuyWebsiteFormData websiteFormData = new HowbuyWebsiteFormData();
		BeanUtils.copyProperties(ac, websiteFormData);

		Date df = DateUtils.parseDate(start, DateUtils.FORMAT_D_YYYYMMDD);
		Date de = DateUtils.parseDate(end, DateUtils.FORMAT_D_YYYYMMDD);
		List<String> dateList = UaaDataUtil.getDateXList(df, de,DateUtils.FORMAT_D_YYYYMMDD);
		websiteFormData.setDateList(dateList);
		HowbuyWebsiteData websiteCollData = analysisHowbuyWebsiteService.queryHowbuyWebsiteCollData(websiteFormData);
		List<HowbuyWebsiteCollDataMapping> collData = websiteCollData.getColl();
		if (!CollectionUtils.isEmpty(collData)) {
			HowbuyWebsiteCollDataMapping collDataMapping = collData.get(0);
			modelMap.put("collData", collDataMapping);
		}
		HowbuyWebsiteData websiteData = analysisHowbuyWebsiteService.queryHowbuyWebsiteDataDetail(websiteFormData);
		List<HowbuyWebsiteCollDataMapping> childCollList = websiteData.getColl();
		modelMap.put("beginDate", start);
		modelMap.put("endDate", end);
		modelMap.put("level", level);
		modelMap.put("channelType", channelType);
		modelMap.put("websiteDataList", childCollList);
		return new ModelAndView(channelDetail).addAllObjects(modelMap);
	}

	/**
	 * 渠道明细 导出
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public void channelDetailOut(HttpServletRequest request,HttpServletResponse response,AccessChannelCommand ac) {
		String start = ac.getBeginDate();
		String end = ac.getEndDate();

		HowbuyWebsiteFormData websiteFormData = new HowbuyWebsiteFormData();
		BeanUtils.copyProperties(ac, websiteFormData);

		Date df = DateUtils.parseDate(start, DateUtils.FORMAT_D_YYYYMMDD);
		Date de = DateUtils.parseDate(end, DateUtils.FORMAT_D_YYYYMMDD);
		List<String> dateList = UaaDataUtil.getDateXList(df, de,DateUtils.FORMAT_D_YYYYMMDD);
		websiteFormData.setDateList(dateList);
		
		HowbuyWebsiteData websiteData = analysisHowbuyWebsiteService.queryHowbuyWebsiteDataDetail(websiteFormData);
		List<HowbuyWebsiteCollDataMapping> childCollList = websiteData.getColl();
		/*String[] heads = {"channelName","enter","pv","uv","validuv","gmuv","simuuv","drkh",
	       "drbk","persons","bills","amt","xdzhl","drxdcjrs","drxdcjbs","drxdcjje","cjzhl"};*/
		String[] heads = ExportExcel.channelHeads;
		String rootpath = request.getSession().getServletContext().getRealPath("/");
		File dataFile = new File(rootpath + "resources/data_detail.xls");
		ExportExcel<HowbuyWebsiteCollDataMapping> ex = new ExportExcel<HowbuyWebsiteCollDataMapping>();
		ex.exportExcel("渠道明细", heads, heads, childCollList, dataFile, "");
		/*String fileName = "data_detail_"
				+ DateUtils.getFormatedDate(new Date(),
						DateUtils.FORMAT_YYYYMMDD_HHMMSS) + ".xls";*/
		String fileName = ac.getCurrentParam() + ".xls";
		FileUtil.down(dataFile, fileName, response);

	}

	/**
	 * 异步加载趋势明细
	 * @param request
	 * @param response
	 * @param ac
	 * @return
	 */
	public ModelAndView trendDetail(HttpServletRequest request,HttpServletResponse response, AccessChannelCommand ac) {
		Map<String, Object> modelMap = new HashMap<String, Object>(0);
		String start = ac.getBeginDate();
		String end = ac.getEndDate();
		int level = ac.getLevel();
		String channelType = ac.getChannelType();
		HowbuyWebsiteFormData websiteFormData = new HowbuyWebsiteFormData();
		BeanUtils.copyProperties(ac, websiteFormData);
		
		Date df = DateUtils.parseDate(start, DateUtils.FORMAT_D_YYYYMMDD);
		Date de = DateUtils.parseDate(end, DateUtils.FORMAT_D_YYYYMMDD);
		List<String> dateList = UaaDataUtil.getDateXList(df, de,DateUtils.FORMAT_D_YYYYMMDD);
		websiteFormData.setDateList(dateList);

		HowbuyWebsiteData websiteCollData = analysisHowbuyWebsiteService.queryHowbuyWebsiteCollData(websiteFormData);
		List<HowbuyWebsiteCollDataMapping> collData = websiteCollData.getColl();
		if (!CollectionUtils.isEmpty(collData)) {
			HowbuyWebsiteCollDataMapping collDataMapping = collData.get(0);
			modelMap.put("collData", collDataMapping);
			List<HowbuyWebsiteDataMapping> websiteDataMappings = websiteCollData.getList();
			modelMap.put("websiteDataList", websiteDataMappings);
			int pageCount = websiteCollData.getPageCount();
			int pages = websiteCollData.getPages();
			int rows = websiteCollData.getPageSize();
			modelMap.put("pages", pages);
			modelMap.put("total", pageCount);
			modelMap.put("rows", rows);
		}
		modelMap.put("beginDate", start);
		modelMap.put("endDate", end);
		modelMap.put("level", level);
		modelMap.put("channelType", channelType);
		
		return new ModelAndView(trendDetail).addAllObjects(modelMap);
	}

	/**
	 * 趋势明细导出
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public void trendDetailOut(HttpServletRequest request,HttpServletResponse response,AccessChannelCommand ac) throws IOException {
		String start = ac.getBeginDate();
		String end = ac.getEndDate();
		HowbuyWebsiteFormData websiteFormData = new HowbuyWebsiteFormData();
		BeanUtils.copyProperties(ac, websiteFormData);
		
		Date df = DateUtils.parseDate(start, DateUtils.FORMAT_D_YYYYMMDD);
		Date de = DateUtils.parseDate(end, DateUtils.FORMAT_D_YYYYMMDD);
		List<String> dateList = UaaDataUtil.getDateXList(df, de,DateUtils.FORMAT_D_YYYYMMDD);
		websiteFormData.setDateList(dateList);

		HowbuyWebsiteData websiteCollData = analysisHowbuyWebsiteService.queryHowbuyWebsiteCollData(websiteFormData);
		List<HowbuyWebsiteDataMapping> websiteDataMappings = websiteCollData.getList();
		String[] heads = ExportExcel.trendHeads;
		String rootpath = request.getSession().getServletContext().getRealPath("/");
		File dataFile = new File(rootpath + "resources/data_detail.xls");
		ExportExcel<HowbuyWebsiteDataMapping> ex = new ExportExcel<HowbuyWebsiteDataMapping>();
		ex.exportExcel("趋势明细", heads, heads, websiteDataMappings, dataFile, "");
		//String fileName = "data_detail_"+ DateUtils.getFormatedDate(new Date(),DateUtils.FORMAT_YYYYMMDD_HHMMSS) + ".xls";
		String fileName = ac.getCurrentParam() + ".xls";
		FileUtil.down(dataFile, fileName, response);
	}

	/** 
	 * TrendDetail Tbody区域
	 * @param request
	 * @param response
	 * @param ac
	 * @return
	 */
	public ModelAndView trendDetailTbody(HttpServletRequest request,HttpServletResponse response, AccessChannelCommand ac) {
		
		String start = ac.getBeginDate();
		String end = ac.getEndDate();
		HowbuyWebsiteFormData websiteFormData = new HowbuyWebsiteFormData();
		BeanUtils.copyProperties(ac, websiteFormData);
		
		Date df = DateUtils.parseDate(start, DateUtils.FORMAT_D_YYYYMMDD);
		Date de = DateUtils.parseDate(end, DateUtils.FORMAT_D_YYYYMMDD);
		List<String> dateList = UaaDataUtil.getDateXList(df, de,DateUtils.FORMAT_D_YYYYMMDD);
		websiteFormData.setDateList(dateList);
		HashMap<String, Object> modelMap = new HashMap<String, Object>(0);
		HowbuyWebsiteData websiteCollData = analysisHowbuyWebsiteService.queryHowbuyWebsiteCollData(websiteFormData);
		List<HowbuyWebsiteCollDataMapping> collData = websiteCollData.getColl();
		if (!CollectionUtils.isEmpty(collData)) {
			List<HowbuyWebsiteDataMapping> websiteDataMappings = websiteCollData.getList();
			modelMap.put("websiteDataList", websiteDataMappings);
		}
		return new ModelAndView(trendDetailTable).addAllObjects(modelMap);
	}

	
	
	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getTrendGraph() {
		return trendGraph;
	}

	public void setTrendGraph(String trendGraph) {
		this.trendGraph = trendGraph;
	}

	public String getTrendDetail() {
		return trendDetail;
	}

	public void setTrendDetail(String trendDetail) {
		this.trendDetail = trendDetail;
	}

	public String getChannelDetail() {
		return channelDetail;
	}

	public void setChannelDetail(String channelDetail) {
		this.channelDetail = channelDetail;
	}

	public String getIndexQueryRouteOne() {
		return indexQueryRouteOne;
	}

	public void setIndexQueryRouteOne(String indexQueryRouteOne) {
		this.indexQueryRouteOne = indexQueryRouteOne;
	}

	public String getIndexQueryRouteTwo() {
		return indexQueryRouteTwo;
	}

	public void setIndexQueryRouteTwo(String indexQueryRouteTwo) {
		this.indexQueryRouteTwo = indexQueryRouteTwo;
	}

	public String getIndexQueryRouteThree() {
		return indexQueryRouteThree;
	}

	public void setIndexQueryRouteThree(String indexQueryRouteThree) {
		this.indexQueryRouteThree = indexQueryRouteThree;
	}

	public String getTrendDetailTable() {
		return trendDetailTable;
	}

	public void setTrendDetailTable(String trendDetailTable) {
		this.trendDetailTable = trendDetailTable;
	}

	public AnalysisHowbuyWebsiteService getAnalysisHowbuyWebsiteService() {
		return analysisHowbuyWebsiteService;
	}

	public void setAnalysisHowbuyWebsiteService(
			AnalysisHowbuyWebsiteService analysisHowbuyWebsiteService) {
		this.analysisHowbuyWebsiteService = analysisHowbuyWebsiteService;
	}

	public AnalysisDataToolService getAnalysisDataToolService() {
		return analysisDataToolService;
	}

	public void setAnalysisDataToolService(
			AnalysisDataToolService analysisDataToolService) {
		this.analysisDataToolService = analysisDataToolService;
	}

	public String getRootChannelJson() {
		return rootChannelJson;
	}

	public void setRootChannelJson(String rootChannelJson) {
		this.rootChannelJson = rootChannelJson;
	}

	public String getZeroChannelTagJson() {
		return zeroChannelTagJson;
	}

	public void setZeroChannelTagJson(String zeroChannelTagJson) {
		this.zeroChannelTagJson = zeroChannelTagJson;
	}

}
