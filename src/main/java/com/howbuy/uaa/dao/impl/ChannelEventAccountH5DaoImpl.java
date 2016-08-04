/**
 * 
 */
package com.howbuy.uaa.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.howbuy.rdb.database.dao.impl.BaseDaoImpl;
import com.howbuy.uaa.common.enums.ChannelColDataEnum;
import com.howbuy.uaa.common.enums.HowbuyWebsiteChannelType;
import com.howbuy.uaa.dao.ChannelEventAccountH5Dao;
import com.howbuy.uaa.dto.ChannelEventAccountH5Dto;
import com.howbuy.uaa.dto.H5ActivityCollDataMapping;
import com.howbuy.uaa.dto.H5ActivityDataMapping;

/**
 * @author qiankun.li
 *
 */
public class ChannelEventAccountH5DaoImpl  extends BaseDaoImpl implements ChannelEventAccountH5Dao {
	
	private static Logger logger = LoggerFactory.getLogger(ChannelEventAccountH5DaoImpl.class);

	private static final String NS = "activity_new.";

	@SuppressWarnings("unchecked")
	<T> List<T> queryForList(String statementName, Object obj) {
		List<T> result = getSqlMapClientTemplate().queryForList(NS+statementName,obj);
		return result;
	}
	
	@SuppressWarnings("unchecked")
	<T> T queryForObject(String statementName, Object obj) {
		T result =(T) getSqlMapClientTemplate().queryForObject(NS+statementName,obj);
		return result;
	}
	
	
	private HashMap<String,Object> buildParam(ChannelEventAccountH5Dto accountH5Dto){
		HashMap<String,Object> map = new HashMap<String, Object>();
		map.put("beginDate", accountH5Dto.getBeginDate());
		map.put("endDate", accountH5Dto.getEndDate());
		map.put("beginTime", accountH5Dto.getBeginTime());
		map.put("endTime", accountH5Dto.getEndTime());
		map.put("level", accountH5Dto.getChannelLevel());
		map.put("channelType", accountH5Dto.getChannelType());
		map.put("channelParent", accountH5Dto.getChannelParent());
		String channel = accountH5Dto.getChannel();
		if(StringUtils.isNotBlank(channel) && channel.startsWith("0."))
			channel=channel.substring(2);
		map.put("channel", channel);
		map.put("channelCode", accountH5Dto.getChannelCode());
		map.put("parent", accountH5Dto.getParent());
		//map.put("dateList", accountH5Dto.getDateList());
		String seach = accountH5Dto.getSearchEngine();
		if(StringUtils.isNotBlank(seach))
			seach=seach.substring(2);
		map.put("searchEngine",seach);
		/*Integer curPage =  accountH5Dto.getCurPage();
		Integer pageRows = accountH5Dto.getPageRows();
		String order = accountH5Dto.getOrder();
		String orderBy = accountH5Dto.getOrderBy();
		if(null != curPage && null!=pageRows){
			int start  =(curPage-1)*pageRows;
			map.put("start", start);
			map.put("curPage", curPage);
			map.put("pageRows", pageRows);
		}
		map.put("order", order);
		map.put("orderBy", orderBy);*/
		return map;
	}
	
	
	private HashMap<String,Object> addParam(ChannelEventAccountH5Dto accountH5Dto){
		HashMap<String,Object> map = new HashMap<String, Object>();
		Integer level = accountH5Dto.getChannelLevel();
		String channelVar = accountH5Dto.getChannel();
		String search = accountH5Dto.getSearchEngine();
		if(null!=level && 1==level.intValue()){
			//当且仅当一级渠道的时候调用
			String channelType = String.valueOf(accountH5Dto.getChannelType());
			//判断汇总数据
			if("-1".equals(channelType)){
				//所有渠道
				map.put("channelType", HowbuyWebsiteChannelType.CHANNEL_COL.getChannelType());
				String channel = ChannelColDataEnum.CHANNEL_COL_ALL.getChannel();
				map.put("channel", channel);
			}else if(HowbuyWebsiteChannelType.CHANNEL_ZJFW.getChannelType().equals(channelType)){
				if(StringUtils.isNotBlank(channelVar)){
					//直接访问
					map.put("channelType", HowbuyWebsiteChannelType.CHANNEL_COL.getChannelType());
					String channel = ChannelColDataEnum.CHANNEL_COL_ZJ.getChannel();
					map.put("channel", channel);
				}
			}else if(HowbuyWebsiteChannelType.CHANNEL_SSYQ.getChannelType().equals(channelType)){
				if(StringUtils.isBlank(search)){
					//搜索引擎
					map.put("channelType", HowbuyWebsiteChannelType.CHANNEL_COL.getChannelType());
					String channel = ChannelColDataEnum.CHANNEL_COL_SSYQ.getChannel();
					map.put("channel", channel);
				}
			}else if(HowbuyWebsiteChannelType.CHANNEL_TGQD.getChannelType().equals(channelType)){
				if(StringUtils.isBlank(channelVar)){
					//推广渠道
					map.put("channelType", HowbuyWebsiteChannelType.CHANNEL_COL.getChannelType());
					String channel = ChannelColDataEnum.CHANNEL_COL_TGQD.getChannel();
					map.put("channel", channel);
				}
			}else if(HowbuyWebsiteChannelType.CHANNEL_OTHER.getChannelType().equals(channelType)){
				//其他渠道
				if(StringUtils.isBlank(channelVar)){
					map.put("channelType", HowbuyWebsiteChannelType.CHANNEL_COL.getChannelType());
					String channel = ChannelColDataEnum.CHANNEL_COL_OTHER.getChannel();
					map.put("channel", channel);
				}
			}
		}
		return map;
	}

	
	
