package com.voole.gxt.client.authority.rpcmethod;

import com.voole.gxt.client.app.MenuToken;
import com.voole.gxt.client.app.ui.MenuCenterView;

@MenuToken(token = "authorityRpcMethodManager", name = "权限方法管理")
public class AuthorityRpcMethodManagerView extends MenuCenterView {
	public AuthorityRpcMethodManagerView() {
		setLabel("authorityRpcMethodManager");
		con.setWidget(new AuthorityRpcMethodGridContainer());
	}

}
