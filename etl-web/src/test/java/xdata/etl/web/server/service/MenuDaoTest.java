/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import xdata.etl.web.server.EtlSpringTestCase;
import xdata.etl.web.server.dao.menu.MenuDao;
import xdata.etl.web.shared.entity.menu.Menu;

/**
 * @author XuehuiHe
 * @date 2013年8月9日
 */
public class MenuDaoTest extends EtlSpringTestCase {

	@Autowired
	private MenuDao menuDao;

	@Test
	public void test() {
		List<Menu> list = menuDao.get();
		System.out.println(list.size());
		
		list = menuDao.get();
		System.out.println(list.size());
	}

}
