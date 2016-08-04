/**
 * 
 */
package com.howbuy.uaa.ldap;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qiankun.li
 * 
 */
public class User {

	/**
	 * 域账号
	 */
	private String account;
	
	/**
	 * 中文名称
	 */
	private String userName;
	
	/**
	 * email
	 */
	private String email;
	
	/**
	 * 详细Dn
	 */
	private String distinguishedName;

	private List<String> memberList = new ArrayList<String>(0);

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<String> getMemberList() {
		return memberList;
	}

	public void setMemberList(List<String> memberList) {
		this.memberList = memberList;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getDistinguishedName() {
		return distinguishedName;
	}

	public void setDistinguishedName(String distinguishedName) {
		this.distinguishedName = distinguishedName;
	}

}
