package com.howbuy.uaa.web.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.howbuy.hadoop.mapping.MappingServer;
import com.howbuy.uaa.command.HowbuyPVUV;
import com.howbuy.uaa.dto.Howbuy2EhowbuyDto;
import com.howbuy.uaa.dto.PVUVCounts;
import com.howbuy.uaa.service.AnalysisReportService;


public class HowbuyPVUVController extends SimpleFormController{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private AnalysisReportService analysisReportService;
	

	public void setAnalysisReportService(AnalysisReportService analysisReportService) {
		this.analysisReportService = analysisReportService;
	}

	@Override
	protected ModelAndView showForm(HttpServletRequest request,
			HttpServletResponse response, BindException errors)
			throws Exception {
		
		Map<String, String> mappingChannels = MappingServer.getMappingChannels();
		
		return super.showForm(request, response, errors).addObject("mappingChannels", mappingChannels);
	}

	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		HowbuyPVUV cmd = (HowbuyPVUV)command;
		String[] channelArr = cmd.getChannelID().split("\\$");
		cmd.setChannelID(channelArr[0]);
		cmd.setSubChannelID(channelArr[1]);
		
		List<Howbuy2EhowbuyDto> retList = analysisReportService.queryHowbuyPVUVStat(cmd);
		
//		PVUVCounts pvuvCunts = analysisReportService.getPVUVStat(cmd);
		
		return new ModelAndView(getSuccessView())
		.addObject("retList", retList)
		.addObject("pageType", cmd.getPageType());
//		.addObject("pvuvCunts", pvuvCunts);
	}
	
	
	
}
