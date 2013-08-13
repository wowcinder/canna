/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.dao.menu;

import java.util.List;

import xdata.etl.web.server.dao.IRpcDao;
import xdata.etl.web.shared.annotations.MenuToken;
import xdata.etl.web.shared.entity.menu.Menu;

/**
 * @author XuehuiHe
 * @date 2013年8月8日
 */
public interface MenuDao extends IRpcDao<Integer, Menu> {
	void initMenuConfig(List<MenuToken> tokens);

	List<Menu> getUserMenu(Integer uid);
}
