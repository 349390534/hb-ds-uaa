package com.howbuy.uaa.persistence;

import java.util.Date;

/**
 * 搜索引擎渠道
 * 
 * @author qiankun.li
 * 
 */
public class ZeroChannelTag {
	private Long id;
	
	private String tagName;

	private String tagCode;
	
	private Date createDate;
	/**
	 * 1:归类搜索 2：归类其他项
	 */
	private int type;
	
	private String title;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public String getTagCode() {
		return tagCode;
	}

	public void setTagCode(String tagCode) {
		this.tagCode = tagCode;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
