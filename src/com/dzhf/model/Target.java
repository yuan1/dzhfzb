package com.dzhf.model;


import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

/**
 * 指标列表
 * @author dzxy 惠晓东
 * @Target.java
 * 创建时间：@2016年3月24日 @下午2:48:21
 */

@Entity
public class Target {

	private int id;
	private String tar_name;   //指标名称
	private String tar_prompt; //指标提示
	private String value;//指标内容
	private String remark;
	private String indexvalue;  //默认值
	private int isgongshi;
	private String sum;
	private int sort;
	private String parentid;
	private Set<DataInfo> datainfo;
	private Set<TargetHistory> targetHistory;

	public Target() {
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
	public String getTar_name() {
		return tar_name;
	}
	public void setTar_name(String tar_name) {
		this.tar_name = tar_name;
	}
	public String getTar_prompt() {
		return tar_prompt;
	}
	public void setTar_prompt(String tar_prompt) {
		this.tar_prompt = tar_prompt;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getIndexvalue() {
		return indexvalue;
	}
	public void setIndexvalue(String indexvalue) {
		this.indexvalue = indexvalue;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public int getIsgongshi() {
		return isgongshi;
	}
	public void setIsgongshi(int isgongshi) {
		this.isgongshi = isgongshi;
	}
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "tar_id")
	@OrderBy("id")
	public Set<TargetHistory> getTargetHistory() {
		return targetHistory;
	}
	public void setTargetHistory(Set<TargetHistory> targetHistory) {
		this.targetHistory = targetHistory;
	}
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "tar_id")
	@OrderBy("id")
	public Set<DataInfo> getDatainfo() {
		return datainfo;
	}
	public void setDatainfo(Set<DataInfo> datainfo) {
		this.datainfo = datainfo;
	}
	public String getSum() {
		return sum;
	}
	public void setSum(String sum) {
		this.sum = sum;
	}
	public int compareTo(Target a) {
		return a.getId() - this.getId();
	}
}
