package com.howbuy.uaa.persistence;

import java.math.BigDecimal;
import java.util.Date;

import com.howbuy.rdb.annotation.EntityPK;
import com.howbuy.rdb.database.dto.impl.BaseDtoAdapter;


@EntityPK(Pk = "id", defaultColumn = false, tableName = "trade_detail")
public class TradeDetail extends BaseDtoAdapter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7016021533653185704L;
	
	private String id;
	private String channelId;
	private String url;
	private String tradeType;
	private String fundType;
	private String fundCode;
	private BigDecimal userTotal;
	private BigDecimal amountTotal;
	private Date reportDateTime;
	private Date createDateTime;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public String getTradeType() {
		return tradeType;
	}
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	public String getFundType() {
		return fundType;
	}
	public void setFundType(String fundType) {
		this.fundType = fundType;
	}
	public String getFundCode() {
		return fundCode;
	}
	public void setFundCode(String fundCode) {
		this.fundCode = fundCode;
	}
	
	public BigDecimal getUserTotal() {
		return userTotal;
	}
	public void setUserTotal(BigDecimal userTotal) {
		this.userTotal = userTotal;
	}
	public BigDecimal getAmountTotal() {
		return amountTotal;
	}
	public void setAmountTotal(BigDecimal amountTotal) {
		this.amountTotal = amountTotal;
	}
	public Date getCreateDateTime() {
		return createDateTime;
	}
	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}
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
	
	
	
	
}
