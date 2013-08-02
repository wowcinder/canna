package com.voole.gxt.client.service.authority;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.voole.gxt.client.canna.exception.CannaException;
import com.voole.gxt.client.service.CannaService;
import com.voole.gxt.client.service.ServiceConfig;
import com.voole.gxt.shared.entity.authority.AuthorityRpcGroup;
import com.voole.gxt.shared.entity.authority.AuthorityRpcMethod;

@ServiceConfig(path = "rpc/authority/rpcgroup.do", name = "权限组管理")
public interface AuthorityRpcGroupService extends
		CannaService<AuthorityRpcGroup>, RemoteService {

	List<AuthorityRpcMethod> get(AuthorityRpcGroup g) throws CannaException;

}
