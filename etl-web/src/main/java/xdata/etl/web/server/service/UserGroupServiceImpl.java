/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xdata.etl.web.client.service.user.UserGroupService;
import xdata.etl.web.server.annotations.AccessAuthorityGroup;
import xdata.etl.web.server.dao.user.UserGroupDao;
import xdata.etl.web.shared.entity.user.UserGroup;

/**
 * @author XuehuiHe
 * @date 2013年8月8日
 */
@Service
@AccessAuthorityGroup("用户组")
public class UserGroupServiceImpl extends RpcServiceImpl<Integer, UserGroup>
		implements UserGroupService {

	public UserGroupServiceImpl() {
	}

	@Autowired
	public UserGroupServiceImpl(UserGroupDao dao) {
		super(dao);
	}

}
