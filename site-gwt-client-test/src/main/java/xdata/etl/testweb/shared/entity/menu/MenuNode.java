/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.testweb.shared.entity.menu;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author XuehuiHe
 * @date 2013年8月22日
 */
@Entity
@Table(name = "menu_node")
@Inheritance(strategy = InheritanceType.JOINED)
public class MenuNode implements Serializable {
	private static final long serialVersionUID = -692226783023465869L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(length = 20, nullable = false)
	private String name;
	@ManyToOne
	private MenuGroup parent;
	@OneToOne
	private MenuNode prev;

	public MenuNode() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
