package com.howbuy.uaa.web.controller;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.howbuy.uaa.command.HowbuyPVUV;
import com.howbuy.uaa.dto.Howbuy2EhowbuyDto;
import com.howbuy.uaa.service.AnalysisReportService;

public class Howbuy2EhowbuyIntervalsController extends SimpleFormController{
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private AnalysisReportService analysisReportService;
	
	public void setAnalysisReportService(AnalysisReportService analysisReportService) {
		this.analysisReportService = analysisReportService;
	}

	@Override
	protected ModelAndView showForm(HttpServletRequest request,
			HttpServletResponse response, BindException errors)
			throws Exception {
		return super.showForm(request, response, errors);
	}

	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		// TODO Auto-generated method stub
		/**
		 * 参数获取
		 */
		HowbuyPVUV form = (HowbuyPVUV)command;
		String[] channelArr = form.getChannelID().split("\\$");
		form.setChannelID(channelArr[0]);
		form.setSubChannelID(channelArr[1]);
		String intervals = form.getIntervals();
		
		/**
		 * 数据查询
		 */
		List<Howbuy2EhowbuyDto> queryList;
		queryList = analysisReportService.queryHowbuy2EhowbuyIntervals(form);
		
		return new ModelAndView(getSuccessView())
		.addObject("pageType", "1")
		.addObject("retList",queryList)
		.addObject("Intervals", intervals);
	}
	
	

}
