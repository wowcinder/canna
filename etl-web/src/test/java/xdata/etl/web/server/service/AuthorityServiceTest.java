/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.service;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import xdata.etl.web.server.EtlSpringTestCase;
import xdata.etl.web.server.util.AuthorityUtil;
import xdata.etl.web.shared.Provider;
import xdata.etl.web.shared.entity.authority.Authority;
import xdata.etl.web.shared.entity.authority.AuthorityGroup;
import xdata.etl.web.shared.service.authority.AuthorityGroupRpcService;
import xdata.etl.web.shared.service.authority.AuthorityRpcService;

/**
 * @author XuehuiHe
 * @date 2013年8月4日
 * 
 */
public class AuthorityServiceTest extends EtlSpringTestCase {
	@Autowired
	private AuthorityRpcService service;

	@Autowired
	private AuthorityGroupRpcService agService;

	private AuthorityGroup ag;
	private Authority a;

	@Before
	public void before() {
		ag = new AuthorityGroup();
		ag.setDisplayOrder(1);
		ag.setName("test");
		agService.save(ag);

		a = new Authority();
		a.setDisplayOrder(1);
		a.setGroup(ag);
		a.setName("ppppppp");
		service.save(a);
	}

	@After
	public void after() {
		if (ag != null) {
			agService.delete(new Provider<Integer>(ag.getId()));
		}
		if (a != null) {
			service.delete(new Provider<Integer>(a.getId()));
		}

	}

	@Test
	public void testSave() {
		AuthorityGroup agNew = agService.get(new Provider<Integer>(ag.getId()));
		Assert.assertEquals(ag.getId(), agNew.getId());
		Assert.assertEquals(ag.getName(), agNew.getName());
		Assert.assertEquals(ag.getDisplayOrder(), agNew.getDisplayOrder());

		Authority aNew = service.get(new Provider<Integer>(a.getId()));
		Assert.assertEquals(a.getId(), aNew.getId());
		Assert.assertEquals(a.getDisplayOrder(), aNew.getDisplayOrder());
		Assert.assertEquals(a.getName(), aNew.getName());
		Assert.assertEquals(a.getToken(), aNew.getToken());
		Assert.assertEquals(a.getToken(),
				AuthorityUtil.getToken(a.getGroup().getName(), a.getName()));
	}

	@Test
	public void changeName() {
		ag.setName("name2");
		agService.update(ag);
		a = service.get(new Provider<Integer>(a.getId()));
		Assert.assertEquals(a.getToken(),
				AuthorityUtil.getToken(a.getGroup().getName(), a.getName()));

		a.setName("name2");
		service.update(a);
		a = service.get(new Provider<Integer>(a.getId()));
		Assert.assertEquals(a.getToken(),
				AuthorityUtil.getToken(a.getGroup().getName(), a.getName()));

	}

	@Test
	public void testDelete() {
		service.delete(new Provider<Integer>(a.getId()));
		a = service.get(new Provider<Integer>(a.getId()));
		Assert.assertNull(a);
	}

	@Test
	public void testDelete2() {
		agService.delete(new Provider<Integer>(ag.getId()));
		ag = agService.get(new Provider<Integer>(ag.getId()));
		Assert.assertNull(ag);
		a = service.get(new Provider<Integer>(a.getId()));
		Assert.assertNull(a);
	}
}
