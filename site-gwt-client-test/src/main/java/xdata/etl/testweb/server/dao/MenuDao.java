/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.testweb.server.dao;

import java.util.List;

import xdata.etl.testweb.shared.entity.menu.MenuNode;

/**
 * @author XuehuiHe
 * @date 2013年8月22日
 */
public interface MenuDao {
	public MenuNode insert(MenuNode node);
	
	public List<MenuNode> get();
}
