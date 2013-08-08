/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.service.user;

import xdata.etl.web.client.service.RpcService;
import xdata.etl.web.shared.entity.user.UserGroup;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author XuehuiHe
 * @date 2013年8月8日
 */
@RemoteServiceRelativePath("rpc/user_group.rpc")
public interface UserGroupService extends RpcService<Integer, UserGroup>,
		RemoteService {

}