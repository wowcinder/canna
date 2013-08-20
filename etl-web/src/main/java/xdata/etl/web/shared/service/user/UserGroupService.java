/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.shared.service.user;

import xdata.etl.web.shared.entity.user.UserGroup;
import xdata.etl.web.shared.service.RpcService;

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
