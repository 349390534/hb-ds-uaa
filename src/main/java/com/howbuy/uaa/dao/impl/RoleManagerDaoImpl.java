package com.howbuy.uaa.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.howbuy.rdb.database.dao.impl.BaseDaoImpl;
import com.howbuy.uaa.dao.RoleManagerDao;
import com.howbuy.uaa.persistence.Permission;
import com.howbuy.uaa.persistence.Role;
import com.howbuy.uaa.persistence.RolePermission;
import com.howbuy.uaa.persistence.User;
import com.howbuy.uaa.persistence.UserRoleRelation;
import com.howbuy.uaa.utils.PermissionException;

public class RoleManagerDaoImpl extends BaseDaoImpl implements RoleManagerDao {

	
	@SuppressWarnings("unchecked")
	public List<Role> queryAllRole() {
		return getSqlMapClientTemplate().queryForList("RoleManager.queryAllRole");		
	}

	
	@SuppressWarnings("unchecked")
	public List<Permission> queryPermissionByRole(long roleId, String level,
			String status) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("level", level);
		map.put("status", status);
		map.put("roleId", roleId);
		return getSqlMapClientTemplate().queryForList("RoleManager.queryPermissionByRole",map);
	}

	public void batchSaveRolePermission(List<RolePermission> list) {
		getSqlMapClientTemplate().insert("RoleManager.batchSaveRolePermission", list);
	}

	
	public void batchDeleteRolePermission(List<Long> list) {
		getSqlMapClientTemplate().delete("RoleManager.batchDeleteRolePermission", list);		
	}

	@Override
	public Role queryRoleByName(String roleName, String status) {
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("roleName", roleName);
		map.put("status", status);
		return (Role) getSqlMapClientTemplate().queryForObject("RoleManager.queryRoleByName",map);
	}
	
	@Override
	public Role queryRoleById(long id, String status) {
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", id);
		map.put("status", status);
		return (Role) getSqlMapClientTemplate().queryForObject("RoleManager.queryRoleById",map);
	}

	@Override
	public void deleteRolePermission(long roleID)throws PermissionException {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("roleId", roleID);
		getSqlMapClientTemplate().delete("RoleManager.deleteRolePermission", map);		
	}

	@SuppressWarnings("unchecked")
	public List<UserRoleRelation> queryUserRoleRel(long roleId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("roleId", roleId);
		return getSqlMapClientTemplate().queryForList("RoleManager.queryUserRoleRel",map);
	}

	@Override
	public void updateRole(String name, String descriptions, String content,
			long roleId,long version) throws PermissionException {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("name", name);
		map.put("descriptions", descriptions);
		map.put("content", content);
		map.put("roleID", roleId);
		map.put("version", version);
		int count = getSqlMapClientTemplate().update("RoleManager.updateRole", map);
		if(count<=0){
			throw new PermissionException("更新失败,无此记录！");
		}
		
	}

	public int insertRole(Role role){
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("descriptions", role.getDescriptions());
		params.put("name", role.getName());
		params.put("content", role.getContent());
		params.put("status", role.getStatus());
		params.put("createtime", role.getCreateTime());
		params.put("createuser", role.getCreateUser());
		return (Integer) getSqlMapClientTemplate().insert("RoleManager.insertRole", params);
	}
	public void deleteRole(Role role) throws PermissionException{
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("roleId", role.getId());
		int count = getSqlMapClientTemplate().delete("RoleManager.deleteRole", params);
		if(count<=0){
			throw new PermissionException("删除失败,无此记录！");
		}
	}


	@SuppressWarnings("unchecked")
	public List<User> queryAllUserByRole(long roleId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("roleId", roleId);
		return getSqlMapClientTemplate().queryForList("RoleManager.queryAllUserByRole",map);
	}
}
