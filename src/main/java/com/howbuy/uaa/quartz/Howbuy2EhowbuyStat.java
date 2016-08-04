package com.howbuy.uaa.quartz;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
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
import com.howbuy.uaa.persistence.Howbuy2EhowbuyStatReport;
import com.howbuy.uaa.quartz.dto.AnaLysisObj;
import com.howbuy.uaa.quartz.dto.PV;
import com.howbuy.uaa.quartz.dto.UA;
import com.howbuy.uaa.service.AnalysisReportService;
import com.howbuy.uaa.utils.DateUtils;

public class Howbuy2EhowbuyStat {
	
	@Autowired
	@Qualifier("hiveHandler")
	public HiveDao hiveDao;
	
	@Autowired
	@Qualifier("analysisReportService")
	private AnalysisReportService reportService;
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	private static Pattern trade_pattern = Pattern.compile("^http[s]{0,1}://trade\\.ehowbuy\\.com.*|^http[s]{0,1}://www\\.ehowbuy\\.com.*");
	
	private static Pattern howbuy_pattern = Pattern.compile("^http://www\\.howbuy\\.com.*|^http://simu\\.howbuy\\.com.*");
	
	/**
	 * 判断url是否是howbuy.com
	 * @param url
	 * @return
	 */
	private boolean isFromHowbuy(String url){
		if(StringUtil.isEmpty(url))
			return false;
		Matcher matcher = howbuy_pattern.matcher(url);
		return matcher.matches();
	}
	
	
	
	private Map<String,List<UA>> getUAByStatDay(String statDay){
		
		log.info("processing getTotalUA");
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("statDay", statDay);
		paramMap.put("at1", "4");
		paramMap.put("at2", "6");
		
		
		UARowCallbackHandler uahandler = new UARowCallbackHandler();
		String sql_totol_ua = "select ua.action_time,ua.action_cookie,ua.action_type,ua.action_amount,ua.action_fundcode from user_action ua where from_unixtime(ua.action_time,'yyyy-MM-dd') = :statDay and (ua.action_type=:at1 or ua.action_type=:at2) order by ua.action_cookie";
		hiveDao.query(sql_totol_ua, paramMap, uahandler);
		log.info("getTotalUA count : {}", uahandler.count);
		return uahandler.getStatMap();
	}
	
	private Map<String,List<PV>> getPVByStatDay(String statDay){
		log.info("processing getPVByStatDay");
		
		String sql_to_getPV = "select pv.src_time, pv.src_cookie,pv.src_url,pv.dest_url,pv.dest_channel,pv.dest_subchannel,pv.dest_land from page_view pv where from_unixtime(cast(pv.src_time as BIGINT),'yyyy-MM-dd') = :statDay order by pv.src_cookie,pv.src_time asc";
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("statDay", statDay);
		
		PVRowCallbackHandler rowHandler = new PVRowCallbackHandler();
		
		hiveDao.query(sql_to_getPV, paramMap, rowHandler);
		
		log.info("getPVByStatDay count : {}",rowHandler.count());
		
		return rowHandler.getStatMap();
		
	}
	
	public Date getStatDate(String statDay) throws ParseException{
		return DateUtils.parseDate(statDay);
	}
	
	/**
	 * 合并pv,ua，按时间排序
	 * @param pvs
	 * @param uas
	 * @return
	 */
	protected List<AnaLysisObj> merge(List<PV> pvs,List<UA> uas){
		
		List<AnaLysisObj> all = new ArrayList<AnaLysisObj>();
		all.addAll(pvs);
		if(null != uas)
			all.addAll(uas);
		
		Collections.sort(all,new Comparator<AnaLysisObj>() {

			@Override
			public int compare(AnaLysisObj o1, AnaLysisObj o2) {
				
				return (int)(o1.getRecTime() - o2.getRecTime());
				
			}
			
		});
		return all;
	}
	
