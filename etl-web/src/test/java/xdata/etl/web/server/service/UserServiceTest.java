/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.service;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import xdata.etl.web.server.EtlSpringTestCase;
import xdata.etl.web.server.dao.authority.AuthorityDao;
import xdata.etl.web.server.dao.authority.AuthorityGroupDao;
import xdata.etl.web.server.dao.user.UserDao;
import xdata.etl.web.server.dao.user.UserGroupDao;
import xdata.etl.web.shared.entity.authority.Authority;
import xdata.etl.web.shared.entity.user.User;

/**
 * @author XuehuiHe
 * @date 2013年8月8日
 */
public class UserServiceTest extends EtlSpringTestCase {
	@Autowired
	public AuthorityDao authDao;
	@Autowired
	public AuthorityGroupDao authGroupDao;
	@Autowired
	public UserDao userDao;
	@Autowired
	public UserGroupDao userGroupDao;
	@Autowired
	public SessionFactory sf;

	@Test
	public void test() {
		Authority auth = authDao.get(1);
		List<Authority> auths = new ArrayList<Authority>();
		auths.add(auth);

		User u = new User();
		u.setEmail("kk@kk.com");
		u.setPassword("kkk");
		u.setExtraAuthorities(auths);

		u = userDao.saveAndReturn(u);
		Assert.assertEquals(1, u.getExtraAuthorities().size());
		authDao.delete(auth.getId());

		Session s = sf.openSession();
		s.beginTransaction();
		u = (User) s.createCriteria(User.class)
				.setFetchMode("extraAuthorities", org.hibernate.FetchMode.JOIN)
				.add(Restrictions.idEq(u.getId())).uniqueResult();
		Assert.assertEquals(0, u.getExtraAuthorities().size());
		s.getTransaction().commit();
	}

}
