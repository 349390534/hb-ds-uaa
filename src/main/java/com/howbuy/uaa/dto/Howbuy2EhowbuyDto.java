package com.howbuy.uaa.dto;

import java.util.Date;

import org.apache.hadoop.hive.ql.parse.HiveParser.stringLiteralSequence_return;

/**
 * howbuy ehowbuy pv/uv 交易
 * @author yichao.song
 *
 */
public class Howbuy2EhowbuyDto {
	
	private Date reportDateTime;

	/**
	 * 频道局面
	 */
	private String url;
	/**
	 * 一级频道
	 */
	private String channelID;
	/**
	 * 二级频道
	 */
	private String subChannelID;
	
	private Long howbuypv;
	
	private Long howbuyuv;
	
	private Long urlpv;
	
	private Long urluv;
	/**
	 * ehowbuy新增UV
	 */
	private Long ehowbuyAddeduv;
	
	/**
	 * howbuy 新增UV
	 */
	private Long howbuyAddedUV;
	/**
	 * 开户数
	 */
	private Long acctopenNum;
	/**
	 * 交易数
	 */
	private Long tradeNum;
	/**
	 * 开户交易数量
	 */
	private Long accttradeNum;
	
	/**
	 *查询类型
	 *6：交易  4:开户
	 */
	private String actiontype;
	/**
	 *时间间隔
	 */
	private String intervals;
	
	private Integer openAcct10days;
	
	private Integer trade10days;
	
	private Integer openAcct30days;
	
	private Integer trade30days;

	public Date getReportDateTime() {
		return reportDateTime;
	}

	public void setReportDateTime(Date reportDateTime) {
		this.reportDateTime = reportDateTime;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	public Long getHowbuypv() {
		return howbuypv;
	}

	public void setHowbuypv(Long howbuypv) {
		this.howbuypv = howbuypv;
	}

	public Long getHowbuyuv() {
		return howbuyuv;
	}

	public void setHowbuyuv(Long howbuyuv) {
		this.howbuyuv = howbuyuv;
	}

	public Long getUrlpv() {
		return urlpv;
	}

	public void setUrlpv(Long urlpv) {
		this.urlpv = urlpv;
	}

	public Long getUrluv() {
		return urluv;
	}

	public void setUrluv(Long urluv) {
		this.urluv = urluv;
	}

	public Long getEhowbuyAddeduv() {
		return ehowbuyAddeduv;
	}

	public void setEhowbuyAddeduv(Long ehowbuyAddeduv) {
		this.ehowbuyAddeduv = ehowbuyAddeduv;
	}

	public Long getHowbuyAddedUV() {
		return howbuyAddedUV;
	}

	public void setHowbuyAddedUV(Long howbuyAddedUV) {
		this.howbuyAddedUV = howbuyAddedUV;
	}

	public Long getAcctopenNum() {
		return acctopenNum;
	}

	public void setAcctopenNum(Long acctopenNum) {
		this.acctopenNum = acctopenNum;
	}

	public Long getTradeNum() {
		return tradeNum;
	}

	public void setTradeNum(Long tradeNum) {
		this.tradeNum = tradeNum;
	}

	public Long getAccttradeNum() {
		return accttradeNum;
	}

	public void setAccttradeNum(Long accttradeNum) {
		this.accttradeNum = accttradeNum;
	}

	public String getActiontype() {
		return actiontype;
	}

	public void setActiontype(String actiontype) {
		this.actiontype = actiontype;
	}

	public String getIntervals() {
		return intervals;
	}

	public void setIntervals(String intervals) {
		this.intervals = intervals;
	}

	public Integer getOpenAcct10days() {
		return openAcct10days;
	}

	public void setOpenAcct10days(Integer openAcct10days) {
		this.openAcct10days = openAcct10days;
	}

	public Integer getTrade10days() {
		return trade10days;
	}

	public void setTrade10days(Integer trade10days) {
		this.trade10days = trade10days;
	}

	public Integer getOpenAcct30days() {
		return openAcct30days;
	}

	public void setOpenAcct30days(Integer openAcct30days) {
		this.openAcct30days = openAcct30days;
	}

	public Integer getTrade30days() {
		return trade30days;
	}

	public void setTrade30days(Integer trade30days) {
		this.trade30days = trade30days;
	}
		
	
}
