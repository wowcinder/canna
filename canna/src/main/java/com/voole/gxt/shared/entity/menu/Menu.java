package com.voole.gxt.shared.entity.menu;

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
import org.hibernate.validator.constraints.NotEmpty;

import com.voole.gxt.shared.entity.CannaEntity;
import com.voole.gxt.shared.entity.authority.AuthorityRpcMethod;

@Entity
@Table(name = "menu")
public class Menu implements Serializable, CannaEntity {
	private static final long serialVersionUID = 7040196637608311304L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull
	@NotEmpty
	@Length(max = 30)
	@Column(length = 30)
	private String name;

	@Length(min = 10, max = 150)
	@Column(length = 150)
	private String token;
	@Column(name = "orders_index")
	private int order;
	@ManyToOne
	@JoinColumn(name = "gid")
	private MenuGroup menuGroup;
	@ManyToOne
	@JoinColumn(name = "mid")
	private AuthorityRpcMethod method;

	public Menu() {
	}

	public Menu(Long id, String name, String token, Integer order, Long gid,
			String gname) {
		this.id = id;
		this.name = name;
		this.token = token;
		this.order = order;
		if (gid != null) {
			setMenuGroup(new MenuGroup(gid, gname));
		}
	}

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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public MenuGroup getMenuGroup() {
		return menuGroup;
	}

	public void setMenuGroup(MenuGroup menuGroup) {
		this.menuGroup = menuGroup;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public AuthorityRpcMethod getMethod() {
		return method;
	}

	public void setMethod(AuthorityRpcMethod method) {
		this.method = method;
	}

	public Menu getSelf() {
		return this;
	}

}
