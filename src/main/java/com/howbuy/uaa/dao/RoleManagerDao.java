package com.howbuy.uaa.dao;

import java.util.List;

import com.howbuy.uaa.persistence.Permission;
import com.howbuy.uaa.persistence.PermissionRelation;
import com.howbuy.uaa.persistence.Role;
import com.howbuy.uaa.persistence.RolePermission;
import com.howbuy.uaa.persistence.User;
import com.howbuy.uaa.persistence.UserPermissionRelation;
import com.howbuy.uaa.persistence.UserRoleRelation;
import com.howbuy.uaa.utils.PermissionException;

public interface RoleManagerDao {

	/**
	 * 
	 * @return 所有角色
	 */
	public List<Role> queryAllRole();
	
	
	/**
	 * 
	 * @param roleId
	 * @param level
	 * @param status
	 * @return 根据角色查询权限
	 */
	public List<Permission> queryPermissionByRole(long roleId,String level,String status);
	
	/**
	 * 批量保存角色权限关系
	 * @param list
	 */
	public void batchSaveRolePermission(List<RolePermission> list);
	
	/**
	 * 批量删除角色权限关系
	 * @param list
	 */
	public void batchDeleteRolePermission(List<Long> list);
	/**
	 * @param roleName
	 * @param status
	 * @return 根据角色名称查询角色
	 */
	public Role queryRoleByName(String roleName,String status);
	
	/**
	 * @param id
	 * @param status
	 * @return 根据角色名称查询角色
	 */
	public Role queryRoleById(long id,String status);
	
	/**
	 * 删除角色权限关系
	 * @param roleId
	 */
	public void deleteRolePermission(long roleID) throws PermissionException;
	
	/**
	 * 
	 * @param roleId
	 * @return 根据角色查询用户角色关系
	 */
	public List<UserRoleRelation> queryUserRoleRel(long roleId);
	
	/**
	 * 更新角色信息
	 * @param name
	 * @param descriptions
	 * @param content
	 */
	public void updateRole(String name,String descriptions,String content,long roleId,long version)throws PermissionException;
	
	/**
	 * 插入角色
	 * @param role
	 * @return
	 */
	public int insertRole(Role role);
	
	/**
	 * 删除角色
	 * @param role
	 */
	public void deleteRole(Role role)throws PermissionException;
	
	/**
	 *  根据角色查询用户信息
	 * @param roleId
	 * @return
	 */
	public List<User> queryAllUserByRole(long roleId);
	
}
