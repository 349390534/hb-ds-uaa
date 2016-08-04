package com.howbuy.uaa.command;

/**
 * 
 * @author qiankun.li
 *
 */
public class FundStatisticsCommand {
	/**
	 * 起始时间
	 */
	private String startDate;
	/**
	 * 结束时间
	 */
	private String endDate;
	/**
	 * 分析维度 1开户、2交易 默认为1
	 */
	private String analysisWd;
	/**
	 * 开户机构
	 */
	private String openInst;
	/**
	 * 开户平台
	 */
	private String openPlatform;
	/**
	 * 合作类型
	 */
	private String cooperateType;
	/**
	 * 开户网点
	 */
	private String openWangDian;
	/**
	 * 基金类型
	 */
	private String fundType;
	
	/**
	 * 当基金类型为空的时候，是否对基金类型汇总：“0”：汇总  “1”：不汇总
	 */
	private String fundTypeStat;

	/**
	 * 用户指标
	 */
	private String openNorm;
	/**
	 * 交易指标
	 */
	private String tradeNorm;
	/**
	 * 趋势明细 排序字段
	 */
	private String orderBy;
	/**
	 * 数据显示排序方式 order:asc/desc
	 */
	private String order;
	/**
	 * 单页数据显示个数
	 */
	private String pageRows;
	/**
	 * 数据的当前页面
	 */
	private String curPage;

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getAnalysisWd() {
		return analysisWd;
	}

	public void setAnalysisWd(String analysisWd) {
		this.analysisWd = analysisWd;
	}

	public String getOpenInst() {
		return openInst;
	}

	public void setOpenInst(String openInst) {
		this.openInst = openInst;
	}

	public String getFundType() {
		return fundType;
	}

	public void setFundType(String fundType) {
		this.fundType = fundType;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getOpenNorm() {
		return openNorm;
	}

	public void setOpenNorm(String openNorm) {
		this.openNorm = openNorm;
	}

	public String getTradeNorm() {
		return tradeNorm;
	}

	public void setTradeNorm(String tradeNorm) {
		this.tradeNorm = tradeNorm;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getPageRows() {
		return pageRows;
	}

	public void setPageRows(String pageRows) {
		this.pageRows = pageRows;
	}

	public String getCurPage() {
		return curPage;
	}

	public void setCurPage(String curPage) {
		this.curPage = curPage;
	}

	public String getOpenPlatform() {
		return openPlatform;
	}

	public void setOpenPlatform(String openPlatform) {
		this.openPlatform = openPlatform;
	}

	public String getCooperateType() {
		return cooperateType;
	}

	public void setCooperateType(String cooperateType) {
		this.cooperateType = cooperateType;
	}

	public String getOpenWangDian() {
		return openWangDian;
	}

	public void setOpenWangDian(String openWangDian) {
		this.openWangDian = openWangDian;
	}

	public String getFundTypeStat() {
		return fundTypeStat;
	}

	public void setFundTypeStat(String fundTypeStat) {
		this.fundTypeStat = fundTypeStat;
	}
}
