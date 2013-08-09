/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.dao.menu;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import xdata.etl.web.server.dao.RpcDao;
import xdata.etl.web.shared.annotations.MenuToken;
import xdata.etl.web.shared.entity.menu.Menu;
import xdata.etl.web.shared.exception.SharedException;

/**
 * @author XuehuiHe
 * @date 2013年8月8日
 */
@Repository
public class MenuDaoImpl extends RpcDao<Integer, Menu> implements MenuDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Menu> get() throws SharedException {
		Session s = getSession();
		List<Menu> list = s.createCriteria(Menu.class)
				.createAlias("menuGroup", "mg").addOrder(Order.desc("mg.pos"))
				.addOrder(Order.desc("pos")).list();
		list.addAll(s.createCriteria(Menu.class)
				.add(Restrictions.isNull("menuGroup"))
				.addOrder(Order.desc("pos")).list());

		return list;
	}

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
