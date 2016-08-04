/**
 * 
 */
package com.howbuy.uaa.dao;

import java.util.List;

import com.howbuy.uaa.dto.ChannelEventAccountDto;
import com.howbuy.uaa.persistence.ChannelEventAccount;

/**
 * @author qiankun.li
 *
 */
public interface ChannelEventAccountDao {

	/**查询用户事件数据
	 * @param accountDto
	 * @return
	 */
	List<ChannelEventAccount> queryChannelEventAccountList(ChannelEventAccountDto accountDto);
	
	/**
	 * 查询所有
	 * @param accountDto
	 * @return
	 */
	List<ChannelEventAccount> queryChannelEventAccountAllList(ChannelEventAccountDto accountDto);
	
	/**
	 * 查询所有汇总数据
	 * @param accountDto
	 * @return
	 */
	ChannelEventAccount getChannelEventAccountAll(ChannelEventAccountDto accountDto);
	
	/**查询平台数据按照时间汇总明细
	 * @return
	 */
	List<ChannelEventAccount> queryChannelEventAccountAllDeatil(ChannelEventAccountDto accountDto);
	
}
