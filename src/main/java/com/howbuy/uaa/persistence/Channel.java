package com.howbuy.uaa.persistence;

import java.util.Date;

import com.howbuy.rdb.annotation.EntityPK;
import com.howbuy.rdb.database.dto.impl.BaseDtoAdapter;

@EntityPK(Pk = "id", defaultColumn = false, tableName = "channel")
public class Channel extends BaseDtoAdapter{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4898819845036417703L;

	private String id;
	private String name;
	private String urlRegex;
	private String parentId;
	private String parentName;
	private Date createDateTime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrlRegex() {
		return urlRegex;
	}
	public void setUrlRegex(String urlRegex) {
		this.urlRegex = urlRegex;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public Date getCreateDateTime() {
		return createDateTime;
	}
	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}
	
	
}
