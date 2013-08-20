/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import xdata.etl.web.server.EtlSpringTestCase;
import xdata.etl.web.shared.service.hbasequery.HbaseQueryService;

/**
 * @author XuehuiHe
 * @date 2013年8月16日
 */
public class HbaseQueryServiceTest extends EtlSpringTestCase {
	@Autowired
	public HbaseQueryService service;

	@Test
	public void test() {
		service.getData("msg_v3a_user_auth", null);

	}
}
