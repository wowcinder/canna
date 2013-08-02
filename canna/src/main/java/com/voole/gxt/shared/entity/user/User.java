package com.voole.gxt.shared.entity.user;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.voole.gxt.client.canna.validator.EmailWithTld;
import com.voole.gxt.shared.entity.CannaEntity;

@Entity
@Table(name = "user")
public class User implements CannaEntity, Serializable {
	private static final long serialVersionUID = 3738785241537057340L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@EmailWithTld
	@Column(length = 40)
	@NotNull
	@Length(min = 1, max = 40)
	private String email;
	@Column(length = 32)
	@NotNull
	private String password;
	@Column(length = 32)
	private String name;
	@Column(length = 11)
	@NotNull
	@Length(min = 11, max = 11)
	private String mobile;
	@Column(length = 20)
	private String extNum;
	@ManyToOne
	@JoinColumn(name = "gid", nullable = false)
	@NotNull
	private UserGroup group;

	public long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public String getMobile() {
		return mobile;
	}

	public String getExtNum() {
		return extNum;
	}

	public UserGroup getGroup() {
		return group;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setExtNum(String extNum) {
		this.extNum = extNum;
	}

	public void setGroup(UserGroup group) {
		this.group = group;
	}

}
