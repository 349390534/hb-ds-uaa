package com.howbuy.uaa.persistence;

import java.util.Date;

/**
 * PV、UV
 * @author yichao.song
 *
 */
public class CountStat {

	private Date statDate;
	
	private Date createDate;
	
	private String channelID;
	
	private String parentChannelID;
	
	private long pv;
	
	private long uv;
	

	public String getParentChannelID() {
		return parentChannelID;
	}

	public void setParentChannelID(String parentChannelID) {
		this.parentChannelID = parentChannelID;
	}

	public Date getStatDate() {
		return statDate;
	}

	public void setStatDate(Date statDate) {
		this.statDate = statDate;
	}

	public String getChannelID() {
		return channelID;
	}

	public void setChannelID(String channelID) {
		this.channelID = channelID;
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}
