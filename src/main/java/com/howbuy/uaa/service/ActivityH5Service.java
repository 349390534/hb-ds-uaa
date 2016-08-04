/**
 * 
 */
package com.howbuy.uaa.service;

import java.util.ArrayList;
import java.util.List;

import com.howbuy.uaa.dao.ChannelEventAccountH5Dao;
import com.howbuy.uaa.dto.ChannelEventAccountH5Dto;
import com.howbuy.uaa.dto.H5ActivityCollDataMapping;
import com.howbuy.uaa.dto.H5ActivityDataMapping;

/**
 * @author qiankun.li 活动H5service
 */
public class ActivityH5Service {

	private ChannelEventAccountH5Dao accountH5Dao;
	
	
	/**
	 * 获取当日汇总数据
	 * @param accountH5Dto
	 * @return
	 */
	public H5ActivityCollDataMapping queryH5ActivityCollData(ChannelEventAccountH5Dto accountH5Dto){
		H5ActivityCollDataMapping collData = new H5ActivityCollDataMapping();
		collData=accountH5Dao.getH5ActivityCollData(accountH5Dto);
		return collData;
	}
	
	/**
	 * 获取渠道数据当天
	 * @return
	 */
	public List<H5ActivityCollDataMapping> queryH5ActivityChannelDataList(ChannelEventAccountH5Dto accountH5Dto){
		List<H5ActivityCollDataMapping> channelList = new ArrayList<H5ActivityCollDataMapping>(0);
		channelList=accountH5Dao.queryH5ActivityChannelDataList(accountH5Dto);
		return channelList;
	}
	
	/**
	 * 获取渠道数据历史
	 * @return
	 */
	public List<H5ActivityCollDataMapping> queryH5ActivityChannelDataHisList(ChannelEventAccountH5Dto accountH5Dto){
		List<H5ActivityCollDataMapping> channelList = new ArrayList<H5ActivityCollDataMapping>(0);
		channelList=accountH5Dao.queryH5ActivityChannelDataHisList(accountH5Dto);
		return channelList;
	}
	
	/**
	 * 获取渠道按照时间分组明细数据
	 * @param accountH5Dto
	 * @return
	 */
	public List<H5ActivityDataMapping> queryChannelDetailDataList(ChannelEventAccountH5Dto accountH5Dto){
		List<H5ActivityDataMapping> channelList = new ArrayList<H5ActivityDataMapping>(0);
		channelList = accountH5Dao.queryChannelDetailDataList(accountH5Dto);
		return channelList;
	}
	
	/**当日按照时间汇总数据
	 * @param accountH5Dto
	 * @return
	 */
	public List<H5ActivityCollDataMapping> queryChannelTrendDataList(
			ChannelEventAccountH5Dto accountH5Dto) {
		List<H5ActivityCollDataMapping> channelList = new ArrayList<H5ActivityCollDataMapping>(0);
		channelList = accountH5Dao.queryChannelTrendDataList(accountH5Dto);
		return channelList;
	}
	
	/**
	 * 查询趋势明细数据
	 * @param accountH5Dto
	 * @return
	 */
	public List<H5ActivityDataMapping> queryChannelTreandData(ChannelEventAccountH5Dto accountH5Dto){
		List<H5ActivityDataMapping> channelList = new ArrayList<H5ActivityDataMapping>(0);
		channelList=accountH5Dao.queryTrendDataList(accountH5Dto);
		return channelList;
	}
	
	public H5ActivityCollDataMapping getH5ActivityCollHisData(
			ChannelEventAccountH5Dto accountH5Dto) {
		H5ActivityCollDataMapping dataColl = new H5ActivityCollDataMapping();
		dataColl  = accountH5Dao.getH5ActivityCollHisData(accountH5Dto);
		return dataColl;
	}
	
	
	/**
	 * 查询历史渠道日期趋势数据
	 * @param accountH5Dto
	 * @return
	 */
	public List<H5ActivityDataMapping> queryHisChannelTrendDataList(ChannelEventAccountH5Dto accountH5Dto){
		List<H5ActivityDataMapping> channelList = new ArrayList<H5ActivityDataMapping>(0);
		channelList=accountH5Dao.queryChannelTrendDataHisList(accountH5Dto);
		return channelList;
	}
	
	
	public void setAccountH5Dao(ChannelEventAccountH5Dao accountH5Dao) {
		this.accountH5Dao = accountH5Dao;
	}

}