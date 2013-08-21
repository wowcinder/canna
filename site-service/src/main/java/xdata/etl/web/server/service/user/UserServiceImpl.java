/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.service.user;

import org.springframework.stereotype.Service;

import xdata.etl.web.server.dao.user.UserDao;
import xdata.etl.web.server.service.RpcDelegateServiceImpl;
import xdata.etl.web.shared.entity.user.User;

/**
 * @author XuehuiHe
 * @date 2013年8月8日
 */
@Service
public class UserServiceImpl extends
		RpcDelegateServiceImpl<Integer, User, UserDao> implements UserService {
	public UserServiceImpl() {
	}
}
