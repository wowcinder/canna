/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.dao.menu;

import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import xdata.etl.web.server.dao.RpcDao;
import xdata.etl.web.shared.entity.menu.Menu;
import xdata.etl.web.shared.entity.menu.MenuGroup;
import xdata.etl.web.shared.exception.SharedException;

/**
 * @author XuehuiHe
 * @date 2013年8月8日
 */
@Repository
public class MenuGroupDaoImpl extends RpcDao<Integer, MenuGroup> implements
		MenuGroupDao {
	@Override
	public void delete(Integer k) throws SharedException {
		Session s = getSession();
		MenuGroup mg = (MenuGroup) s.load(MenuGroup.class, k);
		try {
			for (Menu menu : mg.getMenus()) {
				menu.setMenuGroup(null);
			}
			s.delete(mg);
		} catch (ObjectNotFoundException e) {
		}
	}
	
	@Override
	public void delete(List<Integer> ids) throws SharedException {
		for (Integer k : ids) {
			this.delete(k);
		}
	}
}
