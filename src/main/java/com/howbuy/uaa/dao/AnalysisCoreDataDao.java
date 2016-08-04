package com.howbuy.uaa.dao;

import java.util.List;

import com.howbuy.uaa.dto.RouteDetailDto;

public interface AnalysisCoreDataDao {
	/*
	 * trendDetail
	 */
	public List<RouteDetailDto> queryListTrendDetail(int sr,int er,String orderby,String order);
	
	public int queryRowsOfTrendDetail();
}
