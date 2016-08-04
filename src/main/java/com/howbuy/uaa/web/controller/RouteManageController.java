package com.howbuy.uaa.web.controller;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.howbuy.common.util.NumberUtil;
import com.howbuy.uaa.command.RouteManageCommand;
import com.howbuy.uaa.dto.RouteDetailDto;
import com.howbuy.uaa.service.AnalysisDataToolService;
import com.howbuy.uaa.utils.RouteDataUtil;

public class RouteManageController extends MultiActionController {
	private AnalysisDataToolService analysisDataToolService;
	
	private String index;
	private String routeLevelOne;
	private String routeLevelTwo;
	private String routeLevelThree;
	private String routeDetail;
	
	
	/**
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView index(HttpServletRequest request,HttpServletResponse response)
	{
		List<RouteManageCommand> list = analysisDataToolService.queryAllRoute(1);
//		String navId = request.getParameter("navId");
//		String pageId = request.getParameter("pageId");
//		Map<String,Object> map = new HashMap<String,Object>();
//		map.put("navId", navId);
//		map.put("pageId", pageId);
		return new ModelAndView(index)
		.addObject("option1", list);

	}
	
	/**
	 * @param request
	 * @param response
	 * @param ri
	 * @return
	 */
	public ModelAndView add(HttpServletRequest request,HttpServletResponse response,RouteManageCommand ri) {
		int level = ri.getLevel();
		int oneQid = ri.getOneQid();
		int twoQid = ri.getTwoQid();
		String parent = ri.getParentId();
		
		ri.setCreateDate(new Date());
		//route_info 数据插入 并获取id
		int id;
		int cnt;
		String tag = null;
		if(level == 1)
		{
			cnt = analysisDataToolService.queryCountAllRoute(level);
			oneQid = cnt+1;
		}
		else{
			cnt = analysisDataToolService.queryCountAllRoute(level, parent);
		}
		String qid = String.valueOf(cnt+1);
		ri.setQid(qid);
		tag = RouteDataUtil.formatQid(level, cnt+1);
		if(level == 2){
			String onetag = RouteDataUtil.formatQid(1, oneQid);
			tag = onetag + tag;
		}else if(level ==3 ){
			String onetag = RouteDataUtil.formatQid(1, oneQid);
			String twotag = RouteDataUtil.formatQid(2, twoQid);
			
			tag = onetag +twotag+ tag;
		}
		ri.setTagCode(tag);
		id = analysisDataToolService.insertRoute(ri);
		
		//route_info_detail 数据插入
		if(level == 3){
			String routeinfo = ri.getRouteDetail()+ri.getRouteName();
			//String parentoftwo = ri.getParentOfTwo();
			
//			DecimalFormat df = new DecimalFormat("000");
			DecimalFormat df1 = new DecimalFormat("0000");
			String htag = "0." + ri.getHtag()+df1.format(cnt+1)+"900000";
			analysisDataToolService.insertRouteDetail(routeinfo,htag,id);
		}
			
		List<RouteManageCommand> list = analysisDataToolService.queryAllRoute(level,0,parent);
		if(level == 1)	
			return new ModelAndView(routeLevelOne)
			.addObject("option1", list);
		else if(level == 2) 
		{
			return new ModelAndView(routeLevelTwo)
			.addObject("option2", list);
		}
		else {
			return new ModelAndView(routeLevelThree)
			.addObject("option3", list);
		}
	}
	
	/**修改
	 * @param request
	 * @param response
	 * @param ri
	 * @return
	 */
	public ModelAndView change(HttpServletRequest request,HttpServletResponse response,RouteManageCommand ri) {
		int level = ri.getLevel();
		String parent = ri.getParentId();
		ri.setCreateDate(new Date());
		analysisDataToolService.changeRoute(ri);
		List<RouteManageCommand> list = analysisDataToolService.queryAllRoute(level,1,parent);
		if(level == 1)
			return new ModelAndView(routeLevelOne)
			.addObject("option1", list);
		else if(level == 2) 
		{
			return new ModelAndView(routeLevelTwo)
			.addObject("option2", list);
		}
		else {
			return new ModelAndView(routeLevelThree)
			.addObject("option3", list);
		}
	}
	/*
	 * 初始化渠道列表
	 */
	public ModelAndView initailQd(HttpServletRequest request,HttpServletResponse response,RouteManageCommand ri)
	{
		int level = ri.getLevel();
		String parent = ri.getParentId();
		String channelType = ri.getChannelType();
		int chType = 1;// 默认为网站
		if (StringUtils.isNotBlank(channelType)
				&& NumberUtil.isInteger(channelType)) {
			chType = Integer.valueOf(channelType);
		}
		List<RouteManageCommand> list = analysisDataToolService.queryAllRoute(level,chType,parent);
		if(level == 2)
		{
			return new ModelAndView(routeLevelTwo)
			.addObject("option2", list);
		}
		else if (level == 3) {
			return new ModelAndView(routeLevelThree)
			.addObject("option3", list);
		}
		else
		{
			return new ModelAndView(routeLevelOne)
			.addObject("option1", list);
		}
	}
	
	/*
	 * 获取渠道detail 信息
	 */
	public ModelAndView routeDetail(HttpServletRequest request,HttpServletResponse response,RouteManageCommand ri)
	{
		String id = ri.getId();
		RouteDetailDto detail = analysisDataToolService.queryRouteDetail(id);
		return new ModelAndView(routeDetail)
		.addObject("detail", detail);
	}
	
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getRouteLevelOne() {
		return routeLevelOne;
	}
	public void setRouteLevelOne(String routeLevelOne) {
		this.routeLevelOne = routeLevelOne;
	}
	public String getRouteLevelTwo() {
		return routeLevelTwo;
	}
	public void setRouteLevelTwo(String routeLevelTwo) {
		this.routeLevelTwo = routeLevelTwo;
	}
	public String getRouteLevelThree() {
		return routeLevelThree;
	}
	public void setRouteLevelThree(String routeLevelThree) {
		this.routeLevelThree = routeLevelThree;
	}
	public String getRouteDetail() {
		return routeDetail;
	}
	public void setRouteDetail(String routeDetail) {
		this.routeDetail = routeDetail;
	}
	
	
	public AnalysisDataToolService getAnalysisDataToolService() {
		return analysisDataToolService;
	}
	public void setAnalysisDataToolService(AnalysisDataToolService analysisDataToolService) {
		this.analysisDataToolService = analysisDataToolService;
	}
	
		
}
