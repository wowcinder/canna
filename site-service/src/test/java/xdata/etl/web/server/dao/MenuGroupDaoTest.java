/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.dao;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import xdata.etl.web.server.SiteServiceTestCase;
import xdata.etl.web.server.dao.menu.MenuGroupDao;
import xdata.etl.web.shared.entity.menu.Menu;
import xdata.etl.web.shared.entity.menu.MenuGroup;

/**
 * @author XuehuiHe
 * @date 2013年8月22日
 */
public class MenuGroupDaoTest extends SiteServiceTestCase {
	@Autowired
	MenuGroupDao mgDao;

	@Test
	public void testSave() {
		MenuGroup mg = new MenuGroup();
		mg.setName("test");
		mg.setPos(0);
		mgDao.save(mg);
		Assert.assertEquals(1, mgDao.get().size());
		mgDao.delete(mg.getId());

		mg = new MenuGroup();
		mg.setName("test");
		mg.setPos(0);
		List<Menu> menus = new ArrayList<Menu>();
		for (int i = 0; i < 10; i++) {
			Menu menu = new Menu();
			menu.setName("test" + i);
			menu.setToken("test" + i);
			menu.setPos(i);
			menu.setMenuGroup(mg);
			menus.add(menu);
		}
		mg.setMenus(menus);
		mgDao.save(mg);
		Assert.assertEquals(1, mgDao.get().size());

		mg = mgDao.get().get(0);
		Assert.assertEquals(10, mg.getMenus().size());
		Integer i = 0;
		for (Menu menu : mg.getMenus()) {
			Assert.assertEquals(i, menu.getPos());
			i++;
		}

		List<Integer> ids = new ArrayList<Integer>();
		ids.add(mg.getId());

		mg = new MenuGroup();
		mg.setName("test2");
		mg.setPos(1);
		mgDao.save(mg);
		ids.add(mg.getId());
		List<MenuGroup> g = mgDao.get();
		Assert.assertEquals(2, g.size());
		int j = 0;
		for (MenuGroup menuGroup : g) {
			Assert.assertEquals(j, menuGroup.getPos());
			j++;
		}

		mgDao.delete(ids);

	}
}
