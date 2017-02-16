package com.dzhf.service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;

import com.dzhf.dao.DeptDao;
import com.dzhf.dao.DeptTypeDao;
import com.dzhf.dao.MenusDao;
import com.dzhf.dao.PropertyDao;
import com.dzhf.dao.RoleDao;
import com.dzhf.dao.UsersDao;
import com.dzhf.model.Dept;
import com.dzhf.model.DeptType;
import com.dzhf.model.Menus;
import com.dzhf.model.Property;
import com.dzhf.model.Role;
import com.dzhf.model.SystemInfo;
import com.dzhf.model.Users;
import com.dzhf.util.BasicUtil;

/**
 * @author dzxy 惠晓东
 * @SymServ.java
 * 创建时间：@2016年3月24日 @上午8:38:08
 */
@Service
public class SymServ {
	@Resource
	private CommonServ commonServ;
	@Resource
	private DeptDao deptDao;
	@Resource
	private UsersDao usersDao;
	@Resource
	private RoleDao roleDao;
	@Resource
	private MenusDao menusDao;
	@Resource
	private PropertyDao propertyDao;
	@Resource
	private DeptTypeDao depttypeDao;

	/**
	 * 获得当前角色管辖范围的组织中所有节点
	 * 
	 * @return
	 */
	public List<Integer> getAllDept() {
		List<Integer> list = new ArrayList<Integer>();
		if (commonServ.getRoleDeptList().size() > 0) {
			list = commonServ.getRoleDeptList();
		} else {
			list = commonServ.getAllChildren(BasicUtil.getdept().getId());
		}
		return list;
	}

	// 获得菜单中第一个节点
	public List<Dept> getDeptOne() {
		List<Dept> list = deptDao.getNullDepts();
//		List<Dept> list = deptDao.getNullDepts(BasicUtil.getDeptId());
		return list;
	}

	// 为map设置
	public Map<String, Object> convertToMap(Dept dept) {
		Map<String, Object> map = new HashMap<String, Object>(0);
		map.put("id", dept.getId());
		map.put("text", dept.getDept_name());
		return map;
	}

	// 判断
	public boolean hasIdInRoleDeptList(Dept dept, List<Integer> roleDeptList) {
		if (-1 != roleDeptList.indexOf(dept.getId()))
			return true;
		for (Dept item : dept.getChildren()) {
			if (hasIdInRoleDeptList(item, roleDeptList))
				return true;
		}
		return false;
	}

	/**
	 * 获得机构信息
	 * 
	 * @param dept_id
	 * @return
	 */
	public Dept getDeptMess(int dept_id) {
		Dept dept = deptDao.getById(dept_id);
		return dept;
	}

	/**
	 * addDept
	 * 
	 * @param depts
	 * @return
	 */
	public boolean addDept(Dept depts) {
		boolean flag = false;
		if (this.getAllDept().contains(depts.getDept().getId())) {
			deptDao.save(depts);
			if (commonServ.getRoleDeptList().size() > 0) {
				deptDao.saveRoleDept(BasicUtil.getUsers().getRole().getId(),
						depts.getId());
			}
			flag = true;
		}
		return flag;
	}

	/**
	 * 获取全部公司类型
	 * @return
	 */
	public List<DeptType> getDeptType(){
		return depttypeDao.findAll(new String[]{"isusing"}," id asc ","1");
	}
	
	/**
	 * 根据公司类型获得公司实体列表
	 * @param typeid
	 * @return
	 */
	public List<Dept> getDeptByType(String typeid){
		return deptDao.findAll(new String[]{"deptType_id"}," id asc ",typeid);
	}
	
	/**
	 * 获取全部部门
	 * @param typeid
	 * @return
	 */
	public List<Dept> getDept(){
		return deptDao.findAll();
	}
	
	/**
	 * 根据编号获取公司类型实体
	 * @param depttypeid
	 * @return
	 */
	public DeptType getDeptType(String type){
		return depttypeDao.getById1(Integer.parseInt(type));
	}
	
	/**
	 * updateDept
	 * 
	 * @param depts
	 * @return
	 */
	public boolean updateDeptName(Dept depts) {
		boolean flag = false;
		if (this.getAllDept().contains(depts.getId())) {
			deptDao.updateDept(depts);
			flag = true;
		}
		return flag;
	}

	/**
	 * delDept
	 * 
	 * @param d
	 * @return
	 */
	public boolean deleteDept(int dept_id) {
		boolean flag = false;
		if (this.getAllDept().contains(dept_id)) {
			deptDao.deleteDept(dept_id);
			Dept dept = deptDao.getById(dept_id);
			if (dept.getChildren().size() > 0) {
				for (Dept dept2 : dept.getChildren()) {
					deptDao.deleteDept(dept2.getId());
					deleteDept(dept2.getId());// 循环撤销子节点
				}
			}
			if (dept.getUsers().size() > 0) {
				for (Users users : dept.getUsers()) {
					usersDao.delUser(users.getId());
				}
			}
			flag = true;
		}
		return flag;
	}