	/**
	 * 扫描PV UA 按每个用户
	 * @param pv
	 * @param ua
	 */
	private void scanUrl(List<PV> pvs,List<UA> uas){
		
		try {
			List<AnaLysisObj> mergedList = merge(pvs, uas);
			int offset = -1;
			//找到第一个howbuy url
			for (int i = 0; i < mergedList.size(); i++) {

				AnaLysisObj analysis = mergedList.get(i);

				if (StringUtil.isEmpty(analysis.getDestUrl())) {
					log.debug("index {},desturl is null,cookie:", i,
							analysis.getCookie());
					continue;
				}

				if (isFromHowbuy(analysis.getDestUrl())) {
					offset = i;
					break;
				}
			}
			if (offset == -1) {
				return;
			}
			
			//第一个howbuy 页面
			PV firstPV = (PV) mergedList.get(offset);
			boolean isFirstTime = isFirstTime(firstPV);
			if(isFirstTime){
				
				//howbu增量uv
				recordHowbuyAddedUV(firstPV);
			}
			
			List<AnaLysisObj> sublist = mergedList.subList(offset,mergedList.size());
			
			if (sublist.size() == 1)
				return;
			
			if (isFirstTime) {//首次访问

				for (int i = 1; i < sublist.size(); i++) {
					AnaLysisObj analysisobj = sublist.get(i);
					if (PV.class.isInstance(analysisobj)) {
						PV pv = (PV) analysisobj;
						if (isTradeURL(pv.getDestUrl())) {
							//ehowbuy 增量uv
							recordEhowbuyAddedUV(firstPV);
						}
					} else {//ehowby 增量交易

						UA ua = (UA) analysisobj;
						recordEhowbuyAddedTradeInfo(firstPV, ua);
					}
				}
			} else {

				//无开户、交易
				if (null == uas)
					return;

				int prevOffset = -1;
				for (int i = 1; i < sublist.size(); i++) {
					AnaLysisObj analysisobj = sublist.get(i);
					AnaLysisObj prevAnalysisobj = sublist.get(i - 1);

					//搜寻交易前导页面 
					if (isFromHowbuy(prevAnalysisobj.getDestUrl())) {

						if (isFromHowbuy(analysisobj.getSrcUrl())
								&& isTradeURL(analysisobj.getDestUrl())) {

							prevOffset = i - 1;
							break;

						}
					}
				}

				if (-1 == prevOffset)
					return;

				if (prevOffset == sublist.size() - 1)
					return;

				//前导页 
				PV prevPV = (PV) sublist.get(prevOffset);

				List<AnaLysisObj> prevSubList = sublist.subList(prevOffset,
						sublist.size());

				for (int i = 1; i < prevSubList.size(); i++) {

					AnaLysisObj analysisobj = prevSubList.get(i);

					if (PV.class.isInstance(analysisobj)) {
						PV pv = (PV) analysisobj;
						if (isTradeURL(pv.getDestUrl())) {
							//ignore
						}
					} else {//UA

						UA ua = (UA) analysisobj;
						recordLastUrl(prevPV);
						recordLasturlTradeInfo(prevPV, ua);
					}
				}

			}
		} catch (Exception e) {
			log.warn("processor",e);
		}
		
	}
	
	
	private Map<String,PV> lasturl__map = new HashMap<String,PV>(1000);
	
	/**
	 * 记录前导页 uv
	 * @param firstPV
	 */
	private void recordLastUrl(PV firstPV) {
		if(null == lasturl__map.get(firstPV.getDestUrl()))
			lasturl__map.put(firstPV.getDestUrl(),firstPV);
	}


	private Map<String,Set<PV>> ehowbuy_added_uvSet_map = new HashMap<String,Set<PV>>();
	
	
	/**
	 * ehowbuy新增UV
	 * @param 首次访问页面 firstPV
	 */
	private void recordEhowbuyAddedUV(PV firstPV) {
		
		Set<PV> pv_set = ehowbuy_added_uvSet_map.get(firstPV.getDestUrl());
		if(pv_set == null){
			pv_set = new HashSet<PV>();
			ehowbuy_added_uvSet_map.put(firstPV.getDestUrl(), pv_set);
		}
		pv_set.add(firstPV);
	}
	
	private long getEhowbuyNewAddUV(String url){
		return ehowbuy_added_uvSet_map.get(url) == null ? 0 : ehowbuy_added_uvSet_map.get(url).size();
	}
	
	/**
	 * 首次访问页面 新增howbuy_UV
	 */
	private Map<String,Set<PV>> howbuy_added_uv_uvSet_map = new HashMap<String,Set<PV>>();
	
