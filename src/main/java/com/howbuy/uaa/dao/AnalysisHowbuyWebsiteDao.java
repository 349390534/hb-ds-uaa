package com.howbuy.uaa.dao;

import java.util.List;

import com.howbuy.uaa.dto.HowbuyWebsiteCollDataMapping;
import com.howbuy.uaa.dto.HowbuyWebsiteDataMapping;
import com.howbuy.uaa.dto.HowbuyWebsiteFormData;
import com.howbuy.uaa.dto.RouteDetailDto;
import com.howbuy.uaa.persistence.HowbuyRootChannel;
import com.howbuy.uaa.persistence.ZeroChannelTag;

/**
 * @author qiankun.li
 * 好买网站数据
 */
public interface AnalysisHowbuyWebsiteDao {
	
	public List<RouteDetailDto> queryListTrendDetail(int sr,int er,String orderby,String order);
	
	public int queryRowsOfTrendDetail();
	
	/**
	 * 查询所有根渠道
	 * @return
	 */
	List<HowbuyRootChannel> queryHowbuyRootChannelList();
	
	/**
	 * 查询所有的搜索渠道列表
	 * @return
	 */
	List<ZeroChannelTag> queryChannelTagList();
	
	
	/**
	 * 获取所有根渠道汇总数据
	 * @return
	 */
	List<HowbuyWebsiteCollDataMapping> queryAllRootChannelCollData(HowbuyWebsiteFormData websiteFormData);
	
	
	/**
	 * 查询渠道明细数汇总数据
	 * @param websiteFormData
	 * @return
	 */
	List<HowbuyWebsiteCollDataMapping> queryChannelCollData(HowbuyWebsiteFormData websiteFormData);
	
	/**
	 * 查询渠道明细数汇总数据
	 * @param websiteFormData
	 * @return
	 */
	List<HowbuyWebsiteCollDataMapping> queryChannelCustCollData(HowbuyWebsiteFormData websiteFormData);
	
	/**
	 * 查询渠道趋势明细数据
	 * @param websiteFormData
	 * @return
	 */
	List<HowbuyWebsiteDataMapping> queryChannelTrendDataDetail(HowbuyWebsiteFormData websiteFormData);
	
	/**查询渠道趋势明细数据汇总
	 * @param websiteFormData
	 * @return
	 */
	Integer queryTrendDataCount(HowbuyWebsiteFormData websiteFormData);
	
	/**
	 * 查询渠道趋势明细数据
	 * @param websiteFormData
	 * @return
	 */
	List<HowbuyWebsiteDataMapping> queryRootChannelLineDataDetail(HowbuyWebsiteFormData websiteFormData);
	/**
	 * 查询渠道趋势明细数据
	 * @param websiteFormData
	 * @return
	 */
	List<HowbuyWebsiteDataMapping> queryChannelLineDataDetail(HowbuyWebsiteFormData websiteFormData);
	
	
}
