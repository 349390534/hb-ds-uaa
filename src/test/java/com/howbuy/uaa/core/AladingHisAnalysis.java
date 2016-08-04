package com.howbuy.uaa.core;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.howbuy.uaa.utils.DateUtils;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:context/spring/applicationContext-hive.xml"})
public class AladingHisAnalysis {
	
	DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    
    /**
     * 按日期，cookie 分组用户
     */
    private Map<String,Map<String,Boolean>> stat = new LinkedHashMap<String, Map<String,Boolean>>();
    
    
	private Map<Integer,Map<Integer,Integer>> TYPE_COUNT = new HashMap<Integer, Map<Integer,Integer>>();

	private Set<String> countset = new HashSet<String>();
    
    
    @Autowired
    @Qualifier("hiveHandler")
    private HiveDao hiveDao;

    @Test
    public void extractAladingHis(){
    	String sql = "select pv.key,pv.src_time,pv.src_cookie,pv.src_url,pv.dest_url from partition_pv pv where pv.dt>=2015-02-09 and pv.dt<=2015-05-09";
    	Map param = new HashMap();
    	hiveDao.query(sql, param, new RowCallbackHandler() {
    		
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				
				String cookie = rs.getString("src_cookie");
				String desturl = rs.getString("dest_url");
				String srcurl = rs.getString("src_url");
				if(StringUtils.isEmpty(rs.getString("src_time"))){
					log.warn("no src_time,key:{}",rs.getString("key"));
					return;
				}
				
				if(StringUtils.isEmpty(desturl)){
					log.warn("no desturl,key:{}",rs.getString("key"));
					return;
				}
				if(StringUtils.isEmpty(cookie)){
					log.warn("no cookie");
					return;
				}
				
				String time = rs.getString("src_time");
				//1423454114
				String formatDate = format.format(new Date(Long.parseLong(time)  * 1000));
				
				
				if(stat.get(formatDate) != null){
					Map<String,Boolean> cookiemap = stat.get(formatDate);
					
					if(Boolean.TRUE == cookiemap.get(cookie))
						return;
					
					cookiemap.put(cookie, isAladingSrc(srcurl,desturl));
					
				}else{
					Map<String,Boolean> cookiemap = new LinkedHashMap<String,Boolean>();
					cookiemap.put(cookie, isAladingSrc(srcurl,desturl));
					stat.put(formatDate, cookiemap);
				}
				
				countset.add(cookie);
			}
		});
    	
    	log.info("distinct uv:{}",countset.size());
    	countset = null;
    	
    	for(String date : stat.keySet()){
    		Map<String,Boolean> cookiemap = stat.get(date);
    		Iterator<Map.Entry<String, Boolean>> iter = cookiemap.entrySet().iterator();
    		while(iter.hasNext()){
    			Map.Entry<String, Boolean> entry = iter.next();
    			String cookie = entry.getKey();
    			log.debug("scan date:{}",date);
    			scan(date,cookie);
    			
    		}
    		
    	}
    	
    	log.info("type1:{},type2:{},type3:{},type4:{}",type1,type2,type3,type4);
    	log.info("TYPE_COUNT:{}",TYPE_COUNT);
    }
    
    
    //typ1:1 typ2:2
    private void scan(String date,String cookie){
    	String nextDate = getNextDay(date);
    	
    	if(stat.get(nextDate)==null){
    		return;
    	}
    		
    	
    	int duration = 1;
    	
    	int type = 0;
    	
    	while(stat.get(nextDate) != null){
    		Map<String,Boolean> cookiemap = stat.get(nextDate);
    		
    		if(cookiemap.get(cookie) != null){
    			
    			type |= cookiemap.get(cookie) ? 1 : 2;
    			
    			cookiemap.remove(cookie);
    			
    			duration++;
    		}
    		
    		nextDate = getNextDay(nextDate);
    		
    	}
    	
    	if(type == 0)
    		type4++;
    	else if(type == 1)
    		type1++;
    	else if(type == 2)
    		type2++;
    	else if(type == 3)
    		type3++;
    	else{
    		log.warn("no matched type:{}",type);
    		return;
    	}
    	
    	Map<Integer,Integer> type_num = TYPE_COUNT.get(type);
    	if(type_num == null){
    		Map<Integer,Integer> tm = new HashMap<Integer, Integer>();
    		tm.put(duration, 1);
    		TYPE_COUNT.put(type, tm);
    	}else{
    		Integer count = type_num.get(duration);
    		if(count != null){
    			count = count + 1;
    		}else{
    			count = 1;
    		}
    		type_num.put(duration, count);
    	}
    	
    }
    
    
    private boolean isAladingSrc(String srcurl,String desturl){
    	if(!StringUtils.isEmpty(srcurl) && srcurl.contains("baidu")
    		&& desturl.contains("source=aladdin"))
    		return true;
    	return false;
    }
    
    //始终alading访问
    private int type1;
    //第一次阿拉丁，后续非阿拉丁
    private int type2;
    //第一次阿拉丁，后续混合访问
    private int type3;
    //第一次阿拉丁，后续没有访问
    private int type4;
   

    private String getNextDay(String day){
    	Date date = DateUtils.parseDate(day);
    	
    	String nextDate;
    	if("2015-02-19".equals(day)){//20日无数据
    		nextDate = DateUtils.getFormatedDate(DateUtils.addDays(date, 2));
    	}else{
    		nextDate = DateUtils.getFormatedDate(DateUtils.addDays(date, 1));
    	}
    	return nextDate;
    }
}
