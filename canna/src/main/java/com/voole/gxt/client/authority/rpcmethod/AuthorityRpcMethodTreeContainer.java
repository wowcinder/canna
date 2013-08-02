package com.voole.gxt.client.authority.rpcmethod;

import com.google.gwt.user.client.ui.ScrollPanel;

public class AuthorityRpcMethodTreeContainer extends ScrollPanel {
	public AuthorityRpcMethodTreeContainer() {
		super();
		setWidget(new AuthorityRpcMethodTree());
	}
}
