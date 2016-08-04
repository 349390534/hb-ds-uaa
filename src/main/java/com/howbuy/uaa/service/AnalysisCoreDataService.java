package com.howbuy.uaa.service;

import java.util.List;

import com.howbuy.uaa.dao.AnalysisCoreDataDao;
import com.howbuy.uaa.dto.RouteDetailDto;

public class AnalysisCoreDataService {
	private AnalysisCoreDataDao analysisCoreDataDao;

	public List<RouteDetailDto> queryListTrendDetail(int sr, int rows, String orderby,String order){
		return analysisCoreDataDao.queryListTrendDetail(sr, rows, orderby, order);
	}
	
	public int queryRowsOfTrendDetail(){
		return analysisCoreDataDao.queryRowsOfTrendDetail();
	}

	
	public AnalysisCoreDataDao getAnalysisCoreDataDao() {
		return analysisCoreDataDao;
	}

	public void setAnalysisCoreDataDao(AnalysisCoreDataDao analysisCoreDataDao) {
		this.analysisCoreDataDao = analysisCoreDataDao;
	}
	
	
	
}