	/**
	 * moveDept
	 * 
	 * @param d
	 * @param pid
	 * @return
	 */
	public boolean moveDept(int d, int pid) {
		deptDao.moveDept(d, pid);
		return true;
	}

	/**
	 * 获得当前用户管理范围的用户数
	 * 
	 * @param dept_id
	 * @return
	 */
	public float getUserNum(int dept_id) {
		float num = 0;
		if (dept_id == 000) {
			num = usersDao.getCount(new String[] { "dept.id" },
					String.valueOf(BasicUtil.getdept().getId()));
		} else {
			List<Integer> l = this.getAllDept();
			if (l.contains(dept_id)) {
				num = usersDao.getCount(new String[] { "dept_id" },
						String.valueOf(dept_id));
			}
		}
		return num;
	}

	/**
	 * 获得所有的用户
	 * 
	 * @param pageIndex
	 * @param count
	 * @return
	 */
	public List<Users> getAllUsers(int pageIndex, int count) {
		return usersDao.getByParmeterOnPage(pageIndex, count,
				new String[] { "dept.id" }, "id asc",
				String.valueOf(BasicUtil.getdept().getId()));
	}
	
	public List<Users> getAllUsers() {
		return usersDao.findAll();
	}

	/**
	 * 根据dept_id获得用户
	 * 
	 * @param pageIndex
	 * @param count
	 * @param dept_id
	 * @return
	 */
	public List<Users> getUsersByDept(int pageIndex, int count, int dept_id) {
		List<Integer> l = this.getAllDept();
		List<Users> list = null;
		if (l.contains(dept_id)) {
			list = usersDao.getByParmeterOnPage(pageIndex, count,
					new String[] { "dept.id" }, "username asc",
					String.valueOf(dept_id));
		}
		return list;
	}

	/**
	 * 查看用户
	 * 
	 * @param user_id
	 * @return
	 */
	public Users getOneUser(int user_id) {
		Users users = usersDao.getById(user_id);
		return users;
	}

	/**
	 * 获得角色列表ID
	 * 
	 * @return
	 */
	public List<Integer> getAllRoleList() {
		List<Integer> list = new ArrayList<Integer>();
		for (Role role : roleDao.findAll()) {
			list.add(role.getId());
		}
		return list;
	}

	/**
	 * 获得父id为空的角色
	 * 
	 * @return
	 */
	public List<Role> getRoleRidNull() {
		List<Role> list = roleDao.getNullRole();
		return list;
	}

	// 为map设置
	public Map<String, Object> convertToMap(Role r) {
		Map<String, Object> map = new HashMap<String, Object>(0);
		map.put("id", r.getId());
		map.put("text", r.getName());
		return map;
	}

	@SuppressWarnings("unused")
	public boolean hasIdInRoleDeptList(Role item, List<Integer> roleList) {
		if (-1 != roleList.indexOf(item.getId()))
			return true;
		for (Role r : item.getRoles()) {
			if (hasIdInRoleDeptList(item, roleList))
				return true;
		}
		return false;
	}

	/**
	 * 修改用户
	 * @param users
	 * @return
	 */
	public boolean updateUsers(Users users, String old_pass) {
		if ("".equals(users.getPassword())) {
			users.setPassword(old_pass);
		} else {
			users.setPassword(BasicUtil.md5(users.getPassword()));
		}
		usersDao.update1(users);
		return true;
	}

	/**
	 * 检验用户名
	 * 
	 * @param username
	 * @return
	 */
	public String checkName(String username, String oldName) {
		String s = "";
		if (username.equals(oldName)) {
			s = "ok";
		} else {
			List<Users> list = usersDao.getByParmeter(
					new String[] { "username" }, "id", username);
			if (list.size() < 1) {
				s = "ok";
			}
		}
		return s;
	}

	/**
	 * 检验身份证号
	 * 
	 * @param id_card
	 * @return
	 */
	public String checkIdCard(String id_card, String oidIDCard) {
		String s = "";
		if (id_card.equals(oidIDCard)) {
			s = "ok";
		} else {
			List<Users> list = usersDao.getByParmeter(
					new String[] { "id_card" }, "id", id_card);
			if (list.size() < 1) {
				s = "ok";
			}
		}
		return s;
	}

	/**
	 * 删除用户
	 * 
	 * @param user_id
	 * @return
	 */
	public String delUsers(int user_id) {
		usersDao.delete(user_id);
		return "ok";
	}

