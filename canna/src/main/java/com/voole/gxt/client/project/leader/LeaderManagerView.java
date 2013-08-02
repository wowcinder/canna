package com.voole.gxt.client.project.leader;

import com.voole.gxt.client.app.MenuToken;
import com.voole.gxt.client.app.ui.MenuCenterView;

@MenuToken(token = "projectLeaderManager", name = "项目负责人管理")
public class LeaderManagerView extends MenuCenterView {

	LeaderGridContainer gridCon;

	public LeaderManagerView() {
		setLabel("相关负责人管理");
		gridCon = new LeaderGridContainer();
		con.setWidget(gridCon);
	}
}