	/**
	 * 记录howbuy新增UV
	 */
	private void recordHowbuyAddedUV(PV pv){
		Set<PV> pv_set = howbuy_added_uv_uvSet_map.get(pv.getDestUrl());
		if(pv_set == null){
			pv_set = new HashSet<PV>();
			howbuy_added_uv_uvSet_map.put(pv.getDestUrl(), pv_set);
		}
		pv_set.add(pv);
	}
	
	
	/**
	 * 访问页面uv记录
	 */
	private Map<String,Set<String>> pageURL_UV_map = new HashMap<String, Set<String>>(1000);
	
	/**
	 * 记录页面pv访问量
	 */
	private Map<String,Long> pageURL_PV_map = new HashMap<String, Long>(1000);
	
	/**
	 * 记录howbuy 访问页面 pv、uv
	 * @param pv
	 */
	private void recordHowbuyPVANDUV(List<PV> pvs){
		
		for(int i = 0; i < pvs.size(); i++){
			PV pv = pvs.get(i);
			
			if(StringUtil.isEmpty(pv.getDestUrl()))
				continue;
			
			if(pageURL_PV_map.get(pv.getDestUrl()) == null){
				pageURL_PV_map.put(pv.getDestUrl(), 1l);
			}else{
				long count = pageURL_PV_map.get(pv.getDestUrl());
				pageURL_PV_map.put(pv.getDestUrl(), count+1);
			}
			
			if(pageURL_UV_map.get(pv.getDestUrl()) == null){
				Set<String> cookies = new HashSet<String>(100);
				cookies.add(pv.getCookie());
				pageURL_UV_map.put(pv.getDestUrl(), cookies);
			}else{
				pageURL_UV_map.get(pv.getDestUrl()).add(pv.getCookie());
			}
		}
	}
	
	
	


	/**
	 * 首次访问页面
	 */
	private Map<String,Long> ehowbuy_added_openacct_count = new HashMap<String, Long>();
	
	private Map<String,Long> ehowbuy_added_purchase_count = new HashMap<String, Long>();
	
	
	/**
	 * 前导页面
	 */
	private Map<String,Long> lasturl_openacct_count = new HashMap<String, Long>();
	
	private Map<String,Long> lasturl_purchase_count = new HashMap<String, Long>();
	
	
	/**
	 * 根据url获取开户数
	 * @param url
	 * @return
	 */
	protected long getEhowbuyAddedOpenAcctNum(String url){
		return ehowbuy_added_openacct_count.get(url) == null ? 0 : ehowbuy_added_openacct_count.get(url);
	}
	
	protected long getLasturlOpenAcctNum(String url){
		return lasturl_openacct_count.get(url) == null ? 0 : lasturl_openacct_count.get(url);
	}
	
	
	/**
	 * 根据url获取购买数
	 * @param url
	 * @return
	 */
	protected long getEhowbuyAddedPurNum(String url){
		return ehowbuy_added_purchase_count.get(url) == null ? 0 : ehowbuy_added_purchase_count.get(url);
	}
	
	protected long getLasturlPurNum(String url){
		return lasturl_purchase_count.get(url) == null ? 0 : lasturl_purchase_count.get(url);
	}
	
	
	
	
	/**
	 * 记录url pv
	 */
	
	
	
	/**
	 * 记录pv带来的ua数据
	 * @param firstPV
	 * @param ua
	 */
	private void recordEhowbuyAddedTradeInfo(PV firstPV, UA ua) {
		
		if(ua.getAction().equals("6")){
			
			if(ehowbuy_added_purchase_count.get(firstPV.getDestUrl()) == null){
				ehowbuy_added_purchase_count.put(firstPV.getDestUrl(), 1l);
			}else{
				long count = ehowbuy_added_purchase_count.get(firstPV.getDestUrl());
				ehowbuy_added_purchase_count.put(firstPV.getDestUrl(), count + 1l);
			}
			
		}else if(ua.getAction().equals("4")){
			
			if(ehowbuy_added_openacct_count.get(firstPV.getDestUrl()) == null){
				ehowbuy_added_openacct_count.put(firstPV.getDestUrl(), 1l);
			}else{
				long count = ehowbuy_added_openacct_count.get(firstPV.getDestUrl());
				ehowbuy_added_openacct_count.put(firstPV.getDestUrl(), count + 1l);
			}
		}
		
	}
	
