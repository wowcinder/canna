package com.voole.gxt.server.dao.menu;

import java.util.List;

import com.voole.gxt.server.dao.IBaseDao;
import com.voole.gxt.shared.entity.menu.Menu;

public interface MenuDao extends IBaseDao<Menu> {

	public void saveOrder(List<Long> ids);
}
