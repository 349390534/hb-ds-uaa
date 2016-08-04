package com.howbuy.uaa.command;

import java.util.Date;

public class RouteManageCommand {
	/**
	 * 
	 */
	private String id;
	private String routeName;
	private String account;
	private int level;
	private String parentId;
	private Date createDate;
	private String parentOfTwo;
	private String qid;
	private int mark;
	private String htag;
	private String routeDetail;
	private String channelType;

	/**
	 * 渠道tag
	 */
	private String tagCode;

	/**
	 * 一级渠道qid 适应页面数据
	 */
	private int oneQid;

	/**
	 * 二级渠道qid 适应页面数据
	 */
	private int twoQid;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRouteName() {
		return routeName;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getParentOfTwo() {
		return parentOfTwo;
	}

	public void setParentOfTwo(String parentOfTwo) {
		this.parentOfTwo = parentOfTwo;
	}

	public String getQid() {
		return qid;
	}

	public void setQid(String qid) {
		this.qid = qid;
	}

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

	public String getHtag() {
		return htag;
	}

	public void setHtag(String htag) {
		this.htag = htag;
	}

	public String getRouteDetail() {
		return routeDetail;
	}

	public void setRouteDetail(String routeDetail) {
		this.routeDetail = routeDetail;
	}

	public String getTagCode() {
		return tagCode;
	}

	public void setTagCode(String tagCode) {
		this.tagCode = tagCode;
	}

	public int getOneQid() {
		return oneQid;
	}

	public void setOneQid(int oneQid) {
		this.oneQid = oneQid;
	}

	public int getTwoQid() {
		return twoQid;
	}

	public void setTwoQid(int twoQid) {
		this.twoQid = twoQid;
	}

	public String getChannelType() {
		return channelType;
	}

	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}

}
