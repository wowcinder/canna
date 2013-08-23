/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.shared.entity.menu;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import xdata.etl.web.shared.entity.IdentityRpcEntity;

/**
 * @author XuehuiHe
 * @date 2013年8月22日
 */
@Entity
@Table(name = "menu_node")
@Inheritance(strategy = InheritanceType.JOINED)
public class MenuNode extends IdentityRpcEntity<Integer> {
	private static final long serialVersionUID = -692226783023465869L;
	@Column(length = 20, nullable = false)
	private String name;
	@ManyToOne
	@JoinColumn(name = "parent_id")
	private MenuGroup parent;
	@OneToOne
	private MenuNode prev;

	public MenuNode() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MenuGroup getParent() {
		return parent;
	}

	public void setParent(MenuGroup parent) {
		this.parent = parent;
	}

	public MenuNode getPrev() {
		return prev;
	}

	public void setPrev(MenuNode prev) {
		this.prev = prev;
	}

}
