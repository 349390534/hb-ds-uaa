package com.howbuy.uaa.persistence;

import com.howbuy.rdb.annotation.EntityPK;
import com.howbuy.rdb.database.dto.impl.BaseDtoAdapter;

@EntityPK(Pk = "id", defaultColumn = false, tableName = "ua_permission")
public class Permission extends BaseDtoAdapter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8834040995633382463L;

	private long id;
	private String permissionId;
	private String name;
	private String descriptions;
	private String rescourcetype;
	private String rescourcelevel;
	private String status;
	private String url;
	private long version;
	private long parentId;
	private int urlChannel;
	private String oldPermissionId;

	public String getOldPermissionId() {
		return oldPermissionId;
	}

	public void setOldPermissionId(String oldPermissionId) {
		this.oldPermissionId = oldPermissionId;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(String permissionId) {
		this.permissionId = permissionId;
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

	public String getRescourcetype() {
		return rescourcetype;
	}

	public void setRescourcetype(String rescourcetype) {
		this.rescourcetype = rescourcetype;
	}

	public String getRescourcelevel() {
		return rescourcelevel;
	}

	public void setRescourcelevel(String rescourcelevel) {
		this.rescourcelevel = rescourcelevel;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getUrlChannel() {
		return urlChannel;
	}

	public void setUrlChannel(int urlChannel) {
		this.urlChannel = urlChannel;
	}
}
