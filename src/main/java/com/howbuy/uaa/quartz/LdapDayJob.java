/**
 * 
 */
package com.howbuy.uaa.quartz;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.howbuy.uaa.ldap.UaaLdapTemplate;
import com.howbuy.uaa.ldap.User;

/**
 * @author qiankun.li 每天运行LDAP接口,获取howbuy用户数据
 */
public class LdapDayJob {

	private static final Logger LOGGER = LoggerFactory.getLogger(LdapDayJob.class);
	
	private UaaLdapTemplate ldapTemplate;

	public void run() {
		LOGGER.info("--------ldap start------");
		List<User> allUsers = ldapTemplate.getAllUser(false);
		if(null!=allUsers){
			LOGGER.info("本次获取用户数为["+allUsers.size()+"]个");
		}else{
			LOGGER.error("本次获取用户失败,返回空集合");
		}
		LOGGER.info("--------ldap end------");
		
	}

	public UaaLdapTemplate getLdapTemplate() {
		return ldapTemplate;
	}

	public void setLdapTemplate(UaaLdapTemplate ldapTemplate) {
		this.ldapTemplate = ldapTemplate;
	}

}
