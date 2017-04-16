package com.dzhf.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

/**
 * 菜单
 * @author dzxy 惠晓东
 * @Menus.java
 * 创建时间：@2016年3月24日 @上午8:13:14
 */
@Entity
public class Menus {
	private int id;
	private String menu_name;
	private String url;
	private int status; // 1：菜单2：按钮
	private int sort; // 菜单的显示顺序
	private Menus menus;
	private Set<Menus> children;

	public Menus() {
	}

	@Id
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMenu_name() {
		return menu_name;
	}

	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "parentId")
	public Menus getMenus() {
		return menus;
	}

	public void setMenus(Menus menus) {
		this.menus = menus;
	}

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@JoinColumn(name = "parentId")
	@OrderBy("sort")
	public Set<Menus> getChildren() {
		return children;
	}

	public void setChildren(Set<Menus> children) {
		this.children = children;
	}

}
