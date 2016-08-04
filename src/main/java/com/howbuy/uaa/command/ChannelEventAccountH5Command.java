/**
 * 
 */
package com.howbuy.uaa.command;

/**
 * @author qiankun.li
 * 
 */
public class ChannelEventAccountH5Command {

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
	 * 开始日期
	 */
	private String beginDate;

	/**
	 * 结束日期
	 */
	private String endDate;
	/**
	 * 根渠道tag
	 */
	private String channelCode;

	/**
	 * 父渠道id
	 */
	private Integer parent;

	private String searchEngine;
	
	public String getSearchEngine() {
		return searchEngine;
	}

	public void setSearchEngine(String searchEngine) {
		this.searchEngine = searchEngine;
	}

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

}
