package com.howbuy.uaa.web.controller.job;

import java.text.ParseException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.howbuy.uaa.quartz.Howbuy2EhowbuyStat;
import com.howbuy.uaa.utils.DateUtils;


/**
 * web也页面添加任务处理器
 * @author yichao.song
 *
 */
public abstract class Howbuy2EhowbuyStatProcessor {
	
	public abstract Howbuy2EhowbuyStat createStatJob();

	private static Logger logger = LoggerFactory.getLogger(Howbuy2EhowbuyStatProcessor.class);
	
	private static BlockingQueue queue = new LinkedBlockingQueue(10);
	
	public String getJob() throws InterruptedException{
		String statDay = (String)queue.take();
		logger.info("get Job[{}] from Howbuy2EhowbuyStatProcessor,current queue size: {}",statDay,queue.size());
		return statDay;
	}

	public void addJob(String statDay) throws InterruptedException{
		queue.put(statDay);
		logger.info("put job[{}] to Howbuy2EhowbuyStatProcessor,current queue size: {}",statDay,queue.size());
	}
	
	public void run() throws Exception{
		String statDay = getStatDay();
		addJob(statDay);
	}
	
	
	/**
	 * 获取统计日期 yyyy-MM-dd
	 * @return
	 * @throws ParseException 
	 */
	public String getStatDay() throws ParseException{
		return DateUtils.getFormatedDate(DateUtils.getYesterdayDate());
	}
	
	
	{
		
		new Thread(){

			@Override
			public void run() {
				while(true){
					String statDay = "";
					try {
						statDay = getJob();
						Howbuy2EhowbuyStat statJob = createStatJob();
						statJob.dorun(statDay);
					}catch (Exception e) {
						logger.error("{} process error \n {}",statDay,e);
					}
				}
			}
			
		}.start();
		logger.info("manual thread started");
		
	}
	
	
}
