package com.dzhf.dao;

import java.util.List;

import com.dzhf.commonDao.BaseDao;
import com.dzhf.model.Role;

/**
 * 
 * @author dzxy ������
 * @RoleDao.java
 * ����ʱ�䣺@2016��3��24�� @����8:31:03
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
