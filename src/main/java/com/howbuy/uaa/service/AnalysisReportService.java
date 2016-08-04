package com.howbuy.uaa.service;

import java.util.Date;
import java.util.List;

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

public class AnalysisReportService {
	//private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	private AnalysisDao analysisDao;
	
	/**
	 * 保存频道信息
	 * @param channel
	 */
	public Channel saveChannel(Channel channel){
		return analysisDao.saveChannel(channel);
	}
	
	/**
	 * 返回所有频道信息
	 * @return
	 */
	public List<Channel> queryAllChannel(){
		return analysisDao.queryAllChannel();
	}
		
	/**
	 * 批量保存频道分析结果
	 * @param list
	 */
	public void batchSavingChannelReport(List<ChannelReport> list){
		analysisDao.batchSavingChannelReport(list);
	}
	
	/**
	 * 查询频道分析结果
	 * @param start 所属日期
	 * @param end
	 * @return
	 */
	public List<ChannelReport> queryChannelReport(Date start, Date end){
		return analysisDao.queryChannelReport(null, start, end);
	}
	
	/**
	 * 查询频道分析结果
	 * @param start 所属日期
	 * @param end
	 * @return
	 */
	public List<ChannelReport> queryChannelReport(String channelId, Date start, Date end){
		return analysisDao.queryChannelReport(channelId,start, end);
	}
	
	/**
	 * 批量保存子频道分析结果
	 * @param list
	 */
	 public void batchSavingSubchannelReport(List<SubchannelReport> list){
		 analysisDao.batchSavingSubchannelReport(list);
	 }
	 
	 
	 public void batchHowbuy2EhowbuyPVUV(List<Howbuy2EhowbuyStatReport> list){
		 analysisDao.batchSavingHowbuy2Ehowbuy(list);
	 }
	
	 /**
	  * 查询子频道分析结果
	  * @param parentChannelId
	  * @param start
	  * @param end
	  * @param from
	  * @param to
	  * @return
	  */
	public List<SubchannelReport> querySubChannelReport(String parentChannelId, Date start, Date end, String keyword){
		return analysisDao.querySubChannelReport(parentChannelId, start, end, keyword);
	}

	/**
	 * 批量保存开启信息
	 * @param list
	 */
	public void batchSavingOpenAcct(List<OpenAccountDetail> list){
		analysisDao.batchSavingOpenAcct(list);
	}
	
	/**
	 * 查询开户明细
	 * @param channelId 频道ID
	 * @param start
	 * @param end
	 * @param from
	 * @param to
	 * @return
	 */
	public List<OpenAccountDetail> queryOpenAcctDetail(String channelId, Date start, Date end){
		return analysisDao.queryOpenAcctDetail(channelId, start, end);
	}
	
	/**
	 * 批量插入交易信息
	 * @param list
	 */
	public void batchSavingTradeDetail(List<TradeDetail> list){
		analysisDao.batchSavingTradeDetail(list);
	}
	
	/**
	 * 统计howbuy2ehowbuy pv/uv
	 * @param statDay
	 * @return
	 */
	public PVUVCounts getPVUVStat(HowbuyPVUV condition){
		return analysisDao.getPVUVCounts(condition);
	}
	
	/**
	 * 查询交易明细
	 * @param channelId 频道ID
	 * @param start
	 * @param end
	 * @param from
	 * @param to
	 * @return
	 */
	public List<TradeDetail> channelTradeDetail(String channelId, Date start, Date end){
		return analysisDao.queryTradeDetailByParentChannelId(channelId, start, end);
	}
	
	public List<TradeDetail> subchannelTradeDetail(String subchannelId, String url,Date start, Date end){
		return analysisDao.queryTradeDetail(subchannelId, url, start, end);
	}
	
	
	public void batchSaving(List<SubchannelReport> subChannelReport, List<TradeDetail> tradeList,List<OpenAccountDetail> openAcctList){
		if (subChannelReport != null && subChannelReport.size() > 0) {
			batchSavingSubchannelReport(subChannelReport);
		}
		if (tradeList != null && tradeList.size() > 0) {
			batchSavingTradeDetail(tradeList);
		}
		if (openAcctList != null && openAcctList.size() > 0) {
			batchSavingOpenAcct(openAcctList);
		}
	}
	
	/**
	 * 统计howbuy2ehowbuy PV UV
	 * @param command
	 */
	public List<Howbuy2EhowbuyDto> queryHowbuyPVUVStat(HowbuyPVUV condition){
		return analysisDao.queryHowbuyPVUVStat(condition);
	}
	
	/**
	 * 保存howbuy,pv,uv
	 */
	public void batchSavingUVPV(List<CountStat> countStatList){
		analysisDao.batchSavingUVPV(countStatList);
	}

	public AnalysisDao getAnalysisDao() {
		return analysisDao;
	}

	public void setAnalysisDao(AnalysisDao analysisDao) {
		this.analysisDao = analysisDao;
	}
	
	/**
	 * 某日新用户在未来几天内的转换率
	 * @param list
	 */
	public void batchSaveHowbuy2Ehowbuy(List<Howbuy2Ehowbuy> list){
		 analysisDao.batchSaveHowbuy2Ehowbuy(list);
	 }
	
	/**
	 * 查询howbuy2ehowbuy 未来某段时间内的转化率 
	 *
	 */
	public List<Howbuy2EhowbuyDto> queryHowbuy2EhowbuyIntervals(HowbuyPVUV condition){
		return analysisDao.queryHowbuy2EhowbuyIntervals(condition);
	} 
}
