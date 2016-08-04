package com.howbuy.uaa.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.howbuy.rdb.database.dao.impl.BaseDaoImpl;
import com.howbuy.uaa.command.RouteManageCommand;
import com.howbuy.uaa.dao.AnalysisDataToolDao;
import com.howbuy.uaa.dto.RouteDetailDto;

public class AnalysisDataToolDaoImpl extends BaseDaoImpl implements AnalysisDataToolDao {

	
	@SuppressWarnings("unchecked")
	@Override
	public List<RouteManageCommand> queryAllRoute(int level) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("level", level);
		return getSqlMapClientTemplate().queryForList("AnalysisDataTool.queryAllRoute", params);
	}
	
	
	@Override
	public List<RouteManageCommand> queryAllRoute(int level,int channelType,String parent) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("level", level);
		params.put("parent", parent);
		params.put("channelType",channelType);
		return getSqlMapClientTemplate().queryForList("AnalysisDataTool.queryAllRoute", params);
	}
	
	
	@Override
	public int queryCountAllRoute(int level) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("level", level);
		return (Integer) getSqlMapClientTemplate().queryForObject("AnalysisDataTool.queryCountAllRoute", params);
	}


	@Override
	public int queryCountAllRoute(int level, String parent) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("level", level);
		params.put("parent", parent);
		return (Integer) getSqlMapClientTemplate().queryForObject("AnalysisDataTool.queryCountAllRoute", params);
	}


	@Override
	public int insertRoute(RouteManageCommand ri){
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("level", ri.getLevel());
		params.put("routeName", ri.getRouteName());
		params.put("parentId", ri.getParentId());
		params.put("createDate", ri.getCreateDate());
		params.put("Account", ri.getAccount());
		params.put("qid", ri.getQid());
		params.put("tagCode", ri.getTagCode());
		params.put("channelType", ri.getChannelType());
		return (Integer) getSqlMapClientTemplate().insert("AnalysisDataTool.insertRoute", params);
	}


	@Override
	public void insertRouteDetail(String routeDetail,String htag, int id) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("routeDetail", routeDetail);
		params.put("htag", htag);
		params.put("id", id);
		getSqlMapClientTemplate().update("AnalysisDataTool.insertRouteDetail", params);
	}


	@Override
	public void changeRoute(RouteManageCommand ri) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("routeName", ri.getRouteName());
		params.put("id", ri.getId());
		params.put("createDate", ri.getCreateDate());
		params.put("Account", ri.getAccount());
		getSqlMapClientTemplate().update("AnalysisDataTool.changeRoute", params);
	}


	@Override
	public RouteDetailDto queryRouteDetail(String id) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		return (RouteDetailDto) getSqlMapClientTemplate().queryForObject("AnalysisDataTool.queryRouteDetail", params);
	}


	@Override
	public RouteDetailDto queryRouteDetail(String id1, String id2, String id3) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("id1", id1);
		params.put("id2", id2);
		params.put("id3", id3);
		return (RouteDetailDto) getSqlMapClientTemplate().queryForObject("AnalysisDataTool.queryRouteDetail1", params);
	}


}