	@Override
	public H5ActivityCollDataMapping getH5ActivityCollData(ChannelEventAccountH5Dto accountH5Dto) {
		String statementName = "getH5ActivityCollData";
		HashMap<String, Object> map = buildParam(accountH5Dto);
		HashMap<String,Object> mapCol = addParam(accountH5Dto);
		map.putAll(mapCol);
		H5ActivityCollDataMapping result = queryForObject(statementName, map);
		return result;
	}

	@Override
	public List<H5ActivityCollDataMapping> queryH5ActivityChannelDataList(
			ChannelEventAccountH5Dto accountH5Dto) {
		List<H5ActivityCollDataMapping> result = new ArrayList<H5ActivityCollDataMapping>(0); 
		String statementName = null;//除全部，推广
		Integer rootChannelType = accountH5Dto.getChannelType();
		HowbuyWebsiteChannelType channelType = HowbuyWebsiteChannelType.findByType(String.valueOf(rootChannelType));
		HashMap<String, Object> map = buildParam(accountH5Dto);
		if(null == channelType){
			statementName = "queryAllRootChannelData";//全部
			HashMap<String,Object> mapCol = addParam(accountH5Dto);
			map.putAll(mapCol);
		}else if(channelType.getChannelType().equals(HowbuyWebsiteChannelType.CHANNEL_OTHER.getChannelType())){
			statementName = "queryChannelData";//搜索引擎或者其他
			map.put("type", 2);
		}else if(channelType.getChannelType().equals(HowbuyWebsiteChannelType.CHANNEL_SSYQ.getChannelType())){
			statementName = "queryChannelData";//搜索引擎或者其他
			map.put("type", 1);
		}else if(channelType.getChannelType().equals(HowbuyWebsiteChannelType.CHANNEL_TGQD.getChannelType())){
			statementName = "queryChannelData4tuiguang";//推广
		}
		if(null!=statementName)
			result=queryForList(statementName, map);
		return result;
	}

