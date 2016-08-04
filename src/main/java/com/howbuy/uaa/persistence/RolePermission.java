package com.howbuy.uaa.persistence;

import com.howbuy.rdb.annotation.EntityPK;
import com.howbuy.rdb.database.dto.impl.BaseDtoAdapter;

@EntityPK(Pk = "id", defaultColumn = false, tableName = "ua_role_permission")
public class RolePermission extends BaseDtoAdapter {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2449772566960386282L;
	
	private long id;
	private long roleId;
	private long permissionId;
	private String status;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getRoleId() {
		return roleId;
	}
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
	public long getPermissionId() {
		return permissionId;
	}
	public void setPermissionId(long permissionId) {
		this.permissionId = permissionId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
