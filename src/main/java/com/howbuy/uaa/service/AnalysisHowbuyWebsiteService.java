package com.howbuy.uaa.service;

import java.util.List;

import com.howbuy.uaa.common.enums.HowbuyWebsiteChannelType;
import com.howbuy.uaa.dao.AnalysisHowbuyWebsiteDao;
import com.howbuy.uaa.dto.HowbuyWebsiteCollDataMapping;
import com.howbuy.uaa.dto.HowbuyWebsiteData;
import com.howbuy.uaa.dto.HowbuyWebsiteDataMapping;
import com.howbuy.uaa.dto.HowbuyWebsiteFormData;
import com.howbuy.uaa.dto.RouteDetailDto;
import com.howbuy.uaa.persistence.HowbuyRootChannel;
import com.howbuy.uaa.persistence.ZeroChannelTag;

/**
 * @author qiankun.li
 * 好买网站数据查询
 */
public class AnalysisHowbuyWebsiteService {
	private AnalysisHowbuyWebsiteDao analysisHowbuyWebsiteDao;

	
	public List<RouteDetailDto> queryListTrendDetail(int sr, int rows, String orderby,String order){
		return analysisHowbuyWebsiteDao.queryListTrendDetail(sr, rows, orderby, order);
	}
	
	public int queryRowsOfTrendDetail(){
		return analysisHowbuyWebsiteDao.queryRowsOfTrendDetail();
	}
	
	
	/**
	 * 查询所有根渠道
	 * @return
	 */
	public List<HowbuyRootChannel> queryHowbuyRootChannelList(){
		return analysisHowbuyWebsiteDao.queryHowbuyRootChannelList();
	}
	
	/**
	 * 查询所有搜索引擎渠道
	 * @return
	 */
	public List<ZeroChannelTag> queryZeroChannelTagList(){
		return analysisHowbuyWebsiteDao.queryChannelTagList();
	}
	

	/**
	 * 查询好买网站当前渠道数据明细
	 * @param websiteFormData
	 * @return
	 */
	public HowbuyWebsiteData queryHowbuyWebsiteCollData(final HowbuyWebsiteFormData websiteFormData){
		HowbuyWebsiteData websiteData = null;
		if(null!=websiteFormData){
			websiteData = new HowbuyWebsiteData();
			//汇总数据
			List<HowbuyWebsiteCollDataMapping> collDataMappings = analysisHowbuyWebsiteDao.queryChannelCustCollData(websiteFormData);
			websiteData.setColl(collDataMappings);
			//查询明细数据
			List<HowbuyWebsiteDataMapping> dataMappings = analysisHowbuyWebsiteDao.queryChannelTrendDataDetail(websiteFormData);
			websiteData.setList(dataMappings);
			Integer curPage = websiteFormData.getCurPage();
			Integer pageRows=websiteFormData.getPageRows();
			if(null!=pageRows && curPage!=null){
				Integer pageCount = analysisHowbuyWebsiteDao.queryTrendDataCount(websiteFormData);
				websiteData.setPageCount(pageCount);
				websiteData.setPageIndex(curPage);
				websiteData.setPageSize(pageRows);
				int pages = (pageCount/pageRows) + ((pageCount%pageRows)>0?1:0);
				websiteData.setPages(pages);
			}
		}
		return websiteData;
	}
	
	
	
	/**
	 * 查询好买网站下级渠道数据明细
	 * @param websiteFormData
	 * @return
	 */
	public HowbuyWebsiteData queryHowbuyWebsiteDataDetail(final HowbuyWebsiteFormData websiteFormData){
		HowbuyWebsiteData websiteData = null;
		if(null!=websiteFormData){	
			websiteData = new HowbuyWebsiteData();
			String rootChannelType = websiteFormData.getChannelType();
			HowbuyWebsiteChannelType channelType = HowbuyWebsiteChannelType.findByType(rootChannelType);
			if(null == channelType){
				//查询根级渠道明细数据
				List<HowbuyWebsiteDataMapping> dataMappings = analysisHowbuyWebsiteDao.queryRootChannelLineDataDetail(websiteFormData);
				websiteData.setList(dataMappings);
				//查询汇总数据
				List<HowbuyWebsiteCollDataMapping> collDataMappings=analysisHowbuyWebsiteDao.queryAllRootChannelCollData(websiteFormData);
				websiteData.setColl(collDataMappings);
			}else{
				List<HowbuyWebsiteDataMapping> datalist = analysisHowbuyWebsiteDao.queryChannelLineDataDetail(websiteFormData);
				websiteData.setList(datalist);
				
				List<HowbuyWebsiteCollDataMapping> dataMappings=analysisHowbuyWebsiteDao.queryChannelCollData(websiteFormData);
				websiteData.setColl(dataMappings);
			}
			
		}
		return websiteData;
	}
	
	
	
	public AnalysisHowbuyWebsiteDao getAnalysisHowbuyWebsiteDao() {
		return analysisHowbuyWebsiteDao;
	}

	public void setAnalysisHowbuyWebsiteDao(AnalysisHowbuyWebsiteDao analysisHowbuyWebsiteDao) {
		this.analysisHowbuyWebsiteDao = analysisHowbuyWebsiteDao;
	}
	
}
