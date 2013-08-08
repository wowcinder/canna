/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.shared.entity.user;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
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
@Table(name = "user_group")
public class UserGroup extends IdentityRpcEntity<Integer> {
	private static final long serialVersionUID = 6538171186264110989L;

	@Column(length = 20, nullable = false, unique = true)
	@NotNull
	@Length(min = 1, max = 20)
	private String name;

	@OneToMany(mappedBy = "userGroup", cascade = CascadeType.REMOVE)
	private List<User> users;
	@ManyToMany
	@JoinTable(name = "user_group_to_authority")
	private List<Authority> authorities;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}

}
