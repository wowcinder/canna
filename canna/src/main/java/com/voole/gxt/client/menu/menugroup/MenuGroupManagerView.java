package com.voole.gxt.client.menu.menugroup;

import com.voole.gxt.client.app.MenuToken;
import com.voole.gxt.client.app.ui.MenuCenterView;

@MenuToken(token = "menuGroupManager", name = "菜单组管理")
public class MenuGroupManagerView extends MenuCenterView {
	protected MenuGroupGridContainer gridCon;

	public MenuGroupManagerView() {
		setLabel("菜单组管理");
		gridCon = new MenuGroupGridContainer();
		con.setWidget(gridCon);
	}
}
