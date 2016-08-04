package com.howbuy.uaa.dto;

import java.util.ArrayList;
import java.util.List;

public class PermissionDto {
	private long id;
	private String permissionId;
	private String name;
	private String url;
	private String descriptions;
	private long version;
	private String level;
	private List<PermissionDto> list = new ArrayList<PermissionDto>();
	public long getVersion() {
		return version;
	}
	public void setVersion(long version) {
		this.version = version;
	}
	public String getDescriptions() {
		return descriptions;
	}
	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
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
	public List<PermissionDto> getList() {
		return list;
	}
	public void setList(List<PermissionDto> list) {
		this.list = list;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	
}
