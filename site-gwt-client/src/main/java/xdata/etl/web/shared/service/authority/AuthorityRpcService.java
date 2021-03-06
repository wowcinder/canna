/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.shared.service.authority;

import xdata.etl.web.shared.entity.authority.Authority;
import xdata.etl.web.shared.service.RpcService;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author XuehuiHe
 * @date 2013年8月2日
 */
@RemoteServiceRelativePath("rpc/authority.rpc")
public interface AuthorityRpcService extends RpcService<Integer, Authority>,
		RemoteService {
	Authority queryByName(Integer agId,String name);
}
