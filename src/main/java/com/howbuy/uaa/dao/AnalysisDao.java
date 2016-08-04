package com.howbuy.uaa.dao;

import java.util.Date;
import java.util.List;

import com.howbuy.uaa.command.HowbuyPVUV;
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

public interface AnalysisDao {
	
	public Channel saveChannel(Channel channel);
	
	public List<Channel> queryAllChannel();
	
	
	public void batchSavingChannelReport(List<ChannelReport> list);
	
	public List<ChannelReport> queryChannelReport(String channelId, Date start, Date end);
	
	
	public void batchSavingSubchannelReport(List<SubchannelReport> list);
	
	public List<SubchannelReport> querySubChannelReport(String channelId, Date start, Date end, String keyword);

	
	public void batchSavingOpenAcct(List<OpenAccountDetail> list);
	
	
	public List<OpenAccountDetail> queryOpenAcctDetail(String channelId, Date start, Date end);
	
	
	public void batchSavingTradeDetail(List<TradeDetail> list);
	
	
	public List<TradeDetail> queryTradeDetailByParentChannelId(String channelId, Date start, Date end);
	public List<TradeDetail> queryTradeDetail(String subchannelId, String url, Date start, Date end);
	
	/**
	 * howbuy pv，uv记录
	 * @param countStatList
	 */
	public void batchSavingUVPV(List<CountStat> countStatList);
	
	/**
	 * 查询howbuy uv pv统计信息
	 */
	public List<Howbuy2EhowbuyDto> queryHowbuyPVUVStat(HowbuyPVUV condition);
	
	/**
	 * 记录howbuy2ehowbuy pv uv
	 */
	public void batchSavingHowbuy2Ehowbuy(List<Howbuy2EhowbuyStatReport> reports);
	
	/**
	 * 统计howbuy_stat 按channel_id,parent_id统计pv/uv
	 */
	public PVUVCounts getPVUVCounts(HowbuyPVUV condition);
	
	/**
	 * 统计howbuy2ehowbuystat 转化率    insert操作
	 */
	public void batchSaveHowbuy2Ehowbuy(List<Howbuy2Ehowbuy> list);
	
	/**	
	 * 查询howbuy2ehowbuy 未来某段时间内的转化率 
	 */
	public List<Howbuy2EhowbuyDto> queryHowbuy2EhowbuyIntervals(HowbuyPVUV condition);
}
