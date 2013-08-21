/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.shared.entity.authority;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import xdata.etl.web.shared.entity.IdentityRpcEntity;
import xdata.etl.web.shared.entity.menu.Menu;
import xdata.etl.web.shared.entity.user.User;
import xdata.etl.web.shared.entity.user.UserGroup;

/**
 * @author XuehuiHe
 * @date 2013年8月2日
 */
@Entity
@Table(name = "authority")
public class Authority extends IdentityRpcEntity<Integer> {
	private static final long serialVersionUID = -7252452619145327784L;
	@Column(unique = true, nullable = false, length = 32)
	@NotNull
	@Length(min = 32, max = 32)
	private String token;
	@Column(length = 20, nullable = false)
	@Length(min = 1, max = 20)
	@NotNull
	private String name;
	private Integer displayOrder;
	@ManyToOne
	@JoinColumn(name = "gid")
	private AuthorityGroup group;

	@Column(columnDefinition = "boolean")
	private Boolean isOpen = false;

	@OneToMany(mappedBy = "requireAuthority", cascade = { CascadeType.REMOVE,
			CascadeType.DETACH })
	private Set<Menu> menus;
	@ManyToMany(mappedBy = "extraAuthorities", cascade = { CascadeType.MERGE })
	private Set<User> users;
	@ManyToMany(mappedBy = "authorities", cascade = { CascadeType.REMOVE,
			CascadeType.DETACH })
	private Set<UserGroup> userGroups;

	public Authority() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

	public AuthorityGroup getGroup() {
		return group;
	}

	public void setGroup(AuthorityGroup group) {
		this.group = group;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Boolean getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(Boolean isOpen) {
		this.isOpen = isOpen;
	}

	public Set<Menu> getMenus() {
		return menus;
	}

	public void setMenus(Set<Menu> menus) {
		this.menus = menus;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Set<UserGroup> getUserGroups() {
		return userGroups;
	}

	public void setUserGroups(Set<UserGroup> userGroups) {
		this.userGroups = userGroups;
	}
}
