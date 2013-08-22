/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.testweb.shared.entity.menu;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * @author XuehuiHe
 * @date 2013年8月22日
 */
@Entity
@Table(name = "menu")
@PrimaryKeyJoinColumn(name = "id")
public class Menu extends MenuNode {

	private static final long serialVersionUID = 7073528858738752886L;

	@Column(length = 20, nullable = false)
	private String token;

	public Menu() {
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
