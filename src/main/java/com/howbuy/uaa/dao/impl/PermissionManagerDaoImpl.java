package com.howbuy.uaa.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.howbuy.rdb.database.dao.impl.BaseDaoImpl;
import com.howbuy.uaa.dao.PermissionManagerDao;
import com.howbuy.uaa.persistence.Permission;
import com.howbuy.uaa.persistence.PermissionRelation;
import com.howbuy.uaa.persistence.RolePermission;
import com.howbuy.uaa.persistence.UserPermissionRelation;
import com.howbuy.uaa.utils.PermissionException;

public class PermissionManagerDaoImpl extends BaseDaoImpl implements
		PermissionManagerDao {

	@SuppressWarnings("unchecked")
	public List<Permission> queryChildPermission(String level, long id) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("level", level);
		map.put("id", id);
		return getSqlMapClientTemplate().queryForList("PermissionManager.queryChildPermission",map);	
	}

	@SuppressWarnings("unchecked")
	public List<Permission> queryPermission(String level, String status) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("level", level);
		map.put("status", status);
		return getSqlMapClientTemplate().queryForList("PermissionManager.queryPermission",map);
	}

	@Override
	public Permission queryPermissionByPermissionId(String permissionId,
			String status) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("permissionId", permissionId);
		map.put("status", status);
		return (Permission) getSqlMapClientTemplate().queryForObject("PermissionManager.queryPermissionByPermissionId",map);
	}

	
	public void updatePermission(Permission permission) throws PermissionException {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("permissionId", permission.getPermissionId());
		map.put("descriptions", permission.getDescriptions());
		map.put("name", permission.getName());
		map.put("version", permission.getVersion());
		map.put("id", permission.getId());
		int count = getSqlMapClientTemplate().update("PermissionManager.updatePermission", map);
		if(count<=0){
			throw new PermissionException("更新失败,为查到此记录");
		}
	}

	
	public void deletePermission(long id) throws PermissionException {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", id);
		int count = getSqlMapClientTemplate().delete("PermissionManager.deletePermission", map);
		if(count<=0){
			throw new PermissionException("删除失败,为查到此记录");
		}
	}

	
	public int insertPermission(Permission permission) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("permissionId", permission.getPermissionId());
		map.put("descriptions", permission.getDescriptions());
		map.put("name", permission.getName());
		map.put("rescourcelevel", permission.getRescourcelevel());
		map.put("status", permission.getStatus());
		return (Integer)getSqlMapClientTemplate().insert("PermissionManager.insertPermission", map);
		
	}

	
	public void updatePermissionRelation(long permissionId, long parentId,long version) throws PermissionException {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("permissionId", permissionId);
		map.put("parentId", parentId);
		map.put("version", version);
		int count = getSqlMapClientTemplate().update("PermissionManager.updatePermissionRelation", map);
		if(count<=0){
			throw new PermissionException("更新失败,无此记录！");
		}
	}

	@Override
	public void deletePermissionRelation(long permissionId,long parentId) throws PermissionException {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("permissionId", permissionId);
		map.put("parentId", parentId);
		int count = getSqlMapClientTemplate().delete("PermissionManager.deletePermissionRelation", map);
		if(count<=0){
			throw new PermissionException("删除失败,无此记录！");
		}
	}

	@Override
	public void insertPermissionRelation(PermissionRelation relation) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("childId", relation.getChildId());
		map.put("parentId", relation.getParentId());
		map.put("status", relation.getStatus());
		getSqlMapClientTemplate().insert("PermissionManager.insertPermissionRelation", map);
		
	}

	
	@SuppressWarnings("unchecked")
	public List<UserPermissionRelation> queryUserPermissionRel(long permissionId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("permissionId", permissionId);	
		return getSqlMapClientTemplate().queryForList("PermissionManager.queryUserPermissionRel", map);
	}

	
	@SuppressWarnings("unchecked")
	public List<RolePermission> queryRolePermissionByPerID(long permissionId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("permissionId", permissionId);	
		return getSqlMapClientTemplate().queryForList("PermissionManager.queryRolePermissionByPerID", map);
	}

	@Override
	public Object querVersion(Map<String, Object> map) {
		return getSqlMapClientTemplate().queryForObject("GenericSql.SelectObjectVOSql", map);
	}

	@Override
	public Permission queryPermissionById(long id) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", id);	
		return (Permission)getSqlMapClientTemplate().queryForObject("PermissionManager.queryPermissionById", map);
	}
	public PermissionRelation queryPerRelByChildIdAndParentId(long childId,long parentId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("childId", childId);
		map.put("parentId", parentId);
		return (PermissionRelation)getSqlMapClientTemplate().queryForObject("PermissionManager.queryPerRelByChilIdAndParentID", map);
	}
}
