package com.howbuy.uaa.dao;

import java.util.List;
import java.util.Map;

import com.howbuy.uaa.persistence.Permission;
import com.howbuy.uaa.persistence.PermissionRelation;
import com.howbuy.uaa.persistence.RolePermission;
import com.howbuy.uaa.persistence.UserPermissionRelation;
import com.howbuy.uaa.utils.PermissionException;

public interface PermissionManagerDao {

	/**
	 * 
	 * @param level
	 * @param status
	 * @return 查询所有权限
	 */
	public List<Permission> queryPermission(String level,String status);
	/**
	 * @param level
	 * @param id
	 * @return 所有子权限
	 */
	public List<Permission> queryChildPermission(String level,long id);
	/**
	 * 根据权限ID查询权限
	 * @param permissionId
	 * @param status
	 * @return 
	 */
	public Permission queryPermissionByPermissionId(String permissionId,String status);
	
	/**
	 * 更新权限信息
	 * @param permission
	 */
	public void updatePermission(Permission permission) throws PermissionException;
	
	/**
	 * 更新权限关系
	 * @param permissionId
	 * @param parentId
	 */
	public void updatePermissionRelation(long permissionId,long parentId,long version) throws PermissionException;
	
	/**
	 * 删除权限信息
	 * @param id
	 */
	public void deletePermission(long id) throws PermissionException;
	/**
	 * 删除权限关系
	 * @param permissionId
	 */
	public void deletePermissionRelation(long permissionId,long parentId) throws PermissionException;
	/**
	 * 新建权限信息
	 * @param permission
	 */
	public int insertPermission(Permission permission);
	/**
	 * 插入权限关系
	 * @param relation
	 */
	public void insertPermissionRelation(PermissionRelation relation);
	
	/**
	 * 根据权限ID查询绑定用户
	 * @param permissionId
	 * @return
	 */
	public List<UserPermissionRelation> queryUserPermissionRel(long permissionId);
	
	/**
	 * 根据权限ID查询角色权限关系
	 * @param permissionId
	 * @return
	 */
	public List<RolePermission> queryRolePermissionByPerID(long permissionId);
	
	/**
	 * 根据Id查询权限
	 * @param id
	 * @return
	 */
	public Permission queryPermissionById(long id);
	
	/**
	 * 根据子ID和父ID查询权限关系
	 * @param childId
	 * @param parentId
	 * @return
	 */
	public PermissionRelation queryPerRelByChildIdAndParentId(long childId,long parentId);
	
	public Object querVersion(Map<String,Object> map);

}
