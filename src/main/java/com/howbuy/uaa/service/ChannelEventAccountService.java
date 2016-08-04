/**
 * 
 */
package com.howbuy.uaa.service;

import java.util.List;

import com.howbuy.uaa.dao.ChannelEventAccountDao;
import com.howbuy.uaa.dto.ChannelEventAccountDto;
import com.howbuy.uaa.persistence.ChannelEventAccount;

/**
 * @author qiankun.li
 *
 */
public class ChannelEventAccountService {

	private ChannelEventAccountDao channelEventAccountDao;

	/**查询平台下的网点明细数据
	 * @param accountDto
	 * @return
	 */
	public List<ChannelEventAccount> queryChannelEventAccountList(ChannelEventAccountDto accountDto){
		List<ChannelEventAccount> result = null;
		if(null!=accountDto){
			result = channelEventAccountDao.queryChannelEventAccountList(accountDto);
		}
		return result;
	}
	
	
	/**
	 * 查询平台数据按照iPhone和安卓分别展示
	 * @param accountDto
	 * @return
	 */
	public List<ChannelEventAccount> queryChannelEventAccountListAll(ChannelEventAccountDto accountDto){
		List<ChannelEventAccount> result = null;
		if(null!=accountDto){
			result = channelEventAccountDao.queryChannelEventAccountAllList(accountDto);
		}
		return result;
	}
	
	
	/**
	 * 获取汇总数据
	 * @param accountDto
	 * @return
	 */
	public ChannelEventAccount getDataCount(ChannelEventAccountDto accountDto){
		ChannelEventAccount account = channelEventAccountDao.getChannelEventAccountAll(accountDto);
		account = account==null?new ChannelEventAccount():account;
		return account;
	}
	
	/**
	 * 查询平台数据按照iPhone和安卓汇总展示
	 * @param accountDto
	 * @return
	 */
	public List<ChannelEventAccount> queryChannelEventAccountListDetailiAll(ChannelEventAccountDto accountDto){
		List<ChannelEventAccount> result = null;
		if(null!=accountDto){
			result = channelEventAccountDao.queryChannelEventAccountAllDeatil(accountDto);
		}
		return result;
	}
	
	
	
	public ChannelEventAccountDao getChannelEventAccountDao() {
		return channelEventAccountDao;
	}

	public void setChannelEventAccountDao(
			ChannelEventAccountDao channelEventAccountDao) {
		this.channelEventAccountDao = channelEventAccountDao;
	}
	
	
	
}
