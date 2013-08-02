package com.voole.gxt.server.action;

import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.struts2.convention.annotation.Action;

import com.google.gwt.user.client.rpc.SerializationException;
import com.voole.gxt.client.service.user.UserGroupService;
import com.voole.gxt.client.service.user.UserService;

public class UserAction extends BaseAction {
	@Action("/rpc/user/user")
	public void rpcUserEntryPoint() throws ServletException, IOException,
			SerializationException {
		getGWTHelper(UserService.class).execute();
	}

	@Action("/rpc/user/group")
	public void rpcUserGroupEntryPoint() throws ServletException, IOException,
			SerializationException {
		getGWTHelper(UserGroupService.class).execute();
	}
}
