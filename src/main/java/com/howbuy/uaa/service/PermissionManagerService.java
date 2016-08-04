package com.howbuy.uaa.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.howbuy.uaa.dao.PermissionManagerDao;
import com.howbuy.uaa.dto.PermissionDto;
import com.howbuy.uaa.persistence.Permission;
import com.howbuy.uaa.persistence.PermissionRelation;
import com.howbuy.uaa.persistence.RolePermission;
import com.howbuy.uaa.persistence.UserPermissionRelation;
import com.howbuy.uaa.utils.JsonParse;
import com.howbuy.uaa.utils.PermissionException;

public class PermissionManagerService {

	private PermissionManagerDao permissionManagerDao;

	public void setPermissionManagerDao(PermissionManagerDao permissionManagerDao) {
		this.permissionManagerDao = permissionManagerDao;
	}
	
	public String queryAllPermission(){
		List<Permission> navigationList = permissionManagerDao.queryPermission("1", "0");	
		List<Permission> menuList = new ArrayList<Permission>();
		List<Permission> pageList = new ArrayList<Permission>();
		List<PermissionDto> navList = new ArrayList<PermissionDto>();		
		for(Permission parent:navigationList){
			PermissionDto nav = new PermissionDto();
			nav.setId(parent.getId());
			nav.setName(parent.getName());
			nav.setPermissionId(parent.getPermissionId());
			nav.setUrl(parent.getUrl());
			nav.setLevel(parent.getRescourcelevel());
			menuList = permissionManagerDao.queryChildPermission(parent.getRescourcelevel(), parent.getId());
			List<PermissionDto> meList = new ArrayList<PermissionDto>();
			for(Permission child:menuList){
				PermissionDto menu = new PermissionDto();
				menu.setId(child.getId());
				menu.setName(child.getName());
				menu.setPermissionId(child.getPermissionId());
				pageList = permissionManagerDao.queryChildPermission(child.getRescourcelevel(), child.getId());
				List<PermissionDto> paList = new ArrayList<PermissionDto>();
				for(Permission page:pageList){
					PermissionDto pageVo = new PermissionDto();
					pageVo.setId(page.getId());
					pageVo.setName(page.getName());
					pageVo.setPermissionId(page.getPermissionId());
					pageVo.setDescriptions(page.getDescriptions());
					pageVo.setVersion(page.getVersion());
					pageVo.setUrl(page.getUrl());
					paList.add(pageVo);
				}
				menu.setList(paList);
				meList.add(menu);
			}
			nav.setList(meList);
			navList.add(nav);
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("list", navList);
		String testMap = JsonParse.arrayToJsonStr(map);
//		System.out.println(testMap);
		return testMap;
	}
	/**
	 * 根据权限permissionId查询权限
	 * @param permissionId
	 * @return
	 */
	public Permission queryPermissionByPerId(String permissionId){
		Permission permission = permissionManagerDao.queryPermissionByPermissionId(permissionId, "0");
		return permission;
	}
	
	
	/**
	 * 更新权限信息
	 * @param permission
	 * @throws PermissionException 
	 */
	public void updatePermission(Permission permission) throws PermissionException{
		permissionManagerDao.updatePermission(permission);
	}
	
	/**
	 * 更新权限关系
	 * @param permissionId
	 * @param menuId
	 * @throws PermissionException 
	 */
	public void updatePermissionRelation(long permissionId,long parentId,long version) throws PermissionException{
		permissionManagerDao.updatePermissionRelation(permissionId, parentId,version);
	}
	
	/**
	 * 删除权限信息
	 * @param id
	 * @throws PermissionException 
	 */
	public void deletePermission(long id) throws PermissionException{
		permissionManagerDao.deletePermission(id);
	}
	/**
	 * 删除权限关系
	 * @param permissionId
	 * @throws PermissionException 
	 */
	public void deletePermissionRelation(long permissionId,long parentId) throws PermissionException{
		permissionManagerDao.deletePermissionRelation(permissionId,parentId);
	}
	/**
	 * 新建权限信息
	 * @param permission
	 */
	public int insertPermission(Permission permission){
		int id = permissionManagerDao.insertPermission(permission);
		return id;
	}
	/**
	 * 插入权限关系
	 * @param relation
	 */
	public void insertPermissionRelation(PermissionRelation relation){
		permissionManagerDao.insertPermissionRelation(relation);
	}
	
	/**
	 * 根据权限Id查询绑定用户
	 * @param permissionId
	 * @return
	 */
	public List<UserPermissionRelation> queryUserPermissionRel(long permissionId){
		return permissionManagerDao.queryUserPermissionRel(permissionId);
	}
	
	/**
	 * 根据权限ID查询角色权限关系
	 * @param permissionId
	 * @return
	 */
	public List<RolePermission> queryRolePermissionByPerID(long permissionId){
		return permissionManagerDao.queryRolePermissionByPerID(permissionId);
	}
	public Object queryVersion(Map<String,Object> map){
		return permissionManagerDao.querVersion(map);
	}
	/**
	 * 根据子ID和父ID查询权限关系
	 * @param childId
	 * @param parentId
	 * @return
	 */
	public PermissionRelation queryPerRelByChildIdAndParentId(long childId,long parentId){
		return permissionManagerDao.queryPerRelByChildIdAndParentId(childId, parentId);
	}
	/**
	 * 根据Id查询权限
	 * @param id
	 * @return
	 */
	public Permission queryPermissionById(long id){
		return permissionManagerDao.queryPermissionById(id);
	}
	public List<Permission> queryPermission(String level,String status){
		List<Permission> list = permissionManagerDao.queryPermission(level, status);
		return list;
	}
	/**
	 * 新建权限及插入权限关系
	 * @param permission
	 */
	public void insertPermissionOrPerRel(Permission permission){
		PermissionRelation perRel = new PermissionRelation();
		perRel.setParentId(permission.getParentId());
		int id = permissionManagerDao.insertPermission(permission);
		perRel.setChildId(id);
		perRel.setStatus("0");
		permissionManagerDao.insertPermissionRelation(perRel);
	}
	/**
	 * 更新权限及权限关系
	 * @param permission
	 * @throws PermissionException
	 */
	public void updatePermissionAndPerRel(Permission permission) throws PermissionException{
		permissionManagerDao.updatePermission(permission);
		PermissionRelation pr = permissionManagerDao.queryPerRelByChildIdAndParentId(permission.getId(), permission.getParentId());
		permissionManagerDao.updatePermissionRelation(permission.getId(), permission.getParentId(),pr.getVersion());			
	}
	/**
	 * 删除权限及权限关系
	 * @param permission
	 * @throws PermissionException
	 */
	public void deletePermissionAndPerRel(Permission permission) throws PermissionException{
		permissionManagerDao.deletePermission(permission.getId());
		permissionManagerDao.deletePermissionRelation(permission.getId(), permission.getParentId());
	}
}
