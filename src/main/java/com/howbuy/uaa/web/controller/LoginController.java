package com.howbuy.uaa.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.howbuy.uaa.ldap.LDAPAuthentication;
import com.howbuy.uaa.ldap.UaaLdapTemplate;
import com.howbuy.uaa.persistence.Permission;
import com.howbuy.uaa.persistence.User;
import com.howbuy.uaa.persistence.UserRoleRelation;
import com.howbuy.uaa.service.UserManagerService;
import com.howbuy.uaa.utils.JsonParse;
import com.howbuy.uaa.web.controller.listener.PermissionSessionListener;

public class LoginController extends MultiActionController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PermissionManagerController.class);
	private UserManagerService userManagerService;
	private String index;
	private String home;
	private LDAPAuthentication authentication;
	private UaaLdapTemplate ldapTemplate;
	private String pererror;
	
	private String popLogin;
	
	/**
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView index(HttpServletRequest request,HttpServletResponse response)
	{
//		List<RouteManageCommand> list = analysisDataToolService.queryAllRoute(1);
		User u = (User)request.getSession().getAttribute("USERSESSION");
		
		if(u == null)
			return new ModelAndView(index);
		else
			return new ModelAndView(home);
//		.addObject("option1", list)
		
	}
	public ModelAndView login(HttpServletRequest request,HttpServletResponse response,User user)
	{
		logger.debug(home);
		User u = (User)request.getSession().getAttribute("USERSESSION");
		if(u!=null){
			return new ModelAndView(home);
		}
		String method = request.getMethod();
		if("get".equalsIgnoreCase(method)){
			return new ModelAndView(index);
		}
		String name = user.getUserName();
		String pwd = user.getPwd();
		LOGGER.debug("name:"+name+" is login");
		String yzm = request.getParameter("yzm");
		Map<String,Object> map = new HashMap<String,Object>();
		Object yyzm = request.getSession().getAttribute("validateCode");
		if(yyzm !=null )
			yyzm = yyzm.toString();
		boolean flag = ldapTemplate.authenricate(name, pwd);
		if(flag){
			if(!yzm.equals(yyzm)){
				map.put("message", "yzmError");
				map.put("name", name);
				map.put("pwd", pwd);
				map.put("yzm", yzm);
				return new ModelAndView(index).addAllObjects(map);
			}else{
				User us = userManagerService.queryUserByName(name);
				if(us!= null){
					String headPermission = userManagerService.queryAllPermission();
					List<Permission> allPer = userManagerService.queryAllPerByUser(us);
					List<UserRoleRelation> userRoleList = userManagerService.queryUserRoleRelByUserId(user.getId());
					List<Permission> perList = userManagerService.queryPermissionByUserId(user.getId());
					String allPerList = JsonParse.arrayToJsonStr(allPer);
					HttpSession session = request.getSession(true);
					session.setAttribute("headPermission", headPermission);
					session.setAttribute("allPerList", allPerList);
					session.setAttribute("USERSESSION", us);
					session.setAttribute("ALL_PERMISSION", allPer);
					session.setAttribute("USERROLESESSION", userRoleList);
					session.setAttribute("USERPERMISISON", perList);
					
					session.setAttribute("userbinding", new PermissionSessionListener(us.getId()));
				}else{
					logger.warn(name+" has no permission.");
					map.put("message", name+" has no permission.");
					map.put("name", name);
					return new ModelAndView(index).addAllObjects(map);
				}			
			}	
		}else{
			map.put("message", "userError");
			map.put("name", name);
			map.put("pwd", pwd);
			map.put("yzm", yzm);
			return new ModelAndView(index).addAllObjects(map);
		}
		return new ModelAndView(home).addAllObjects(map);
		
	}  
	
	public ModelAndView pererror(HttpServletRequest request,HttpServletResponse response)
	{	
		return new ModelAndView(pererror);		
	}
	
	
	public ModelAndView loginout(HttpServletRequest request,HttpServletResponse response)
	{
		request.getSession().invalidate();
		
		return new ModelAndView(index);
		
	}
	
	
	/**
	 * 判断是否超时
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView isSessionOut(HttpServletRequest request,HttpServletResponse response)
	{
		
		User u = (User)request.getSession().getAttribute("USERSESSION");
		if(u==null){
			return new ModelAndView(popLogin);
		}
		return null;
	}
	
	
	public UserManagerService getUserManagerService() {
		return userManagerService;
	}
	public void setUserManagerService(UserManagerService userManagerService) {
		this.userManagerService = userManagerService;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getHome() {
		return home;
	}
	public void setHome(String home) {
		this.home = home;
	}
	public LDAPAuthentication getAuthentication() {
		return authentication;
	}
	public void setAuthentication(LDAPAuthentication authentication) {
		this.authentication = authentication;
	}
	public UaaLdapTemplate getLdapTemplate() {
		return ldapTemplate;
	}
	public void setLdapTemplate(UaaLdapTemplate ldapTemplate) {
		this.ldapTemplate = ldapTemplate;
	}
	public String getPererror() {
		return pererror;
	}
	public void setPererror(String pererror) {
		this.pererror = pererror;
	}

	public String getPopLogin() {
		return popLogin;
	}

	public void setPopLogin(String popLogin) {
		this.popLogin = popLogin;
	}
}
