package com.howbuy.uaa.core;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * HiveDao Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>���� 4, 2013</pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:context/spring/applicationContext-hive.xml"})
public class HiveDaoTest {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    
    private String[] args = new String[]{"http://www.howbuy.com/subject/xfcxdltx/","http://www.howbuy.com/subject/xfcbsdcjjtx/"}; 
    
    @Autowired
    @Qualifier("hiveHandler")
    private HiveDao hiveDao;

//    @Before
//    public void before() throws Exception {
//
//    }
//
//    @After
//    public void after() throws Exception {
//    }

    /**
     * Method: extractData(java.sql.ResultSet rs)
     */
    @Test
    public void testExtractData() throws Exception {
        ResultSet res = null;
        String sql = "select src_ip from page_view limit 30000";
        System.out.println("Running: " + sql);
        List list = hiveDao.getJdbcOperations().queryForList(sql);
        for(int i = 0; i < list.size(); i++){
        	Map m = (Map)list.get(i);
        	String destUrl = (String)m.get("dest_url");
        	if(null != destUrl && destUrl.contains("/savingbox/deposit/deposit.htm"))
        		System.out.println("key:" + destUrl);
        }
    }
    
    @Test
    public void testGetPv() throws Exception {
        ResultSet res = null;
        String sql =  "select pv.src_time,pv.src_url,pv.dest_url from page_view pv  where pv.src_cookie = '0x050e11491394eed6' order by pv.src_time asc";
        System.out.println("Running: " + sql);
        hiveDao.getJdbcOperations().query(sql, new RowMapper(){

			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				System.out.println("src_time:" + rs.getString(1) + "	src_url:" + rs.getString(2) + "	dest_url:" + rs.getString(3));
				return null;
			}});
        
//        System.out.println(hiveDao.getJdbcOperations().queryForList(sql, args));
    }
    
    @Test
    public void testGetUv() throws Exception {
    	String sql = "select pv.src_time, pv.src_cookie,pv.src_url,pv.dest_url "
    			+ "from page_view pv "
    			+ "where pv.src_cookie in (:cookies) "
    			+ "and from_unixtime(cast(pv.src_time as BIGINT),'yyyy-MM-dd') = :statDay "
    			+ "order by pv.src_cookie,pv.src_time asc";
    	System.out.println("Running:" + sql);
//    	String[] cookies = new String[]{"0x00002bb4eae2140e","0x000016810626351d","0x00003414936ee5ac"};
    	List<String> cookies = new ArrayList<String>();
    	cookies.add("0x00002bb4eae2140e");
    	cookies.add("0x000016810626351d");
    	cookies.add("0x00003414936ee5ac");
    	
    	Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("cookies", cookies);
		paramMap.put("statDay", "2014-12.24");
		
		hiveDao.query(sql, paramMap, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				System.out.println("src_cookie:" + rs.getString("src_cookie") + " src_time:" + rs.getString("src_time") + " dest_url:" + rs.getString("dest_url"));
			}
		});
    }
    
    
    @Test
    public void testgetTotalUser() throws Exception {
    	String sql = "select distinct pv.src_cookie "
    			+ "from page_view pv where from_unixtime(cast(pv.src_time as BIGINT),'yyyy-MM-dd') = :statDay";
    	System.out.println("Running:" + sql);
    	
    	
    	Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("statDay", "2014-12-22");
		
		hiveDao.query(sql, paramMap, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				System.out.println("src_cookie:" + rs.getString("src_cookie"));
			}
		});
    }
    
    @Test
    public void testgetTotalUA() throws Exception {
    	String sql = "select ua.action_cookie,ua.action_type,ua.action_amount,ua.action_fundcode from user_action ua "
//    			+ "where from_unixtime(ua.action_time,'yyyy-MM-dd') ='2014-12-22'";
    			+ "where from_unixtime(ua.action_time,'yyyy-MM-dd') = :statDay "
    			+ "and (ua.action_type=:at1 or ua.action_type=:at2) order by ua.action_cookie";
//    			+ "and (ua.action_type='4' or ua.action_type='6') order by ua.action_cookie";
    	System.out.println("Running:" + sql);
    	
    	
    	Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("statDay", "2014-12-22");
		paramMap.put("at1", "4");
		paramMap.put("at2", "6");
		
		hiveDao.query(sql, paramMap, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				System.out.println("src_cookie:" + rs.getString("action_cookie") + " action_type:" + rs.getString("action_type") + " action_amt:" + rs.getString("action_amount"));
			}
		});
		
//		List ret = hiveDao.getJdbcOperations().queryForList(sql);
//		System.out.println(ret.size());
    }
    
    @Test
    public void testConn(){
    	String sql = "select count(1) count from user_action";
    	Map<String, Object> paramMap = new HashMap<String, Object>();
    	hiveDao.query(sql, paramMap, new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				System.out.println(rs.getString("count"));
			}
		});
    }
    

}
