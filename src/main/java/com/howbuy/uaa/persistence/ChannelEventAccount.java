package com.howbuy.uaa.persistence;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * App用户事件数据信息
 * 
 * @author qiankun.li
 * 
 */
public class ChannelEventAccount {

	private Long proid;

	private String outletcode;

	private Long activateNum;

	private Long openaccNum;

	private Long bindcardNum;

	private Long orderNum;

	private BigDecimal orderAmount;

	private Timestamp createTime;

	public Long getProid() {
		return proid;
	}

	public void setProid(Long proid) {
		this.proid = proid;
	}

	public String getOutletcode() {
		return outletcode;
	}

	public void setOutletcode(String outletcode) {
		this.outletcode = outletcode;
	}

	public Long getActivateNum() {
		return activateNum;
	}

	public void setActivateNum(Long activateNum) {
		this.activateNum = activateNum;
	}

	public Long getOpenaccNum() {
		return openaccNum;
	}

	public void setOpenaccNum(Long openaccNum) {
		this.openaccNum = openaccNum;
	}

	public Long getBindcardNum() {
		return bindcardNum;
	}

	public void setBindcardNum(Long bindcardNum) {
		this.bindcardNum = bindcardNum;
	}

	public Long getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Long orderNum) {
		this.orderNum = orderNum;
	}

	public BigDecimal getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

}
