package com.voole.gxt.client.rpcclient.authority;

import java.util.ArrayList;
import java.util.List;

import com.voole.gxt.client.rpcclient.CannaRpcClient;
import com.voole.gxt.client.rpcclient.callback.GetCallback;
import com.voole.gxt.client.rpcclient.callback.UpdateCallback;
import com.voole.gxt.client.service.CannaAsyncCallback;
import com.voole.gxt.client.service.ClientServiceFactory;
import com.voole.gxt.client.service.authority.AuthorityRpcMethodService;
import com.voole.gxt.client.service.authority.AuthorityRpcMethodServiceAsync;
import com.voole.gxt.shared.entity.authority.AuthorityRpcMethod;
import com.voole.gxt.shared.entity.authority.AuthorityRpcService;

public class AuthorityRpcMethodRpcClient extends
		CannaRpcClient<AuthorityRpcMethod> {
	private static final AuthorityRpcMethodServiceAsync service = ClientServiceFactory
			.getService(AuthorityRpcMethodService.class);

	@Override
	public AuthorityRpcMethodServiceAsync getService() {
		return service;
	}

	public void getService(final GetCallback<List<AuthorityRpcService>> callback) {
		getService().getService(
				new CannaAsyncCallback<List<AuthorityRpcService>>() {
					@Override
					public void onSuccess(List<AuthorityRpcService> result) {
						callback.postGet(result);
					}
				});
	}

	public void updatePassStatus(final List<AuthorityRpcMethod> methods,
			final Boolean isAlwaysPass,
			final UpdateCallback<List<AuthorityRpcMethod>> callback) {
		List<Long> ids = new ArrayList<Long>();
		for (AuthorityRpcMethod method : methods) {
			ids.add(method.getId());
		}
		getService().updatePassStatus(ids, isAlwaysPass,
				new CannaAsyncCallback<Void>() {
					@Override
					public void onSuccess(Void result) {
						for (AuthorityRpcMethod method : methods) {
							method.setAlwaysPass(isAlwaysPass);
						}
						callback.postUpdate(methods);
					}
				});
	}

	public void getAuthorityRpcMethodsForComBox(
			final GetCallback<List<AuthorityRpcMethod>> callback) {
		getService().getAuthorityRpcMethodsForComBox(
				new CannaAsyncCallback<List<AuthorityRpcMethod>>() {
					@Override
					public void onSuccess(
							List<AuthorityRpcMethod> result) {
						callback.postGet(result);
					}
				});
	}
}
