package com.howbuy.uaa.command;

public class AccessChannelCommand {

	/**
	 * 开始日期
	 */
	private String beginDate;

	/**
	 * 结束日期
	 */
	private String endDate;

	/**
	 * 根渠道号
	 */
	private String channelCode;
	
	/**
	 * 渠道号
	 */
	private String channel;

	/**
	 * 根渠道类型 1:直接访问，3：搜索引擎，4：推广渠道，2：其他渠道
	 */
	private String channelType;
	/**
	 * 根渠道名称
	 */
	private String channelName;

	/**
	 * 搜索引擎
	 */
	private String searchEngine;

	/**
	 * 推广渠道等级
	 */
	private Integer level;
	/**
	 * 父渠道id
	 */
	private Integer parent;

	/**
	 * 推广渠道根渠道tag
	 */
	private String channelRoot;

	/**
	 * 其他渠道
	 */
	private String otherChannel;

	/**
	 * 趋势明细 orderBy ：
	 */
	private String orderBy;
	/**
	 * 数据显示排序方式 order:asc/desc
	 */
	private String order;
	/**
	 * pageRows：单页数据显示个数
	 */
	private Integer pageRows;
	/**
	 * curPage：数据的当前页面
	 */
	private Integer curPage;
	
	/**
	 * 当前查询参数
	 */
	private String currentParam;

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

	public String getChannelType() {
		return channelType;
	}

	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getSearchEngine() {
		return searchEngine;
	}

	public void setSearchEngine(String searchEngine) {
		this.searchEngine = searchEngine;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getParent() {
		return parent;
	}

	public void setParent(Integer parent) {
		this.parent = parent;
	}

	public String getChannelRoot() {
		return channelRoot;
	}

	public void setChannelRoot(String channelRoot) {
		this.channelRoot = channelRoot;
	}

	public String getOtherChannel() {
		return otherChannel;
	}

	public void setOtherChannel(String otherChannel) {
		this.otherChannel = otherChannel;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public Integer getPageRows() {
		return pageRows;
	}

	public void setPageRows(Integer pageRows) {
		this.pageRows = pageRows;
	}

	public Integer getCurPage() {
		return curPage;
	}

	public void setCurPage(Integer curPage) {
		this.curPage = curPage;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getCurrentParam() {
		return currentParam;
	}

	public void setCurrentParam(String currentParam) {
		this.currentParam = currentParam;
	}

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

}