	private void recordLasturlTradeInfo(PV firstPV, UA ua) {
		
		if(ua.getAction().equals("6")){
			
			if(lasturl_purchase_count.get(firstPV.getDestUrl()) == null){
				lasturl_purchase_count.put(firstPV.getDestUrl(), 1l);
			}else{
				long count = lasturl_purchase_count.get(firstPV.getDestUrl());
				lasturl_purchase_count.put(firstPV.getDestUrl(), count + 1l);
			}
			
		}else if(ua.getAction().equals("4")){
			
			if(lasturl_openacct_count.get(firstPV.getDestUrl()) == null){
				lasturl_openacct_count.put(firstPV.getDestUrl(), 1l);
			}else{
				long count = lasturl_openacct_count.get(firstPV.getDestUrl());
				lasturl_openacct_count.put(firstPV.getDestUrl(), count + 1l);
			}
		}
		
	}


	/**
	 * 判断是否是第一次访问howbuy
	 * @param pv
	 * @return
	 */
	private boolean isFirstTime(PV pv){
		
		return "1".equals(pv.getLand());
	}
	
	

	private boolean isTradeURL(String url){
		if(StringUtil.isEmpty(url))
			return false;
		Matcher matcher = trade_pattern.matcher(url);
		return matcher.matches();
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
		try {
			long cur = System.currentTimeMillis();
			
			Map<String,List<UA>> u_uas = getUAByStatDay(statDay);
			
			Map<String,List<PV>> u_pvs = getPVByStatDay(statDay);
			
			doScan(u_pvs, u_uas);
			
			u_pvs = null;
			u_uas = null;
			
			
			log.info("saving..............................");
			this.save(statDay);
			
			log.info("consume time : {} seconds", (System.currentTimeMillis() - cur)/1000);
			log.info("----------------" + statDay + " stat finished--------------------------");
		} catch (Exception e) {
			log.error("process error",e);
		}
	}
	
	
	
	/**
	 * 保存分析数据
	 */
	public void save(String statDay) throws Exception{
		
		Date createDate = new Date();
		
		List<Howbuy2EhowbuyStatReport> firstUrlRepts = new ArrayList<Howbuy2EhowbuyStatReport>();
		
		//首次着陆页
		for(Map.Entry<String, Set<PV>> entry : howbuy_added_uv_uvSet_map.entrySet()){
			String url = entry.getKey();
			long count = entry.getValue().size();
			PV pv = entry.getValue().iterator().next();
			long openAcctNum = getEhowbuyAddedOpenAcctNum(url);
			long purNum = getEhowbuyAddedPurNum(url);
			
			Howbuy2EhowbuyStatReport subChannelRep = new Howbuy2EhowbuyStatReport();
			subChannelRep.setChannelId(pv.getSubChannelID());
			subChannelRep.setParentId(pv.getChannelID());
			subChannelRep.setUv2ehowbuy(getEhowbuyNewAddUV(url));
			subChannelRep.setPv(pageURL_PV_map.get(pv.getDestUrl()));
			subChannelRep.setUv(pageURL_UV_map.get(pv.getDestUrl()).size());
			subChannelRep.setTradeTotal(purNum);
			subChannelRep.setOpenAccountTotal(openAcctNum);
			subChannelRep.setPageUrl(url);
			subChannelRep.setHowbuyAddedUV(count);
			subChannelRep.setPageType("2");
			subChannelRep.setCreateDateTime(createDate);
			subChannelRep.setReportDateTime(getStatDate(statDay));
			firstUrlRepts.add(subChannelRep);
		}
		
		List<Howbuy2EhowbuyStatReport> lastUrlRepts = new ArrayList<Howbuy2EhowbuyStatReport>();
		
		//前导页
		for(Map.Entry<String, PV> entry : lasturl__map.entrySet()){
			String url = entry.getKey();
			PV pv = entry.getValue();
			long openAcctNum = getLasturlOpenAcctNum(url);
			long purNum = getLasturlPurNum(url);
			
			Howbuy2EhowbuyStatReport subChannelRep = new Howbuy2EhowbuyStatReport();
			subChannelRep.setChannelId(pv.getSubChannelID());
			subChannelRep.setParentId(pv.getChannelID());
			subChannelRep.setPv(pageURL_PV_map.get(pv.getDestUrl()));
			subChannelRep.setUv(pageURL_UV_map.get(pv.getDestUrl()).size());
			subChannelRep.setTradeTotal(purNum);
			subChannelRep.setOpenAccountTotal(openAcctNum);
			subChannelRep.setPageUrl(url);
			subChannelRep.setPageType("1");
			subChannelRep.setCreateDateTime(createDate);
			subChannelRep.setReportDateTime(getStatDate(statDay));
			lastUrlRepts.add(subChannelRep);
		}
		
		firstUrlRepts.addAll(lastUrlRepts);
		
		reportService.batchHowbuy2EhowbuyPVUV(firstUrlRepts);
		
	}
	
	
	/**
	 * 按cookie，找到对应的ua，pv，进行行为扫描
	 * @param u_pvs
	 * @param u_ua
	 */
	private void doScan(Map<String,List<PV>> u_pvs,Map<String,List<UA>> u_ua){
		for(Map.Entry<String, List<PV>> entry : u_pvs.entrySet()){
				
			List<UA> perUA = getUAByCookie(u_ua, entry.getKey());
			
			recordHowbuyPVANDUV(entry.getValue());
			
			scanUrl(entry.getValue(), perUA);
		}
	}
	
	
	class UARowCallbackHandler implements RowCallbackHandler{
		
