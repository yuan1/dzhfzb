package com.dzhf.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * �����͹�˾���ݴ洢
 * @author dzxy ������
 * @DataInfo.java
 * ����ʱ�䣺@2016��3��26�� @����12:40:42
 */

@Entity
public class DataInfo {

	private int id;
	private Dept dept;
	private Target targets;
	private String month;
	private String value;
	private String addtime;
	
	public DataInfo() {
		super();
	}
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getAddtime() {
		return addtime;
	}
	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "dept_id")
	public Dept getDept() {
		return dept;
	}
	public void setDept(Dept dept) {
		this.dept = dept;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tar_id")
	public Target getTargets() {
		return targets;
	}
	public void setTargets(Target targets) {
		this.targets = targets;
	}
}
