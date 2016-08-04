package com.howbuy.uaa.core;


import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.howbuy.uaa.command.RouteManageCommand;
import com.howbuy.uaa.dto.RouteDetailDto;
import com.howbuy.uaa.persistence.Channel;
import com.howbuy.uaa.persistence.ChannelReport;
import com.howbuy.uaa.persistence.Howbuy2Ehowbuy;
import com.howbuy.uaa.persistence.OpenAccountDetail;
import com.howbuy.uaa.persistence.SubchannelReport;
import com.howbuy.uaa.persistence.TradeDetail;
import com.howbuy.uaa.service.AnalysisDataToolService;
import com.howbuy.uaa.service.AnalysisReportService;

@ContextConfiguration(locations={
		"classpath:/context/spring/applicationContext-datasource.xml"
//		,"classpath:/context/spring/applicationContext-dozer.xml"
		,"classpath:/context/spring/applicationContext-manager.xml"
})
public class MysqlDataSourceTest extends AbstractJUnit4SpringContextTests{
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AnalysisReportService analysisReportService;
	@Autowired
	private AnalysisDataToolService analysisConsoleService;
	
	@Test
	public void testTx(){
		
		Channel channel = new Channel();
		channel.setName("公募档案页");
		channel.setUrlRegex("^www.howbuy.com/sub/*$");
		channel.setCreateDateTime(new Date());
		channel = analysisReportService.saveChannel(channel);
		
		Channel subchannel = new Channel();
		subchannel.setName(channel.getName()+"_子频道"+new Random().nextInt(100));
		subchannel.setUrlRegex("^www.howbuy.com/sub/*$");
		subchannel.setCreateDateTime(new Date());
		subchannel.setParentId(channel.getId());
		subchannel = analysisReportService.saveChannel(subchannel);
		
		List<ChannelReport> channelReport = new ArrayList<ChannelReport>();
		for (int i = 0; i < 10; i++) {
			ChannelReport e = new ChannelReport();
			e.setChannelId(channel.getId());
			e.setPv(new BigDecimal(new Random().nextInt(1000)));
			e.setUv(new BigDecimal(new Random().nextInt(1000)));
			e.setUserTotal(new BigDecimal(new Random().nextInt(1000)));
			e.setToTradeTotal(new BigDecimal(new Random().nextInt(1000)));
			e.setOpenAccountTotal(new BigDecimal(new Random().nextInt(1000)));
			e.setTradeTotal(new BigDecimal(new Random().nextInt(1000)));
			e.setSubscribeTotal(new BigDecimal(new Random().nextInt(1000)));
			e.setCreateDateTime(new Date());
			e.setReportDateTime(new Date());
			channelReport.add(e);
		}
		
		List<SubchannelReport> subChannelReport = new ArrayList<SubchannelReport>();
		for (int i = 0; i < 10; i++) {
			SubchannelReport e = new SubchannelReport();
//			e.setChannelId(subchannel.getId());
//			e.setPageUrl("www.howbuy.com/"+new Random().nextInt(100000)+"/pub.html");
//			e.setPv(new BigDecimal(new Random().nextInt(1000)));
//			e.setUv(new BigDecimal(new Random().nextInt(1000)));
//			e.setUserTotal(new BigDecimal(new Random().nextInt(1000)));
//			e.setToTradeTotal(new BigDecimal(new Random().nextInt(1000)));
//			e.setOpenAccountTotal(new BigDecimal(new Random().nextInt(1000)));
//			e.setTradeTotal(new BigDecimal(new Random().nextInt(1000)));
//			e.setSubscribeTotal(new BigDecimal(new Random().nextInt(1000)));
			e.setCreateDateTime(new Date());
			e.setReportDateTime(new Date());
			subChannelReport.add(e);
		}
		
		List<TradeDetail> tradeList = new ArrayList<TradeDetail>();
		for (int i = 0; i < 10; i++) {
			TradeDetail detail = new TradeDetail();
			detail.setChannelId(subchannel.getId());
			detail.setTradeType("买基金");
			detail.setFundType("货币型");
			detail.setFundCode("482002");
			detail.setUserTotal(new BigDecimal(new Random().nextInt(100)));
			detail.setAmountTotal(new BigDecimal(new Random().nextInt(10000)));
			detail.setReportDateTime(new Date());
			detail.setCreateDateTime(new Date());
			tradeList.add(detail);
		}
		
		List<OpenAccountDetail> openAcctList = new ArrayList<OpenAccountDetail>();
		for (int i = 0; i < 10; i++) {
			OpenAccountDetail detail = new OpenAccountDetail();
			detail.setChannelId(subchannel.getId());
			detail.setUserId("0000001testuser");
			detail.setIdType("1");
			detail.setIdNo("110101198001010010");
			detail.setBankNo("95599400091837131");
			detail.setCoopId("web");
			detail.setActId("act_001");
			detail.setReportDateTime(new Date());
			detail.setCreateDateTime(new Date());
			openAcctList.add(detail);
		}
		
//		analysisReportService.batchSaving(channelReport, subChannelReport, tradeList, openAcctList);
	}
	
