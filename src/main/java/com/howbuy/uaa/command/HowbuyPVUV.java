package com.howbuy.uaa.command;

/**
 * howbuy pvuv统计
 * @author yichao.song
 *
 */
public class HowbuyPVUV {

	private String start;
	
	private String endTime;
	
	private String channelID;
	
	private String subChannelID;
	
	/*
	 *  1：前导页  2：首次访问页面
	 */
	private String pageType;
	
	private String intervals;
	
	/**
	 * 1：交易
	 * 2：开户
	 */
	private String actionType;
	
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getChannelID() {
		return channelID;
	}
	public void setChannelID(String channelID) {
		this.channelID = channelID;
	}
	public String getSubChannelID() {
		return subChannelID;
	}
	public void setSubChannelID(String subChannelID) {
		this.subChannelID = subChannelID;
	}
	public String getPageType() {
		return pageType;
	}
	public void setPageType(String pageType) {
		this.pageType = pageType;
	}

	public String getActionType() {
		return actionType;
	}
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	public String getIntervals() {
		return intervals;
	}
	public void setIntervals(String intervals) {
		this.intervals = intervals;
	}
	
}
