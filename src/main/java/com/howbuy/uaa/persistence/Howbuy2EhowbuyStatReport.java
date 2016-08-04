package com.howbuy.uaa.persistence;

import java.math.BigDecimal;
import java.util.Date;

import com.howbuy.rdb.annotation.EntityPK;
import com.howbuy.rdb.database.dto.impl.BaseDtoAdapter;


@EntityPK(Pk = "id", defaultColumn = false, tableName = "subchannel_report")
public class Howbuy2EhowbuyStatReport extends BaseDtoAdapter {

    /**
	 * 
	 */
	private static final long serialVersionUID = -8269230488638101390L;
	/**
	 * 
	 */
	private String id;
	/**
	 * 1：前导页  2：首次访问页面
	 */
	private String pageType;
    private String channelId;
    private String parentId;
    private String pageUrl;
    private long pv;
    private long uv;
    private long pv2ehowbuy;
    private long uv2ehowbuy;
    private long userTotal;
    private long toTradeTotal;
    private long openAccountTotal;
    private long tradeTotal;
    private BigDecimal amountTotal;
    private long subscribeTotal;
    private Date reportDateTime;
    private Date createDateTime;
    /*
     * howbuy url新增UV
     */
    private long howbuyAddedUV;
    
    
	public long getHowbuyAddedUV() {
		return howbuyAddedUV;
	}
	public void setHowbuyAddedUV(long howbuyAddedUV) {
		this.howbuyAddedUV = howbuyAddedUV;
	}
	public long getPv2ehowbuy() {
		return pv2ehowbuy;
	}
	public void setPv2ehowbuy(long pv2ehowbuy) {
		this.pv2ehowbuy = pv2ehowbuy;
	}
	public long getUv2ehowbuy() {
		return uv2ehowbuy;
	}
	public void setUv2ehowbuy(long uv2ehowbuy) {
		this.uv2ehowbuy = uv2ehowbuy;
	}
	public long getPv() {
		return pv;
	}
	public void setPv(long pv) {
		this.pv = pv;
	}
	public long getUv() {
		return uv;
	}
	public void setUv(long uv) {
		this.uv = uv;
	}
	public long getUserTotal() {
		return userTotal;
	}
	public void setUserTotal(long userTotal) {
		this.userTotal = userTotal;
	}
	public long getToTradeTotal() {
		return toTradeTotal;
	}
	public void setToTradeTotal(long toTradeTotal) {
		this.toTradeTotal = toTradeTotal;
	}
	public long getOpenAccountTotal() {
		return openAccountTotal;
	}
	public void setOpenAccountTotal(long openAccountTotal) {
		this.openAccountTotal = openAccountTotal;
	}
	public long getTradeTotal() {
		return tradeTotal;
	}
	public void setTradeTotal(long tradeTotal) {
		this.tradeTotal = tradeTotal;
	}
	public long getSubscribeTotal() {
		return subscribeTotal;
	}
	public void setSubscribeTotal(long subscribeTotal) {
		this.subscribeTotal = subscribeTotal;
	}
	public String getPageType() {
		return pageType;
	}
	public void setPageType(String pageType) {
		this.pageType = pageType;
	}
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
	public String getPageUrl() {
		return pageUrl;
	}
	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
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
	public BigDecimal getAmountTotal() {
		return amountTotal;
	}
	public void setAmountTotal(BigDecimal amountTotal) {
		this.amountTotal = amountTotal;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

}
