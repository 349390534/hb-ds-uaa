package com.howbuy.uaa.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.howbuy.rdb.database.dao.impl.BaseDaoImpl;
import com.howbuy.uaa.dao.AnalysisCoreDataDao;
import com.howbuy.uaa.dao.AnalysisDataToolDao;
import com.howbuy.uaa.dto.RouteDetailDto;

public class AnalysisCoreDataDaoImpl extends BaseDaoImpl implements AnalysisCoreDataDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<RouteDetailDto> queryListTrendDetail(int sr, int rows, String orderby,String order) {
		// TODO Auto-generated method stub
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("startrow", sr);
		params.put("rows",rows);
		params.put("orderbyfd", orderby);
		params.put("orderfd", order);
		return getSqlMapClientTemplate().queryForList("AnalysisDataTool.queryListTrendDetail", params);
	
	}


	@Override
	public int queryRowsOfTrendDetail() {
		// TODO Auto-generated method stub
		return (Integer) getSqlMapClientTemplate().queryForObject("AnalysisDataTool.queryRowsOfTrendDetail");
	}
	
}
