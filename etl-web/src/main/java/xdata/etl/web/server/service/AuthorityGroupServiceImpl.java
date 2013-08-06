/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xdata.etl.web.client.service.authority.AuthorityGroupService;
import xdata.etl.web.server.dao.authority.AuthorityGroupDao;
import xdata.etl.web.shared.annotations.AuthenticationService;
import xdata.etl.web.shared.entity.authority.AuthorityGroup;

/**
 * @author XuehuiHe
 * @date 2013年8月4日
 * 
 */
@Service
@AuthenticationService("权限组")
public class AuthorityGroupServiceImpl extends
		RpcServiceImpl<Integer, AuthorityGroup> implements
		AuthorityGroupService {
	public AuthorityGroupServiceImpl() {
	}

	@Autowired
	public AuthorityGroupServiceImpl(AuthorityGroupDao rpcDao) {
		super(rpcDao);
	}

	@Override
	public Integer queryByName(String name) {
		return getRpcDao().queryByName(name);
	}

	@Override
	public AuthorityGroupDao getRpcDao() {
		return (AuthorityGroupDao) super.getRpcDao();
	}

}
