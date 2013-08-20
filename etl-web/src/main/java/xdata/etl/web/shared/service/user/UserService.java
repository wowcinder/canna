/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.shared.service.user;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import xdata.etl.web.shared.entity.user.User;
import xdata.etl.web.shared.service.RpcService;

/**
 * @author XuehuiHe
 * @date 2013年8月8日
 */
@RemoteServiceRelativePath("rpc/user.rpc")
public interface UserService extends RpcService<Integer, User>, RemoteService {

}
