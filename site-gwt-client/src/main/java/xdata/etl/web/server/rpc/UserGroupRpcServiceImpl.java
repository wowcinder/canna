/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.rpc;

import org.springframework.stereotype.Service;

import xdata.etl.web.server.annotations.AccessAuthorityGroup;
import xdata.etl.web.server.service.user.UserGroupService;
import xdata.etl.web.shared.entity.user.UserGroup;
import xdata.etl.web.shared.service.user.UserGroupRpcService;

/**
 * @author XuehuiHe
 * @date 2013年8月8日
 */
@Service
@AccessAuthorityGroup("用户组")
public class UserGroupRpcServiceImpl extends
		RpcServiceImpl<Integer, UserGroup, UserGroupService> implements
		UserGroupRpcService {

	public UserGroupRpcServiceImpl() {
	}

}
