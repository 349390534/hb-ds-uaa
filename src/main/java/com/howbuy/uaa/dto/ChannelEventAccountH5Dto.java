package com.howbuy.uaa.dto;

import java.util.List;

/**
 * H5用户事件数据信息
 * 
 * @author qiankun.li
 * 
 */
public class ChannelEventAccountH5Dto {

	/**
	 * 1:直接访问，2：其他渠道，3：搜索引擎，4：推广
	 */
	private Integer channelType;

	/**
	 * 渠道级别
	 */
	private Integer channelLevel;

	/**
	 * 父渠道号
	 */
	private String channelParent;

	/**
	 * 渠道号
	 */
	private String channel;

	/**
	 * 开始时间
	 */
	private String beginTime;

	/**
	 * 结束时间
	 */
	private String endTime;

	/**
	 * 开始日期
	 */
	private String beginDate;

	/**
	 * 结束日期
	 */
	private String endDate;

	/**
	 * 搜索引擎
	 */
	private String searchEngine;

	/**
	 * 渠道code
	 */
	private String channelCode;

	/**
	 * 父渠道号
	 */
	private Integer parent;

	/**
	 * 日期集合
	 */
	List<String> dateList;

	public Integer getChannelType() {
		return channelType;
	}

	public void setChannelType(Integer channelType) {
		this.channelType = channelType;
	}

	public Integer getChannelLevel() {
		return channelLevel;
	}

	public void setChannelLevel(Integer channelLevel) {
		this.channelLevel = channelLevel;
	}

	public String getChannelParent() {
		return channelParent;
	}

	public void setChannelParent(String channelParent) {
		this.channelParent = channelParent;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getSearchEngine() {
		return searchEngine;
	}

	public void setSearchEngine(String searchEngine) {
		this.searchEngine = searchEngine;
	}

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	public Integer getParent() {
		return parent;
	}

	public void setParent(Integer parent) {
		this.parent = parent;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public List<String> getDateList() {
		return dateList;
	}

	public void setDateList(List<String> dateList) {
		this.dateList = dateList;
	}

}
