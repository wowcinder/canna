/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.service.authority;

import xdata.etl.web.client.service.RpcService;
import xdata.etl.web.shared.entity.authority.Authority;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author XuehuiHe
 * @date 2013年8月2日
 */
@RemoteServiceRelativePath("rpc/test.rpc")
public interface AuthorityService extends RpcService<String, Authority>,
		RemoteService {
}
