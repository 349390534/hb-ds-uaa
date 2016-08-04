package com.howbuy.uaa.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.howbuy.uaa.filter.PermissionchantFilter;
import com.howbuy.uaa.persistence.Permission;
import com.howbuy.uaa.persistence.RolePermission;
import com.howbuy.uaa.persistence.UserPermissionRelation;
import com.howbuy.uaa.service.PermissionManagerService;
import com.howbuy.uaa.utils.PermissionException;

public class PermissionManagerController extends MultiActionController {
	private static final Logger LOGGER = LoggerFactory.getLogger(PermissionManagerController.class);
	private PermissionManagerService permissionManagerService;
	private String index;
	private String error;
	
	
	/**
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView index(HttpServletRequest request,HttpServletResponse response)
	{
//		List<RouteManageCommand> list = analysisDataToolService.queryAllRoute(1);
		 
		logger.debug(index);
		String navId = request.getParameter("navId");
		String pageId = request.getParameter("pageId");
		String json = permissionManagerService.queryAllPermission();
		Map<String,Object> modelMap = new HashMap<String, Object>();
		modelMap.put("permission", json);
//		modelMap.put("navId", navId);
//		modelMap.put("pageId", pageId);
		return new ModelAndView(index).addAllObjects(modelMap);
//		.addObject("option1", list)
		
	}
	public ModelAndView addPermission(HttpServletRequest request,HttpServletResponse response,Permission permission)
	{
		
		Map<String,Object> map = new HashMap<String,Object>();		
		Permission per = permissionManagerService.queryPermissionByPerId(permission.getPermissionId());
		logger.debug("per:"+per);
		if(per!=null){
			map.put("perMessage", "error");
		}else{
			permissionManagerService.insertPermissionOrPerRel(permission);  
			PermissionchantFilter.allPermission = permissionManagerService.queryPermission("", "0");
    		map.put("perMessage", "success");
		}

		return new ModelAndView(error).addAllObjects(map);
		
	}
	public ModelAndView updatePermission(HttpServletRequest request,HttpServletResponse response,Permission permission)
	{
		String oldPermission = permission.getOldPermissionId();
		String permissionId = permission.getPermissionId();
		long id = permission.getId();
		Map<String,Object> map = new HashMap<String,Object>();
		if(!oldPermission.equals(permissionId)){
			Permission per = permissionManagerService.queryPermissionByPerId(permissionId);
			logger.debug("per:"+per);
			if(per!=null){
				map.put("perMessage", "error");
				return new ModelAndView(error).addAllObjects(map);
			}
		}
//		Permission p = permissionManagerService.queryPermissionById(id);
//		permission.setVersion(p.getVersion());
		try {
			permissionManagerService.updatePermissionAndPerRel(permission);
		} catch (PermissionException e) {
			
			map.put("perMessage", "updateError");
			return new ModelAndView(error).addAllObjects(map);
		}
		map.put("perMessage", "success");			
		return new ModelAndView(error).addAllObjects(map);
		
	}
	public ModelAndView queryDeletePermission(HttpServletRequest request,HttpServletResponse response,Permission permission)
	{
		Map<String,Object> map = new HashMap<String,Object>();
		List<UserPermissionRelation> userPerRelList = permissionManagerService.queryUserPermissionRel(permission.getId());
    	List<RolePermission> rolePerList = permissionManagerService.queryRolePermissionByPerID(permission.getId());
    	if((userPerRelList == null || userPerRelList.size()==0) &&
    			(rolePerList == null || rolePerList.size()==0)){
    		map.put("perMessage", "success");
    	}else{
    		map.put("perMessage", "error");  		
    	}
			
		return new ModelAndView(error).addAllObjects(map);
		
	}
	public ModelAndView deletePermission(HttpServletRequest request,HttpServletResponse response,Permission permission)
	{
		Map<String,Object> map = new HashMap<String,Object>();
    	try {
			permissionManagerService.deletePermissionAndPerRel(permission);
		} catch (PermissionException e) {
			
			map.put("perMessage", "deleteError");
			return new ModelAndView(error).addAllObjects(map);
		}
    	
    	map.put("perMessage", "success");

		return new ModelAndView(error).addAllObjects(map);
		
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

	public void setPermissionManagerService(
			PermissionManagerService permissionManagerService) {
		this.permissionManagerService = permissionManagerService;
	}
}
