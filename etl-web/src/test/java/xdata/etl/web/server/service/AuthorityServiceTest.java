/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.service;

import java.util.List;

import junit.framework.Assert;

import org.hibernate.Hibernate;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import xdata.etl.web.client.service.authority.AuthorityGroupService;
import xdata.etl.web.client.service.authority.AuthorityService;
import xdata.etl.web.server.EtlSpringTestCase;
import xdata.etl.web.shared.Provider;
import xdata.etl.web.shared.entity.authority.Authority;
import xdata.etl.web.shared.entity.authority.AuthorityGroup;

/**
 * @author XuehuiHe
 * @date 2013年8月4日
 * 
 */
public class AuthorityServiceTest extends EtlSpringTestCase {
	@Autowired
	private AuthorityService service;

	@Autowired
	private AuthorityGroupService agService;

	@Test
	public void test() {
		AuthorityGroup ag = new AuthorityGroup();
		ag.setDisplayOrder(1);
		ag.setName("测试");

		Integer agId = agService.save(ag);

		Authority a = new Authority();
		a.setDisplayOrder(1);
		a.setGroup(ag);
		a.setId("ksldjflsjdlf");
		a.setName("ksldjflsjd");

		service.save(a);

		a = service.get(new Provider<String>("ksldjflsjdlf"));
		Assert.assertNotNull(a);
		Assert.assertEquals(a.getName(), "ksldjflsjd");
		Assert.assertNotNull(a.getGroup());
		Hibernate.initialize(a.getGroup());
		Assert.assertEquals(agId, a.getGroup().getId());

		ag = agService.get(new Provider<Integer>(agId));
		Assert.assertFalse(Hibernate.isInitialized(ag.getAuthorities()));

		a.setGroup(null);

		service.update(a);

		agService.delete(new Provider<Integer>(ag.getId()));

		List<Authority> list = service.get();
		Assert.assertEquals(1, list.size());

	}
}
