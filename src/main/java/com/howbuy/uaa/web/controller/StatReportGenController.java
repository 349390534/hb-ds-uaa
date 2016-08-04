package com.howbuy.uaa.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.howbuy.common.util.StringUtil;
import com.howbuy.uaa.web.controller.job.ChannelPVUVStatProcessor;
import com.howbuy.uaa.web.controller.job.Howbuy2EhowbuyStatProcessor;
import com.howbuy.uaa.web.controller.job.StatProcessor;

/**
 * 
 * @author yichao.song
 *
 */
public class StatReportGenController extends MultiActionController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private StatProcessor processor;
	
	private Howbuy2EhowbuyStatProcessor howbuy2ehowbuyProcessor;
	
	private ChannelPVUVStatProcessor pvuvProcessor;
	

	public void setHowbuy2ehowbuyProcessor(
			Howbuy2EhowbuyStatProcessor howbuy2ehowbuyProcessor) {
		this.howbuy2ehowbuyProcessor = howbuy2ehowbuyProcessor;
	}

	public void setProcessor(StatProcessor processor) {
		this.processor = processor;
	}
	
	

	public void setPvuvProcessor(ChannelPVUVStatProcessor pvuvProcessor) {
		this.pvuvProcessor = pvuvProcessor;
	}

	/**
	 * 交易网站统计(报表)
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 * @throws  
	 */
	public void addEhowbuyJob(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		String statDay = request.getParameter("statDay");
		
		response.setCharacterEncoding("gbk");
		
		if(StringUtil.isEmpty(statDay)){
			response.getOutputStream().write("统计日期[statDay]不能为空".getBytes("gbk"));
		}else{
			processor.addJob(statDay);
			response.getOutputStream().write(("统计任务[" + statDay + "]正处理中,请稍后登入统计页面查阅").getBytes("gbk"));
			logger.info("加入统计时间：{}", statDay);
		}
	}
	
	public void addHowbuy2EhowbuyJob(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		String statDay = request.getParameter("statDay");
		
		response.setCharacterEncoding("gbk");
		
		if(StringUtil.isEmpty(statDay)){
			response.getOutputStream().write("统计日期[statDay]不能为空".getBytes("gbk"));
		}else{
			howbuy2ehowbuyProcessor.addJob(statDay);
			response.getOutputStream().write(("统计任务[" + statDay + "]正处理中,请稍后登入统计页面查阅").getBytes("gbk"));
			logger.info("加入统计时间：{}",statDay);
		}
	}
	
	public void addPVUVStat(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		String statDay = request.getParameter("statDay");
		
		response.setCharacterEncoding("gbk");
		
		if(StringUtil.isEmpty(statDay)){
			response.getOutputStream().write("统计日期[statDay]不能为空".getBytes("gbk"));
		}else{
			pvuvProcessor.addJob(statDay);
			response.getOutputStream().write(("统计任务[" + statDay + "]正处理中,请稍后登入统计页面查阅").getBytes("gbk"));
			logger.info("加入统计时间：{}", statDay);
		}
	}

}
