package com.voole.gxt.client.project.leader;

import com.sencha.gxt.widget.core.client.box.AlertMessageBox;
import com.voole.gxt.client.canna.gridcontainer.CannaGridContainer;
import com.voole.gxt.client.rpcclient.project.ProjectLeaderRpcClient;
import com.voole.gxt.shared.entity.project.ProjectLeader;

public class LeaderGridContainer extends CannaGridContainer<ProjectLeader> {

	public LeaderGridContainer() {
		super(new LeaderGrid(),new ProjectLeaderRpcClient());
		setEditor(new LeaderEditor());
	}

	@Override
	protected void noFoundDeleteItems() {
		AlertMessageBox d = new AlertMessageBox("删除", "全选择要删除的leader");
		d.show();
	}

}
