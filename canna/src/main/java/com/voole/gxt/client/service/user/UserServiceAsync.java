package com.voole.gxt.client.service.user;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.voole.gxt.client.service.CannaServiceAsync;
import com.voole.gxt.shared.entity.user.User;

public interface UserServiceAsync extends CannaServiceAsync<User> {

	void modifyPassword(Long id, String newPassword,
			AsyncCallback<String> callback);

}
