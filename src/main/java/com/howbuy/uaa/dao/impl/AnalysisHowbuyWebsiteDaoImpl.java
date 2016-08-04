package com.howbuy.uaa.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.howbuy.rdb.database.dao.impl.BaseDaoImpl;
import com.howbuy.uaa.common.enums.ChannelColDataEnum;
import com.howbuy.uaa.common.enums.HowbuyWebsiteChannelType;
import com.howbuy.uaa.dao.AnalysisHowbuyWebsiteDao;
import com.howbuy.uaa.dto.HowbuyWebsiteCollDataMapping;
import com.howbuy.uaa.dto.HowbuyWebsiteDataMapping;
import com.howbuy.uaa.dto.HowbuyWebsiteFormData;
import com.howbuy.uaa.dto.RouteDetailDto;
import com.howbuy.uaa.persistence.HowbuyRootChannel;
import com.howbuy.uaa.persistence.ZeroChannelTag;

public class AnalysisHowbuyWebsiteDaoImpl extends BaseDaoImpl implements AnalysisHowbuyWebsiteDao{
	
	private static Logger logger = LoggerFactory.getLogger(AnalysisHowbuyWebsiteDaoImpl.class);
	
	private static final String NS = "analysisHowbuyWebsite.";	
	
    @SuppressWarnings("unchecked")
 	private <T> List<T> queryForList(String statementName,Object obj){
     	List<T> result= getSqlMapClientTemplate().queryForList(statementName,obj);
     	return result;
     }
     
     @SuppressWarnings("unchecked")
     private <T> List<T> queryForList(String statementName){
     	List<T> result= getSqlMapClientTemplate().queryForList(statementName);
     	return result;
     }
	
	
	@Override
	public List<RouteDetailDto> queryListTrendDetail(int sr, int rows, String orderby,String order) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("startrow", sr);
		params.put("rows",rows);
		params.put("orderbyfd", orderby);
		params.put("orderfd", order);
		return queryForList("AnalysisDataTool.queryListTrendDetail", params);
	
	}


	@Override
	public int queryRowsOfTrendDetail() {
		return (Integer) getSqlMapClientTemplate().queryForObject("AnalysisDataTool.queryRowsOfTrendDetail");
	}
	
	@Override
	public List<HowbuyRootChannel> queryHowbuyRootChannelList() {
		String statementName = NS + "selectRootChannel";
		List<HowbuyRootChannel> result = queryForList(statementName);
		return result;
	}


	@Override
	public List<ZeroChannelTag> queryChannelTagList() {
		String statementName = NS + "selectZeroChannelTag";
		List<ZeroChannelTag> result = queryForList(statementName);
		return result;
	}


	private HashMap<String,Object> buildParam(HowbuyWebsiteFormData websiteFormData){
		HashMap<String,Object> map = new HashMap<String, Object>();
		map.put("beginDate", websiteFormData.getBeginDate());
		map.put("endDate", websiteFormData.getEndDate());
		map.put("level", websiteFormData.getLevel());
		map.put("channelType", websiteFormData.getChannelType());
		map.put("channelRoot", websiteFormData.getChannelRoot());
		map.put("channel", websiteFormData.getChannel());
		map.put("channelCode", websiteFormData.getChannelCode());
		map.put("parent", websiteFormData.getParent());
		map.put("dateList", websiteFormData.getDateList());
		map.put("searchEngine", websiteFormData.getSearchEngine());
		Integer curPage =  websiteFormData.getCurPage();
		Integer pageRows = websiteFormData.getPageRows();
		String order = websiteFormData.getOrder();
		String orderBy = websiteFormData.getOrderBy();
		if(null != curPage && null!=pageRows){
			int start  =(curPage-1)*pageRows;
			map.put("start", start);
			map.put("curPage", curPage);
			map.put("pageRows", pageRows);
		}
		map.put("order", order);
		map.put("orderBy", orderBy);
		return map;
	}
	
	@Override
	public List<HowbuyWebsiteCollDataMapping> queryAllRootChannelCollData(HowbuyWebsiteFormData websiteFormData) {
		String statementName = NS + "queryAllRootChannelData";
		HashMap<String,Object> map = buildParam(websiteFormData);
		List<HowbuyWebsiteCollDataMapping> result = queryForList(statementName,map);
		return result;
	}


	@Override
	public List<HowbuyWebsiteCollDataMapping> queryChannelCollData(
			HowbuyWebsiteFormData websiteFormData) {
		String statementName = NS + "queryChannelData";
		HashMap<String,Object> map = buildParam(websiteFormData);
		List<HowbuyWebsiteCollDataMapping> result=null;
		try {
			result = queryForList(statementName,map);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return result;
	}


	@Override
	public List<HowbuyWebsiteCollDataMapping> queryChannelCustCollData(
			HowbuyWebsiteFormData websiteFormData) {
		String statementName = NS + "queryCustCollData";
		HashMap<String,Object> map = buildParam(websiteFormData);
		HashMap<String,Object> mapCol = addParam(websiteFormData);
		map.putAll(mapCol);
		List<HowbuyWebsiteCollDataMapping> result = queryForList(statementName,map);
		return result;
	}

	
	private HashMap<String,Object> addParam(HowbuyWebsiteFormData websiteFormData){
		HashMap<String,Object> map = new HashMap<String, Object>();
		Integer level = websiteFormData.getLevel();
		String channelVar = websiteFormData.getChannel();
		String search = websiteFormData.getSearchEngine();
		if(null!=level && 1==level.intValue()){
			//当且仅当一级渠道的时候调用
			String channelType = websiteFormData.getChannelType();
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
	public List<HowbuyWebsiteDataMapping> queryChannelTrendDataDetail(
			HowbuyWebsiteFormData websiteFormData) {
		String statementName = NS + "queryTrendData";
		HashMap<String,Object> map = buildParam(websiteFormData);
		HashMap<String,Object> mapCol = addParam(websiteFormData);
		map.putAll(mapCol);
		List<HowbuyWebsiteDataMapping> result = queryForList(statementName,map);
		return result;
	}


	@Override
	public Integer queryTrendDataCount(HowbuyWebsiteFormData websiteFormData) {
		String statementName = NS + "queryTrendDataCount";
		HashMap<String,Object> map = buildParam(websiteFormData);
		Integer result =  (Integer)getSqlMapClientTemplate().queryForObject(statementName,map);
		return result;
	}


	@Override
	public List<HowbuyWebsiteDataMapping> queryRootChannelLineDataDetail(
			HowbuyWebsiteFormData websiteFormData) {
		String statementName = NS + "queryRootLineData";
		HashMap<String, Object> map = buildParam(websiteFormData);
		List<HowbuyWebsiteDataMapping> result = queryForList(statementName, map);
		return result;
	}
	
	 
	@Override
	public List<HowbuyWebsiteDataMapping> queryChannelLineDataDetail(
			HowbuyWebsiteFormData websiteFormData) {
		String statementName = NS + "queryLineData";
		HashMap<String, Object> map = buildParam(websiteFormData);
		String channelType = websiteFormData.getChannelType();
		//判断汇总数据
		if(HowbuyWebsiteChannelType.CHANNEL_ZJFW.getChannelType().equals(channelType)){
			//直接访问
			map.put("channelType", HowbuyWebsiteChannelType.CHANNEL_COL.getChannelType());
			String channel = ChannelColDataEnum.CHANNEL_COL_ZJ.getChannel();
			map.put("channel", channel);
		}
		List<HowbuyWebsiteDataMapping> result=null;
		try {
			result = queryForList(statementName, map);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return result;
	}
	
	
}
