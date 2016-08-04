/**
 * 
 */
package com.howbuy.uaa.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.howbuy.rdb.database.dao.impl.BaseDaoImpl;
import com.howbuy.uaa.dao.ChannelEventAccountDao;
import com.howbuy.uaa.dto.ChannelEventAccountDto;
import com.howbuy.uaa.persistence.ChannelEventAccount;

/**
 * @author qiankun.li
 * 
 */
public class ChannelEventAccountDaoImpl extends BaseDaoImpl implements
		ChannelEventAccountDao {
	private static Logger logger = LoggerFactory.getLogger(ChannelEventAccountDaoImpl.class);

	private static final String NS = "wireless.";

	@SuppressWarnings("unchecked")
	private <T> List<T> queryForList(String statementName, Object obj) {
		List<T> result = getSqlMapClientTemplate().queryForList(NS+statementName,obj);
		return result;
	}
	
	@SuppressWarnings("unchecked")
	private <T> T queryForObject(String statementName, Object obj) {
		T result =(T) getSqlMapClientTemplate().queryForObject(NS+statementName,obj);
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.howbuy.uaa.dao.ChannelEventAccountDao#queryChannelEventAccountList
	 * (com.howbuy.uaa.dto.ChannelEventAccountDto)
	 */
	@Override
	public List<ChannelEventAccount> queryChannelEventAccountList(
			ChannelEventAccountDto accountDto) {
		logger.debug("enter queryChannelEventAccountList.");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String proid = accountDto.getProid();
		if(StringUtils.isNotBlank(proid)){
			String[] ps  = proid.split(",");
			paramMap.put("proid", ps);
		}
		paramMap.put("outletcode", accountDto.getOutletcode());
		paramMap.put("startTime", accountDto.getBeginTime());
		paramMap.put("endTime", accountDto.getEndTime());
		String statementName ="selectEventAccount";
		List<ChannelEventAccount>  list = queryForList(statementName, paramMap);
		return list;
	}

	@Override
	public List<ChannelEventAccount> queryChannelEventAccountAllList(
			ChannelEventAccountDto accountDto) {
		logger.debug("enter queryChannelEventAccountAllList.");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String proid = accountDto.getProid();
		if(StringUtils.isNotBlank(proid)){
			String[] ps  = proid.split(",");
			paramMap.put("proid", ps);
		}
		paramMap.put("outletcode", accountDto.getOutletcode());
		paramMap.put("startTime", accountDto.getBeginTime());
		paramMap.put("endTime", accountDto.getEndTime());
		String statementName ="selectEventAccountAll";
		List<ChannelEventAccount>  list = queryForList(statementName, paramMap);
		return list;
	}

	@Override
	public ChannelEventAccount getChannelEventAccountAll(
			ChannelEventAccountDto accountDto) {
		logger.debug("enter getChannelEventAccountAll.");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String proid = accountDto.getProid();
		if(StringUtils.isNotBlank(proid)){
			String[] ps  = proid.split(",");
			paramMap.put("proid", ps);
		}
		paramMap.put("outletcode", accountDto.getOutletcode());
		paramMap.put("startTime", accountDto.getBeginTime());
		paramMap.put("endTime", accountDto.getEndTime());
		String statementName ="countAll";
		ChannelEventAccount account = queryForObject(statementName, paramMap);
		return account;
	}

	@Override
	public List<ChannelEventAccount> queryChannelEventAccountAllDeatil(ChannelEventAccountDto accountDto) {
		logger.debug("enter queryChannelEventAccountAllDeatil.");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String proid = accountDto.getProid();
		if(StringUtils.isNotBlank(proid)){
			String[] ps  = proid.split(",");
			paramMap.put("proid", ps);
		}
		paramMap.put("outletcode", accountDto.getOutletcode());
		paramMap.put("startTime", accountDto.getBeginTime());
		paramMap.put("endTime", accountDto.getEndTime());
		String statementName ="selectEventAccountAllDetail";
		List<ChannelEventAccount>  list = queryForList(statementName, paramMap);
		return list;
	}
	
	
}
