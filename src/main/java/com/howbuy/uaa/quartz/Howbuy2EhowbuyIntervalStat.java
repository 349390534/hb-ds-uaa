package com.howbuy.uaa.quartz;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowCallbackHandler;

import com.howbuy.uaa.core.HiveDao;
import com.howbuy.uaa.persistence.Howbuy2Ehowbuy;
import com.howbuy.uaa.service.AnalysisReportService;
import com.howbuy.uaa.utils.DateUtils;

/**
 * 定期统计howbuy转化至ehowbuy的开户，交易数量 
 * @author yichao.song
 *
 */
public class Howbuy2EhowbuyIntervalStat {
	
	@Autowired
	@Qualifier("hiveHandler")
	public HiveDao hiveDao;
	
	/*@Autowired
	@Qualifier("analysisReportService")*/
	private AnalysisReportService reportService;
	
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public String getStatDay() throws ParseException{
		return DateUtils.getFormatedDate(DateUtils.getYesterdayDate());
	}
	
	public void run() throws ParseException{
		dorun(getStatDay());
	}
	
	public void dorun(String statDay){
		
		logger.info("----------------" + statDay + " stat start--------------------------");
		
		long cur = System.currentTimeMillis();
		
		//计算前10天，前30天新增UV
		
		List<Howbuy2Ehowbuy> stats_10 = getReveserRowCount(statDay, -10, new String[]{"4","6"});
		
		List<Howbuy2Ehowbuy> stats_30 = getReveserRowCount(statDay, -30, new String[]{"4","6"});
		
		stats_10.addAll(stats_30);
		
		reportService.batchSaveHowbuy2Ehowbuy(stats_10);
		
		logger.info("..................saving..............................");
		
		logger.info("consume time : {} seconds", (System.currentTimeMillis() - cur)/1000);
		
		logger.info("----------------" + statDay + " stat finished--------------------------");
	}
	
	
	/**
	 * 根据时间，业务类型（4，开户，6，申购）统计statDay新增UV，截止endDay（含）的开户，交易数
	 * @param statDay
	 * @param interval 统计周期（天）
	 * @param actiontype
	 * @return
	 */
	protected List<Howbuy2Ehowbuy> getRowCount(String statDay,int interval,String[] actiontype){
		logger.error("processing getRowCount,statDay:{},interval:{}",statDay,interval);
		
		String to_day = DateUtils.getFormatedDate(DateUtils.getDate(statDay,interval));
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("statDay", statDay);
		paramMap.put("to_day", to_day);
		paramMap.put("actiontype", Arrays.asList(actiontype));
		RowCountCallbackHandler handler = new RowCountCallbackHandler(statDay,interval);
		String sql = "select pp.dest_channel,pp.dest_subchannel,ua.action_type,count(1) count from user_action ua join ("
				 +"select lpv.dest_channel,lpv.dest_subchannel,lpv.src_cookie from landing_page_view lpv "
				 + "where from_unixtime(cast(lpv.src_time as bigint),'yyyy-MM-dd')=:statDay "
				 + "and length(lpv.dest_channel)>0 and length(lpv.dest_subchannel)>0"
				 + ") pp on ua.action_cookie=pp.src_cookie where ua.action_type in (:actiontype) "
				 + "and datediff(from_unixtime(ua.action_time,'yyyy-MM-dd'),:statDay) >=0 "
				 + "and datediff(from_unixtime(ua.action_time,'yyyy-MM-dd'),:to_day) <=0 "
				 + "group by pp.dest_channel,pp.dest_subchannel,ua.action_type";
		hiveDao.query(sql, paramMap, handler);
		
		return handler.getList();
		
	}
	
	protected List<Howbuy2Ehowbuy> getReveserRowCount(String statDay,int interval,String[] actiontype){
		logger.error("processing getRowCount,statDay:{},interval:{}",statDay,interval);
		
		String fromday = DateUtils.getFormatedDate(DateUtils.getDate(statDay,interval));
		
		logger.error("processing getRowCount,fromday:{}",fromday);
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("statDay", statDay);
		paramMap.put("fromday", fromday);
		paramMap.put("actiontype", Arrays.asList(actiontype));
		RowCountCallbackHandler handler = new RowCountCallbackHandler(fromday,interval);
		String sql = "select pp.dest_channel,pp.dest_subchannel,ua.action_type,count(1) count from user_action ua join ("
				 +"select lpv.dest_channel,lpv.dest_subchannel,lpv.src_cookie from landing_page_view lpv "
				 + "where from_unixtime(cast(lpv.src_time as bigint),'yyyy-MM-dd')=:fromday "
				 + "and length(lpv.dest_channel)>0 and length(lpv.dest_subchannel)>0"
				 + ") pp on ua.action_cookie=pp.src_cookie where ua.action_type in (:actiontype) "
				 + "and datediff(from_unixtime(ua.action_time,'yyyy-MM-dd'),:statDay) <=0 "
				 + "and datediff(from_unixtime(ua.action_time,'yyyy-MM-dd'),:fromday) >=0 "
				 + "group by pp.dest_channel,pp.dest_subchannel,ua.action_type";
		hiveDao.query(sql, paramMap, handler);
		
		return handler.getList();
		
	}
	
	
	
	public Date getStatDate(String statDay) throws ParseException{
		return DateUtils.parseDate(statDay);
	}
	
	
	class RowCountCallbackHandler implements RowCallbackHandler{
		
		private Date statDate;
		
		private int interval;
		
		
		private List<Howbuy2Ehowbuy> list = new ArrayList<Howbuy2Ehowbuy>();
		
		
		public RowCountCallbackHandler(String fromday, int interval) {
			this.statDate = DateUtils.parseDate(fromday);
			this.interval = Math.abs(interval);
		}


		@Override
		public void processRow(ResultSet rs) throws SQLException {
			
			Howbuy2Ehowbuy atn = new Howbuy2Ehowbuy();
			atn.setChannel(rs.getString("dest_channel"));
			atn.setSubchannel(rs.getString("dest_subchannel"));
			atn.setActiontype(rs.getString("action_type"));
			atn.setCount(rs.getInt("count"));
			atn.setIntervals(interval);
			atn.setStartDate(statDate);
			list.add(atn);
		}


		public List<Howbuy2Ehowbuy> getList() {
			return list;
		}
		
		
		
	
	}
	
	class ActionTypeNum{
		private String actionType;
		
		private long count;
		
		private String channel;
		
		private String subChannel;
		
		public String getChannel() {
			return channel;
		}

		public void setChannel(String channel) {
			this.channel = channel;
		}

		public String getSubChannel() {
			return subChannel;
		}

		public void setSubChannel(String subChannel) {
			this.subChannel = subChannel;
		}

		public String getActionType() {
			return actionType;
		}

		public void setActionType(String actionType) {
			this.actionType = actionType;
		}

		public long getCount() {
			return count;
		}

		public void setCount(long count) {
			this.count = count;
		}
		
	}
	
}
