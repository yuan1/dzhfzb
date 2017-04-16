package com.dzhf.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

/**
 * 角色
 * @author dzxy 惠晓东
 * @Role.java
 * 创建时间：@2016年3月24日 @上午8:14:07
 */
@Entity
public class Role {
	private int id;
	private String name;
	private String create_time;
	private String remark;
	private int sort;
	private Set<Users> users; // 角色和用户一对多关联
	private Set<Dept> depts; // 角色和部门多多关联
	private Set<Menus> menus; // 角色和菜单多对多关联
	private Role role; // 继承关联
	private Set<Role> roles;

	public Role() {
	}

	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getRemark() {
		return remark;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "role_menu", joinColumns = { @JoinColumn(name = "role_id") }, inverseJoinColumns = { @JoinColumn(name = "menu_id") })
	@OrderBy("sort")
	public Set<Menus> getMenus() {
		return menus;
	}

	public void setMenus(Set<Menus> menus) {
		this.menus = menus;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id")
	@OrderBy("id")
	public Set<Users> getUsers() {
		return users;
	}

	public void setUsers(Set<Users> users) {
		this.users = users;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "role_dept", joinColumns = { @JoinColumn(name = "role_id") }, inverseJoinColumns = { @JoinColumn(name = "dept_id") })
	@OrderBy("id")
	public Set<Dept> getDepts() {
		return depts;
	}

	public void setDepts(Set<Dept> depts) {
		this.depts = depts;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rid")
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@JoinColumn(name = "rid")
	@OrderBy("sort")
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

}
