package com.howbuy.uaa.persistence;

import java.math.BigDecimal;
import java.util.Date;

import com.howbuy.rdb.annotation.EntityPK;
import com.howbuy.rdb.database.dto.impl.BaseDtoAdapter;


@EntityPK(Pk = "id", defaultColumn = false, tableName = "channel_report")
public class ChannelReport extends BaseDtoAdapter {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1758421921046485308L;
	private String id;
	private String channelId;
    private String channelName;
    private BigDecimal pv;
    private BigDecimal uv;
    private BigDecimal userTotal;
    private BigDecimal toTradeTotal;
    private BigDecimal openAccountTotal;
    private BigDecimal tradeTotal;
    private BigDecimal amountTotal;
    private BigDecimal subscribeTotal;
    private Date reportDateTime;
    private Date createDateTime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public BigDecimal getPv() {
		return pv;
	}
	public void setPv(BigDecimal pv) {
		this.pv = pv;
	}
	public BigDecimal getUv() {
		return uv;
	}
	public void setUv(BigDecimal uv) {
		this.uv = uv;
	}
	public BigDecimal getUserTotal() {
		return userTotal;
	}
	public void setUserTotal(BigDecimal userTotal) {
		this.userTotal = userTotal;
	}
	public BigDecimal getToTradeTotal() {
		return toTradeTotal;
	}
	public void setToTradeTotal(BigDecimal toTradeTotal) {
		this.toTradeTotal = toTradeTotal;
	}
	public BigDecimal getOpenAccountTotal() {
		return openAccountTotal;
	}
	public void setOpenAccountTotal(BigDecimal openAccountTotal) {
		this.openAccountTotal = openAccountTotal;
	}
	public BigDecimal getTradeTotal() {
		return tradeTotal;
	}
	public void setTradeTotal(BigDecimal tradeTotal) {
		this.tradeTotal = tradeTotal;
	}
	
	public BigDecimal getSubscribeTotal() {
		return subscribeTotal;
	}
	public void setSubscribeTotal(BigDecimal subscribeTotal) {
		this.subscribeTotal = subscribeTotal;
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
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public BigDecimal getAmountTotal() {
		return amountTotal;
	}
	public void setAmountTotal(BigDecimal amountTotal) {
		this.amountTotal = amountTotal;
	}
	
	

}
