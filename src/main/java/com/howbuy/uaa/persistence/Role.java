package com.howbuy.uaa.persistence;

import java.util.ArrayList;
import java.util.List;

import com.howbuy.rdb.annotation.EntityPK;
import com.howbuy.rdb.database.dto.impl.BaseDtoAdapter;

@EntityPK(Pk = "id", defaultColumn = false, tableName = "ua_role")
public class Role extends BaseDtoAdapter {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -261516442197624175L;
	
	private long id;
	private String name;
	private String descriptions;
	private String content;
	private String status;
	private long version;
	private String allPerId;
	private String oldName ;
	private List<Permission> permission = new ArrayList<Permission>();
	public String getOldName() {
		return oldName;
	}
	public void setOldName(String oldName) {
		this.oldName = oldName;
	}
	public List<Permission> getPermission() {
		return permission;
	}
	public void setPermission(List<Permission> permission) {
		this.permission = permission;
	}
	public String getAllPerId() {
		return allPerId;
	}
	public void setAllPerId(String allPerId) {
		this.allPerId = allPerId;
	}
	public long getVersion() {
		return version;
	}
	public void setVersion(long version) {
		this.version = version;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescriptions() {
		return descriptions;
	}
	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
