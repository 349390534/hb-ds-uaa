/**
 * 
 */
package com.howbuy.uaa.ldap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.ldap.core.AuthenticationSource;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;

/**
 * @author qiankun.li
 * 
 */
public class UaaLdapTemplate {

	private LDAPAuthentication authentication;

	private static LdapTemplate template;
	
	private Map<String, List<User>> cacheMap = new HashMap<String, List<User>>();
	
	private final String USER_CACHE_KEY = "user_cache_key";

	/**
	 * ldap服务器URL
	 */
	private String url;
	/**
	 * baseDn
	 */
	private String baseDn;
	/**
	 * ldap服务器账号
	 */
	private String principal;
	/**
	 * ldap服务器密码
	 */
	private String credentials;

	void init() {
		LdapContextSource cs = new LdapContextSource();
		cs.setCacheEnvironmentProperties(false);
		cs.setUrl(url);
		cs.setBase(baseDn);
		cs.setAuthenticationSource(new AuthenticationSource() {
			@Override
			public String getCredentials() {
				return credentials;
			}

			@Override
			public String getPrincipal() {
				return principal;
			}
		});
		template = new LdapTemplate(cs);
	}

	/**获取所有的用户数据
	 * @param isFromCache 是否从本地缓存取数据 true/false
	 * @return
	 */
	public List<User> getAllUser(boolean isFromCache) {
		List<User> result = null;
		boolean isFromLocal = false;
		if(isFromCache){
			result = cacheMap.get(USER_CACHE_KEY);
			if(null==result){
				isFromLocal = true;
			}
		}else{
			isFromLocal = true;
		}
		if(isFromLocal){
			try {
				result = new ArrayList<User>();
				String baseCeo = "OU=CEO";
				String base_hk = "OU=staff-hk,OU=howbuy-hk";
				String base_pd = "OU=staff-pd,OU=howbuy-pd";
				String base_sxs = "OU=trainee";
				List<User> ceo = template.search(baseCeo, "(objectclass=user)",
						new UserMapper());
				result.addAll(ceo);
				List<User> hk = template.search(base_hk, "(objectclass=user)",
						new UserMapper());
				result.addAll(hk);
				List<User> pd = template.search(base_pd, "(objectclass=user)",
						new UserMapper());
				result.addAll(pd);
				List<User> sxs = template.search(base_sxs, "(objectclass=user)",
						new UserMapper());
				result.addAll(sxs);
				
				putUsersToCache(result);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	private void putUsersToCache(List<User> result){
		cacheMap.put(USER_CACHE_KEY, result);
		System.out.println("put  key ["+USER_CACHE_KEY+"] value into localCache successed");
	}
	
	/**
	 * 判断用户是否合法，当用户名密码都正确的时候返回true，否则false
	 * @param UID
	 * @param password
	 * @return
	 */
	public boolean authenricate(String UID, String password) {
		return authentication.authenricate(UID, password);
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getBaseDn() {
		return baseDn;
	}

	public void setBaseDn(String baseDn) {
		this.baseDn = baseDn;
	}

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public String getCredentials() {
		return credentials;
	}

	public void setCredentials(String credentials) {
		this.credentials = credentials;
	}

	public LDAPAuthentication getAuthentication() {
		return authentication;
	}

	public void setAuthentication(LDAPAuthentication authentication) {
		this.authentication = authentication;
	}

}
