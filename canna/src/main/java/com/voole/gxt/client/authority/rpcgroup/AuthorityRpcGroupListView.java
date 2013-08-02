package com.voole.gxt.client.authority.rpcgroup;

import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.ListView;
import com.voole.gxt.client.property.authority.AuthorityRpcGroupProperties;
import com.voole.gxt.client.rpcclient.authority.AuthorityRpcGroupRcpClient;
import com.voole.gxt.client.rpcclient.callback.GetCallback;
import com.voole.gxt.shared.entity.authority.AuthorityRpcGroup;

public class AuthorityRpcGroupListView extends
		ListView<AuthorityRpcGroup, String> implements
		GetCallback<List<AuthorityRpcGroup>> {
	private final static AuthorityRpcGroupProperties props = GWT
			.create(AuthorityRpcGroupProperties.class);
	private final static AuthorityRpcGroupRcpClient rpc = new AuthorityRpcGroupRcpClient();

	public AuthorityRpcGroupListView() {
		super(new ListStore<AuthorityRpcGroup>(props.key()), props.name());
		setWidth(200);
		rpc.get(this);
	}

	@Override
	public void postGet(List<AuthorityRpcGroup> t) {
		getStore().addAll(t);
	}

}
