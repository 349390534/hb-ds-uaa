package com.howbuy.uaa.dto;

import java.util.Date;

public class RouteDetailDto {
	/**
	 * 三级渠道ID
	 * HTAG
	 * 备注
	 * 创建时间
	 * 创建用户
	 * 渠道路径
	 */
	public String refId;
	public String htag;
	public String remark;
	public Date createDate;
	public String account;
	public String routeDetail;
	
	
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getRefId() {
		return refId;
	}
	public void setRefId(String refId) {
		this.refId = refId;
	}
	public String getHtag() {
		return htag;
	}
	public void setHtag(String htag) {
		this.htag = htag;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getRouteDetail() {
		return routeDetail;
	}
	public void setRouteDetail(String routeDetail) {
		this.routeDetail = routeDetail;
	}
	
}