	//@Test
	public void testSaveChannel(){
		Channel channel = new Channel();
		channel.setName("牛基频道");
		channel.setUrlRegex("^www.howbuy.com/sub/*$");
		channel.setCreateDateTime(new Date());
		//channel.setParentId("1");
		Channel result = analysisReportService.saveChannel(channel);
		log.info(result.getId());
	}
	
	//@Test
	public void testQueryChannel(){
		List<Channel> list = analysisReportService.queryAllChannel();
		if(list != null){
			for (Channel channel : list) {
				log.info(channel.toString());
			}
		}
		
	}
	
	//@Test
	public void testBatchSavingChannelReport(){
		List<ChannelReport> list = new ArrayList<ChannelReport>();
		for (int i = 0; i < 100; i++) {
			ChannelReport e = new ChannelReport();
			e.setChannelId("1");
			e.setPv(new BigDecimal(new Random().nextInt(1000)));
			e.setUv(new BigDecimal(new Random().nextInt(1000)));
			e.setUserTotal(new BigDecimal(new Random().nextInt(1000)));
			e.setToTradeTotal(new BigDecimal(new Random().nextInt(1000)));
			e.setOpenAccountTotal(new BigDecimal(new Random().nextInt(1000)));
			e.setTradeTotal(new BigDecimal(new Random().nextInt(1000)));
			e.setAmountTotal(new BigDecimal(new Random().nextInt(100000)));
			e.setSubscribeTotal(new BigDecimal(new Random().nextInt(1000)));
			e.setCreateDateTime(new Date());
			e.setReportDateTime(new Date());
			list.add(e);
		}
		analysisReportService.batchSavingChannelReport(list);
	}
	
	//@Test
	public void testQueryChannelReport() throws ParseException{
		Date start = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2013-12-26 17:53:00");
		Date end = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2014-12-26 17:53:00");
		List<ChannelReport> list =  analysisReportService.queryChannelReport(start, end);
		if(list != null ){
			for (ChannelReport channelReport : list) {
				log.info(channelReport.toString());
			}
		}
	}
	
	//@Test
	public void testBatchSavingSubchannelReport(){
		List<SubchannelReport> list = new ArrayList<SubchannelReport>();
		for (int i = 0; i < 100; i++) {
			SubchannelReport e = new SubchannelReport();
			e.setChannelId("1");
			e.setPageUrl("www.howbuy.com/"+new Random().nextInt(100000)+"/pub.html");
//			e.setPv(new BigDecimal(new Random().nextInt(1000)));
//			e.setUv(new BigDecimal(new Random().nextInt(1000)));
//			e.setUserTotal(new BigDecimal(new Random().nextInt(1000)));
//			e.setToTradeTotal(new BigDecimal(new Random().nextInt(1000)));
//			e.setOpenAccountTotal(new BigDecimal(new Random().nextInt(1000)));
//			e.setTradeTotal(new BigDecimal(new Random().nextInt(1000)));
//			e.setSubscribeTotal(new BigDecimal(new Random().nextInt(1000)));
			e.setCreateDateTime(new Date());
			e.setReportDateTime(new Date());
			list.add(e);
		}
		analysisReportService.batchSavingSubchannelReport(list);
	}
	