		String currentCookie = "";
		
		long count = 0;
		
		Map<String,List<UA>> statmap = new HashMap<String, List<UA>>(1000);
		
		@Override
		public void processRow(ResultSet rs) throws SQLException {
			
			
			String cookie = rs.getString("action_cookie");
			if(!StringUtil.isEmpty(cookie)){
				UA ua = new UA();
				ua.setAction(rs.getString("action_type"));
				ua.setCookie(cookie);
				try {
					ua.setRecTime(Long.parseLong(rs.getString("action_time")));
				} catch (NumberFormatException e) {
					log.warn("cookie:{} process error",cookie);
					return;
				}
				
				count++;
				
				if(ua.getAction().equals("6")){
					ua.setAmt(new BigDecimal(rs.getString("action_amount")));
					ua.setFundCode(rs.getString("action_fundcode"));
				}
				if(cookie.equals(currentCookie)){
					List<UA> uas = statmap.get(cookie);
					uas.add(ua);
				}else{
					List<UA> uas = new ArrayList<UA>();
					uas.add(ua);
					statmap.put(cookie, uas);
					currentCookie = cookie;
				}
			}
		}
		
		/**
		 * 按用于cookie分组user_action
		 * @return
		 */
		public Map<String,List<UA>> getStatMap(){
			return statmap;
		}
		
		/**
		 * 处理总数
		 * @return
		 */
		public long count(){
			return count;
		}
	}
	
	class PVRowCallbackHandler implements RowCallbackHandler{
		private String currentcookie = "";
		private long count;
		
		final Map<String,List<PV>> statMap = new HashMap<String, List<PV>>(1000);
		
		@Override
		public void processRow(ResultSet rs) throws SQLException {
			String cookie = rs.getString("src_cookie");
			if(!StringUtil.isEmpty(cookie)){
				PV pv = new PV();
				pv.setCookie(cookie);
				pv.setDestUrl(rs.getString("dest_url"));
				pv.setSrcUrl(rs.getString("src_url"));
				pv.setChannelID(rs.getString("dest_channel"));
				pv.setSubChannelID(rs.getString("dest_subchannel"));
				pv.setLand(rs.getString("dest_land"));
				try {
					pv.setRecTime(Long.parseLong(rs.getString("src_time")));
				} catch (NumberFormatException e) {
					log.warn("cookie:{} process error",cookie);
					return;
				}
				
				count++;
				
				if(cookie.equals(currentcookie)){
					List<PV> pvs = statMap.get(cookie);
					pvs.add(pv);
				}else{
					List<PV> pvs = new ArrayList<PV>();
					pvs.add(pv);
					statMap.put(cookie, pvs);
					currentcookie = cookie;
				}
			}
		}
		
		/**
		 * 按用于cookie分组user_action
		 * @return
		 */
		public Map<String,List<PV>> getStatMap(){
			return statMap;
		}
		
		/**
		 * 处理总数
		 * @return
		 */
		public long count(){
			return count;
		}
		
	}
}
