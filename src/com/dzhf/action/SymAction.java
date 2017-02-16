package com.dzhf.action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.dzhf.model.Dept;
import com.dzhf.model.Property;
import com.dzhf.model.Role;
import com.dzhf.model.SystemInfo;
import com.dzhf.model.Users;
import com.dzhf.service.CommonServ;
import com.dzhf.service.SymServ;
import com.dzhf.util.BasicUtil;
import com.dzhf.util.DBbackUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
/**
 * @author dzxy 惠晓东
 * @SymAction.java
 * 创建时间：@2016年3月24日 @上午8:41:50
 */
@Controller("sym")
@Scope("prototype")
public class SymAction extends ActionSupport {
	private static final long serialVersionUID = -7253369870237262519L;
	private ServletResponse response = ServletActionContext.getResponse();
	
	// 公共引用
	@Resource
	private CommonServ commonSer;
	
	// 系统设置
	private SystemInfo sysinfo;
	public SystemInfo getSysinfo() {
		return sysinfo;
	}
	public void setSysinfo(SystemInfo sysinfo) {
		this.sysinfo = sysinfo;
	}

	// 数据库
	private String[] backfilenames;
	public String[] getBackfilenames() {
			return backfilenames;
		}
	public void setBackfilenames(String[] backfilenames) {
			this.backfilenames = backfilenames;
		}

