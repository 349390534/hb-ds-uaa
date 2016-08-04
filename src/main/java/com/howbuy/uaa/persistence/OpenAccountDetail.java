package com.howbuy.uaa.persistence;

import java.util.Date;

import com.howbuy.rdb.annotation.EntityPK;
import com.howbuy.rdb.database.dto.impl.BaseDtoAdapter;


@EntityPK(Pk = "id", defaultColumn = false, tableName = "open_account_detail")
public class OpenAccountDetail extends BaseDtoAdapter {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8626780997496742349L;

	private String id;
	private String channelId;
	private String idType;
	private String idNo;
	private String userId;
	private String bankType;
	private String bankNo;
	private String coopId;
	private String actId;
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
	public String getIdType() {
		return idType;
	}
	public void setIdType(String idType) {
		this.idType = idType;
	}
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getBankType() {
		return bankType;
	}
	public void setBankType(String bankType) {
		this.bankType = bankType;
	}
	public String getBankNo() {
		return bankNo;
	}
	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}
	public String getCoopId() {
		return coopId;
	}
	public void setCoopId(String coopId) {
		this.coopId = coopId;
	}
	public String getActId() {
		return actId;
	}
	public void setActId(String actId) {
		this.actId = actId;
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
	
	
}
