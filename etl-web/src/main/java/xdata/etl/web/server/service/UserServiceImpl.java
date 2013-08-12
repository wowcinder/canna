/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xdata.etl.web.client.service.user.UserService;
import xdata.etl.web.server.dao.user.UserDao;
import xdata.etl.web.shared.annotations.AccessAuthorityGroup;
import xdata.etl.web.shared.entity.user.User;

/**
 * @author XuehuiHe
 * @date 2013年8月8日
 */
@Service
@AccessAuthorityGroup("用户")
public class UserServiceImpl extends RpcServiceImpl<Integer, User> implements
		UserService {
	public UserServiceImpl() {
	}

	@Autowired
	public UserServiceImpl(UserDao dao) {
		super(dao);
	}

}
