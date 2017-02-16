package com.dzhf.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * �û���
 * @author dzxy ������
 * @Users.java
 * ����ʱ�䣺@2016��3��24�� @����8:14:32
 */
@Entity
public class Users {
	private Integer id;
	private String username; // �û���
	private String realname; // ����
	private String sex; // �Ա�
	private String birthday; // ��������
	private String id_card;// ���֤��
	private String position;// ְ��
	private String address; // ��ַ
	private String phone; // �ֻ�
	private String remark; // ��ע
	private String password; // ����
	private String creat_time;
	private int del; // 0: ��ֹ 1:����
	private Dept dept; // �뵥λ���һӳ��
	private Role role; // ...

	public Users() {
	}

	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dept_id")
	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id")
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getId_card() {
		return id_card;
	}

	public void setId_card(String id_card) {
		this.id_card = id_card;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCreat_time() {
		return creat_time;
	}

	public void setCreat_time(String creat_time) {
		this.creat_time = creat_time;
	}

	public int getDel() {
		return del;
	}

	public void setDel(int del) {
		this.del = del;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", username=" + username + ", realname="
				+ realname + ", sex=" + sex + ", birthday=" + birthday
				+ ", id_card=" + id_card + ", position=" + position
				+ ", address=" + address + ", phone=" + phone + ", remark="
				+ remark + ", password=" + password + ", creat_time="
				+ creat_time + ", del=" + del + ", dept=" + dept + ", role="
				+ role + "]";
	}
}
