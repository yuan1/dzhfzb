package com.dzhf.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class TargetHistory {

	private int id;
	private DeptType depttype;
	private Target target;
	private String month;
	
	public TargetHistory() {
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
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tar_id")
	public Target getTarget() {
		return target;
	}
	public void setTarget(Target target) {
		this.target = target;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "depttype_id")
	public DeptType getDepttype() {
		return depttype;
	}
	public void setDepttype(DeptType depttype) {
		this.depttype = depttype;
	}
}
