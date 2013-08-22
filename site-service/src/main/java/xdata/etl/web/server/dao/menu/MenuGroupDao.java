/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.dao.menu;

import java.util.List;

import xdata.etl.web.server.dao.IRpcDao;
import xdata.etl.web.shared.annotations.MenuToken;
import xdata.etl.web.shared.entity.menu.MenuGroup;

/**
 * @author XuehuiHe
 * @date 2013年8月8日
 */
public interface MenuGroupDao extends IRpcDao<Integer, MenuGroup> {
	void savePos(List<Integer> ids);

	void initMenu(List<MenuToken> tokens);
}
