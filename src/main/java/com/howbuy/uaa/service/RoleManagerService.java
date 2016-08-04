package com.howbuy.uaa.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.howbuy.uaa.dao.RoleManagerDao;
import com.howbuy.uaa.persistence.Permission;
import com.howbuy.uaa.persistence.Role;
import com.howbuy.uaa.persistence.RolePermission;
import com.howbuy.uaa.persistence.User;
import com.howbuy.uaa.persistence.UserRoleRelation;
import com.howbuy.uaa.utils.PermissionException;

public class RoleManagerService {

	private RoleManagerDao roleManagerDao;
	private PermissionManagerService permissionManagerService;

	public void setPermissionManagerService(
			PermissionManagerService permissionManagerService) {
		this.permissionManagerService = permissionManagerService;
	}

	public void setRoleManagerDao(RoleManagerDao roleManagerDao) {
		this.roleManagerDao = roleManagerDao;
	}
	
	public List<Role> queryAllRole(){
		return roleManagerDao.queryAllRole();
	}
	
	
	public List<Permission> queryPermissionByRole(long roleId){
		List<Permission> navigationList = new ArrayList<Permission>();
		navigationList = roleManagerDao.queryPermissionByRole(roleId, "", "0");

		return navigationList;
	}
	public Role queryRoleByName(String roleName){
		Role role = roleManagerDao.queryRoleByName(roleName, "0");
		return role;
	}
	public void deleteRolePermission(long roleId) throws PermissionException{
		roleManagerDao.deleteRolePermission(roleId);
	}
	public void batchDeleteRolePermission(List<Long> list){
		roleManagerDao.batchDeleteRolePermission(list);
	}
	
	public void batchSaveRolePermission(List<RolePermission> list){
		roleManagerDao.batchSaveRolePermission(list);
	}
	public List<UserRoleRelation> queryUserRoleRel(long roleId){
		return roleManagerDao.queryUserRoleRel(roleId);
	}
	public void updateRole(String name,String descriptions,String content,long roleId,long version) throws PermissionException{
		roleManagerDao.updateRole(name, descriptions, content, roleId,version);
	}
	public int insertRole(Role role){
		int id = roleManagerDao.insertRole(role);
		return id;
	}
	public void deleteRole(Role role) throws PermissionException{
		roleManagerDao.deleteRole(role);	
	}
	public Role queryRoleById(long id){
		
		return roleManagerDao.queryRoleById(id, "0");
	}
	public String queryAllPermission(){
		String allPermission = permissionManagerService.queryAllPermission();
		return allPermission;
	}
	public void insertRoleAndRolePerRel(Role role,String allPerId) throws PermissionException{
		Role r = roleManagerDao.queryRoleByName(role.getName(),"0");
//		Map<String,Object> map = new HashMap<String,Object>();
		if(r!=null){
			throw new PermissionException("角色已存在");
		}else{	
			int id = roleManagerDao.insertRole(role);
			String[] strId = {};
			if(!"".equals(allPerId))
				  strId = allPerId.split(":");
			RolePermission perRel = null;
			List<RolePermission> list = new ArrayList<RolePermission>();
			for(int i=0;i<strId.length;i++){
				 perRel = new RolePermission();
				 perRel.setRoleId(id);
				 long perId = (Long.valueOf(strId[i].toString())).longValue();
				 Permission per = permissionManagerService.queryPermissionById(perId);
				 if(per == null){
//					 map.put("perMessage", "error");
//					 return map;
					 throw new PermissionException("权限不存在!");
				 }
				 perRel.setPermissionId(perId);
				 perRel.setStatus("0");
				 list.add(perRel);
			}
			if(list!=null && list.size()>0)
				roleManagerDao.batchSaveRolePermission(list);
//			map.put("perMessage", "success");
		}
		
		
	}
	
	public void updateRoleAndRolePerRel(Role role,String allPerId) throws PermissionException{
		roleManagerDao.updateRole(role.getName(), role.getDescriptions(), role.getContent(), role.getId(),role.getVersion());
		roleManagerDao.deleteRolePermission(role.getId());	
		String[] strId = {};
		if(!"".equals(allPerId))
		  strId = allPerId.split(":");
		RolePermission perRel = null;
		List<RolePermission> list = new ArrayList<RolePermission>();
		for(int i=0;i<strId.length;i++){
			perRel = new RolePermission();
			perRel.setRoleId(role.getId());
			long perId = (Long.valueOf(strId[i].toString())).longValue();
			Permission per = permissionManagerService.queryPermissionById(perId);
			 if(per == null){
				throw new PermissionException("更新错误");
			 }
			perRel.setPermissionId(perId);
			perRel.setStatus("0");
			list.add(perRel);
		}
		if(list != null && list.size()>0)
			roleManagerDao.batchSaveRolePermission(list);
	}
	public void deleteRoleAndRolePerRel(Role role) throws PermissionException{
		roleManagerDao.deleteRole(role);
		roleManagerDao.deleteRolePermission(role.getId());
	}
	public List<User> queryAllUserByRole(long roleId){
		return roleManagerDao.queryAllUserByRole(roleId);
	}
	
}
