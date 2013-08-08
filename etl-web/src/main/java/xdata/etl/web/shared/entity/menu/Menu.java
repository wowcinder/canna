/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.shared.entity.menu;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import xdata.etl.web.shared.entity.IdentityRpcEntity;
import xdata.etl.web.shared.entity.authority.Authority;

/**
 * @author XuehuiHe
 * @date 2013年8月8日
 */
@Entity
@Table(name = "menu")
public class Menu extends IdentityRpcEntity<Integer> {
	private static final long serialVersionUID = -7971390347504825311L;
	@Column(length = 20, nullable = false)
	@NotNull
	@Length(min = 1, max = 20)
	private String name;
	@Column(length = 20, nullable = false)
	@NotNull
	@Length(min = 1, max = 20)
	private String token;
	@ManyToOne
	private MenuGroup menuGroup;
	@ManyToOne
	private Authority requireAuthority;
	private Integer pos;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public MenuGroup getMenuGroup() {
		return menuGroup;
	}

	public void setMenuGroup(MenuGroup menuGroup) {
		this.menuGroup = menuGroup;
	}

	public Authority getRequireAuthority() {
		return requireAuthority;
	}

	public void setRequireAuthority(Authority requireAuthority) {
		this.requireAuthority = requireAuthority;
	}

	public Integer getPos() {
		return pos;
	}

	public void setPos(Integer pos) {
		this.pos = pos;
	}

}
