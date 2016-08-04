package com.howbuy.uaa.core;

import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.howbuy.uaa.quartz.Howbuy2EhowbuyStat;
import com.howbuy.uaa.quartz.PVUVStat;

/**
 * HiveDao Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>���� 4, 2013</pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:context/spring/applicationContext-quartz-test.xml"})
public class QuartzTester {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    
   @Test
   public void testQuartz() throws IOException{
	   System.out.println("-----------start-------------");
	   System.out.println(new InputStreamReader(System.in).read());
	   System.out.println("--------------finish----------------");
   }
    
}
