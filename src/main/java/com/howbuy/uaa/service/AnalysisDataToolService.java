package com.howbuy.uaa.service;

import java.util.List;

import com.howbuy.uaa.command.RouteManageCommand;
import com.howbuy.uaa.dao.AnalysisDataToolDao;
import com.howbuy.uaa.dto.RouteDetailDto;

public class AnalysisDataToolService {
	private AnalysisDataToolDao analysisDataToolDao;
	
	/**
	 * @param level
	 * 
	 * @return 返回所有该级渠道信息
	 */
	public List<RouteManageCommand> queryAllRoute(int level)
	{
		return analysisDataToolDao.queryAllRoute(level);
	}
	
	public AnalysisDataToolDao getAnalysisDataToolDao() {
		return analysisDataToolDao;
	}

	public void setAnalysisDataToolDao(AnalysisDataToolDao analysisDataToolDao) {
		this.analysisDataToolDao = analysisDataToolDao;
	}

	/**按照级别 类型 父渠道查询渠道
	 * @param level
	 * @param channelType 1:网站推广 2:无线推广
	 * @param parent
	 * @return
	 */
	public List<RouteManageCommand> queryAllRoute(int level,int channelType,String parent)
	{
		return analysisDataToolDao.queryAllRoute(level,channelType,parent);
	}
	/**
	 * 
	 * @param level 渠道等级
	 * 		  parent 父级id
	 * @return 个数
	 */
	public int queryCountAllRoute(int level) {
		return analysisDataToolDao.queryCountAllRoute(level);
	}
	public int queryCountAllRoute(int level,String parent) {
		return analysisDataToolDao.queryCountAllRoute(level,parent);
	}
	/**
	 * 
	 * @param ri 插入渠道信息
	 */
	public int insertRoute(RouteManageCommand ri)
	{
		return analysisDataToolDao.insertRoute(ri);
	}
	/**
	 * 向route_info_detail插数据
	 * @param htag 
	 * @param id 三级渠道id
	 */
	public void insertRouteDetail(String routeDetail,String htag,int id)
	{
		analysisDataToolDao.insertRouteDetail(routeDetail,htag,id);
	}
	/**
	 * 
	 * @param ri 修改渠道信息
	 */
	public void changeRoute(RouteManageCommand ri)
	{
		analysisDataToolDao.changeRoute(ri);
	}
	/**
	 * 返回该三级渠道的详细信息
	 * @param id
	 * @return
	 */
	public RouteDetailDto queryRouteDetail(String id)
	{
		return analysisDataToolDao.queryRouteDetail(id);
	}
	
	/**
	 * 根据一级二级三级qid获取routedetail
	 * @return
	 */
	public RouteDetailDto queryRouteDetail(String id1,String id2,String id3)
	{
		
		return analysisDataToolDao.queryRouteDetail(id1,id2,id3);
	}
	
	
	
	public AnalysisDataToolDao getAnalysisConsoleDao() {
		return analysisDataToolDao;
	}

	public void setAnalysisConsoleDao(AnalysisDataToolDao analysisDataToolDao) {
		this.analysisDataToolDao = analysisDataToolDao;
	}
	
	
	
}
