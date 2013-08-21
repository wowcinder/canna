/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.shared.service.authority;

import xdata.etl.web.shared.entity.authority.AuthorityGroup;
import xdata.etl.web.shared.service.RpcService;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author XuehuiHe
 * @date 2013年8月4日
 * 
 */
@RemoteServiceRelativePath("rpc/authority_group.rpc")
public interface AuthorityGroupRpcService extends
		RpcService<Integer, AuthorityGroup>, RemoteService {
	Integer queryByName(String name);
}
