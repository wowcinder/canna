package com.voole.gxt.client.service.authority;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.voole.gxt.client.canna.exception.CannaException;
import com.voole.gxt.client.service.CannaService;
import com.voole.gxt.client.service.ServiceConfig;
import com.voole.gxt.shared.entity.authority.AuthorityRpcMethod;
import com.voole.gxt.shared.entity.authority.AuthorityRpcService;

@ServiceConfig(path = "rpc/authority/rpcmethod.do", name = "权限方法管理")
public interface AuthorityRpcMethodService extends
		CannaService<AuthorityRpcMethod>, RemoteService {
	List<AuthorityRpcService> getService() throws CannaException;

	void updatePassStatus(List<Long> ids, Boolean isAlwaysPass)
			throws CannaException;

	List<AuthorityRpcMethod> getAuthorityRpcMethodsForComBox()
			throws CannaException;
}
