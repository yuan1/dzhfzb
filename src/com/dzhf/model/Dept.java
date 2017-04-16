package com.dzhf.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

/**
 * ��֯����
 * @author dzxy ������
 * @Dept.java
 * ����ʱ�䣺@2016��3��24�� @����8:12:53
 */
@Entity
public class Dept {
	private int id;
	private String dept_name;
	private int status; // 0:�쳣 1������
	private int sort; // ����
	private Dept dept;
	private String duty_person; // ������
	private String d_phone; // ���ε绰
	private String remark; // ��ע
	private String d_address; // ���εص�
	private DeptType dept_type;
	private Set<Dept> children;
	private Set<Users> users;
	private Set<ReviewInfo> reviewinfo;
	
	private Set<DataInfo> datainfos;

	public Dept() {
	}

	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public String getDuty_person() {
		return duty_person;
	}

	public String getD_address() {
		return d_address;
	}

	public void setD_address(String d_address) {
		this.d_address = d_address;
	}

	public void setDuty_person(String duty_person) {
		this.duty_person = duty_person;
	}

	public String getD_phone() {
		return d_phone;
	}

	public void setD_phone(String d_phone) {
		this.d_phone = d_phone;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
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
	@JoinColumn(name = "pid")
	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@JoinColumn(name = "pid")
	@OrderBy("sort")
	public Set<Dept> getChildren() {
		return children;
	}

	public void setChildren(Set<Dept> children) {
		this.children = children;
	}

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "dept_id")
	@OrderBy("id")
	public Set<Users> getUsers() {
		return users;
	}

	public void setUsers(Set<Users> users) {
		this.users = users;
	}

	public int compareTo(Dept a) {
		return a.getId() - this.getId();
	}

	@ManyToOne(fetch=FetchType.LAZY,cascade = {CascadeType.PERSIST, CascadeType.MERGE}, targetEntity=DeptType.class )
    @JoinColumn(name="deptType_id")
	public DeptType getDept_type() {
		return dept_type;
	}
	public void setDept_type(DeptType dept_type) {
		this.dept_type = dept_type;
	}

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "dept_id")
	@OrderBy("id")
	public Set<DataInfo> getDatainfos() {
		return datainfos;
	}

	public void setDatainfos(Set<DataInfo> datainfos) {
		this.datainfos = datainfos;
	}

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "dept_id")
	@OrderBy("id")
	public Set<ReviewInfo> getReviewinfo() {
		return reviewinfo;
	}
	public void setReviewinfo(Set<ReviewInfo> reviewinfo) {
		this.reviewinfo = reviewinfo;
	}
}
