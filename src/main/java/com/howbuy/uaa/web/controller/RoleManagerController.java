package com.howbuy.uaa.web.controller;

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
import com.howbuy.uaa.persistence.Permission;
import com.howbuy.uaa.persistence.Role;
import com.howbuy.uaa.persistence.User;
import com.howbuy.uaa.persistence.UserRoleRelation;
import com.howbuy.uaa.service.RoleManagerService;
import com.howbuy.uaa.utils.PermissionException;


public class RoleManagerController extends MultiActionController {
	private static final Logger LOGGER = LoggerFactory.getLogger(RoleManagerController.class);
	private RoleManagerService roleManagerService;
	
	private String index;
	private String error;
	private String rolePerDetail;
	
	public void setRolePerDetail(String rolePerDetail) {
		this.rolePerDetail = rolePerDetail;
	}
	public String getRolePerDetail() {
		return rolePerDetail;
	}
	/**
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView index(HttpServletRequest request,HttpServletResponse response)
	{
		List<Role> list = roleManagerService.queryAllRole();
		String allPermission = roleManagerService.queryAllPermission();
		for(Role role:list){
			List<Permission> permission = roleManagerService.queryPermissionByRole(role.getId());
			role.setPermission(permission);
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("allPermission", allPermission);
		map.put("roleList", list);
		return new ModelAndView(index).addAllObjects(map);
	}
	public ModelAndView addRole(HttpServletRequest request,HttpServletResponse response,Role role)
	{
		String allPerId = role.getAllPerId();
		String roleName = role.getName();
		String desc = role.getDescriptions();
		String content = role.getContent();
		if(!"".equals(roleName) && roleName !=null){
			if(roleName.length()>50){
				roleName = roleName.substring(0, 50);
				role.setName(roleName);
			}
			
		}
		if(!"".equals(desc) && desc !=null){
			if(desc.length()>100){
				desc = desc.substring(0, 100);
				role.setDescriptions(desc);
			}
			
		}
		if(!"".equals(content) && content !=null){
			if(content.length()>100){
				content = content.substring(0, 100);
				role.setContent(content);
			}
			
		}
		
//		Role r = roleManagerService.queryRoleByName(role.getName());
//		Map<String,Object> map = new HashMap<String,Object>();
//		if(r!=null){
//			map.put("perMessage", "error");
//		}else{
			
		Map<String, Object> map = new HashMap<String,Object>();
		try {
			 roleManagerService.insertRoleAndRolePerRel(role, allPerId);
			 map.put("perMessage", "success");
		} catch (PermissionException e) {
			map.put("perMessage", "error");
		}
//			map.put("perMessage", "success");
//		}
		
	
		return new ModelAndView(error).addAllObjects(map);
	}
	
	public ModelAndView updateRole(HttpServletRequest request,HttpServletResponse response,Role role)
	{
		String allPerId = role.getAllPerId();
		String roleName = role.getName();
		String desc = role.getDescriptions();
		String content = role.getContent();
		String oldName = role.getOldName();
//		long version = role.getVersion();
		if(!"".equals(roleName) && roleName !=null){
			if(roleName.length()>50){
				roleName = roleName.substring(0, 50);
				role.setName(roleName);
			}
			
		}
		if(!"".equals(desc) && desc !=null){
			if(desc.length()>100){
				desc = desc.substring(0, 100);
				role.setDescriptions(desc);
			}
			
		}
		if(!"".equals(content) && content !=null){
			if(content.length()>100){
				content = content.substring(0, 100);
				role.setContent(content);
			}
			
		}
		Map<String,Object> map = new HashMap<String,Object>();
		if(!oldName.equals(roleName)){
			Role r = roleManagerService.queryRoleByName(role.getName());
			if(r!=null){
				map.put("perMessage", "error");
				return new ModelAndView(error).addAllObjects(map);
			}
		}
		try {
				roleManagerService.updateRoleAndRolePerRel(role, allPerId);
//				request.getSession().getServletContext().setAttribute("ROLEUPDATE", Boolean.TRUE);
//				request.getSession().getServletContext().setAttribute("ROLEID_"+role.getId(), role);
				List<User> userList = roleManagerService.queryAllUserByRole(role.getId());
				String key = UaaContants.KEY_USERID;
				Set<User> set = (Set<User>)request.getSession().getServletContext().getAttribute(key);
				if(userList!=null && userList.size()>0){
					for(User user:userList){
						if(set == null ||set.size() == 0){
							set = new HashSet<User>();
							set.add(user);
							request.getSession().getServletContext().setAttribute(key,set);
						}else{
							set.add(user);
						}
						
					}
				}
				
			} catch (PermissionException e) {
				map.put("perMessage", "updateError");
				return new ModelAndView(error).addAllObjects(map);
			}
			map.put("perMessage", "success");
		
	
		return new ModelAndView(error).addAllObjects(map);
	}
	
	public ModelAndView queryDeleteRole(HttpServletRequest request,HttpServletResponse response,Role role)
	{
		Map<String,Object> map = new HashMap<String,Object>();
		List<UserRoleRelation> userRoleRelList = roleManagerService.queryUserRoleRel(role.getId());
    	if((userRoleRelList == null || userRoleRelList.size()==0) ){
    		map.put("perMessage", "success");
    	}else{
    		map.put("perMessage", "error");  		
    	}
			
		return new ModelAndView(error).addAllObjects(map);
		
	}
	public ModelAndView deleteRole(HttpServletRequest request,HttpServletResponse response,Role role)
	{
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			roleManagerService.deleteRoleAndRolePerRel(role);
		} catch (PermissionException e) {
			map.put("perMessage", "deleteError");
			return new ModelAndView(error).addAllObjects(map);
		}	
    	map.put("perMessage", "success");

		return new ModelAndView(error).addAllObjects(map);
		
	}
	public ModelAndView queryAllPer(HttpServletRequest request,HttpServletResponse response)
	{
		
		List<Role> list = roleManagerService.queryAllRole();
		String allPermission = roleManagerService.queryAllPermission();
		for(Role role:list){
			List<Permission> permission = roleManagerService.queryPermissionByRole(role.getId());
			role.setPermission(permission);
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("allPermission", allPermission);
		map.put("roleList", list);
		return new ModelAndView(rolePerDetail).addAllObjects(map);
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
	public void setRoleManagerService(RoleManagerService roleManagerService) {
		this.roleManagerService = roleManagerService;
	}
		
}
