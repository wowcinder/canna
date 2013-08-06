/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xdata.etl.web.client.service.authority.AuthorityService;
import xdata.etl.web.server.dao.authority.AuthorityDao;
import xdata.etl.web.shared.annotations.AuthenticationService;
import xdata.etl.web.shared.entity.authority.Authority;

/**
 * @author XuehuiHe
 * @date 2013年8月2日
 */
@Service
@AuthenticationService("权限")
public class AuthorityServiceImpl extends RpcServiceImpl<Integer, Authority>
		implements AuthorityService {
	public AuthorityServiceImpl() {
	}

	@Autowired
	public AuthorityServiceImpl(AuthorityDao rpcDao) {
		super(rpcDao);
	}

	@Override
	public Integer queryByName(Integer agId, String name) {
		return getRpcDao().queryByName(agId, name);
	}

	@Override
	public AuthorityDao getRpcDao() {
		return (AuthorityDao) super.getRpcDao();
	}
}
