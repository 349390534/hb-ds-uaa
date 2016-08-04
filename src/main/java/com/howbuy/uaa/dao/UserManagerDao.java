package com.howbuy.uaa.dao;

import java.util.List;

import com.howbuy.uaa.persistence.Permission;
import com.howbuy.uaa.persistence.User;
import com.howbuy.uaa.persistence.UserPermissionRelation;
import com.howbuy.uaa.persistence.UserRoleRelation;
import com.howbuy.uaa.utils.PermissionException;

public interface UserManagerDao {
	/**
	 * 根据用户名查询用户
	 * @param name
	 * @return
	 */
	public User queryUserByName(String name);
	
	/**
	 * 更新用户基本信息
	 * @param user
	 */
	public void updateUser(User user) throws PermissionException;
	
	/**
	 * 根据用户ID查询用户角色关系
	 * @param userId
	 * @return
	 */
	public List<UserRoleRelation> queryUserRoleRelByUserId(long userId);
	
	/**
	 * 根据用户ID查询用户权限关系
	 * @param userId
	 * @return
	 */
	public List<UserPermissionRelation> queryUserPerRelByUserId(long userId);
	
	/**
	 * 删除用户角色关系
	 * @param userId
	 */
	public void deleteUserRoleRelByUserId(long userId)throws PermissionException;
	/**
	 * 批量更新用户权限关系
	 * @param list
	 */
	public void batchSaveUserRoleRelBy(List<UserRoleRelation> list);
	
	/**
	 * 删除用户权限关系
	 * @param userId
	 */
	public void deleteUserPerRelByUserId(long userId) throws PermissionException;
	
	/**
	 * 批量更新用户权限关系
	 * @param list
	 */
	public void batchSaveUserPerRel(List<UserPermissionRelation> list);
	
	/**
	 * 根据用户ID查询用户所有角色下的所有权限
	 * @param userId
	 * @return
	 */
	public List<Permission> queryRolePermissionByUserId(long userId);
	
	/**
	 * 用户登录
	 * @param user
	 * @return
	 */
	public User querUser(User user);
	
	/**
	 * 根据用户ID查询用户所有权限
	 * @param userId
	 * @return
	 */
	public List<Permission> queryPermissionByUserId(long userId);
	/**
	 * 查询所有用户
	 * @return
	 */
	public List<User> queryAllUser();
	/**
	 * 根据ID查询用户
	 * @param userId
	 * @return
	 */
	public User queryUserById(long userId);
	
	/**
	 * 根据角色ID，用户ID查询用户角色
	 * @param userId
	 * @param roleId
	 * @return
	 */
	public UserRoleRelation queryUserRoleByUserIdAndRoleId(long userId,long roleId);
	
	/**
	 * 根据用户ID，权限ID查询用户权限
	 * @param userId
	 * @param perId
	 * @return
	 */
	public UserPermissionRelation querUserPerByUserIdAndPerId(long userId,long perId);
	
	public void addUser(User user)throws PermissionException;
	/**
	 * 根据ID查询用户角色关系
	 * @param id
	 * @return
	 */
	public UserRoleRelation queryUserRoleById(long id);
	/**
	 * 根据ID查询用户权限关系
	 * @param id
	 * @return
	 */
	public UserPermissionRelation queryUserPerById(long id);
}
