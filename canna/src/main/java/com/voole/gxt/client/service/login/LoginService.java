package com.voole.gxt.client.service.login;

import com.google.gwt.user.client.rpc.RemoteService;
import com.voole.gxt.client.service.ServiceConfig;

@ServiceConfig(name = "登录", path = "rpc/login.do")
public interface LoginService extends RemoteService {
	boolean isLogin();

	boolean login(String email, String password);

	boolean isPermit(String email, String token);

	boolean isPermit(String token);
}
