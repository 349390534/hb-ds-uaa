package com.howbuy.uaa.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.howbuy.rdb.database.dao.impl.BaseDaoImpl;
import com.howbuy.uaa.command.HowbuyPVUV;
import com.howbuy.uaa.dao.AnalysisDao;
import com.howbuy.uaa.dto.Howbuy2EhowbuyDto;
import com.howbuy.uaa.dto.PVUVCounts;
import com.howbuy.uaa.persistence.Channel;
import com.howbuy.uaa.persistence.ChannelReport;
import com.howbuy.uaa.persistence.CountStat;
import com.howbuy.uaa.persistence.Howbuy2Ehowbuy;
import com.howbuy.uaa.persistence.Howbuy2EhowbuyStatReport;
import com.howbuy.uaa.persistence.OpenAccountDetail;
import com.howbuy.uaa.persistence.SubchannelReport;
import com.howbuy.uaa.persistence.TradeDetail;

public class AnalysisDaoImpl extends BaseDaoImpl implements AnalysisDao {


	@Override
	public Channel saveChannel(Channel channel) {
		Object result = getSqlMapClientTemplate().insert("AnalysisReport.insertChannel", channel);
		channel.setId((String) result);
		return channel;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Channel> queryAllChannel() {
		return getSqlMapClientTemplate().queryForList("AnalysisReport.queryAllChannel");
	}


	@Override
	public void batchSavingChannelReport(List<ChannelReport> list) {
		getSqlMapClientTemplate().insert("AnalysisReport.batchSavingChannelReport", list);
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<ChannelReport> queryChannelReport(String channelId,Date start, Date end) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("start", start);
		params.put("end", end);
		params.put("channelId", channelId);
		return getSqlMapClientTemplate().queryForList("AnalysisReport.queryChannelReport", params);
	}


	@Override
	public void batchSavingSubchannelReport(List<SubchannelReport> list) {
		getSqlMapClientTemplate().insert("AnalysisReport.batchSavingSubchannelReport", list);
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<SubchannelReport> querySubChannelReport(String channelId,
			Date start, Date end, String keyword) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("channelId", channelId);
		params.put("start", start);
		params.put("end", end);
		params.put("keyword", keyword);
		return getSqlMapClientTemplate().queryForList("AnalysisReport.querySubChannelReport", params);
	}


	@Override
	public void batchSavingOpenAcct(List<OpenAccountDetail> list) {
		getSqlMapClientTemplate().insert("AnalysisReport.batchSavingOpenAcct", list);
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<OpenAccountDetail> queryOpenAcctDetail(String channelId,Date start,Date end) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("start", start);
		params.put("end", end);
		return getSqlMapClientTemplate().queryForList("AnalysisReport.queryOpenAcctDetail", params);
	}


	@Override
	public void batchSavingTradeDetail(List<TradeDetail> list) {
		getSqlMapClientTemplate().insert("AnalysisReport.batchSavingTradeDetail", list);
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<TradeDetail> queryTradeDetail(String subchannelId, String url, Date start,
			Date end) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("channelId", subchannelId);
		params.put("start", start);
		params.put("end", end);
		params.put("url", url);
		return getSqlMapClientTemplate().queryForList("AnalysisReport.queryTradeDetail", params);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TradeDetail> queryTradeDetailByParentChannelId(String channelId, Date start,
			Date end) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("channelId", channelId);
		params.put("start", start);
		params.put("end", end);
		return getSqlMapClientTemplate().queryForList("AnalysisReport.queryTradeDetailByParentChannelId", params);
	}


	@Override
	public void batchSavingUVPV(List<CountStat> countStatList) {
		
		getSqlMapClientTemplate().insert("AnalysisReport.batchSavingPVUV", countStatList);
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Howbuy2EhowbuyDto> queryHowbuyPVUVStat(HowbuyPVUV condition) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("channelID", condition.getSubChannelID());
		params.put("parentChannelID", condition.getChannelID());
		params.put("start", condition.getStart());
		params.put("end", condition.getEndTime());
		params.put("pageType", condition.getPageType());
		return getSqlMapClientTemplate().queryForList("AnalysisReport.queryHowbuy2EhowbuyPVUVstat", params);
	}


	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void batchSavingHowbuy2Ehowbuy(List<Howbuy2EhowbuyStatReport> reports) {
		int batch_size = 5000;
		int times = reports.size()/batch_size;
		int offset = 0;
		List tosaveList = new ArrayList(50000);
		for(int i = 1;i <= times;i++){
			int toindex = batch_size * i;
			List sublist = reports.subList(offset, toindex);
			tosaveList.addAll(sublist);
			getSqlMapClientTemplate().insert("AnalysisReport.batchSavingHowbuy2EhowbuyStat", tosaveList);
			offset = toindex;
			tosaveList.clear();
		}
		if(offset < reports.size()){
			tosaveList.addAll(reports.subList(offset, reports.size()));
			getSqlMapClientTemplate().insert("AnalysisReport.batchSavingHowbuy2EhowbuyStat", tosaveList);
		}
		
	}


	@SuppressWarnings("unchecked")
	@Override
	public PVUVCounts getPVUVCounts(HowbuyPVUV condition) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("channelId", condition.getSubChannelID());
		params.put("parentId", condition.getChannelID());
		params.put("start", condition.getStart());
		params.put("end", condition.getEndTime());
		params.put("pageType", condition.getPageType());
		List<PVUVCounts> retList = getSqlMapClientTemplate().queryForList("AnalysisReport.queryCountsGroupBy_Channel_Subchannel", params);
		if(retList.size() > 0)
			return (PVUVCounts)retList.get(0);
		else 
			return null;
	}
	
	@Override
	public void batchSaveHowbuy2Ehowbuy(List<Howbuy2Ehowbuy> list){
		getSqlMapClientTemplate().insert("AnalysisReport.batchInsertHowbuy2EhowbuyIntervalStat",list);
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Howbuy2EhowbuyDto> queryHowbuy2EhowbuyIntervals(HowbuyPVUV condition) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("channelId", condition.getChannelID());
		params.put("subchannelId", condition.getSubChannelID());
		params.put("start", condition.getStart());
		params.put("end", condition.getEndTime());
		params.put("intervals", condition.getIntervals());
		return getSqlMapClientTemplate().queryForList("AnalysisReport.queryHowbuy2EhowbuyIntervals", params);
	}
	
	

}
