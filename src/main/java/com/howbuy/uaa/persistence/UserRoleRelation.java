package com.howbuy.uaa.persistence;

import com.howbuy.rdb.annotation.EntityPK;
import com.howbuy.rdb.database.dto.impl.BaseDtoAdapter;

@EntityPK(Pk = "id", defaultColumn = false, tableName = "ua_user_role_relation")
public class UserRoleRelation extends BaseDtoAdapter {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -93542763411525435L;
	
	private long id;
	private long userId;
	private long roleId;
	private String status;
	private String roleIds;
	private String oldroleIds;
	private long version;
	
	public long getVersion() {
		return version;
	}
	public void setVersion(long version) {
		this.version = version;
	}
	public String getOldroleIds() {
		return oldroleIds;
	}
	public void setOldroleIds(String oldroleIds) {
		this.oldroleIds = oldroleIds;
	}
	public String getRoleIds() {
		return roleIds;
	}
	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getRoleId() {
		return roleId;
	}
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
