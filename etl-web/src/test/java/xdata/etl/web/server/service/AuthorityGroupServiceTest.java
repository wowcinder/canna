/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import xdata.etl.web.client.service.authority.AuthorityGroupService;
import xdata.etl.web.server.EtlSpringTestCase;
import xdata.etl.web.shared.entity.authority.Authority;
import xdata.etl.web.shared.entity.authority.AuthorityGroup;

/**
 * @author XuehuiHe
 * @date 2013年8月4日
 * 
 */
public class AuthorityGroupServiceTest extends EtlSpringTestCase {
	@Autowired
	private AuthorityGroupService service;

	@Test
	public void test() {

		AuthorityGroup ag = new AuthorityGroup();
		ag.setDisplayOrder(1);
		ag.setName("test4");
		List<Authority> list = new ArrayList<Authority>();
		
		Authority a = new Authority();
		a.setDisplayOrder(1);
		a.setName("kkkkk");
		a.setId("pppppp");
		a.setGroup(ag);
		list.add(a);
		
		ag.setAuthorities(list);

		// ag.setAuthorities(new ArrayList<Authority>());
		try {
			service.save(ag);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
