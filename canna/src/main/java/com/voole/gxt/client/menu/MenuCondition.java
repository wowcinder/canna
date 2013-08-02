package com.voole.gxt.client.menu;

import com.voole.gxt.client.canna.gridcontainer.paging.PagingCondition;

public class MenuCondition implements PagingCondition {
	private static final long serialVersionUID = -5898198903443563955L;
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
