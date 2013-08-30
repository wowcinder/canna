/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import xdata.etl.web.server.SiteServiceTestCase;
import xdata.etl.web.server.dao.hbasemeta.HbaseTableVersionDao;
import xdata.etl.web.shared.common.paging.EtlPagingLoadConfigBean;
import xdata.etl.web.shared.entity.hbasemeta.HbaseTableVersion;

/**
 * @author XuehuiHe
 * @date 2013年8月30日
 */
public class HbaseTableVersionDaoTest extends SiteServiceTestCase {
	@Autowired
	private HbaseTableVersionDao dao;

	@Test
	public void test() {
		System.out.println(dao.get().size());

		EtlPagingLoadConfigBean p = new EtlPagingLoadConfigBean();
		p.setOffset(0);
		p.setLimit(50);
		
		for (HbaseTableVersion version : dao.get(p).getData()) {
			System.out.println(version);
		}

	}
}
