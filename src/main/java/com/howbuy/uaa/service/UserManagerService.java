package com.howbuy.uaa.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.howbuy.uaa.dao.PermissionManagerDao;
import com.howbuy.uaa.dao.RoleManagerDao;
import com.howbuy.uaa.dao.UserManagerDao;
import com.howbuy.uaa.dto.PermissionDto;
import com.howbuy.uaa.persistence.Permission;
import com.howbuy.uaa.persistence.Role;
import com.howbuy.uaa.persistence.User;
import com.howbuy.uaa.persistence.UserPermissionRelation;
import com.howbuy.uaa.persistence.UserRoleRelation;
import com.howbuy.uaa.utils.JsonParse;
import com.howbuy.uaa.utils.PermissionException;

public class UserManagerService {
	
	private UserManagerDao userManagerDao;
	private PermissionManagerDao permissionManagerDao;
	private PermissionManagerService permissionManagerService;
	private RoleManagerDao roleManagerDao;

	public void setRoleManagerDao(RoleManagerDao roleManagerDao) {
		this.roleManagerDao = roleManagerDao;
	}

	public void setPermissionManagerService(
			PermissionManagerService permissionManagerService) {
		this.permissionManagerService = permissionManagerService;
	}

	public void setPermissionManagerDao(PermissionManagerDao permissionManagerDao) {
		this.permissionManagerDao = permissionManagerDao;
	}

	public void setUserManagerDao(UserManagerDao userManagerDao) {
		this.userManagerDao = userManagerDao;
	}

	/**
	 * 根据用户名查询用户
	 * @param name
	 * @return
	 */
	public User queryUserByName(String name){
		User user = userManagerDao.queryUserByName(name);
		return user;
	}
	
	/**
	 * 更新用户基本信息
	 * @param user
	 * @throws PermissionException 
	 */
	public void updateUser(User user) throws PermissionException{
		userManagerDao.updateUser(user);
	}
	
	/**
	 * 根据用户ID查询用户角色关系
	 * @param userId
	 * @return
	 */
	public List<UserRoleRelation> queryUserRoleRelByUserId(long userId){
		List<UserRoleRelation> list = userManagerDao.queryUserRoleRelByUserId(userId);
		return list;
	}
	
	/**
	 * 根据用户ID查询用户权限关系
	 * @param userId
	 * @return
	 */
	public List<UserPermissionRelation> queryUserPerRelByUserId(long userId){
		return userManagerDao.queryUserPerRelByUserId(userId);
	}
	
	/**
	 * 删除用户角色关系
	 * @param userId
	 * @throws PermissionException 
	 */
	public void deleteUserRoleRelByUserId(long userId) throws PermissionException{
		userManagerDao.deleteUserRoleRelByUserId(userId);
	}
	/**
	 * 批量更新用户权限关系
	 * @param list
	 */
	public void batchSaveUserRoleRelBy(List<UserRoleRelation> list){
		userManagerDao.batchSaveUserRoleRelBy(list);
	}
	
	/**
	 * 删除用户权限关系
	 * @param userId
	 * @throws PermissionException 
	 */
	public void deleteUserPerRelByUserId(long userId) throws PermissionException{
		userManagerDao.deleteUserPerRelByUserId(userId);
	}
	
