/**
 * 
 */
package com.howbuy.uaa.web.controller;

import java.io.File;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.howbuy.uaa.command.ChannelEventAccountH5Command;
import com.howbuy.uaa.command.RouteManageCommand;
import com.howbuy.uaa.dto.ChannelEventAccountH5Dto;
import com.howbuy.uaa.dto.H5ActivityCollDataMapping;
import com.howbuy.uaa.dto.H5ActivityDataMapping;
import com.howbuy.uaa.persistence.ChannelEventAccount;
import com.howbuy.uaa.persistence.HowbuyRootChannel;
import com.howbuy.uaa.persistence.ZeroChannelTag;
import com.howbuy.uaa.service.ActivityH5Service;
import com.howbuy.uaa.service.AnalysisDataToolService;
import com.howbuy.uaa.service.AnalysisHowbuyWebsiteService;
import com.howbuy.uaa.utils.DateUtils;
import com.howbuy.uaa.utils.ExportExcel;
import com.howbuy.uaa.utils.FileUtil;
import com.howbuy.uaa.utils.JsonParse;
import com.howbuy.uaa.utils.UaaDataUtil;

/**
 * @author qiankun.li 活动控制器
 */
public class ActivityController extends MultiActionController {

	private String index;
	private String indexQueryRouteOne;
	private String indexQueryRouteTwo;
	private String indexQueryRouteThree;
	private String graphView;
	private String detailView;
	/**
	 * 根渠道json对象
	 */
	private String rootChannelJson;
	/**
	 * 搜索渠道json对象
	 */
	private String zeroChannelTagJson;

	private AnalysisDataToolService analysisDataToolService;
	private AnalysisHowbuyWebsiteService analysisHowbuyWebsiteService;
	private ActivityH5Service activityH5Service;

	
	void init() {
		List<HowbuyRootChannel> rootChannelList = analysisHowbuyWebsiteService
				.queryHowbuyRootChannelList();
		rootChannelJson = JsonParse.arrayToJsonStr(rootChannelList);
		List<ZeroChannelTag> channelTagList = analysisHowbuyWebsiteService
				.queryZeroChannelTagList();
		zeroChannelTagJson = JsonParse.arrayToJsonStr(channelTagList);
	}

