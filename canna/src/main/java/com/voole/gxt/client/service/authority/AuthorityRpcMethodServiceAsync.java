package com.voole.gxt.client.service.authority;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.voole.gxt.client.service.CannaServiceAsync;
import com.voole.gxt.shared.entity.authority.AuthorityRpcMethod;
import com.voole.gxt.shared.entity.authority.AuthorityRpcService;

public interface AuthorityRpcMethodServiceAsync extends
		CannaServiceAsync<AuthorityRpcMethod> {

	void getService(AsyncCallback<List<AuthorityRpcService>> callback);

	void updatePassStatus(List<Long> ids, Boolean isAlwaysPass,
			AsyncCallback<Void> callback);

	void getAuthorityRpcMethodsForComBox(
			AsyncCallback<List<AuthorityRpcMethod>> callback);

}
