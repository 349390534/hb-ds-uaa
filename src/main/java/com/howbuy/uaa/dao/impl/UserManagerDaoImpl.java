package com.howbuy.uaa.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.howbuy.rdb.database.dao.impl.BaseDaoImpl;
import com.howbuy.uaa.dao.UserManagerDao;
import com.howbuy.uaa.persistence.Permission;
import com.howbuy.uaa.persistence.User;
import com.howbuy.uaa.persistence.UserPermissionRelation;
import com.howbuy.uaa.persistence.UserRoleRelation;
import com.howbuy.uaa.utils.PermissionException;

public class UserManagerDaoImpl extends BaseDaoImpl implements UserManagerDao {

	public User queryUserByName(String name) {
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("name", name);	
		return (User)getSqlMapClientTemplate().queryForObject("UserManager.queryUserByName", map);
	}

	public void updateUser(User user) throws PermissionException {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("name", user.getUserName());
		map.put("chineseName", user.getChineseName());
		map.put("email", user.getEmail());
		map.put("telphone", user.getTelphone());
		map.put("version", user.getVersion());
		int count = getSqlMapClientTemplate().update("UserManager.updateUser",map);
		if(count<=0){
			throw new PermissionException("更新失败,无此记录！");
		}
	}

	@SuppressWarnings("unchecked")
	public List<UserRoleRelation> queryUserRoleRelByUserId(long userId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", userId);	
		return getSqlMapClientTemplate().queryForList("UserManager.queryUserRoleRelByUserId", map);
	}

	@SuppressWarnings("unchecked")
	public List<UserPermissionRelation> queryUserPerRelByUserId(long userId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", userId);	
		return getSqlMapClientTemplate().queryForList("UserManager.queryUserPerRelByUserId", map);
	}

	
	public void deleteUserRoleRelByUserId(long userId) throws PermissionException{
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", userId);	
		int count = getSqlMapClientTemplate().delete("UserManager.deleteUserRoleRelByUserId", map);
		if(count<=0){
			throw new PermissionException("更新失败,无此记录！");
		}
	}

	
	public void batchSaveUserRoleRelBy(List<UserRoleRelation> list) {
		getSqlMapClientTemplate().insert("UserManager.batchSaveUserRoleRelBy", list);
	}

	public void deleteUserPerRelByUserId(long userId) throws PermissionException{
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", userId);	
		int count = getSqlMapClientTemplate().delete("UserManager.deleteUserPerRelByUserId", map);
		if(count<=0){
			throw new PermissionException("更新失败,无此记录！");
		}
	}

	
	public void batchSaveUserPerRel(List<UserPermissionRelation> list) {
		getSqlMapClientTemplate().insert("UserManager.batchSaveUserPerRel", list);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Permission> queryRolePermissionByUserId(long userId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", userId);	
		return getSqlMapClientTemplate().queryForList("UserManager.queryRolePermissionByUserId", map);
	}

	@Override
	public User querUser(User user) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("name",user.getUserName());
		map.put("pwd",user.getPwd());
		map.put("status", user.getStatus());
		return (User)getSqlMapClientTemplate().queryForObject("UserManager.queryUser", map);
	}

	@SuppressWarnings("unchecked")
	public List<Permission> queryPermissionByUserId(long userId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", userId);	
		return getSqlMapClientTemplate().queryForList("UserManager.queryPermissionByUserId", map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> queryAllUser() {
		return getSqlMapClientTemplate().queryForList("UserManager.queryAllUser");
	}

	@Override
	public User queryUserById(long userId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", userId);	
		return (User)getSqlMapClientTemplate().queryForObject("UserManager.queryUserById",map);
	}

	@Override
	public UserRoleRelation queryUserRoleByUserIdAndRoleId(long userId,
			long roleId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", userId);
		map.put("roleId", roleId);
		
		return (UserRoleRelation)getSqlMapClientTemplate().queryForObject("UserManager.queryUserRoleByUserIdAndRoleId",map);		
	}

	@Override
	public UserPermissionRelation querUserPerByUserIdAndPerId(long userId,
			long perId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", userId);
		map.put("perId", perId);
		
		return (UserPermissionRelation)getSqlMapClientTemplate().queryForObject("UserManager.querUserPerByUserIdAndPerId",map);		
		
	}

	@Override
	public void addUser(User user) throws PermissionException {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userName", user.getUserName());
		map.put("chineseName", user.getChineseName());
		map.put("tel", user.getTelphone());
		map.put("email", user.getEmail());
		map.put("status", "0");
		getSqlMapClientTemplate().insert("UserManager.addUser", map);
		
	}

	@Override
	public UserRoleRelation queryUserRoleById(long id) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", id);		
		return (UserRoleRelation)getSqlMapClientTemplate().queryForObject("UserManager.queryUserRoleById",map);		
	}
	public UserPermissionRelation queryUserPerById(long id) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", id);		
		return (UserPermissionRelation)getSqlMapClientTemplate().queryForObject("UserManager.queryUserPerById",map);		
	}
}
