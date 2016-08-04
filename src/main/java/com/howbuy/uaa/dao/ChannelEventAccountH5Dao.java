/**
 * 
 */
package com.howbuy.uaa.dao;

import java.util.List;

import com.howbuy.uaa.dto.ChannelEventAccountH5Dto;
import com.howbuy.uaa.dto.H5ActivityCollDataMapping;
import com.howbuy.uaa.dto.H5ActivityDataMapping;


/**
 * @author qiankun.li
 * 活动表dao
 */
public interface ChannelEventAccountH5Dao {
	
	/**获取当日汇总数据
	 * @param accountH5Dto
	 * @return
	 */
	H5ActivityCollDataMapping getH5ActivityCollData(ChannelEventAccountH5Dto accountH5Dto);
	
	/**查询当日渠道明细数据
	 * @param accountH5Dto
	 * @return
	 */
	List<H5ActivityCollDataMapping> queryH5ActivityChannelDataList(ChannelEventAccountH5Dto accountH5Dto);
	
	/**获取当日汇总数据
	 * @param accountH5Dto
	 * @return
	 */
	H5ActivityCollDataMapping getH5ActivityCollHisData(ChannelEventAccountH5Dto accountH5Dto);
	
	/**
	 * 查询历史渠道数据
	 * @param accountH5Dto
	 * @return
	 */
	List<H5ActivityCollDataMapping> queryH5ActivityChannelDataHisList(ChannelEventAccountH5Dto accountH5Dto);
	
	/**
	 * 查询渠道按日期分组明细数据
	 * @param accountH5Dto
	 * @return
	 */
	List<H5ActivityDataMapping> queryChannelDetailDataList(ChannelEventAccountH5Dto accountH5Dto);
	
	/**
	 * 查询当日按照时间数据明细
	 * @param accountH5Dto
	 * @return
	 */
	List<H5ActivityCollDataMapping> queryChannelTrendDataList(ChannelEventAccountH5Dto accountH5Dto);
	
	
	/**
	 * 查询趋势明细
	 * @param accountH5Dto
	 * @return
	 */
	List<H5ActivityDataMapping> queryTrendDataList(ChannelEventAccountH5Dto accountH5Dto);
	
	/**
	 * 查询历史渠道日期趋势数据
	 * @param accountH5Dto
	 * @return
	 */
	List<H5ActivityDataMapping> queryChannelTrendDataHisList(ChannelEventAccountH5Dto accountH5Dto);
}
