package com.howbuy.uaa.quartz;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowCallbackHandler;

import com.howbuy.common.util.StringUtil;
import com.howbuy.uaa.core.HiveDao;
import com.howbuy.uaa.persistence.Channel;
import com.howbuy.uaa.persistence.SubchannelReport;
import com.howbuy.uaa.persistence.TradeDetail;
import com.howbuy.uaa.quartz.dto.PV;
import com.howbuy.uaa.quartz.dto.UA;
import com.howbuy.uaa.service.AnalysisReportService;
import com.howbuy.uaa.utils.DateUtils;

public class StatReport {
	
	@Autowired
	@Qualifier("hiveHandler")
	public HiveDao hiveDao;
	
//	@Autowired
//	@Qualifier("cacheManager")
//	public CacheManager cacheMgr;
	
	@Autowired
	@Qualifier("analysisReportService")
	private AnalysisReportService reportService;
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	private static Pattern trade_pattern = Pattern.compile("^http://trade.ehowbuy.com.*");
	/**
	 * 统计二级频道着陆页购买金额
	 */
	private Map<String,Map<String,BigDecimal>> channel_trade_amt = new HashMap<String,Map<String,BigDecimal>>();
	/**
	 * 统计二级频引导到trade的人数
	 */
	private Map<String,Map<String,Integer>> channel_to_trade = new HashMap<String,Map<String,Integer>>();
	
	/**
	 * 统计二级频道引导的开户人数
	 */
	private Map<String,Map<String,Integer>> channel_acct_open = new HashMap<String,Map<String,Integer>>();
	
	/**
	 * 统计二级频道pv
	 */
	private Map<String,Map<String,Integer>> channel_pv = new HashMap<String,Map<String,Integer>>();
	
	/**
	 * 统计二级频道uv
	 */
	private Map<String,Map<String,Integer>> channel_uv = new HashMap<String,Map<String,Integer>>();
	
	/**
	 * 统计二级频道引导的购买人数
	 */
	private Map<String,Map<String,Integer>> channel_trade_num = new HashMap<String,Map<String,Integer>>();
	
	/**
	 * 按二级频道，url,基金代码记录购买金额
	 */
	private Map<String,Map<String,Map<String,BigDecimal>>> channel_url_fundcode_amt = new HashMap<String,Map<String,Map<String,BigDecimal>>>();
	
	
	private Map<String,Set<String>> channel_urls_show = new HashMap<String,Set<String>>(100);
	
