package com.voole.gxt.client.project.manager;

import com.sencha.gxt.widget.core.client.box.AlertMessageBox;
import com.voole.gxt.client.canna.gridcontainer.CannaGridContainer;
import com.voole.gxt.client.rpcclient.project.ProjectManagerRpcClient;
import com.voole.gxt.shared.entity.project.ProjectManager;

public class ManagerGridContainer extends CannaGridContainer<ProjectManager> {
	public ManagerGridContainer() {
		super(new ManagerGrid(),new ProjectManagerRpcClient());
		setEditor(new ManagerEditor());
	}

	@Override
	protected void noFoundDeleteItems() {
		AlertMessageBox d = new AlertMessageBox("删除", "请选择要删除的经理");
		d.show();
	}

}
