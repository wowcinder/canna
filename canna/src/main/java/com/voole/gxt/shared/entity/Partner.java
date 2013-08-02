package com.voole.gxt.shared.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

@MappedSuperclass
public class Partner implements Serializable , CannaEntity{
	private static final long serialVersionUID = -3568051039262481717L;
	private long id;
	private String name;
	private String position;
	private String mobile;
	private String telphone;
	private String email;
	private String remark;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}

	@Column(length = 20)
	@NotNull
	public String getName() {
		return name;
	}

	@Column(length = 20)
	public String getPosition() {
		return position;
	}

	@Column(length = 11)
	public String getMobile() {
		return mobile;
	}

	@Column(length = 20)
	public String getTelphone() {
		return telphone;
	}

	@Column(length = 30)
	public String getEmail() {
		return email;
	}

	@Column(length = 150)
	public String getRemark() {
		return remark;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
