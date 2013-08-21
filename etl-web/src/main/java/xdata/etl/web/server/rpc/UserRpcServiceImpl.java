/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.rpc;

import org.springframework.stereotype.Service;

import xdata.etl.web.server.annotations.AccessAuthorityGroup;
import xdata.etl.web.server.service.user.UserService;
import xdata.etl.web.shared.entity.user.User;
import xdata.etl.web.shared.service.user.UserRpcService;

/**
 * @author XuehuiHe
 * @date 2013年8月8日
 */
@Service
@AccessAuthorityGroup("用户")
public class UserRpcServiceImpl extends
		RpcServiceImpl<Integer, User, UserService> implements UserRpcService {
	public UserRpcServiceImpl() {
	}

}
