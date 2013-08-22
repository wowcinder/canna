/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import xdata.etl.testweb.server.dao.MenuDao;
import xdata.etl.testweb.shared.entity.menu.MenuGroup;
import xdata.etl.testweb.shared.entity.menu.MenuNode;

/**
 * @author XuehuiHe
 * @date 2013年8月22日
 */
public class Testdd extends SiteServiceTestCase {
	@Autowired
	private MenuDao dao;

	@org.junit.Test
	public void test() {
//		MenuGroup mg = new MenuGroup();
//		mg.setName("test group");
//		dao.insert(mg);
//		Menu menu = new Menu();
//		menu.setName("1 menu");
//		menu.setToken("1menu");
//		menu.setParent(mg);
//		dao.insert(menu);

		// MenuNode node = new MenuNode();
		// node.setName("sjdljflsj");
		// dao.insert(node);
//		MenuNode node = new MenuNode();
//		node.setName("second");
//		dao.insert(node);
		// Menu menu = new Menu();
		// menu.setName("a menu");
		// menu.setToken("sdjsdflj");
		// dao.insert(menu);
		MenuGroup mg = new MenuGroup();
		mg.setId(3);
		
		dao.delete(mg);
		
		List<MenuNode> list = dao.get();
		System.out.println(list.size());

		// dao.insert(null, null, null);

	}
}