	/**
	 * 批量更新用户权限关系
	 * @param list
	 */
	public void batchSaveUserPerRel(List<UserPermissionRelation> list){
		userManagerDao.batchSaveUserPerRel(list);
	}
	public List<Permission> queryRolePermissionByUserId(long userId){
		return userManagerDao.queryRolePermissionByUserId(userId);
	}
	public User quertUser(User user){
		return userManagerDao.querUser(user);
	}
	public List<Permission> queryPermissionByUserId(long userId){
		return userManagerDao.queryPermissionByUserId(userId);
	}
	public String queryAllPermissionByUserId(User user){
		List<Permission> rolePerList = userManagerDao.queryRolePermissionByUserId(user.getId());
		List<Permission> perList = userManagerDao.queryPermissionByUserId(user.getId());
		List<Long> allPer = new ArrayList<Long>();
		for(Permission rolePer:rolePerList){
			allPer.add(rolePer.getId());
		}
		for(Permission per:perList){
			if(!allPer.contains(per.getId()))
				allPer.add(per.getId());
		}
		List<Permission> navigationList = permissionManagerDao.queryPermission("1", "0");	
		List<Permission> menuList = new ArrayList<Permission>();
		List<Permission> pageList = new ArrayList<Permission>();
		List<PermissionDto> navList = new ArrayList<PermissionDto>();		
		for(Permission parent:navigationList){
			if(allPer.contains(parent.getId())){
				PermissionDto nav = new PermissionDto();
				nav.setId(parent.getId());
				nav.setName(parent.getName());
				nav.setPermissionId(parent.getPermissionId());
				nav.setUrl(parent.getUrl());
				menuList = permissionManagerDao.queryChildPermission(parent.getRescourcelevel(), parent.getId());
				List<PermissionDto> meList = new ArrayList<PermissionDto>();
				for(Permission child:menuList){
					if(allPer.contains(child.getId())){
						PermissionDto menu = new PermissionDto();
						menu.setId(child.getId());
						menu.setName(child.getName());
						menu.setPermissionId(child.getPermissionId());
						pageList = permissionManagerDao.queryChildPermission(child.getRescourcelevel(), child.getId());
						List<PermissionDto> paList = new ArrayList<PermissionDto>();
						for(Permission page:pageList){
							if(allPer.contains(page.getId())){
								PermissionDto pageVo = new PermissionDto();
								pageVo.setId(page.getId());
								pageVo.setName(page.getName());
								pageVo.setPermissionId(page.getPermissionId());
								pageVo.setUrl(page.getUrl());
								paList.add(pageVo);
							}
							
						}
						menu.setList(paList);
						meList.add(menu);
					}
					
				}
				nav.setList(meList);
				navList.add(nav);
			}
			
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("list", navList);
		String testMap = JsonParse.arrayToJsonStr(map);
//		System.out.println(testMap);
		return testMap;
	}
	public void updateUserRoleRel(long userId,String roleIds,String oldIds) throws PermissionException{
		String[] olds = {};
		List<UserRoleRelation> list = userManagerDao.queryUserRoleRelByUserId(userId);
		if(!"".equals(oldIds))
			olds = oldIds.split(":"); 
		else{
			if(list != null && list.size() > 0){
				throw new PermissionException("更新失败，用户角色已改变!");
			}
		}
		for(int i=0;i<olds.length;i++){
			
    		long id = (Long.valueOf(olds[i].toString())).longValue();
    		UserRoleRelation ur = userManagerDao.queryUserRoleById(id);
    		if(ur == null){
    			throw new PermissionException("更新失败,无此记录！"); 			
    		}		
    	}
		if(list!=null && list.size()>0){
			userManagerDao.deleteUserRoleRelByUserId(userId);
		}	
    	List<UserRoleRelation> urr = new ArrayList<UserRoleRelation>();
    	
    	String[] str = {};
    	if(!"".equals(roleIds))
    		str = roleIds.split(":"); 
    	UserRoleRelation ur = null;
    	for(int i=0;i<str.length;i++){
    		ur = new UserRoleRelation();
    		long perId = (Long.valueOf(str[i].toString())).longValue();
    		Role role = roleManagerDao.queryRoleById(perId, "");
    		if(role == null){
    			throw new PermissionException("色不存在!");
    		}
    		ur.setRoleId(perId);
    		ur.setUserId(userId);
    		ur.setStatus("0");
    		urr.add(ur);
    	}
    	if(urr!=null && urr.size()>0)
    		userManagerDao.batchSaveUserRoleRelBy(urr);
	}
	
	public void updateUserPerRel(long userId,String perIds,String oldIds) throws PermissionException{
		String[] olds = {};
		List<UserPermissionRelation> list = userManagerDao.queryUserPerRelByUserId(userId);
		if(!"".equals(oldIds))
			olds = oldIds.split(":"); 
		else{
			if(list != null && list.size() > 0){
				throw new PermissionException("更新失败，用户权限已改变!");
			}
		}
		for(int i=0;i<olds.length;i++){
			
    		long id = (Long.valueOf(olds[i].toString())).longValue();
    		UserPermissionRelation ur = userManagerDao.queryUserPerById(id);;
    		if(ur == null){
    			throw new PermissionException("更新失败,无此记录！"); 			
    		}		
    	}
		if(list!=null && list.size()>0){
			userManagerDao.deleteUserPerRelByUserId(userId);
		}	
    	List<UserPermissionRelation> urr = new ArrayList<UserPermissionRelation>();
    	
    	String[] str = {};
    	if(!"".equals(perIds))
    		str = perIds.split(":"); 
    	UserPermissionRelation ur = null;
    	for(int i=0;i<str.length;i++){
    		ur = new UserPermissionRelation();
    		long perId = (Long.valueOf(str[i].toString())).longValue();
    		Permission per = permissionManagerDao.queryPermissionById(perId);
    		if(per == null){
    			throw new PermissionException("权限不存在!");
    		}
    		ur.setPermissionId(perId);
    		ur.setUserId(userId);
    		ur.setStatus("0");
    		urr.add(ur);
    	}
    	if(urr!=null && urr.size()>0)
    		userManagerDao.batchSaveUserPerRel(urr);
	}
	public String queryAllPermission(){
		String allPermission = permissionManagerService.queryAllPermission();
		return allPermission;
	}
	public List<Permission> queryAllPerByUser(User user){
		List<Permission> rolePerList = userManagerDao.queryRolePermissionByUserId(user.getId());
		List<Permission> perList = userManagerDao.queryPermissionByUserId(user.getId());
		List<Long> allPer = new ArrayList<Long>();
		List<Permission> allPerList = new ArrayList<Permission>();
		for(Permission rolePer:rolePerList){
			allPer.add(rolePer.getId());
			allPerList.add(rolePer);
		}
		for(Permission per:perList){
			if(!allPer.contains(per.getId())){
				allPer.add(per.getId());
				allPerList.add(per);
			}
				
		}
		return allPerList;
	}
	public List<User> queryAllUser(){
		return userManagerDao.queryAllUser();
	}
	public User queryUserByUserId(long userId){
		return userManagerDao.queryUserById(userId);
	}
	public void addUser(User user) throws PermissionException{
		User us = userManagerDao.queryUserByName(user.getUserName());
		if(us != null){
			throw new PermissionException("添加失败");
		}else{
			userManagerDao.addUser(user);
		}
		
	}
}
