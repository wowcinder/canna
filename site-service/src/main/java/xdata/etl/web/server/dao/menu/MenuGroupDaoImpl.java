/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.dao.menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import xdata.etl.web.server.dao.RpcDao;
import xdata.etl.web.shared.annotations.MenuToken;
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

	@SuppressWarnings("unchecked")
	@Override
	public List<MenuGroup> get() throws SharedException {
		Session s = getSession();
		List<MenuGroup> groups = s
				.createCriteria(MenuGroup.class)
				.addOrder(Order.asc("pos"))
				.setFetchMode("menus", FetchMode.JOIN)
				.createAlias("menus", "menus", Criteria.LEFT_JOIN)
				.addOrder(Order.asc("menus.pos"))
				.setResultTransformer(
						CriteriaSpecification.DISTINCT_ROOT_ENTITY).list();
		return groups;
	}

	@SuppressWarnings("unchecked")
	@Override
	public MenuGroup update(MenuGroup v) throws SharedException {
		Session s = getSession();
		s.update(v);

		List<Integer> ids = new ArrayList<Integer>();
		for (Menu menu : v.getMenus()) {
			ids.add(menu.getId());
		}
		Criteria c = s.createCriteria(Menu.class)
				.createAlias("menuGroup", "mg")
				.add(Restrictions.eq("mg.id", v.getId()));
		if (ids.size() > 0) {
			c.add(Restrictions.not(Restrictions.in("id", ids)));
		}

		List<Menu> menus = c.list();
		for (Menu menu : menus) {
			menu.setMenuGroup(null);
			s.merge(menu);
		}
		return v;
	}

	@Override
	public void delete(Integer k) throws SharedException {
		Session s = getSession();
		MenuGroup mg = (MenuGroup) s.load(MenuGroup.class, k);
		try {
			for (Menu menu : mg.getMenus()) {
				menu.setMenuGroup(null);
				s.update(menu);
			}
			mg.setMenus(null);
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

	@Override
	public void savePos(List<Integer> ids) {
		Session s = getSession();
		for (int i = 0; i < ids.size(); i++) {
			Integer id = ids.get(i);
			MenuGroup mg = (MenuGroup) s.load(MenuGroup.class, id);
			mg.setPos(i);
			s.update(mg);
		}
	}

	@Override
	public void initMenu(List<MenuToken> tokens) {
		Map<String, List<MenuToken>> map = new HashMap<String, List<MenuToken>>();
		for (MenuToken menuToken : tokens) {
			if (!map.containsKey(menuToken.group())) {
				map.put(menuToken.group(), new ArrayList<MenuToken>());
			}
			map.get(menuToken.group()).add(menuToken);
		}
		int gPos = 0;
		for (String group : map.keySet()) {
			MenuGroup mg = new MenuGroup();
			if (group.length() == 0) {
				mg.setName("未分组");
			} else {
				mg.setName(group);
			}
			mg.setPos(gPos);

			List<Menu> menus = new ArrayList<Menu>();
			int mPos = 0;
			for (MenuToken menuToken : map.get(group)) {
				Menu menu = new Menu();
				menu.setName(menuToken.name());
				menu.setMenuGroup(mg);
				menu.setPos(mPos);
				menu.setToken(menuToken.token());
				menus.add(menu);
				mPos++;
			}
			mg.setMenus(menus);
			save(mg);
			gPos++;
		}
	}

}
