package com.voole.gxt.server.dao.menu;

import java.util.List;

import com.voole.gxt.server.dao.IBaseDao;
import com.voole.gxt.shared.entity.menu.MenuGroup;

public interface MenuGroupDao extends IBaseDao<MenuGroup> {
	public void saveOrder(List<Long> ids);
	public List<MenuGroup> getMenuGroupsForMenuView();
}