	/**
	 * 按二级频道，url,基金代码统计购买人数
	 */
	private Map<String,Map<String,Map<String,Long>>> channel_url_fundCode_buynum = new HashMap<String,Map<String,Map<String,Long>>>();
	
	
	private static DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * 获取统计日去重cookie
	 * @return
	 * @throws ParseException
	 */
	private List<String> getTotalUser(String statDay) throws ParseException{
		log.info("processing getTotalUser");
		final  List<String> uvSet = new ArrayList<String>(500000);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("statDay", statDay);
		String sql_total_uv = "select distinct pv.src_cookie from page_view pv where from_unixtime(cast(pv.src_time as BIGINT),'yyyy-MM-dd') = :statDay";
		hiveDao.query(sql_total_uv, paramMap, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				uvSet.add(rs.getString(1));
			}
		});
		return uvSet;	
	}
	
	private Map<String,List<UA>> getTotalUA(String statDay){
		
		log.info("processing getTotalUA");
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("statDay", statDay);
		paramMap.put("at1", "4");//开户
		paramMap.put("at2", "6");//买基金
		
		
		final Map<String,List<UA>> tempMap = new HashMap<String, List<UA>>(1000);
		String sql_totol_ua = "select ua.action_time,ua.action_cookie,ua.action_type,ua.action_amount,ua.action_fundcode from user_action ua where from_unixtime(ua.action_time,'yyyy-MM-dd') = :statDay and (ua.action_type=:at1 or ua.action_type=:at2) order by ua.action_cookie";
		hiveDao.query(sql_totol_ua, paramMap, new RowCallbackHandler() {
		
			String tempCookie = "";
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				
				String cookie = rs.getString("action_cookie");
				if(!StringUtil.isEmpty(cookie)){
					UA ua = new UA();
					ua.setAction(rs.getString("action_type"));
					ua.setCookie(cookie);
					try {
						ua.setRecTime(Long.parseLong(rs
								.getString("action_time")));
					} catch (NumberFormatException e) {
						log.warn("cookie:{} process error",cookie);
						return;
					}
					if(ua.getAction().equals("6")){
						ua.setAmt(new BigDecimal(rs.getString("action_amount")));
						ua.setFundCode(rs.getString("action_fundcode"));
					}
					if(cookie.equals(tempCookie)){
						List<UA> uas = tempMap.get(cookie);
						uas.add(ua);
					}else{
						List<UA> uas = new ArrayList<UA>();
						uas.add(ua);
						tempMap.put(cookie, uas);
						tempCookie = cookie;
					}
				}
			}
		});
		log.info("getTotalUA size : {}", tempMap.size());
		return tempMap;
	}
	
	/**
	 * 根据cookie，批量查询PV
	 * @param cookies
	 * @return
	 * @throws ParseException 
	 */
	private Map<String,List<PV>> getPVByCookie(List<String> cookies,String statDay) throws ParseException{
		log.debug("getPVByCookie,size {}",cookies.size());
		String sql_to_getPV = "select pv.src_time, pv.src_cookie,pv.src_url,pv.dest_url from page_view pv where pv.src_cookie in (:cookies) and from_unixtime(cast(pv.src_time as BIGINT),'yyyy-MM-dd') = :statDay order by pv.src_cookie,pv.src_time asc";
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("cookies", cookies);
		paramMap.put("statDay", statDay);
		final Map<String,List<PV>> tempMap = new HashMap<String, List<PV>>(1000);
		hiveDao.query(sql_to_getPV, paramMap, new RowCallbackHandler() {
			String tempcookie = "";
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				String cookie = rs.getString("src_cookie");
				if(!StringUtil.isEmpty(cookie)){
					PV pv = new PV();
					pv.setCookie(cookie);
					pv.setDestUrl(rs.getString("dest_url"));
					pv.setSrcUrl(rs.getString("src_url"));
					if(cookie.equals(tempcookie)){
						List<PV> pvs = tempMap.get(cookie);
						pvs.add(pv);
					}else{
						List<PV> pvs = new ArrayList<PV>();
						pvs.add(pv);
						tempMap.put(cookie, pvs);
					}
				}
			}
		});
		return tempMap;
		
	}
	
	private Map<String,List<PV>> getPVByStatDay(String statDay){
		log.info("processing getPVByStatDay");
		String sql_to_getPV = "select pv.src_time, pv.src_cookie,pv.src_url,pv.dest_url from page_view pv where from_unixtime(cast(pv.src_time as BIGINT),'yyyy-MM-dd') = :statDay order by pv.src_cookie,pv.src_time asc";
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("statDay", statDay);
		final Map<String,List<PV>> tempMap = new HashMap<String, List<PV>>(1000);
		hiveDao.query(sql_to_getPV, paramMap, new RowCallbackHandler() {
			String tempcookie = "";
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				String cookie = rs.getString("src_cookie");
				if(!StringUtil.isEmpty(cookie)){
					PV pv = new PV();
					pv.setCookie(cookie);
					pv.setDestUrl(rs.getString("dest_url"));
					pv.setSrcUrl(rs.getString("src_url"));
					try {
						pv.setRecTime(Long.parseLong(rs.getString("src_time")));
					} catch (NumberFormatException e) {
						log.warn("cookie:{} process error",cookie);
						return;
					}
					if(cookie.equals(tempcookie)){
						List<PV> pvs = tempMap.get(cookie);
						pvs.add(pv);
					}else{
						List<PV> pvs = new ArrayList<PV>();
						pvs.add(pv);
						tempMap.put(cookie, pvs);
						tempcookie = cookie;
					}
				}
			}
		});
		log.info("getPVByStatDay size : {}",tempMap.size());
		return tempMap;
		
	}
	
	public Date getStatDate(String statDay) throws ParseException{
		return DateUtils.parseDate(statDay);
	}
	
	/**
	 * 扫描PV UA 按每个用户
	 * @param pv
	 * @param ua
	 */
	private void scanUrl(List<PV> pvs,List<UA> uas){
		boolean ispurchase = false,isacctopen = false;
		BigDecimal sum = BigDecimal.ZERO;
		if(null != uas){
			for(UA ua : uas){
				if("4".equals(ua.getAction())){//开户
					isacctopen = true;
				}else if("6".equals(ua.getAction())){//买基金
					ispurchase = true;
					sum = sum.add(ua.getAmt());
				}
			}
		}
		
		
		PV orgPV = null;
		Channel orgChannel = null;
		
		Set<String> uaSet = new HashSet<String>();
		
		
		List<Channel> subChannel = getSubchannelList(channels);
		
		log.debug("subChannel: {}",subChannel);
		
		boolean isReg = false;
		
		for(PV pv : pvs){//按时间升序排列，取第一个匹配的
			
			log.debug("processing pv : {}",pv.getDestUrl());
			
			if(StringUtil.isEmpty(pv.getDestUrl()))
				continue;
			
			
			for(Channel channel : subChannel){
				
				if(pv.getDestUrl().matches(channel.getUrlRegex())){
					
					recChannelPV(channel, pv);
					
					if(!uaSet.contains(pv.getDestUrl())){
						uaSet.add(pv.getDestUrl());
						recChannelUV(channel, pv);
					}
					
					if(!isReg && (ispurchase || isacctopen)){
						
						isReg = true;
						
						recChanneShowURL(channel, pv);
						
						if(isacctopen){//开户
							recChannelAcctOpen(channel, pv);
						}
						
						if(ispurchase){//买基金
							recChannelTradeAmt(channel, pv,sum,uas);
							recChannelTradeNum(channel, pv);
							recChannelUrlFundCodeAmt(channel, pv, uas);
							recChannelUrlFundCodeNum(channel, pv, uas);
						}
						
						recChannel2Trade(channel,pv);
						
					}else{//未开户，未购买基金。记录二级频道以及对应PV
						if(orgPV == null)
							orgPV = pv;
						if(orgChannel == null)
							orgChannel = channel;
					}
					
					break;
				}
			}
			
			if(isTradeURL(pv) && orgPV != null && orgChannel != null){
				recChannel2Trade(orgChannel,orgPV);
				return;
			}
		}
	}
	
	

	private boolean isTradeURL(PV pv){
		if(StringUtil.isEmpty(pv.getDestUrl()))
			return false;
		Matcher matcher = trade_pattern.matcher(pv.getDestUrl());
		if(matcher.matches())
			return true;
		else
			return false;
	}
	
	/**
	 * 记录到达交易页面以及对应的二级频道
	 * @param channel
	 * @param pv
	 */
	private void recChannel2Trade(Channel channel,PV pv){
		Map<String,Integer> channeltrade = channel_to_trade.get(channel.getId());
		if(null == channeltrade){//主频道为空
			channeltrade = new HashMap<String,Integer>();
			channeltrade.put(pv.getDestUrl(), 1);
			channel_to_trade.put(channel.getId(), channeltrade);
		}else{
			if(null == channeltrade.get(pv.getDestUrl())){//二级频道为空
				channeltrade.put(pv.getDestUrl(), 1);
			}else{
				Integer count = channeltrade.get(pv.getDestUrl());
				channeltrade.put(pv.getDestUrl(), count + 1);
			}
		}
	}
	
	
	/**
	 * 记录基金购买次数
	 * @param fundCode
	 */
	private void recChannelUrlFundCodeNum(Channel channel,PV pv,List<UA> uas){
		
		Map<String,Map<String,Long>> url_fundcode_num = channel_url_fundCode_buynum.get(channel.getId());
		if(null == url_fundcode_num)//主频道为空
			url_fundcode_num = new HashMap<String,Map<String,Long>>();
		calFundCodeNum(uas, url_fundcode_num,pv);
		channel_url_fundCode_buynum.put(channel.getId(), url_fundcode_num);
	}
	
	private void calFundCodeNum(List<UA> uas,Map<String,Map<String,Long>> url_fundcode_num,PV pv){
		
		for(UA ua : uas){
			if("6".equals(ua.getAction())){
				if(url_fundcode_num.get(pv.getDestUrl()) != null){
					Map<String,Long> fundcode_num = url_fundcode_num.get(pv.getDestUrl());
					if(fundcode_num.get(ua.getFundCode()) != null){
						Long num = fundcode_num.get(ua.getFundCode());
						fundcode_num.put(ua.getFundCode(), num + 1);
					}else{
						fundcode_num.put(ua.getFundCode(), 1l);
					}
				}else{
					Map<String,Long> fundcode_num = new HashMap<String, Long>();
					fundcode_num.put(ua.getFundCode(), 1l);
					url_fundcode_num.put(pv.getDestUrl(), fundcode_num);
				}
			}
		}
	}
	
	/**
	 * 记录Channel pv
	 * @param channel
	 * @param pv
	 */
	private void recChannelPV(Channel channel,PV pv){
		Map<String,Integer> channelpv = channel_pv.get(channel.getId());
		if(null == channelpv){//主频道为空
			channelpv = new HashMap<String,Integer>();
			channelpv.put(pv.getDestUrl(), 1);
			channel_pv.put(channel.getId(), channelpv);
		}else{
			if(null == channelpv.get(pv.getDestUrl())){//二级频道为空
				channelpv.put(pv.getDestUrl(), 1);
			}else{
				Integer count = channelpv.get(pv.getDestUrl());
				channelpv.put(pv.getDestUrl(), count + 1);
			}
		}
	}
	
	private void recChannelUV(Channel channel,PV pv){
		Map<String,Integer> channeluv = channel_uv.get(channel.getId());
		if(null == channeluv){//主频道为空
			channeluv = new HashMap<String,Integer>();
			channeluv.put(pv.getDestUrl(), 1);
			channel_uv.put(channel.getId(), channeluv);
		}else{
			if(null == channeluv.get(pv.getDestUrl())){//二级频道为空
				channeluv.put(pv.getDestUrl(), 1);
			}else{
				Integer count = channeluv.get(pv.getDestUrl());
				channeluv.put(pv.getDestUrl(), count + 1);
			}
		}
	}
	
	private void recChannelTradeNum(Channel channel,PV pv){
		Map<String,Integer> channelNum = channel_trade_num.get(channel.getId());
		if(null == channelNum){//主频道为空
			channelNum = new HashMap<String,Integer>();
			channelNum.put(pv.getDestUrl(), 1);
			channel_trade_num.put(channel.getId(), channelNum);
		}else{
			if(null == channelNum.get(pv.getDestUrl())){//二级频道为空
				channelNum.put(pv.getDestUrl(), 1);
			}else{
				Integer count = channelNum.get(pv.getDestUrl());
				channelNum.put(pv.getDestUrl(), count + 1);
			}
		}
	}
	
	/**
	 * 按二级频道 url分组，记录购买基金金额
	 * @param channel
	 * @param pv
	 */
	private void recChannelTradeAmt(Channel channel,PV pv,BigDecimal sum,List<UA> uas){
		Map<String,BigDecimal> purSumMap = channel_trade_amt.get(channel.getId());
		if(null == purSumMap){//主频道为空
			purSumMap = new HashMap<String,BigDecimal>();
			purSumMap.put(pv.getDestUrl(), sum);
			channel_trade_amt.put(channel.getId(), purSumMap);
		}else{
			if(null == purSumMap.get(pv.getDestUrl())){//二级频道为空
				purSumMap.put(pv.getDestUrl(), sum);
			}else{
				BigDecimal amts = purSumMap.get(pv.getDestUrl());
				purSumMap.put(pv.getDestUrl(), amts.add(sum));
				
			}
		}
	}
	
	/**
	 * 记录按二级目录，url,基金代码分组的购买记录
	 */
	private void recChannelUrlFundCodeAmt(Channel channel,PV pv,List<UA> uas){
		Map<String,Map<String,BigDecimal>> url_fundcode_amt = channel_url_fundcode_amt.get(channel.getId());
		if(null == url_fundcode_amt)//主频道为空
			url_fundcode_amt = new HashMap<String,Map<String,BigDecimal>>();
		calFundCodeAmt(uas, url_fundcode_amt,pv);
		channel_url_fundcode_amt.put(channel.getId(), url_fundcode_amt);
	}
	
	public void calFundCodeAmt(List<UA> uas,Map<String,Map<String,BigDecimal>> url_fundcode_amt,PV pv){
		for(UA ua : uas){
			
			if("6".equals(ua.getAction())){
				
				if(url_fundcode_amt.get(pv.getDestUrl()) != null){
					Map<String,BigDecimal> fundcode_amt = url_fundcode_amt.get(pv.getDestUrl());
					if(fundcode_amt.get(ua.getFundCode()) != null){
						BigDecimal amt = fundcode_amt.get(ua.getFundCode());
						fundcode_amt.put(ua.getFundCode(), ua.getAmt().add(amt));
					}else{
						fundcode_amt.put(ua.getFundCode(), ua.getAmt());
					}
				}else{
					Map<String,BigDecimal> fundcode_amt = new HashMap<String, BigDecimal>();
					fundcode_amt.put(ua.getFundCode(), ua.getAmt());
					url_fundcode_amt.put(pv.getDestUrl(), fundcode_amt);
				}
			}
		}
	}
	
	
	
	/**
	 * 按二级频道，url分组，记录开户数量
	 * @param channel
	 * @param pv
	 * @param sum
	 */
	private void recChannelAcctOpen(Channel channel,PV pv){
		Map<String,Integer> acctOpenCountMap = channel_acct_open.get(channel.getId());
		if(null == acctOpenCountMap){//主频道为空
			acctOpenCountMap = new HashMap<String,Integer>();
			acctOpenCountMap.put(pv.getDestUrl(), 1);
			channel_acct_open.put(channel.getId(), acctOpenCountMap);
		}else{
			if(null == acctOpenCountMap.get(pv.getDestUrl())){//二级频道为空
				acctOpenCountMap.put(pv.getDestUrl(), 1);
			}else{
				Integer count = acctOpenCountMap.get(pv.getDestUrl());
				acctOpenCountMap.put(pv.getDestUrl(), count + 1);
			}
		}
	}
	
	/**
	 * 按二级频道分组，记录要显示的url
	 * @param channel
	 * @param pv
	 */
	private void recChanneShowURL(Channel channel,PV pv){
		Set<String> urls = channel_urls_show.get(channel.getId());
		if(null == urls){//主频道为空
			urls = new HashSet<String>();
			urls.add(pv.getDestUrl());
			channel_urls_show.put(channel.getId(), urls);
		}else{
			urls.add(pv.getDestUrl());
		}
	}
	
	
	private List<Channel> channels = null;
	
	/**
	 * 一级频道DTO
	 * @return
	 */
	private List<Channel> initChannels(){
		if(null == channels){
			channels = reportService.queryAllChannel();
		}
		return channels;
	}
	
	public Map<String, String> getSubchannelMap(List<Channel> list){
		if(list != null){
			Map<String, String> map = new HashMap<String, String>();
			for (Channel channel : list) {
				if(channel.getParentId() != null && !"".equals(channel.getParentId())){
					map.put(channel.getId(), channel.getParentId());
				}
			}
			return map;
		}
		return null;
	}
	
	public List<Channel> getSubchannelList(List<Channel> list){
		List<Channel> channels = new ArrayList<Channel>();
		if(list != null){
			for (Channel channel : list) {
				if(channel.getParentId() != null && !"".equals(channel.getParentId())){
					channels.add(channel);
				}
			}
		}
		
		return channels;
	}
	
	
	private List<UA> getUAByCookie(Map<String,List<UA>> uas,String cookie){
		return uas.get(cookie);
	}
	
	/**
	 * 获取统计日期 yyyy-MM-dd
	 * @return
	 * @throws ParseException 
	 */
	public String getStatDay() throws ParseException{
		return DateUtils.getFormatedDate(DateUtils.getYesterdayDate());
	}
	
	public void run() throws Exception{
		dorun(getStatDay());
	}
			
	
	public void dorun(String statDay) throws Exception{
		log.info("----------------" + statDay + " stat start--------------------------");
		long cur = System.currentTimeMillis();
		
		initChannels();
		
//		List<String> cookieList = getTotalUser();
//		String statDay = getStatDay();
		
		Map<String,List<UA>> u_uas = getTotalUA(statDay);
		
//		int startOffset = 0;
//		int batch_size = 200000;
//		int endOffset = 0;
		
//		List<String> cookieList = new ArrayList<String>();
//		cookieList.addAll(uas.keySet());
		
//		cookieList = cookieList.subList(0, 100);
//		log.info("cookieList.size():"  + cookieList.size());
		
		
//		List<String> batch_cookie_list = null;
//		while((startOffset + batch_size) < cookieList.size()){
//			endOffset += batch_size;
//			
//			batch_cookie_list = cookieList.subList(startOffset, endOffset);
//			
//			log.info("processing {} to  {} :",startOffset,endOffset);
//			
//			doScan(batch_cookie_list,uas);
//			
//			startOffset = endOffset;
//			
//		}
		
//		log.info("processing remian : {}",cookieList.size()-startOffset);
//		if((cookieList.size() - startOffset) > 0 ){
//			batch_cookie_list = cookieList.subList(startOffset, cookieList.size());
//			doScan(batch_cookie_list,uas);
//		}
		
		Map<String,List<PV>> u_pvs = getPVByStatDay(statDay);
		doScan(u_pvs, u_uas);
		
		u_pvs = null;
		u_uas = null;
		
		
		
		
		log.info("channel_tradeAmt : {}",channel_trade_amt.size());
		log.info("channel_2trade : {}",channel_to_trade.size());
		log.info("channel_PV : {}",channel_pv.size());
		log.info("channel_UV: {}",channel_uv.size());
		log.info("channel_AcctOpen: {}",channel_acct_open.size());
		
		log.info("saving..............................");
		this.save(statDay);
		
		log.info("consume time : {} seconds", (System.currentTimeMillis() - cur)/1000);
		log.info("----------------" + statDay + " stat finished--------------------------");
	}
	
	/**
	 * 保存分析数据
	 */
	public void save(String statDay) throws Exception{
		
		Map<String,String> levels_map = getSubchannelMap(channels);
		
		log.debug("lveels_map:{}",levels_map);
		
		Date createDate = new Date();
		
		List<SubchannelReport> subChannelRepts = new ArrayList<SubchannelReport>();
		
		for(Iterator<String> iter = channel_urls_show.keySet().iterator();iter.hasNext();){
			String channelId = iter.next();
			Map<String,Integer> pv = channel_pv.get(channelId);
			Map<String,Integer> uv = channel_uv.get(channelId);
			Map<String,Integer> toTrade = channel_to_trade.get(channelId);
			Map<String,Integer> acctOpen = channel_acct_open.get(channelId);
			Map<String,BigDecimal> tradeAmt = channel_trade_amt.get(channelId);
			Map<String,Integer> tradeNum = channel_trade_num.get(channelId);
			
			for(String url : channel_urls_show.get(channelId)){
				long perUrlPV = 0,perUrlUV = 0,perUrl2Trade = 0,perUrlopenacct = 0,perUrlTradeNum = 0;
				BigDecimal perUrlTradeAmt = BigDecimal.ZERO;
				
				if(null != pv){
					
					perUrlPV = pv.get(url) == null ? 0 : pv.get(url);
					
//					for(Map.Entry<String, Integer> pvEntry : pv.entrySet()){
//						if(url.equals(pvEntry.getKey()))
//							perUrlPV += pvEntry.getValue();
//					}
				}
				
				if(null != uv){
					
					perUrlUV = uv.get(url) == null ? 0 : uv.get(url);
					
//					for(Map.Entry<String, Integer> uvEntry : uv.entrySet()){
//						if(url.equals(uvEntry.getKey())){
//							perUrlUV += uvEntry.getValue();
//						}
//					}
				}
				
				if(null != toTrade){
					
					perUrl2Trade = toTrade.get(url) == null ?  0 :toTrade.get(url);
					
				}
				
				if(null != acctOpen){
					
					perUrlopenacct = acctOpen.get(url) == null ? 0 : acctOpen.get(url);
					
				}
				
				if(null != tradeAmt){
					
					perUrlTradeAmt = tradeAmt.get(url) == null ? BigDecimal.ZERO : tradeAmt.get(url);
					
				}
				
				if(null != tradeNum){
					
					perUrlTradeNum = tradeNum.get(url) == null ? 0 : tradeNum.get(url);
					
					for(Map.Entry<String, Integer> tradenumEntry : tradeNum.entrySet()){
						if(url.equals(tradenumEntry.getKey()))
							perUrlTradeNum += tradenumEntry.getValue();
					}
				}
				
				SubchannelReport subChanelRep = new SubchannelReport();
				subChanelRep.setChannelId(channelId);
				subChanelRep.setParentId(levels_map.get(channelId));
				subChanelRep.setAmountTotal(perUrlTradeAmt);
				subChanelRep.setTradeTotal(new BigDecimal(perUrlTradeNum));
				subChanelRep.setUserTotal(new BigDecimal(perUrlUV));
				subChanelRep.setUv(new BigDecimal(perUrlUV));
				subChanelRep.setPv(new BigDecimal(perUrlPV));
				subChanelRep.setOpenAccountTotal(new BigDecimal(perUrlopenacct));
				subChanelRep.setToTradeTotal(new BigDecimal(perUrl2Trade));
				subChanelRep.setCreateDateTime(createDate);
				subChanelRep.setReportDateTime(getStatDate(statDay));
				subChanelRep.setPageUrl(url);
				subChannelRepts.add(subChanelRep);
				
			}
			
		}
		
		List<TradeDetail> tradeList = new ArrayList<TradeDetail>();
			
		for(Map.Entry<String, Map<String,Map<String,BigDecimal>>> entry : channel_url_fundcode_amt.entrySet()){
			String channelId = entry.getKey();
			Map<String,Map<String,BigDecimal>> url_fundcode_amt_map = entry.getValue();
			
			for(Map.Entry<String, Map<String,BigDecimal>> url_fund_amt_entry : url_fundcode_amt_map.entrySet()){
				String url = url_fund_amt_entry.getKey();
				log.debug("------------url:{}--------------",url);
				Map<String,BigDecimal> fundcode_amt_map = url_fund_amt_entry.getValue();
				
				for(Map.Entry<String, BigDecimal> fundcode_amt_entry : fundcode_amt_map.entrySet()){
					Long num = channel_url_fundCode_buynum.get(channelId).get(url).get(fundcode_amt_entry.getKey());
					TradeDetail detail = new TradeDetail();
					detail.setChannelId(channelId);
					detail.setTradeType("6");
					detail.setFundCode(fundcode_amt_entry.getKey());
					detail.setUrl(url);
					detail.setUserTotal(new BigDecimal(num));
					detail.setAmountTotal(fundcode_amt_entry.getValue());
					detail.setCreateDateTime(createDate);
					detail.setReportDateTime(getStatDate(statDay));
					tradeList.add(detail);
				}
			}
		}
		
		reportService.batchSaving(subChannelRepts, tradeList, null);
	}
	
	
	/**
	 * 按cookie，找到对应的ua，pv，进行行为扫描
	 * @param u_pvs
	 * @param u_ua
	 */
	private void doScan(Map<String,List<PV>> u_pvs,Map<String,List<UA>> u_ua){
		for(Map.Entry<String, List<PV>> entry : u_pvs.entrySet()){
				
			List<UA> perUA = getUAByCookie(u_ua, entry.getKey());
			
			scanUrl(entry.getValue(), perUA);
		}
	}
	
}
