package com.voole.gxt.server.action;

import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.struts2.convention.annotation.Action;

import com.google.gwt.user.client.rpc.SerializationException;
import com.voole.gxt.client.service.login.LoginService;

public class LoginAction extends BaseAction {
	@Action("/rpc/login")
	public void rpcLoginEntryPoint() throws ServletException, IOException,
			SerializationException {
		getGWTHelper(LoginService.class).execute();
	}
}
