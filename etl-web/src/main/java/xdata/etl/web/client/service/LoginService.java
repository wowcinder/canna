/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author XuehuiHe
 * @date 2013年8月13日
 */
@RemoteServiceRelativePath("rpc/login.rpc")
public interface LoginService extends RemoteService {
	Integer login(String username, String password);

	Boolean isLogin();

	void logout();
}
