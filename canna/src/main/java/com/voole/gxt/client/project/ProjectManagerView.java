package com.voole.gxt.client.project;

import com.voole.gxt.client.app.MenuToken;
import com.voole.gxt.client.app.ui.MenuCenterView;

@MenuToken(token = "projectManager", name = "项目管理")
public class ProjectManagerView extends MenuCenterView {

	protected ProjectGridContainer gridCon;

	public ProjectManagerView() {
		setLabel("项目管理");
		gridCon = new ProjectGridContainer();
		gridCon.getStore().clear();
		con.setWidget(gridCon);
	}
}
