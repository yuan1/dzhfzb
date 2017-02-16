package com.dzhf.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 属性
 * @author dzxy 惠晓东
 * @Property.java
 * 创建时间：@2016年3月24日 @上午8:13:53
 */
@Entity
public class Property {
	private int id;
	private String p_name;
	private String type;
	private String core;
	private String remark;
	

	public Property() {
	}

	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getP_name() {
		return p_name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setP_name(String p_name) {
		this.p_name = p_name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCore() {
		return core;
	}

	public void setCore(String core) {
		this.core = core;
	}

}
