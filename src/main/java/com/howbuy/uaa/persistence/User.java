package com.howbuy.uaa.persistence;

import java.sql.Timestamp;

import com.howbuy.rdb.annotation.EntityPK;
import com.howbuy.rdb.database.dto.impl.BaseDtoAdapter;

@EntityPK(Pk = "id", defaultColumn = false, tableName = "ua_user")
public class User extends BaseDtoAdapter{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4248816005826370480L;
	
	private long id;
	private String userName;
	private String pwd;
	private String chineseName;
	private String email;
	private String telphone;
	private String status;
	private Timestamp logindate;
	private long version;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getChineseName() {
		return chineseName;
	}
	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Timestamp getLogindate() {
		return logindate;
	}
	public void setLogindate(Timestamp logindate) {
		this.logindate = logindate;
	}
	

	
}
