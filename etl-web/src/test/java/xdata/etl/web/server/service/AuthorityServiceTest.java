/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import xdata.etl.web.client.service.authority.AuthorityService;

/**
 * @author XuehuiHe
 * @date 2013年8月4日
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:springMVC.xml",
		"classpath:gwtUtil.xml" })
public class AuthorityServiceTest {
	@Autowired
	private AuthorityService service;

	@Test
	public void test() {
		System.out.println("------------------" + service);
	}
}
