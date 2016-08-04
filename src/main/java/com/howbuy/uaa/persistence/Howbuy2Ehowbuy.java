package com.howbuy.uaa.persistence;

import java.util.Date;
/**
 * howbuy新增UV，ehowbuy开户，交易量
 * @author yichao.song
 *
 */
public class Howbuy2Ehowbuy {
	
	private Date startDate;
	
	private int intervals;
	
	private String actiontype;
	
	private int count;
	
	private String channel;
	
	private String subchannel;
	
	
	
	public int getIntervals() {
		return intervals;
	}
	public void setIntervals(int intervals) {
		this.intervals = intervals;
	}
	
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public String getActiontype() {
		return actiontype;
	}
	public void setActiontype(String actiontype) {
		this.actiontype = actiontype;
	}
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	
	public String getSubchannel() {
		return subchannel;
	}
	public void setSubchannel(String subchannel) {
		this.subchannel = subchannel;
	}
	
}