	private String filename;
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}

	// 注入
	@Resource
	private SymServ systemManageSer;
	
	private int p; // 菜单所属
	@Resource
	private SymServ symServ;
	
	private int d; // 用户组织唯一ID
	private String o; // 用户操作
	private Dept depts;// 获取组织提交信息
	private int pid; // 要移动的父id
	// 分页用
	private int pageIndex = 0;// 当前页数
	private int user_id;

	// ========用户管理=============
	private Users users;
	private String username;
	private String id_card;
	private String old_name;
	private String old_idCard;
	private String old_pass;

	// ============角色管理=======================
	private int role_id;
	private Role role;
	@Resource
	private CommonServ commonServ;
	private String permission;

	private int pro_id;
	private Property property;

	// ================deptManage===========================
	/**
	 * 异步获得组织节点
	 * 
	 * 根据管辖范围(若是没有，则根据下属单位)
	 * 
	 * @throws IOException
	 */
	public void findDeptTree() throws IOException {
		response.setContentType("text/json; charset=utf-8");
		List<Integer> deptList = symServ.getAllDept();
		List<Dept> menusL = symServ.getDeptOne();
		Dept m = menusL.get(0);
		//定义一个List<Map<String, Object>>存放数据
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(0);
		Map<String, Object> map = symServ.convertToMap(m);
		map.put("children", getTreeDeptList(m, deptList));
		list.add(map);
		//JSON.toJSONString(list)就是要的json数据
		response.getWriter().println(JSON.toJSONString(list));
	}

	/**
	 * 递归添加子节点
	 * 
	 * @param d
	 * @param deptList
	 * @return
	 */
	private List<Map<String, Object>> getTreeDeptList(Dept d,
			List<Integer> deptList) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(0); // 菜单树
		for (Dept item : d.getChildren()) {
			if (symServ.hasIdInRoleDeptList(item, deptList)) {
				Map<String, Object> map = symServ.convertToMap(item);
				map.put("children", getTreeDeptList(item, deptList));
				list.add(map);
			}
		}
		return list;
	}

	/**
	 * deptView
	 * 
	 * @return
	 */
	public String dept() {
		ActionContext.getContext().put("deptMess", symServ.getDeptMess(d));
		ActionContext.getContext().put("depttype", symServ.getDeptType());
		return "deptManage";
	}

	/**
	 * addDept
	 * 
	 * @throws IOException
	 */
	public void addDept() throws IOException {
		if (symServ.addDept(depts)) {
			response.getWriter().print(1);
		}
	}

	/**
	 * updateDept
	 * 
	 * @throws IOException
	 */
	public void updateDept() throws IOException {
		if (symServ.updateDeptName(depts)) {
			response.getWriter().print(1);
		}
	}

	/**
	 * delDept
	 * 
	 * @throws IOException
	 */
	public void delDept() throws IOException {
		if (symServ.deleteDept(d)) {
			response.getWriter().print(1);
		}
	}

	/**
	 * moveDept
	 * 
	 * @throws IOException
	 */
	public void moveDept() throws IOException {
		if (symServ.moveDept(d, pid)) {
			response.getWriter().print(1);
		}
	}

	// =======================userManage============================================
	public String user() {
		if (o.equals("r")) {
			List<Users> allUser = null;
			int counts = (int) Math.ceil(symServ.getUserNum(d));
			if (d == 000) {
				allUser = symServ.getAllUsers(pageIndex * 10, 10);
			} else {
				allUser = symServ.getUsersByDept(pageIndex * 10, 10, d);
			}
			ActionContext.getContext().put("allPage",
					(int) Math.floor(counts / 10));
			ActionContext.getContext().put("counts", counts);
			ActionContext.getContext().put("allUser", allUser);
		} else if (o.equals("s") || o.equals("u")) {
			ActionContext.getContext().put("usersMessage",
					symServ.getOneUser(user_id));
		}
		return "userManage";
	}

	/**
	 * 修改用户
	 * 
	 * @throws IOException
	 */
	public void updateUserDo() throws IOException {
		boolean a=symServ.updateUsers(users, old_pass);
		System.out.println(a);
		if (a) {
			response.getWriter().print(1);
		}
	}

	/**
	 * 检验用户名
	 * 
	 * @throws IOException
	 */
	public void checkUserName() throws IOException {
		if ("ok".equals(symServ.checkName(username, old_name))) {
			response.getWriter().print(1);
		}
	}

	/**
	 * 检验身份证号
	 * 
	 * @throws IOException
	 */
	public void checkIdCard() throws IOException {
		if ("ok".equals(symServ.checkIdCard(id_card, old_idCard))) {
			response.getWriter().print(1);
		}
	}

	/**
	 * 删除用户
	 * 
	 * @throws IOException
	 */
	public void delUsers() throws IOException {
		if ("ok".equals(symServ.delUsers(user_id))) {
			response.getWriter().print(1);
		}
	}

	/**
	 * 添加用户
	 * 
	 * @throws IOException
	 */
	public void addUsers() throws IOException {
		if (symServ.addUsers(users)) {
			response.getWriter().print(1);
		}
	}

	// =====================角色管理==========================
	/**
	 * 获得角色树
	 * 
	 * @throws IOException
	 */
	public void getRoleTree() throws IOException {
		response.setContentType("text/json; charset=utf-8");
		List<Integer> roleList = symServ.getAllRoleList();
		List<Role> roleNull = symServ.getRoleRidNull();
		Role r = roleNull.get(0);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(0);
		Map<String, Object> map = symServ.convertToMap(r);
		map.put("children", getTreeRoleList(r, roleList));
		list.add(map);
		response.getWriter().println(JSON.toJSONString(list));
	}

	/**
	 * 用于递归获得子菜单
	 * 
	 * @param r
	 * @param roleList
	 * @return
	 */
	private List<Map<String, Object>> getTreeRoleList(Role r,
			List<Integer> roleList) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(0); // 菜单树
		for (Role item : r.getRoles()) {
			if (symServ.hasIdInRoleDeptList(item, roleList)) {
				Map<String, Object> map = symServ.convertToMap(item);
				map.put("children", getTreeRoleList(item, roleList));
				list.add(map);
			}
		}
		return list;
	}

	/**
	 * 角色管理
	 * 
	 * @return
	 */
	public String role() {
		if (o.equals("r") || o.equals("u")) {
			ActionContext.getContext().put("roleMess",
					symServ.getRoleMessage(role_id));
		} else if (o.equals("q")) {
			ActionContext.getContext().put("roleM",
					symServ.getRoleMessage(role_id));
			ActionContext.getContext().put("menusList", symServ.getMenusList());
			ActionContext.getContext().put("roleMenus",
					symServ.getRoleMenus(role_id));
		}
		return "roleManage";
	}

	/**
	 * 添加角色
	 * 
	 * @throws IOException
	 */
	public void addRoleDo() throws IOException {
		if ("ok".equals(symServ.addRoleDo(role))) {
			response.getWriter().print(1);
		}
	}

	/**
	 * 修改角色的基本信息
	 * 
	 * @throws IOException
	 */
	public void updateRole() throws IOException {
		if (symServ.updateRole(role)) {
			response.getWriter().print(1);
		}
	}

	/**
	 * 删除
	 * 
	 * @throws IOException
	 */
	public void delRoleDo() throws IOException {
		if (symServ.delRoleDo(role_id)) {
			response.getWriter().print(1);
		}
	}

	/**
	 * 获得角色对应的管理范围
	 * 
	 * @throws IOException
	 */
	public void findDeptTreeForScope() throws IOException {
		response.setContentType("text/json; charset=utf-8");
		List<Integer> deptList = symServ.getAllDept();
		List<Dept> menusL = symServ.getDeptOne();
		Dept m = menusL.get(0);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(0);
		Map<String, Object> map = symServ.convertToMapForScope(m, role_id);
		map.put("children", getTreeDeptListForScope(m, deptList));
		list.add(map);
		response.getWriter().println(JSON.toJSONString(list));
	}

	/**
	 * 用于管理范围的设置
	 * 
	 * @param d
	 * @param deptList
	 * @return
	 */
	private List<Map<String, Object>> getTreeDeptListForScope(Dept d,
			List<Integer> deptList) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(0); // 菜单树
		for (Dept item : d.getChildren()) {
			if (symServ.hasIdInRoleDeptList(item, deptList)) {
				Map<String, Object> map = symServ.convertToMapForScope(item,
						role_id);
				map.put("children", getTreeDeptListForScope(item, deptList));
				list.add(map);
			}
		}
		return list;
	}

	/**
	 * 权限编辑
	 * 
	 * @throws IOException
	 */
	public void roleEditDo() throws IOException {
		String manageScope = ServletActionContext.getRequest().getParameter(
				"manageScope");
		if ("ok".equals(symServ.editRoleMenu(role_id, permission, manageScope))) {
			response.getWriter().print(1);
			ActionContext
					.getContext()
					.getSession()
					.put("topMenusList",
							commonServ.findTopMenus(BasicUtil.getUsers()
									.getId()));
		} else {
			response.getWriter().print(0);
		}
	}

	// =====================属性设置============================================
	public String proList() {
		ActionContext.getContext().put("proList", symServ.getPro());
		return "proList";
	}

	public String uPro() {
		ActionContext.getContext().put("pro", symServ.getPro(pro_id));
		return "uPro";
	}

	public void DuPro() throws IOException {
		if (symServ.uPro(property)) {
			response.getWriter().print(1);
		}
	}

	public void delPro() throws IOException {
		if (symServ.delPro(pro_id)) {
			response.getWriter().print(1);
		}
	}

	public String addPro() {
		return "addPro";
	}

	public void aProDo() throws IOException {
		if (symServ.addPro(property)) {
			response.getWriter().print(1);
		}
	}

	public int getP() {
		return p;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	public void setP(int p) {
		this.p = p;
	}

	public String getPermission() {
		return permission;
	}

	public int getPro_id() {
		return pro_id;
	}

	public void setPro_id(int pro_id) {
		this.pro_id = pro_id;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public int getD() {
		return d;
	}

	public void setD(int d) {
		this.d = d;
	}

	public String getOld_pass() {
		return old_pass;
	}

	public void setOld_pass(String old_pass) {
		this.old_pass = old_pass;
	}

	public String getO() {
		return o;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public String getOld_idCard() {
		return old_idCard;
	}

	public void setOld_idCard(String old_idCard) {
		this.old_idCard = old_idCard;
	}

	public void setO(String o) {
		this.o = o;
	}

	public Dept getDepts() {
		return depts;
	}

	public void setDepts(Dept depts) {
		this.depts = depts;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

	public String getId_card() {
		return id_card;
	}

	public void setId_card(String id_card) {
		this.id_card = id_card;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getOld_name() {
		return old_name;
	}

	public void setOld_name(String old_name) {
		this.old_name = old_name;
	}

	// 页面公共传值
	public void pubSetValue() {
		ActionContext.getContext().put("topMenuList",
				commonSer.findTopMenus(BasicUtil.getUserId()));
		// ActionContext.getContext().put("parent", p);
	}


	// =======================个人信息===================
	public String personalMessage() {
		pubSetValue();
		ActionContext.getContext().put("userSee3",
				systemManageSer.getOneUser(BasicUtil.getUserId()));
		return "personalMessage";
	}

	// =============系统设置========================
	// 环境状态
	public String environmentStatus() {
		pubSetValue();
		sysinfo = systemManageSer.getSysInfo();
		return "environmentStatus";
	}

	// ======================数据库管理====================
	// 备份
	public void backDB() throws IOException {
		response.setContentType("text/json; charset=utf-8");
		String str=DBbackUtil.backup(ServletActionContext
				.getServletContext().getRealPath("/") + "resources/backup/");
		if ("备份成功!".equals(str)){
			response.getWriter().print(1);
		} else {
			response.getWriter().print(0);
		}
	}

	// 备份列表
	public String backfilelist() {
		pubSetValue();
		File file = new File(ServletActionContext.getServletContext()
				.getRealPath("/") + "resources/backup/");
		backfilenames = file.list();
		return "backfilelist";
	}

	// 还原
	public void restoreDB() throws IOException {
		response.setContentType("text/json; charset=utf-8");
		if ("还原数据库成功!".equals(DBbackUtil.load(ServletActionContext
				.getServletContext().getRealPath("/")
				+ "resources/backup/"
				+ filename + ".sql"))) {
			response.getWriter().print(1);
		} else {
			response.getWriter().print(0);
		}
	}

	// 删除
	public void deleteBackDb() throws IOException {
		response.setContentType("text/json; charset=utf-8");
		if ("删除成功!".equals(DBbackUtil.deletefile(ServletActionContext
				.getServletContext().getRealPath("/")
				+ "resources/backup/"
				+ filename + ".sql"))) {
			response.getWriter().print(1);
		} else {
			response.getWriter().print(0);
		}
	}
}
