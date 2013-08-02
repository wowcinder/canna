package com.voole.gxt.client.menu;

import com.voole.gxt.client.app.MenuToken;
import com.voole.gxt.client.app.ui.MenuCenterView;

@MenuToken(token = "menuManager", name = "菜单管理")
public class MenuManagerView extends MenuCenterView {
	protected MenuGridContainer gridCon;

	public MenuManagerView() {
		setLabel("菜单管理");
		gridCon = new MenuGridContainer();
		con.setWidget(gridCon);
	}

}
