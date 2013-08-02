package com.voole.gxt.client.rpcclient.user;

import com.voole.gxt.client.rpcclient.CannaRpcClient;
import com.voole.gxt.client.rpcclient.callback.UpdateCallback;
import com.voole.gxt.client.service.CannaAsyncCallback;
import com.voole.gxt.client.service.ClientServiceFactory;
import com.voole.gxt.client.service.user.UserService;
import com.voole.gxt.client.service.user.UserServiceAsync;
import com.voole.gxt.shared.entity.user.User;

public class UserRpcClient extends CannaRpcClient<User> {
	private static final UserServiceAsync service = ClientServiceFactory
			.getService(UserService.class);

	@Override
	public UserServiceAsync getService() {
		return service;
	}

	public void modifyPassword(final User t, final UpdateCallback<User> callback) {
		getService().modifyPassword(t.getId(), t.getPassword(),
				new CannaAsyncCallback<String>() {
					@Override
					public void onSuccess(String result) {
						t.setPassword(result);
						callback.postUpdate(t);
					}
				});
	}
}
