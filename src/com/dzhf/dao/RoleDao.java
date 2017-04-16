package com.dzhf.dao;

import java.util.List;

import com.dzhf.commonDao.BaseDao;
import com.dzhf.model.Role;

/**
 * 
 * @author dzxy 惠晓东
 * @RoleDao.java
 * 创建时间：@2016年3月24日 @上午8:31:03
 */
public interface RoleDao extends BaseDao<Role>{

	List<Role> getNullRole();

	void insertRoleMenus(int id, Integer integer);

	List<?> getRoleMenus(int id);

	void updateRoleMess(Role role);

	List<Integer> getRoleDeptList(int role_id);

	void deleteRole(int role_id);

	void editRoleMenu(int role_id, int parseInt);

	void deleteRoleDept(int role_id);

	void editRoleDept(int role_id, int parseInt);

}