	//@Test
	public void testQuerySubchannelReport() throws ParseException{
		Date start = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2014-12-26 18:17:05");
		Date end = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2014-12-26 18:17:05");
		List<SubchannelReport> list =  analysisReportService.querySubChannelReport("1", start, end, null);
		if(list != null ){
			for (SubchannelReport channelReport : list) {
				log.info(channelReport.toString());
			}
		}
	}
	
	//@Test
	public void batchSavingOpenAcct(){
		List<OpenAccountDetail> list = new ArrayList<OpenAccountDetail>();
		for (int i = 0; i < 20; i++) {
			OpenAccountDetail detail = new OpenAccountDetail();
			detail.setChannelId("2");
			detail.setUserId("0000001testuser");
			detail.setIdType("1");
			detail.setIdNo("110101198001010010");
			detail.setBankNo("95599400091837131");
			detail.setCoopId("web");
			detail.setActId("act_001");
			detail.setReportDateTime(new Date());
			detail.setCreateDateTime(new Date());
			list.add(detail);
		}
		analysisReportService.batchSavingOpenAcct(list);
	}
	
	//@Test
	public void batchSavingTradeDetail(){
		List<TradeDetail> list = new ArrayList<TradeDetail>();
		for (int i = 0; i < 20; i++) {
			TradeDetail detail = new TradeDetail();
			detail.setChannelId("2");
			detail.setTradeType("买基金");
			detail.setFundType("货币型");
			detail.setFundCode("482002");
			detail.setUserTotal(new BigDecimal(new Random().nextInt(100)));
			detail.setAmountTotal(new BigDecimal(new Random().nextInt(10000)));
			detail.setReportDateTime(new Date());
			detail.setCreateDateTime(new Date());
			list.add(detail);
		}
		analysisReportService.batchSavingTradeDetail(list);
	}
	
	
	public void queryOpenAcctDetail() throws ParseException{
		Date start = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2013-12-26 18:17:05");
		Date end = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2015-12-26 18:17:05");
		List<OpenAccountDetail> list =  analysisReportService.queryOpenAcctDetail("2", start, end);
		if(list != null ){
			for (OpenAccountDetail detail : list) {
				log.info(detail.toString());
			}
		}
	}
	
	//@Test
	public void queryTradeDetail() throws ParseException{
		Date start = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2013-12-26 18:17:05");
		Date end = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2015-12-26 18:17:05");
		/*List<TradeDetail> list =  analysisReportService.queryTradeDetail("2", start, end);
		if(list != null ){
			for (TradeDetail detail : list) {
				log.info(detail.toString());
			}
		}*/
	}
	
	//@Test
	public void testBatchHowbuy2EhowbuyStat(){
		
		Howbuy2Ehowbuy stat = new Howbuy2Ehowbuy();
		
		List<Howbuy2Ehowbuy> list = new ArrayList<Howbuy2Ehowbuy>();
		stat.setStartDate(new Date());
		stat.setIntervals(5);
		stat.setActiontype("trade");;
		stat.setCount(100);
		stat.setChannel("fund");
		stat.setSubchannel("detail");
		list.add(stat);
		analysisReportService.batchSaveHowbuy2Ehowbuy(list);
		
		
	}
	
	/*
	 * 渠道管理数据库操作 测试
	 */
	@Test
	public void testInsertRoute(){
		RouteManageCommand ri = new RouteManageCommand();
		ri.setLevel(1);
		ri.setCreateDate(new Date());
		ri.setParentId("");
		ri.setRouteName("谷歌");
		analysisConsoleService.insertRoute(ri);
	}
	
	@Test
	public void queryRouteDetail(){
		String id1 = "1";
		String id2 = "2";
		String id3 = "1";
		RouteDetailDto rDetail = analysisConsoleService.queryRouteDetail(id1, id2, id3);
		System.out.println(rDetail.refId+"\n"+rDetail.routeDetail);
		
	}
	
}
