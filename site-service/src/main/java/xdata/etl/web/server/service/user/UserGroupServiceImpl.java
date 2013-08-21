/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.service.user;

import org.springframework.stereotype.Service;

import xdata.etl.web.server.dao.user.UserGroupDao;
import xdata.etl.web.server.service.RpcDelegateServiceImpl;
import xdata.etl.web.shared.entity.user.UserGroup;

/**
 * @author XuehuiHe
 * @date 2013年8月8日
 */
@Service
public class UserGroupServiceImpl extends
		RpcDelegateServiceImpl<Integer, UserGroup, UserGroupDao> implements
		UserGroupService {

	public UserGroupServiceImpl() {
	}

}
