package com.dzhf.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dzhf.dao.DeptDao;
import com.dzhf.dao.RoleDao;
import com.dzhf.dao.UsersDao;
import com.dzhf.model.Dept;
import com.dzhf.model.Menus;
import com.dzhf.model.Role;
import com.dzhf.util.BasicUtil;
/**
 * @author dzxy 惠晓东
 * @CommonServ.java
 * 创建时间：@2016年3月24日 @上午8:38:43
 */
@Service
public class CommonServ {
	@Resource
	private UsersDao usersDao;
	@Resource
	private RoleDao roleDao;
	@Resource
	private DeptDao deptDao;

	/**
	 * getopMenusAndLeftMenus
	 * 
	 * @param user_id
	 * @return
	 */
	public List<Menus> findTopMenus(int user_id) {
		Role role = roleDao
				.getById(usersDao.getById(user_id).getRole().getId());
		List<Menus> list = new ArrayList<Menus>(role.getMenus());
		return list;
	}

	/**
	 * 获得角色对应的管理范围
	 * 
	 * @return
	 */
	public List<Integer> getRoleDeptList() {
		Role role = roleDao.getById(usersDao
				.getById(BasicUtil.getUsers().getId()).getRole().getId());
		List<Integer> list = new ArrayList<Integer>();
		for (Dept dept : role.getDepts()) {
			if (dept.getStatus() == 1) {
				list.add(dept.getId());
			}
		}
		return list;
	}

	private List<Integer> sonList = null;

	/**
	 * 获得所有子节点
	 * 
	 * @param pid
	 * @return
	 */
	public List<Integer> getAllChildren(int pid) {
		sonList = new ArrayList<Integer>();
		sonList.add(pid);
		return getChildren(pid);
	}

	/**
	 * 同上
	 * 
	 * @param pid
	 * @return
	 */
	private List<Integer> getChildren(int pid) {
		Dept dept = deptDao.getById(pid);
		if (dept.getChildren().size() > 0) {
			for (Dept dept2 : dept.getChildren()) {
				if(dept2.getStatus()==1){
					sonList.add(dept2.getId());
					getChildren(dept2.getId());
				}
			}
		}
		return sonList;
	}

	/**
	 * 获得当前角色的组织id
	 * 
	 * 如果具有管理范围则按照管理范围
	 * 
	 * 否则 按照下属单位
	 * 
	 * @return
	 */
	public List<Integer> getAllRoleDept() {
		List<Integer> list = new ArrayList<Integer>();
		if (this.getRoleDeptList().size() > 0) {
			list = this.getRoleDeptList();
		} else {
			list = this.getAllChildren(BasicUtil.getdept().getId());
		}
		return list;
	}

	
}
