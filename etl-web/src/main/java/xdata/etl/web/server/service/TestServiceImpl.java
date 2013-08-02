/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xdata.etl.web.client.service.TestService;
import xdata.etl.web.server.dao.authority.AuthorityDao;
import xdata.etl.web.shared.entity.authority.Authority;

/**
 * @author XuehuiHe
 * @date 2013年8月2日
 */
@Service
public class TestServiceImpl extends RpcServiceImpl<String, Authority>
		implements TestService {
	public TestServiceImpl() {
	}

	@Autowired
	public TestServiceImpl(AuthorityDao rpcDao) {
		super(rpcDao);
	}
}
