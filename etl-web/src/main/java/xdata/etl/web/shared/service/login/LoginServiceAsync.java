/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.shared.service.login;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author XuehuiHe
 * @date 2013年8月13日
 */
public interface LoginServiceAsync {

	void isLogin(AsyncCallback<Boolean> callback);

	void login(String username, String password, AsyncCallback<Integer> callback);

	void logout(AsyncCallback<Void> callback);

}