	public ModelAndView index(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView view = new ModelAndView(index);
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("rootChannelJson", rootChannelJson);
		modelMap.put("zeroChannelTagJson", zeroChannelTagJson);
		return view.addAllObjects(modelMap);
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
		int chType = 2;// 默认为无线
		List<RouteManageCommand> list = analysisDataToolService.queryAllRoute(
				level, chType, parent);
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
	 * 画图
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView graph(HttpServletRequest request,
			HttpServletResponse response, ChannelEventAccountH5Command h5Command) {
		Map<String, Object> modelMap = new HashMap<String, Object>(0);
		
		String beginDate = h5Command.getBeginDate();
		String endDate = h5Command.getEndDate();
		//是否同一天
		boolean isOneDay = false;
		if(StringUtils.isNotBlank(beginDate)&&beginDate.equals(endDate))
			isOneDay=true;
		modelMap.put("isOneDay", isOneDay?1:0);
		if(isOneDay){
			Calendar now = Calendar.getInstance();
			now.set(Calendar.SECOND, 0);//秒归零
			if(!beginDate.equals(DateUtils.getFormatedDate(new Date()))){
				now.setTime(DateUtils.parseDate(beginDate));
			}
			String showtime = DateUtils.getCurrent5Min(now);
			modelMap.put("time", showtime);
			String today = DateUtils.getFormatedDate(now.getTime(), DateUtils.FORMAT_D_YYYYMMDD);
			String beginTime = today +" 00:05";
			String endTime =  today +" "+showtime;
			if(!beginDate.equals(DateUtils.getFormatedDate(new Date()))){
				now.set(Calendar.DATE, now.get(Calendar.DATE)+1);
				String afterday = DateUtils.getFormatedDate(now.getTime(), DateUtils.FORMAT_D_YYYYMMDD);
				endTime=afterday +" 00:00";
			}
			ChannelEventAccountH5Dto accountH5Dto = new ChannelEventAccountH5Dto();
			BeanUtils.copyProperties(h5Command, accountH5Dto);
			accountH5Dto.setBeginTime(beginTime);
			accountH5Dto.setEndTime(endTime);
			//汇总数据
			H5ActivityCollDataMapping collDataMapping = activityH5Service.queryH5ActivityCollData(accountH5Dto);
			modelMap.put("collData", collDataMapping);
			//渠道数据
			List<H5ActivityCollDataMapping> channelList=activityH5Service.queryH5ActivityChannelDataList(accountH5Dto);
			if(!CollectionUtils.isEmpty(channelList)){
				String jsonDataChannel = JsonParse.arrayToJsonStr(channelList); 
				modelMap.put("jsonDataChannel", jsonDataChannel);
			}
			//趋势明细数据
			List<H5ActivityDataMapping> dataList = activityH5Service.queryChannelDetailDataList(accountH5Dto);
			if(!CollectionUtils.isEmpty(dataList)){
				String jsonData = JsonParse.arrayToJsonStr(dataList);
				modelMap.put("jsonData", jsonData);
			}
			if(!beginDate.equals(DateUtils.getFormatedDate(new Date()))){
				now.setTime(DateUtils.parseDate(beginDate));
			}
			List<String> datex24=DateUtils.getDataX24(now);
			String datex24Json = JsonParse.arrayToJsonStr(datex24);
			modelMap.put("dataX24", datex24Json);
			
			//对比数据
			String compare = ServletRequestUtils.getStringParameter(request,"compare", "");
			if(StringUtils.isNotBlank(compare)){
				String compareDate =request.getParameter("compare_date");
				Date endDateCom = DateUtils.getDate(compareDate, 1);
				String compareDateEnd  = DateUtils.getFormatedDate(endDateCom,DateUtils.FORMAT_D_YYYYMMDD);
				String beginCpmpare = compareDate+" 00:05";
				accountH5Dto.setBeginTime(beginCpmpare);
				accountH5Dto.setEndTime(compareDateEnd);
				
				List<H5ActivityDataMapping> dataListComp = activityH5Service.queryChannelDetailDataList(accountH5Dto);
				if(!CollectionUtils.isEmpty(dataListComp)){
					String jsonData = JsonParse.arrayToJsonStr(dataListComp);
					modelMap.put("jsonDataCompare", jsonData);
				}
				
				//计算以每隔五分钟为间隔的数据
				Calendar compareC = Calendar.getInstance();
				compareC.setTime(DateUtils.parseDate(compareDate));
				List<String> datex24Comp=DateUtils.getDataX24(compareC);
				String datex24JsonComp = JsonParse.arrayToJsonStr(datex24Comp);
				modelMap.put("dataX24Compare", datex24JsonComp);
			}
		}else{
			ChannelEventAccountH5Dto accountH5Dto = new ChannelEventAccountH5Dto();
			BeanUtils.copyProperties(h5Command, accountH5Dto);
			// 查询汇总数据
			H5ActivityCollDataMapping colldata = activityH5Service.getH5ActivityCollHisData(accountH5Dto);
			modelMap.put("collData", colldata);
			
			//查询渠道明细
			List<H5ActivityCollDataMapping> channelList= activityH5Service.queryH5ActivityChannelDataHisList(accountH5Dto);
			if(!CollectionUtils.isEmpty(channelList)){
				String jsonDataChannel = JsonParse.arrayToJsonStr(channelList); 
				modelMap.put("jsonDataChannel", jsonDataChannel);
			}
			//趋势明细数据
			List<H5ActivityDataMapping> dataList = activityH5Service.queryHisChannelTrendDataList(accountH5Dto);
			if(!CollectionUtils.isEmpty(dataList)){
				String jsonData = JsonParse.arrayToJsonStr(dataList);
				modelMap.put("jsonData", jsonData);
			}
			
			Date df = DateUtils.parseDate(beginDate, DateUtils.FORMAT_D_YYYYMMDD);
			Date de = DateUtils.parseDate(endDate, DateUtils.FORMAT_D_YYYYMMDD);
			List<String> dateList = UaaDataUtil.getDateXList(df,de,DateUtils.FORMAT_D_YYYYMMDD);
			if(!CollectionUtils.isEmpty(dateList)){
				String jsonData = JsonParse.arrayToJsonStr(dateList);
				modelMap.put("datax", jsonData);
			}
			modelMap.put("fromdate", beginDate);
		}
		
		return new ModelAndView(graphView).addAllObjects(modelMap);
	}
	
	
	/**
	 * 查询明细数据
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView queryDetailData(HttpServletRequest request,
			HttpServletResponse response, ChannelEventAccountH5Command h5Command) {
		Map<String, Object> modelMap = new HashMap<String, Object>(0);
		String start =h5Command.getBeginDate();
		String end = h5Command.getEndDate();
		Integer level = h5Command.getChannelLevel();
		Integer channelType = h5Command.getChannelType();
		//查询渠道数据明细
		ChannelEventAccountH5Dto accountH5Dto = new ChannelEventAccountH5Dto();
		BeanUtils.copyProperties(h5Command, accountH5Dto);
		H5ActivityCollDataMapping colldata =null;
		List<H5ActivityCollDataMapping> channelList =null;
		List<H5ActivityDataMapping> trendList=null;
		//是否同一天
		boolean isOneDay = false;
		if(StringUtils.isNotBlank(start)&&start.equals(end))
			isOneDay=true;
		if(isOneDay){
			accountH5Dto.setBeginTime(start+" 00:05");
			String endTime = DateUtils.getFormatedDate(DateUtils.getDate(end, 1), DateUtils.FORMAT_D_YYYYMMDD);
			accountH5Dto.setEndTime(endTime);
			//汇总数据
			colldata = activityH5Service.queryH5ActivityCollData(accountH5Dto);
			//渠道数据
			channelList=activityH5Service.queryH5ActivityChannelDataList(accountH5Dto);
			//趋势明细数据 一日
			List<H5ActivityCollDataMapping> dataDetailList = activityH5Service.queryChannelTrendDataList(accountH5Dto);
			modelMap.put("trendList", dataDetailList);
		}else{
			// 查询汇总数据
			colldata = activityH5Service.getH5ActivityCollHisData(accountH5Dto);
			modelMap.put("colldata", colldata);
			//查询渠道明细
			channelList= activityH5Service.queryH5ActivityChannelDataHisList(accountH5Dto);
			//查询趋势明细数据
			trendList=activityH5Service.queryChannelTreandData(accountH5Dto);
			modelMap.put("trendList", trendList);
		}
		modelMap.put("collData", colldata);
		modelMap.put("channelList", channelList);
		modelMap.put("beginDate", start);
		modelMap.put("endDate", end);
		modelMap.put("level", level);
		modelMap.put("channelType", channelType);
		return new ModelAndView(detailView).addAllObjects(modelMap);
	}

	
	
	/**
	 * 下载数据
	 * @param request
	 * @param response
	 * @param h5Command
	 */
	@SuppressWarnings("unchecked")
	public void download(HttpServletRequest request,
			HttpServletResponse response, ChannelEventAccountH5Command h5Command) {
		String currentParam = request.getParameter("currentParam");
		Map<String, Object> modelMap = new HashMap<String, Object>(0);
		Calendar now = Calendar.getInstance();
		now.set(Calendar.SECOND, 0);//秒归零
		String showtime = DateUtils.getCurrent5Min(now);
		modelMap.put("time", showtime);
		String today = DateUtils.getFormatedDate(now.getTime(), DateUtils.FORMAT_D_YYYYMMDD);
		String beginTime = today +" 00:05";
		String endTime=today +" "+showtime;
		ChannelEventAccountH5Dto accountH5Dto = new ChannelEventAccountH5Dto();
		BeanUtils.copyProperties(h5Command, accountH5Dto);
		accountH5Dto.setBeginTime(beginTime);
		accountH5Dto.setEndTime(endTime);
		//趋势明细数据
		List<H5ActivityCollDataMapping> dataDetailList = activityH5Service.queryChannelTrendDataList(accountH5Dto);
		List<H5ActivityCollDataMapping> dataListComp=null;
		//对比数据
		String compare = ServletRequestUtils.getStringParameter(request,"compare", "");
		String compareDate=null;
		if(StringUtils.isNotBlank(compare)){
			compareDate =request.getParameter("compare_date");
			Date endDateCom = DateUtils.getDate(compareDate, 1);
			String compareDateEnd  = DateUtils.getFormatedDate(endDateCom,DateUtils.FORMAT_D_YYYYMMDD);
			String beginCpmpare = compareDate+" 00:05";
			accountH5Dto.setBeginTime(beginCpmpare);
			accountH5Dto.setEndTime(compareDateEnd);
			dataListComp = activityH5Service.queryChannelTrendDataList(accountH5Dto);
		}
		
		Collection<ChannelEventAccount>[] dataSet = null;
		if(dataListComp==null){
			dataSet = new Collection[]{dataDetailList};
		}else{
			dataSet = new Collection[]{dataDetailList,dataListComp};
		}
		String[] heads = ExportExcel.activityHeads;
		String rootpath = request.getSession().getServletContext().getRealPath("/");
		File dataFile = new File(rootpath + "resources/data_detail.xls");
		ExportExcel<ChannelEventAccount> ex = new ExportExcel<ChannelEventAccount>();
		String[] title = new String[]{currentParam,currentParam+"_"+compareDate};
		ex.exportExcel(title, heads, heads, dataSet, dataFile, "","");
		String fileName = currentParam + ".xls";
		FileUtil.down(dataFile, fileName, response);
	
	}
	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public AnalysisDataToolService getAnalysisDataToolService() {
		return analysisDataToolService;
	}

	public void setAnalysisDataToolService(
			AnalysisDataToolService analysisDataToolService) {
		this.analysisDataToolService = analysisDataToolService;
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

	public String getGraphView() {
		return graphView;
	}

	public void setGraphView(String graphView) {
		this.graphView = graphView;
	}

	public String getDetailView() {
		return detailView;
	}

	public void setDetailView(String detailView) {
		this.detailView = detailView;
	}

	public ActivityH5Service getActivityH5Service() {
		return activityH5Service;
	}

	public void setActivityH5Service(ActivityH5Service activityH5Service) {
		this.activityH5Service = activityH5Service;
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

	public AnalysisHowbuyWebsiteService getAnalysisHowbuyWebsiteService() {
		return analysisHowbuyWebsiteService;
	}

	public void setAnalysisHowbuyWebsiteService(
			AnalysisHowbuyWebsiteService analysisHowbuyWebsiteService) {
		this.analysisHowbuyWebsiteService = analysisHowbuyWebsiteService;
	}

}