/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.service.login;


/**
 * @author XuehuiHe
 * @date 2013年8月13日
 */
public interface LoginService {
	Integer login(String username, String password);

	Boolean isLogin();

	void logout();
}
