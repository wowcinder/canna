package com.voole.gxt.client.service.user;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.voole.gxt.client.service.CannaServiceAsync;
import com.voole.gxt.shared.entity.authority.AuthorityRpcGroup;
import com.voole.gxt.shared.entity.user.UserGroup;

public interface UserGroupServiceAsync extends CannaServiceAsync<UserGroup> {

	void getAuthorityRpcGroups(Long id,
			AsyncCallback<List<AuthorityRpcGroup>> callback);

}
