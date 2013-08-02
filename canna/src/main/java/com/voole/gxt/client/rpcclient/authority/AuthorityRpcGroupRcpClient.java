package com.voole.gxt.client.rpcclient.authority;

import java.util.List;

import com.voole.gxt.client.rpcclient.CannaRpcClient;
import com.voole.gxt.client.rpcclient.callback.GetCallback;
import com.voole.gxt.client.service.CannaAsyncCallback;
import com.voole.gxt.client.service.ClientServiceFactory;
import com.voole.gxt.client.service.authority.AuthorityRpcGroupService;
import com.voole.gxt.client.service.authority.AuthorityRpcGroupServiceAsync;
import com.voole.gxt.shared.entity.authority.AuthorityRpcGroup;
import com.voole.gxt.shared.entity.authority.AuthorityRpcMethod;

public class AuthorityRpcGroupRcpClient extends
		CannaRpcClient<AuthorityRpcGroup> {
	private static final AuthorityRpcGroupServiceAsync serice = ClientServiceFactory
			.getService(AuthorityRpcGroupService.class);

	@Override
	public AuthorityRpcGroupServiceAsync getService() {
		return serice;
	}

	public void get(AuthorityRpcGroup g,
			final GetCallback<List<AuthorityRpcMethod>> callback) {
		getService().get(g, new CannaAsyncCallback<List<AuthorityRpcMethod>>() {
			@Override
			public void onSuccess(List<AuthorityRpcMethod> result) {
				callback.postGet(result);
			}
		});
	}

}
