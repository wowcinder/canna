package com.voole.gxt.client.user.group;

import com.voole.gxt.client.app.MenuToken;
import com.voole.gxt.client.app.ui.MenuCenterView;

@MenuToken(name = "用户组管理", token = "userGroupManager")
public class UserGroupManagerView extends MenuCenterView {

	UserGroupGridContainer gridCon = new UserGroupGridContainer();

	public UserGroupManagerView() {
		setLabel("用户组管理");
		con.setWidget(gridCon);
	}
}