	@Override
	public List<H5ActivityCollDataMapping> queryH5ActivityChannelDataHisList(
			ChannelEventAccountH5Dto accountH5Dto) {
		List<H5ActivityCollDataMapping> result = new ArrayList<H5ActivityCollDataMapping>(0); 
		String statementName = null;
		HashMap<String, Object> map = buildParam(accountH5Dto);
		Integer rootChannelType = accountH5Dto.getChannelType();
		HowbuyWebsiteChannelType channelType = HowbuyWebsiteChannelType.findByType(String.valueOf(rootChannelType));
		if(null == channelType){//全部根渠道
			statementName = "queryChannelDataHisRoot";
			HashMap<String,Object> mapCol = addParam(accountH5Dto);
			map.putAll(mapCol);
		}else if(channelType.getChannelType().equals(HowbuyWebsiteChannelType.CHANNEL_TGQD.getChannelType())){
			statementName = "queryChannelDataHistuiguang";//推广
		}else if(channelType.getChannelType().equals(HowbuyWebsiteChannelType.CHANNEL_SSYQ.getChannelType())){
			statementName = "queryChannelDataHisSsyqOrOther";//搜索引擎
			map.put("type", 1);
		}else if(channelType.getChannelType().equals(HowbuyWebsiteChannelType.CHANNEL_OTHER.getChannelType())){
			statementName = "queryChannelDataHisSsyqOrOther";//搜索引擎
			map.put("type", 2);
		}
		if(null!=statementName)
			result=queryForList(statementName, map);
		return result;
	}

	
	@Override
	public List<H5ActivityDataMapping> queryChannelDetailDataList(
			ChannelEventAccountH5Dto accountH5Dto) {
		List<H5ActivityDataMapping> result = new ArrayList<H5ActivityDataMapping>(0); 
		String statementName = null;
		HashMap<String, Object> map = buildParam(accountH5Dto);
		Integer rootChannelType = accountH5Dto.getChannelType();
		HowbuyWebsiteChannelType channelType = HowbuyWebsiteChannelType.findByType(String.valueOf(rootChannelType));
		if(null == channelType){
			statementName = "queryAllRootChannelTrendData";
			HashMap<String,Object> mapCol = addParam(accountH5Dto);
			map.putAll(mapCol);
		}
		if(null!=channelType){
			if(channelType.getChannelType().equals(HowbuyWebsiteChannelType.CHANNEL_ZJFW.getChannelType())){
				statementName="queryChannelDataDetailZjfw";
			}else if(channelType.getChannelType().equals(HowbuyWebsiteChannelType.CHANNEL_OTHER.getChannelType())){
				statementName="queryChannelDataDetailSsyqOrOther";
				map.put("type", 2);
			}else if(channelType.getChannelType().equals(HowbuyWebsiteChannelType.CHANNEL_SSYQ.getChannelType())){
				statementName="queryChannelDataDetailSsyqOrOther";
				map.put("type", 1);
			}else if(channelType.getChannelType().equals(HowbuyWebsiteChannelType.CHANNEL_TGQD.getChannelType())){
				statementName="queryChannelDataDetailRoute";
			}
		}
		result=queryForList(statementName, map);
		return result;
	}

	
	@Override
	public List<H5ActivityCollDataMapping> queryChannelTrendDataList(
			ChannelEventAccountH5Dto accountH5Dto) {
		List<H5ActivityCollDataMapping> result = new ArrayList<H5ActivityCollDataMapping>(0); 
		String statementName = "queryChannelTrendDataDetail";
		HashMap<String, Object> map = buildParam(accountH5Dto);
		Integer rootChannelType = accountH5Dto.getChannelType();
		HowbuyWebsiteChannelType channelType = HowbuyWebsiteChannelType.findByType(String.valueOf(rootChannelType));
		if(null == channelType){
			HashMap<String,Object> mapCol = addParam(accountH5Dto);
			map.putAll(mapCol);
		}
		logger.debug(statementName);
		result=queryForList(statementName, map);
		return result;
	}

	@Override
	public List<H5ActivityDataMapping> queryTrendDataList(
			ChannelEventAccountH5Dto accountH5Dto) {
		List<H5ActivityDataMapping> result = new ArrayList<H5ActivityDataMapping>(0); 
		String statementName = "queryTrendDataList";
		HashMap<String, Object> map = buildParam(accountH5Dto);
		HashMap<String,Object> mapCol = addParam(accountH5Dto);
		map.putAll(mapCol);
		result=queryForList(statementName, map);
		return result;
	}

	@Override
	public H5ActivityCollDataMapping getH5ActivityCollHisData(
			ChannelEventAccountH5Dto accountH5Dto) {
		String statementName = "getH5ActivityCollDataHis";
		HashMap<String, Object> map = buildParam(accountH5Dto);
		HashMap<String,Object> mapCol = addParam(accountH5Dto);
		map.putAll(mapCol);
		H5ActivityCollDataMapping result = queryForObject(statementName, map);
		return result;
	}

	@Override
	public List<H5ActivityDataMapping> queryChannelTrendDataHisList(
			ChannelEventAccountH5Dto accountH5Dto) {
		List<H5ActivityDataMapping> result = new ArrayList<H5ActivityDataMapping>(0); 
		String statementName = null;
		HashMap<String, Object> map = buildParam(accountH5Dto);
		Integer rootChannelType = accountH5Dto.getChannelType();
		HowbuyWebsiteChannelType channelType = HowbuyWebsiteChannelType.findByType(String.valueOf(rootChannelType));
		if(null == channelType){
			statementName = "queryAllRootChannelTrendHisData";
			HashMap<String,Object> mapCol = addParam(accountH5Dto);
			map.putAll(mapCol);
		}
		if(null!=channelType){
			if(channelType.getChannelType().equals(HowbuyWebsiteChannelType.CHANNEL_ZJFW.getChannelType())){
				statementName="queryChannelHisDataDetailZjfw";//直接访问
			}else if(channelType.getChannelType().equals(HowbuyWebsiteChannelType.CHANNEL_OTHER.getChannelType())){
				statementName="queryChannelHisDataDetailSsyqOrOther";//
				map.put("type", 2);
			}else if(channelType.getChannelType().equals(HowbuyWebsiteChannelType.CHANNEL_SSYQ.getChannelType())){
				statementName="queryChannelHisDataDetailSsyqOrOther";
				map.put("type", 1);
			}else if(channelType.getChannelType().equals(HowbuyWebsiteChannelType.CHANNEL_TGQD.getChannelType())){
				statementName="queryChannelHisDataDetailRoute";//
			}
		}
		result=queryForList(statementName, map);
		return result;
	}

	 
	
	
}
