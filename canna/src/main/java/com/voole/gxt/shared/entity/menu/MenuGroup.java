package com.voole.gxt.shared.entity.menu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.voole.gxt.shared.entity.CannaEntity;

@Entity
@Table(name = "menu_group")
public class MenuGroup implements Serializable, CannaEntity {
	private static final long serialVersionUID = -4384062107738486020L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(length = 50)
	@NotNull
	@Length(max = 50)
	private String name;
	@Column(name = "orders_index")
	private int order;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "menuGroup")
	private List<Menu> menus = new ArrayList<Menu>();

	public MenuGroup() {
	}

	public MenuGroup(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		if (getId() == 0) {
			return super.hashCode();
		}
		return new Long(getId()).hashCode();
	}

	public boolean equals(Object obj) {
		MenuGroup mg = (MenuGroup) obj;
		if (obj == null) {
			return false;
		}
		if (mg.getId() == 0 && getId() == 0) {
			return mg == this;
		}
		if (mg.getId() != 0 && getId() != 0) {
			return mg.getId() == getId();
		}
		return false;
	}
}
