package com.howbuy.uaa.core;

import java.text.ParseException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.howbuy.uaa.quartz.Howbuy2EhowbuyIntervalStat;
import com.howbuy.uaa.quartz.Howbuy2EhowbuyStat;
import com.howbuy.uaa.quartz.PVUVStat;
import com.howbuy.uaa.quartz.StatReport;

/**
 * HiveDao Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>���� 4, 2013</pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:context/spring/applicationContext-hive.xml",
								   "classpath:context/spring/applicationContext-quartz-stat.xml",
								   "classpath:context/spring/applicationContext-datasource.xml",
								   "classpath:context/spring/applicationContext-dozer.xml",
								   "classpath:context/spring/applicationContext-manager.xml",
								   "classpath:context/spring/applicationContext.xml"})
public class StatHiveTest {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    
    
    @Autowired
    @Qualifier("Howbuy2EhowbuyStat")
    private Howbuy2EhowbuyStat howbuy2EhowbuyStat;
    
    @Autowired
    @Qualifier("PVUVStat")
    PVUVStat pvusStat;
    
    @Autowired
    @Qualifier("StatReport")
    StatReport statReport;
    
    
    @Autowired
    @Qualifier("Howbuy2EhowbuyIntervalStat")
    Howbuy2EhowbuyIntervalStat howbuy2EhowbuyIntervalStat;

//    @Before
//    public void before() throws Exception {
//
//    }
//
//    @After
//    public void after() throws Exception {
//    }
    
    @Test
    public void  runStatReport()throws Exception{
    	statReport.dorun("2015-02-09");
    }

    
    @Test
    public void runHiveQuartz() throws Exception {
       howbuy2EhowbuyStat.run();
    }
    
    
    @Test
    public void runHivePVUV() throws ParseException{
    	pvusStat.run();
    }
    
    @Test
    public void runHivehowbuy2EhowbuyIntervalStat() throws Exception{
    	howbuy2EhowbuyIntervalStat.dorun("2015-02-02");
    }

}
