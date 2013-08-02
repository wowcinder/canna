package com.voole.gxt.client.project.manager;

import com.voole.gxt.client.app.MenuToken;
import com.voole.gxt.client.app.ui.MenuCenterView;

@MenuToken(token = "projectManagerManager", name = "项目经理管理")
public class ManagerManagerView extends MenuCenterView {

	ManagerGridContainer gridCon;

	public ManagerManagerView() {
		setLabel("相关经理管理");
		gridCon = new ManagerGridContainer();
		con.setWidget(gridCon);
	}

}
