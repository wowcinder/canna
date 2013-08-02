package com.voole.gxt.server.action;

import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.struts2.convention.annotation.Action;

import com.google.gwt.user.client.rpc.SerializationException;
import com.voole.gxt.client.service.authority.AuthorityRpcGroupService;
import com.voole.gxt.client.service.authority.AuthorityRpcMethodService;

public class AuthorityAction extends BaseAction {

	@Action("/rpc/authority/rpcgroup")
	public void rpcAuthorityRpcGroup() throws ServletException, IOException,
			SerializationException {
		getGWTHelper(AuthorityRpcGroupService.class).execute();
	}

	@Action("/rpc/authority/rpcmethod")
	public void rpcAuthorityRpcMethod() throws ServletException, IOException,
			SerializationException {
		getGWTHelper(AuthorityRpcMethodService.class).execute();
	}
}
