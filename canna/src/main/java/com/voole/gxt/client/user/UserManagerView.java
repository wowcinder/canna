package com.voole.gxt.client.user;

import com.voole.gxt.client.app.MenuToken;
import com.voole.gxt.client.app.ui.MenuCenterView;

@MenuToken(name = "用户管理", token = "userManager")
public class UserManagerView extends MenuCenterView {

	UserGridContainer gridCon = new UserGridContainer();

	public UserManagerView() {
		setLabel("用户管理");
		con.setWidget(gridCon);
	}
}
