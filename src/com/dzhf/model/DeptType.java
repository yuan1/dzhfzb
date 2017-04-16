package com.dzhf.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

/**
 * 注册公司类型
 * @author dzxy 惠晓东
 * @DeptType.java
 * 创建时间：@2016年3月24日 @下午2:34:18
 */
@Entity
public class DeptType {

	private int id;
	private String type;
	private int isusing;
	private Set<TargetHistory> targetHistory;
	
	public DeptType() {
		super();
	}
	@Id
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getIsusing() {
		return isusing;
	}
	public void setIsusing(int isusing) {
		this.isusing = isusing;
	}
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "depttype_id")
	@OrderBy("id")
	public Set<TargetHistory> getTargetHistory() {
		return targetHistory;
	}
	public void setTargetHistory(Set<TargetHistory> targetHistory) {
		this.targetHistory = targetHistory;
	}
}
