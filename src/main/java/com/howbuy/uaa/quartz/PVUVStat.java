package com.howbuy.uaa.quartz;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowCallbackHandler;

import com.howbuy.common.util.StringUtil;
import com.howbuy.uaa.core.HiveDao;
import com.howbuy.uaa.persistence.CountStat;
import com.howbuy.uaa.service.AnalysisReportService;
import com.howbuy.uaa.utils.DateUtils;

public class PVUVStat {
	
	private static Logger logger = LoggerFactory.getLogger(PVUVStat.class);
	
	@Autowired
	@Qualifier("hiveHandler")
	public HiveDao hiveDao;
	
	@Autowired
	@Qualifier("analysisReportService")
	private AnalysisReportService reportService;
	
	/**
	 * 获取统计日期 yyyy-MM-dd 前一日
	 * @return
	 * @throws ParseException 
	 */
	public String getStatDay() throws ParseException{
		return DateUtils.getFormatedDate(DateUtils.getYesterdayDate());
	}
	
	public Date getStatDate(String statDay) throws ParseException{
		return DateUtils.parseDate(statDay);
	}
	
	public void run() throws ParseException{
		dorun(getStatDay());
	}
	

	public void dorun(String statDay){
		
		try {
			logger.info("----------------" + statDay + " stat start--------------------------");
			long cur = System.currentTimeMillis();
			
			Map<String,Long> pvMap = getHowbuyPVByStatDay(statDay);
			Map<String,Long> uvMap = getHowbuyUVByStatDay(statDay);
			Date createDate = new Date();
			Date date_statDay = getStatDate(statDay);
			List<CountStat> countStatList = new ArrayList<CountStat>();
			for(Map.Entry<String, Long> pventry : pvMap.entrySet()){
				
				
				String[] channelIDArr = pventry.getKey().split("\\$");
				
				String channelID = channelIDArr[0];
				String subChannelID =  channelIDArr.length == 2 ? channelIDArr[1] : null;
				
				long pv = pventry.getValue();
				long uv = uvMap.get(pventry.getKey()) == null ? 0 : uvMap.get(pventry.getKey());
				CountStat countstat = new CountStat();
				countstat.setChannelID(subChannelID);
				countstat.setParentChannelID(channelID);
				countstat.setPv(pv);
				countstat.setUv(uv);
				countstat.setStatDate(date_statDay);
				countstat.setCreateDate(createDate);
				countStatList.add(countstat);
			}
			logger.info("size:{},saving..............................",countStatList.size());
			this.save(countStatList);
			
			logger.info("consume time : {} seconds", (System.currentTimeMillis() - cur)/1000);
			logger.info("----------------" + statDay + " stat finished--------------------------");
			
		} catch (Exception e) {
			logger.error("pv_uv_stat_error,{}",e);
		}
	}
	
	/**
	 * 保存pv、uv
	 * @param countStatList
	 */
	private void save(List<CountStat> countStatList){
		reportService.batchSavingUVPV(countStatList);
	}
	
	
	/**
	 * 获取howbuy PV数量
	 * @param statDay
	 * @return
	 */
	private Map<String,Long> getHowbuyPVByStatDay(String statDay){
		
		logger.info("processing getPVByStatDay");
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("statDay", statDay);
		Map<String,Long> pvMap = new HashMap<String,Long>();
		
		CountRowCallbackHandler pvhandler = new CountRowCallbackHandler(pvMap);
		String sql_totol_pv = "select pv.dest_channel,pv.dest_subchannel,count(1) count  from page_view pv where from_unixtime(cast(pv.src_time as bigint),'yyyy-MM-dd')=:statDay and length(pv.dest_channel)>0  group by pv.dest_channel,pv.dest_subchannel";
		hiveDao.query(sql_totol_pv, paramMap, pvhandler);
		return pvhandler.getStatMap();
	}
	
	/**
	 * 获取howbuy UV数量
	 * @param statDay
	 * @return
	 */
	private Map<String,Long> getHowbuyUVByStatDay(String statDay){
		
		logger.info("processing getHowbuyUVByStatDay");
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("statDay", statDay);
		Map<String,Long> uvMap = new HashMap<String,Long>();
		
		CountRowCallbackHandler pvhandler = new CountRowCallbackHandler(uvMap);
		String sql_totol_uv = "select pv.dest_channel,pv.dest_subchannel,count(1) count from (select distinct src_cookie,dest_url,dest_channel,dest_subchannel from page_view where from_unixtime(cast(src_time as bigint),'yyyy-MM-dd')=:statDay and length(dest_channel)>0) pv group by pv.dest_channel,pv.dest_subchannel";
		hiveDao.query(sql_totol_uv, paramMap, pvhandler);
		return pvhandler.getStatMap();
	}
	
	
	class CountRowCallbackHandler implements RowCallbackHandler{
		
		private Map<String,Long> pvMap;

		public CountRowCallbackHandler(Map<String, Long> pvMap) {
			this.pvMap = pvMap;
		}

		@Override
		public void processRow(ResultSet rs) throws SQLException {
			String subchannelID = rs.getString("dest_subchannel");
			String channelID = rs.getString("dest_channel");
			String key = channelID + "$" + (StringUtil.isEmpty(subchannelID) ? "" : subchannelID);
			pvMap.put(key, Long.parseLong(rs.getString("count")));
		}
		
		public Map<String,Long> getStatMap(){
			return pvMap;
		}
	}
}
