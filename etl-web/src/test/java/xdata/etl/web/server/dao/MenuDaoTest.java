/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import xdata.etl.web.server.EtlSpringTestCase;
import xdata.etl.web.server.dao.authority.AuthorityDao;
import xdata.etl.web.server.dao.menu.MenuDao;
import xdata.etl.web.server.dao.user.UserDao;
import xdata.etl.web.shared.entity.user.User;

/**
 * @author XuehuiHe
 * @date 2013年8月13日
 */
public class MenuDaoTest extends EtlSpringTestCase {
	@Autowired
	MenuDao dao;
	@Autowired
	UserDao userDao;
	@Autowired
	AuthorityDao authorityDao;

	@Test
	public void test() {
		User user = new User();
		user.setEmail("kk@kk.com");
		user.setPassword("sjdlfjsljd");
		user.setExtraAuthorities(authorityDao.get());
		
		userDao.save(user);
		
		System.out
				.println("---------------------------------------------------------------------------------");
		// dao.get();
		// System.out.println(dao.get().size());
		System.out.println(userDao.getUserAuthorities(user.getId()).size());
	}
}
