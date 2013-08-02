package com.voole.gxt.client.rpcclient.user;

import java.util.List;

import com.voole.gxt.client.rpcclient.CannaRpcClient;
import com.voole.gxt.client.rpcclient.callback.GetCallback;
import com.voole.gxt.client.service.CannaAsyncCallback;
import com.voole.gxt.client.service.ClientServiceFactory;
import com.voole.gxt.client.service.user.UserGroupService;
import com.voole.gxt.client.service.user.UserGroupServiceAsync;
import com.voole.gxt.shared.entity.authority.AuthorityRpcGroup;
import com.voole.gxt.shared.entity.user.UserGroup;

public class UserGroupRpcClient extends CannaRpcClient<UserGroup> {

	private static final UserGroupServiceAsync service = ClientServiceFactory
			.getService(UserGroupService.class);

	@Override
	public UserGroupServiceAsync getService() {
		return service;
	}

	public void getAuthorityRpcGroups(Long id,
			final GetCallback<List<AuthorityRpcGroup>> callback) {
		getService().getAuthorityRpcGroups(id,
				new CannaAsyncCallback<List<AuthorityRpcGroup>>() {
					@Override
					public void onSuccess(List<AuthorityRpcGroup> result) {
						callback.postGet(result);
					}
				});
	}
}
