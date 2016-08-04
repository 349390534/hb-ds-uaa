package com.howbuy.uaa.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.howbuy.uaa.common.contants.UaaContants;
import com.howbuy.uaa.ldap.UaaLdapTemplate;
import com.howbuy.uaa.persistence.Permission;
import com.howbuy.uaa.persistence.Role;
import com.howbuy.uaa.persistence.User;
import com.howbuy.uaa.persistence.UserPermissionRelation;
import com.howbuy.uaa.persistence.UserRoleRelation;
import com.howbuy.uaa.service.RoleManagerService;
import com.howbuy.uaa.service.UserManagerService;
import com.howbuy.uaa.utils.JsonParse;
import com.howbuy.uaa.utils.PermissionException;

public class UserManagerController extends MultiActionController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PermissionManagerController.class);
	private UserManagerService userManagerService;
	private String index;
	private String error;
	private String userInfo;
	private RoleManagerService roleManagerService;
	private UaaLdapTemplate ldapTemplate;

	/**
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView index(HttpServletRequest request,HttpServletResponse response)
	{
		logger.debug(index);	
		Map<String,Object> map = new HashMap<String,Object>();
		String navId = request.getParameter("navId");
		String pageId = request.getParameter("pageId");
		List<com.howbuy.uaa.ldap.User> ldapUser = ldapTemplate.getAllUser(true);
		map.put("ldapUser", ldapUser);
		request.getSession().setAttribute("ldapUser", ldapUser);
		//List<com.howbuy.uaa.ldap.User> userList = ldapTemplate.getAllUser();
		List<String> list =new ArrayList<String>(ldapUser.size());
		for(com.howbuy.uaa.ldap.User u :ldapUser){
			String s =u.getAccount();
			list.add(s);
		}
		List<User> allUser = userManagerService.queryAllUser();
		String json = JsonParse.arrayToJsonStr(list);
		map.put("json", json);
		map.put("allUser", allUser);
//		map.put("navId", navId);
//		map.put("pageId", pageId);
		return new ModelAndView(index).addAllObjects(map);		
	}
	public ModelAndView queryUserByName(HttpServletRequest request,HttpServletResponse response,User user)
	{
		String userName=user.getUserName();
		User u = userManagerService.queryUserByName(userName);
		Map<String,Object> map = new HashMap<String,Object>();
		if(u!=null){
			List<Role> roleList = roleManagerService.queryAllRole();
			List<UserRoleRelation> userRoleList = userManagerService.queryUserRoleRelByUserId(u.getId());
			List<UserPermissionRelation> userPerList = userManagerService.queryUserPerRelByUserId(u.getId());
			String allPermission = userManagerService.queryAllPermission();
//			List<Long> allList = userManagerService.queryAllPerByUser(u);
			List<Permission> rolePerList = userManagerService.queryRolePermissionByUserId(u.getId());
			List<Permission> perList = userManagerService.queryPermissionByUserId(u.getId());
			String rolelist = JsonParse.arrayToJsonStr(roleList);
			String userrolelist = JsonParse.arrayToJsonStr(userRoleList);
			String roleperlist = JsonParse.arrayToJsonStr(rolePerList);
			String perlist = JsonParse.arrayToJsonStr(perList);
			String userperlist = JsonParse.arrayToJsonStr(userPerList);
			
			map.put("userInfo", u);
			map.put("roleList", rolelist);
			map.put("userRoleList", userrolelist);
			map.put("allPermission", allPermission);
			map.put("rolePerList", roleperlist);
			map.put("perList", perlist);
			map.put("userperlist", userperlist);
			
			return new ModelAndView(userInfo).addAllObjects(map);
		}else{
			com.howbuy.uaa.ldap.User userLdap = new com.howbuy.uaa.ldap.User();
			userLdap.setAccount(userName);
			Object ldapUser =request.getSession().getAttribute("ldapUser");
			if(null!=ldapUser){
				List<com.howbuy.uaa.ldap.User>  ldapUserList= (List<com.howbuy.uaa.ldap.User>)ldapUser;
				for(com.howbuy.uaa.ldap.User userVar:ldapUserList){
					String account = userVar.getAccount();
					if(userName.equals(account)){
						userLdap=userVar;
						break;
					}
				}
				
			}else{
				throw new RuntimeException("need login");
			}
			map.put("userLdap", userLdap);
			
		}
		
		return new ModelAndView(userInfo).addAllObjects(map);		
	}
	public ModelAndView updateUser(HttpServletRequest request,HttpServletResponse response,User user)
	{
//		User u = userManagerService.queryUserByName(user.getUserName());
		Map<String,Object> map = new HashMap<String,Object>();
//		if(u!=null){
//			user.setVersion(u.getVersion());
//		}
		try {
			userManagerService.updateUser(user);
		} catch (PermissionException e) {
			map.put("perMessage", "updateError");
			return new ModelAndView(error).addAllObjects(map);
		}	
    	map.put("perMessage", "success");

		return new ModelAndView(error).addAllObjects(map);	
	}
	
	public ModelAndView updateUserRole(HttpServletRequest request,HttpServletResponse response,UserRoleRelation userRoleRelation)
	{
		long userId = userRoleRelation.getUserId();
		String roleIds = userRoleRelation.getRoleIds();
		String oldIds = userRoleRelation.getOldroleIds();
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			userManagerService.updateUserRoleRel(userId, roleIds,oldIds);
			String key = UaaContants.KEY_USERID;
			User user = userManagerService.queryUserByUserId(userId);
			Set<User> set = (Set<User>)request.getSession().getServletContext().getAttribute(key);
			if(set == null ||set.size() == 0){
				set = new HashSet<User>();
				set.add(user);
				request.getSession().getServletContext().setAttribute(key,set);
			}else{
				set.add(user);
			}
		} catch (PermissionException e) {
			map.put("perMessage", "updateError");
			return new ModelAndView(error).addAllObjects(map);
		}
			
    	map.put("perMessage", "success");

		return new ModelAndView(error).addAllObjects(map);	
	}
	public ModelAndView updateUserPer(HttpServletRequest request,HttpServletResponse response,UserPermissionRelation userPermissionRelation)
	{
		long userId = userPermissionRelation.getUserId();
		String perIds = userPermissionRelation.getPerIDs();
		String oldIds = userPermissionRelation.getOldperIds();
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			userManagerService.updateUserPerRel(userId, perIds,oldIds);
			String key = UaaContants.KEY_USERID;
			User user = userManagerService.queryUserByUserId(userId);
			Set<User> set = (Set<User>)request.getSession().getServletContext().getAttribute(key);
			if(set == null ||set.size() == 0){
				set = new HashSet<User>();
				set.add(user);
				request.getSession().getServletContext().setAttribute(key,set);
			}else{
				set.add(user);
			}
		} catch (PermissionException e) {
			map.put("perMessage", "updateError");
			return new ModelAndView(error).addAllObjects(map);
		}
			
    	map.put("perMessage", "success");

		return new ModelAndView(error).addAllObjects(map);	
	}
	
	public ModelAndView addUser(HttpServletRequest request,HttpServletResponse response,User user)
	{
//		User u = userManagerService.queryUserByName(user.getUserName());
		Map<String,Object> map = new HashMap<String,Object>();
//		if(u!=null){
//			user.setVersion(u.getVersion());
//		}
		try {
			userManagerService.addUser(user);
		} catch (PermissionException e) {
			map.put("perMessage", "addError");
			return new ModelAndView(error).addAllObjects(map);
		}	
    	map.put("perMessage", "success");

		return new ModelAndView(error).addAllObjects(map);	
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
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(String userInfo) {
		this.userInfo = userInfo;
	}
	public void setRoleManagerService(RoleManagerService roleManagerService) {
		this.roleManagerService = roleManagerService;
	}
	public UaaLdapTemplate getLdapTemplate() {
		return ldapTemplate;
	}
	public void setLdapTemplate(UaaLdapTemplate ldapTemplate) {
		this.ldapTemplate = ldapTemplate;
	}

}
