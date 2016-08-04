package com.howbuy.uaa.dao;

import java.util.List;

import com.howbuy.uaa.command.RouteManageCommand;
import com.howbuy.uaa.dto.RouteDetailDto;


public interface AnalysisDataToolDao {
	/*
	 * 查询该level下的所有渠道
	 */
	public List<RouteManageCommand> queryAllRoute(int level);
	
	public List<RouteManageCommand> queryAllRoute(int level,int channelType,String parent); 
	
	
	/*
	 * 查询该level下，父节点为parent 的渠道个数
	 */
	public int queryCountAllRoute(int level);
	public int queryCountAllRoute(int level,String parent);
	
	
	/*
	 * 向表route_info插入数据
	 */
	public int insertRoute(RouteManageCommand ri);
	
	
	/*
	 * 向route_info_detail插入数据
	 */
	public void insertRouteDetail(String routeDetail,String htag,int id);
	
	
	/*
	 * 修改route_info数据
	 */
	public void changeRoute(RouteManageCommand ri);
	
	
	/*
	 * 获取该三级渠道的详细信息根据三级渠道id
	 */
	public RouteDetailDto queryRouteDetail(String id);
	
	/*
	 * 根据一级二级三级qid获取详细信息
	 */
	public RouteDetailDto queryRouteDetail(String id1,String id2,String id3);
	
	
}
