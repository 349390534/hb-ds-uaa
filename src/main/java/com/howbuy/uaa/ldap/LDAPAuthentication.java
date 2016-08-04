
package com.howbuy.uaa.ldap;

import java.util.Hashtable;

import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.Control;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LDAPAuthentication {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(LDAPAuthentication.class);

	private String url;
	private String baseDn;
	private String principal;
	private String credentials;
	private String factory = "com.sun.jndi.ldap.LdapCtxFactory";
	private LdapContext ctx = null;
	private final Control[] connCtls = null;

	private void LDAP_connect() throws Exception {
		if (null == ctx) {
			Hashtable<String, String> env = new Hashtable<String, String>();
			env.put(Context.INITIAL_CONTEXT_FACTORY, factory);
			env.put(Context.PROVIDER_URL, url + baseDn);
			env.put(Context.SECURITY_AUTHENTICATION, "simple");

			env.put(Context.SECURITY_PRINCIPAL, principal);
			env.put(Context.SECURITY_CREDENTIALS, credentials);
			// 此处若不指定用户名和密码,则自动转换为匿名登录
			try {
				ctx = new InitialLdapContext(env, connCtls);
			} catch (javax.naming.AuthenticationException e) {
				throw e;
			} catch (Exception e) {
				throw e;
			}
		}
	}

	private String getUserDN(String uid) throws Exception {
		String userDN = "";
		LDAP_connect();
		try {
			SearchControls constraints = new SearchControls();
			constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);
			NamingEnumeration<SearchResult> en = ctx.search("","sAMAccountName=" + uid, constraints);
			if (en == null || !en.hasMoreElements()) {
				LOGGER.warn("未找到用户:" + uid);
				return userDN;
			}
			// maybe more than one element
			while (en != null && en.hasMoreElements()) {
				Object obj = en.nextElement();
				if (obj instanceof SearchResult) {
					SearchResult si = (SearchResult) obj;
					userDN += si.getName();
					userDN += "," + baseDn;
				}
			}
		} catch (Exception e) {
			LOGGER.error("查找用户[" + uid + "]时产生异常,{}", e.getMessage());
		}
		return userDN;
	}

	public boolean authenricate(String UID, String password) {
		boolean valide = false;
		String userDN = "";
		try {
			userDN = getUserDN(UID);
			if(StringUtils.isNotBlank(userDN)){
				LOGGER.info("userDN:" + userDN);
				ctx.addToEnvironment(Context.SECURITY_PRINCIPAL, userDN);
				ctx.addToEnvironment(Context.SECURITY_CREDENTIALS, password);
				ctx.reconnect(connCtls);
				LOGGER.info(userDN + ",验证通过");
				valide = true;
			}
		} catch (AuthenticationException e) {
			LOGGER.warn(userDN + ",验证失败,{}", e.getMessage());
		} catch (NamingException e) {
			LOGGER.warn(userDN + ",验证失败,{}", e.getMessage());
		} catch (Exception e) {
			LOGGER.warn(userDN + ",验证失败,{}", e.getMessage());
		}
		
		try {
			ctx.close();
		} catch (NamingException e) {
			LOGGER.info("关闭LdapContext对象失败,{}", e.getMessage());
		}finally{
			if(null!=ctx){
				ctx=null;
			}
		}
		
		return valide;
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

}
