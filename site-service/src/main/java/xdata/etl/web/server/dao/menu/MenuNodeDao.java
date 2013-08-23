/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.dao.menu;

import java.util.List;

import xdata.etl.web.server.dao.IRpcDao;
import xdata.etl.web.shared.annotations.MenuToken;
import xdata.etl.web.shared.entity.menu.MenuNode;

/**
 * @author XuehuiHe
 * @date 2013年8月8日
 */
public interface MenuNodeDao extends IRpcDao<Integer, MenuNode> {
	void insertMenuConfig(List<MenuToken> tokens);

	public List<MenuNode> getUserMenus(Integer uid);

	/**
	 * @param parentId
	 * @param prevId
	 * @param nodes
	 * @return
	 */
	List<MenuNode> update(Integer parentId, Integer prevId, List<MenuNode> nodes);

}
