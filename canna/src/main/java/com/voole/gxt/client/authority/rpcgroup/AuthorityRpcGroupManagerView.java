package com.voole.gxt.client.authority.rpcgroup;

import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.voole.gxt.client.app.MenuToken;
import com.voole.gxt.client.app.ui.MenuCenterView;

@MenuToken(name = "权限组管理", token = "authorityRpcGroupManager")
public class AuthorityRpcGroupManagerView extends MenuCenterView {
	HorizontalLayoutContainer c;

	AuthorityRpcGroupGridContainer gridCon;

	public AuthorityRpcGroupManagerView() {
		setLabel("权限组管理");
		gridCon = new AuthorityRpcGroupGridContainer();
		con.setWidget(gridCon);
	}
}
