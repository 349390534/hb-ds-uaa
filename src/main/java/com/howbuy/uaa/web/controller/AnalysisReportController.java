package com.howbuy.uaa.web.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.howbuy.uaa.persistence.Channel;
import com.howbuy.uaa.persistence.ChannelReport;
import com.howbuy.uaa.persistence.SubchannelReport;
import com.howbuy.uaa.persistence.TradeDetail;
import com.howbuy.uaa.service.AnalysisReportService;


public class AnalysisReportController extends MultiActionController{
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	private AnalysisReportService analysisReportService;
	
	private String channelReport;
	private String subchannelReport;
	private String tradeDetail;
	
	public ModelAndView channelReport(HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<ChannelReport> list = new ArrayList<ChannelReport>();
		String channelId = request.getParameter("channelId");
		String startParam = request.getParameter("start");
		String endParam = request.getParameter("end");
		
		if(!isEmpty(startParam, endParam)){
			startParam += " 00:00:00";
			endParam += " 23:59:59";
			Date start = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(startParam);
			Date end = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endParam);
			list = analysisReportService.queryChannelReport(channelId,start, end);
		}else{
			startParam =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			endParam = startParam;
		}
		
		List<Channel> channelList = analysisReportService.queryAllChannel();
		
		return new ModelAndView(channelReport)
		.addObject("list", list)
		.addObject("channelList", channelList)
		.addObject("start", startParam)
		.addObject("end", endParam)
		.addObject("channelId", channelId);
	}
	
	public ModelAndView subchannelReport(HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<SubchannelReport> list = new ArrayList<SubchannelReport>();
		String channelId = request.getParameter("channelId");
		String keyword = request.getParameter("keyword");
		String startParam = request.getParameter("start");
		String endParam = request.getParameter("end");
		if(!isEmpty(channelId,startParam,endParam)){
			startParam += " 00:00:00";
			endParam += " 23:59:59";
			Date start = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(startParam);
			Date end = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endParam);
			list = analysisReportService.querySubChannelReport(channelId, start, end, keyword);
		}else{
			startParam =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			endParam = startParam;
		}
		return new ModelAndView(subchannelReport)
		.addObject("list", list)
		.addObject("start", startParam)
		.addObject("end", endParam)
		.addObject("keyword",keyword)
		.addObject("channelId",channelId);
	}
	
	public ModelAndView channelTradeDetail(HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<TradeDetail> list = new ArrayList<TradeDetail>();
		String channelId = request.getParameter("channelId");
		String startParam = request.getParameter("start");
		String endParam = request.getParameter("end");
		if(!isEmpty(channelId, startParam, endParam)){
			startParam += " 00:00:00";
			endParam += " 23:59:59";
			Date start = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(startParam);
			Date end = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endParam);
			list = analysisReportService.channelTradeDetail(channelId, start, end);
		}
		return new ModelAndView(tradeDetail)
		.addObject("list", list)
		.addObject("start", startParam)
		.addObject("end", endParam);
	}
	
	public ModelAndView subchannelTradeDetail(HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<TradeDetail> list = new ArrayList<TradeDetail>();
		String channelId = request.getParameter("channelId");
		String url = request.getParameter("url");
		String startParam = request.getParameter("start");
		String endParam = request.getParameter("end");
		if(!isEmpty(channelId, url, startParam, endParam)){
			startParam += " 00:00:00";
			endParam += " 23:59:59";
			Date start = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(startParam);
			Date end = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endParam);
			list = analysisReportService.subchannelTradeDetail(channelId, url, start, end);
		}
		return new ModelAndView(tradeDetail)
		.addObject("list", list)
		.addObject("start", startParam)
		.addObject("end", endParam)
		.addObject("url", url);
	}

	public AnalysisReportService getAnalysisReportService() {
		return analysisReportService;
	}

	public void setAnalysisReportService(AnalysisReportService analysisReportService) {
		this.analysisReportService = analysisReportService;
	}

	public String getChannelReport() {
		return channelReport;
	}

	public void setChannelReport(String channelReport) {
		this.channelReport = channelReport;
	}

	public String getSubchannelReport() {
		return subchannelReport;
	}

	public void setSubchannelReport(String subchannelReport) {
		this.subchannelReport = subchannelReport;
	}

	public String getTradeDetail() {
		return tradeDetail;
	}

	public void setTradeDetail(String tradeDetail) {
		this.tradeDetail = tradeDetail;
	}
	
	public boolean isEmpty(String... params){
		if(params != null){
			for (String string : params) {
				if(string == null || "".equals(string.trim())){
					return true;
				}
			}
		}
		return false;
	}
	
}
