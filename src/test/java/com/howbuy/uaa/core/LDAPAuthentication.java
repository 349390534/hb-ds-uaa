package com.howbuy.uaa.core;

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

public class LDAPAuthentication {
	private final String URL = "ldap://192.168.230.11:389/";
	private final String BASEDN = "ou=howbuy,dc=howbuy,dc=domain";
	private final String FACTORY = "com.sun.jndi.ldap.LdapCtxFactory";
	private LdapContext ctx = null;
	private final Control[] connCtls = null;

	private void LDAP_connect() throws Exception {
		Hashtable<String, String> env = new Hashtable<String, String>();
		env.put(Context.INITIAL_CONTEXT_FACTORY, FACTORY);
		env.put(Context.PROVIDER_URL, URL + BASEDN);
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		
		String root = "ldap_howbuyreport";// root
		env.put(Context.SECURITY_PRINCIPAL, root);
		env.put(Context.SECURITY_CREDENTIALS, "MrG7CL1R");
		// 此处若不指定用户名和密码,则自动转换为匿名登录
		
		try {
			ctx = new InitialLdapContext(env, connCtls);
		} catch (javax.naming.AuthenticationException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
	}
		

	private String getUserDN(String uid) throws Exception {
		String userDN = "";
		LDAP_connect();
		try {
			SearchControls constraints = new SearchControls();
			constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);
			NamingEnumeration<SearchResult> en = ctx.search("", "sAMAccountName=" + uid, constraints);
			if (en == null || !en.hasMoreElements()) {
				System.out.println("未找到该用户");
			}
			// maybe more than one element
			while (en != null && en.hasMoreElements()) {
				Object obj = en.nextElement();
				if (obj instanceof SearchResult) {
					SearchResult si = (SearchResult) obj;
					userDN += si.getName();
					userDN += "," + BASEDN;
				} else {
					System.out.println(obj);
				}
			}
		} catch (Exception e) {
			System.out.println("查找用户时产生异常。");
			e.printStackTrace();
		}
		return userDN;
	}
	
	 public boolean authenricate(String UID, String password) {
	        boolean valide = false;
	        String userDN = "";
	        try {
	        	userDN = getUserDN(UID);
	        	if(StringUtils.isNotBlank(userDN)){
	        		System.out.println("userDN:" + userDN);
	        		ctx.addToEnvironment(Context.SECURITY_PRINCIPAL, userDN);
	        		ctx.addToEnvironment(Context.SECURITY_CREDENTIALS, password);
	        		ctx.reconnect(connCtls);
	        		System.out.println(userDN + " 验证通过");
	        		valide = true;
	        	}
	        } catch (AuthenticationException e) {
	            System.out.println(" 验证失败");
	            e.printStackTrace();
	            valide = false;
	        } catch (NamingException e) {
	            System.out.println(userDN + " 验证失败");
	            valide = false;
	        } catch (Exception e) {
				e.printStackTrace();
			}
	 
	        return valide;
	    }
		
	 public static void main(String[] args) {
		 
		 LDAPAuthentication auth = new LDAPAuthentication();
		 
		System.out.println(auth.authenricate("qiankun.li","!QAZ@WSX3edc"));
		System.out.println(auth.authenricate("qiankun.li","123456"));
		System.out.println(auth.authenricate("qiankun.li22","!QAZ@WSX3edc"));
		System.out.println(auth.authenricate("qiankun.li22","1234"));
	}
	
}
