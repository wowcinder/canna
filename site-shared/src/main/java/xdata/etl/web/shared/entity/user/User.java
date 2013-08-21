/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.shared.entity.user;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import xdata.etl.web.shared.entity.IdentityRpcEntity;
import xdata.etl.web.shared.entity.authority.Authority;

/**
 * @author XuehuiHe
 * @date 2013年8月7日
 */
@Entity
@Table(name = "user")
public class User extends IdentityRpcEntity<Integer> {
	private static final long serialVersionUID = -1801720788002068921L;
	@Column(length = 50, nullable = false, unique = true)
	@NotNull
	@Length(min = 1, max = 50)
	private String email;
	@Column(length = 32)
	@NotNull
	@Length(min = 32, max = 32)
	private String password;

	@ManyToOne
	private UserGroup userGroup;

	@ManyToMany
	@JoinTable(name = "user_to_extra_authority")
	private List<Authority> extraAuthorities;

	public List<Authority> getExtraAuthorities() {
		return extraAuthorities;
	}

	public void setExtraAuthorities(List<Authority> extraAuthorities) {
		this.extraAuthorities = extraAuthorities;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserGroup getUserGroup() {
		return userGroup;
	}

	public void setUserGroup(UserGroup userGroup) {
		this.userGroup = userGroup;
	}

}
