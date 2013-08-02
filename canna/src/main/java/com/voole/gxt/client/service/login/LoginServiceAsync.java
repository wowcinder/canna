package com.voole.gxt.client.service.login;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LoginServiceAsync {

	void isLogin(AsyncCallback<Boolean> callback);

	void login(String email, String password, AsyncCallback<Boolean> callback);

	void isPermit(String email, String token, AsyncCallback<Boolean> callback);

	void isPermit(String token, AsyncCallback<Boolean> callback);

}
