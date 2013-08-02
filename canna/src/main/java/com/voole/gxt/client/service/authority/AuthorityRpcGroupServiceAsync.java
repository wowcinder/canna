package com.voole.gxt.client.service.authority;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.voole.gxt.client.service.CannaServiceAsync;
import com.voole.gxt.shared.entity.authority.AuthorityRpcGroup;
import com.voole.gxt.shared.entity.authority.AuthorityRpcMethod;

public interface AuthorityRpcGroupServiceAsync extends
		CannaServiceAsync<AuthorityRpcGroup> {

	void get(AuthorityRpcGroup g,
			AsyncCallback<List<AuthorityRpcMethod>> callback);

}
