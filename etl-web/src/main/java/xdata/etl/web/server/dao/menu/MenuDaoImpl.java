/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.dao.menu;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import xdata.etl.web.server.dao.RpcDao;
import xdata.etl.web.shared.annotations.MenuToken;
import xdata.etl.web.shared.entity.menu.Menu;

/**
 * @author XuehuiHe
 * @date 2013年8月8日
 */
@Repository
public class MenuDaoImpl extends RpcDao<Integer, Menu> implements MenuDao {

	@Override
	public void initMenuConfig(List<MenuToken> tokens) {
		Session s = getSession();
		for (MenuToken mt : tokens) {
			Menu menu = (Menu) s.createCriteria(Menu.class)
					.add(Restrictions.eq("token", mt.token())).uniqueResult();
			if (menu == null) {
				menu = new Menu();
				menu.setToken(mt.token());
				menu.setName(mt.name());
				s.saveOrUpdate(menu);
				menu.setPos(menu.getId());
			} else {
				menu.setName(mt.name());
				s.saveOrUpdate(menu);
			}
		}
	}
}
