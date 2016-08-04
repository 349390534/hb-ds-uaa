package com.howbuy.uaa.persistence;

import com.howbuy.rdb.annotation.EntityPK;
import com.howbuy.rdb.database.dto.impl.BaseDtoAdapter;

@EntityPK(Pk = "id", defaultColumn = false, tableName = "ua_user_permission_relation")
public class UserPermissionRelation extends BaseDtoAdapter {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2513751473802304612L;
	
	private long id;
	private long userId;
	private long permissionId;
	private String perIDs;
	private String status;
	private String oldperIds;
	private long version;
	
	public long getVersion() {
		return version;
	}
	public void setVersion(long version) {
		this.version = version;
	}
	public String getOldperIds() {
		return oldperIds;
	}
	public void setOldperIds(String oldperIds) {
		this.oldperIds = oldperIds;
	}
	public String getPerIDs() {
		return perIDs;
	}
	public void setPerIDs(String perIDs) {
		this.perIDs = perIDs;
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
