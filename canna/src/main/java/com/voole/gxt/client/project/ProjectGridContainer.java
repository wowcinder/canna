package com.voole.gxt.client.project;

import com.sencha.gxt.widget.core.client.box.AlertMessageBox;
import com.sencha.gxt.widget.core.client.form.SimpleComboBox;
import com.voole.gxt.client.canna.gridcontainer.CannaGridContainer;
import com.voole.gxt.client.project.combox.ProjectLeaderComBox;
import com.voole.gxt.client.project.combox.ProjectManagerComBox;
import com.voole.gxt.client.rpcclient.project.ProjectRpcClient;
import com.voole.gxt.shared.entity.project.Project;
import com.voole.gxt.shared.entity.project.ProjectLeader;
import com.voole.gxt.shared.entity.project.ProjectManager;

public class ProjectGridContainer extends CannaGridContainer<Project> {

	public ProjectGridContainer() {
		super(new ProjectGrid(),new ProjectRpcClient());
		SimpleComboBox<ProjectLeader> leader = new ProjectLeaderComBox();
		SimpleComboBox<ProjectManager> manager = new ProjectManagerComBox();
		setEditor(new ProjectEditor(leader, manager));
	}

	@Override
	protected void noFoundDeleteItems() {
		AlertMessageBox d = new AlertMessageBox("删除", "全选择要删除的项目");
		d.show();
	}

}