	/**
	 * 添加用户
	 * 
	 * @param users
	 * @return
	 */
	public boolean addUsers(Users users) {
		users.setCreat_time(BasicUtil.getCurrentTime());
		users.setPassword(BasicUtil.md5(users.getPassword()));
		usersDao.save(users);
		return true;
	}

	// =================角色管理==================================
	/**
	 * 根据role_id获得角色的基本信息
	 * 
	 * @param role_id
	 * @return
	 */
	public Role getRoleMessage(int role_id) {
		return roleDao.getById(role_id);
	}

	/**
	 * 添加角色
	 * 
	 * @param role
	 * @return
	 */
	public String addRoleDo(Role role) {
		role.setCreate_time(BasicUtil.getCurrentTime());
		roleDao.save(role);
		List<Integer> list = getRoleMenus(role);
		for (int i = 0; i < list.size(); i++) {
			roleDao.insertRoleMenus(role.getId(), list.get(i));
		}
		return "ok";
	}

	/**
	 * 获得父角色的权限（菜单id）
	 * 
	 * @param role
	 * @return
	 */
	private List<Integer> getRoleMenus(Role role) {
		List<?> list = roleDao.getRoleMenus(role.getRole().getId());
		List<Integer> integers = new ArrayList<Integer>();
		for (int i = 0; i < list.size(); i++) {
			integers.add((Integer) list.get(i));
		}
		return integers;
	}

	/**
	 * updateRole
	 * 
	 * @param role
	 * @return
	 */
	public boolean updateRole(Role role) {
		roleDao.updateRoleMess(role);
		return true;
	}

	/**
	 * 删除
	 * 
	 * @param role_id
	 * @return
	 */
	public boolean delRoleDo(int role_id) {
		roleDao.delete(role_id);
		return true;
	}

	// 获得当前角色所拥有权限
	public String getRoleMenus(int role_id) {
		String roleMenus = "";
		Role role = getRoleMessage(role_id);
		if (role != null) {
			if (role.getMenus().size() > 0) {
				for (Menus menus : role.getMenus()) {
					roleMenus += menus.getId() + ",";
				}
			}
		}
		if (!roleMenus.equals("")) {
			roleMenus = roleMenus.substring(0, roleMenus.length() - 1);
		}
		// System.out.println(roleMenus);
		return roleMenus;
	}

	// 获得所有菜单
	public List<Menus> getMenusList() {
		return menusDao.findAll(new String[]{" parentId asc "," sort ASC "});
	}

	/**
	 * 用于管理范围的设置
	 * @param dept
	 * @return
	 */
	public Map<String, Object> convertToMapForScope(Dept dept, int role_id) {
		Map<String, Object> map = new HashMap<String, Object>(0);
		map.put("id", dept.getId());
		map.put("text", dept.getDept_name());
		if (roleDao.getRoleDeptList(role_id).contains(dept.getId())) {
			map.put("checked", true);
		}
		return map;
	}

	// 权限编辑do
	public String editRoleMenu(int role_id, String permission,
			String manageScope) {
		if (permission.equals("")) {
			roleDao.deleteRole(role_id);
		} else {
			roleDao.deleteRole(role_id);
			String[] rolesMess = permission.split(",");
			for (String string : rolesMess) {
				roleDao.editRoleMenu(role_id, Integer.parseInt(string));
			}
		}
		if (manageScope.equals("")) {
			roleDao.deleteRoleDept(role_id);
		} else {
			roleDao.deleteRoleDept(role_id);
			String[] deptsMess = manageScope.split(",");
			for (String string : deptsMess) {
				roleDao.editRoleDept(role_id, Integer.parseInt(string));
			}
		}
		return "ok";
	}

	// =====================属性管理=======================
	public List<Property> getPro() {
		return propertyDao.findAll();
	}

	public Property getPro(int pro_id) {
		return propertyDao.getById(pro_id);
	}

	public boolean uPro(Property property) {
		propertyDao.update(property);
		return true;
	}

	public boolean delPro(int pro_id) {
		propertyDao.delete(pro_id);
		return true;
	}

	public boolean addPro(Property property) {
		propertyDao.save(property);
		return true;
	}
	
	// =================系统设置================================
		public SystemInfo getSysInfo() {
			SystemInfo si = new SystemInfo();
			Properties properties = System.getProperties();
			si.setOsname(properties.getProperty("os.name") + "  版本"
					+ properties.getProperty("os.version"));
			si.setHostIP(ServletActionContext.getRequest().getRemoteAddr());
			si.setAppDir(ServletActionContext.getServletContext().getRealPath("/"));
			try {
				si.setSerIP(InetAddress.getLocalHost().getHostAddress());
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
			return si;
		}
}
