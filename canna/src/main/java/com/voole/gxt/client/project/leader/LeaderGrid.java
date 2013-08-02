package com.voole.gxt.client.project.leader;

import com.google.gwt.core.shared.GWT;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.voole.gxt.client.canna.grid.CannaGrid;
import com.voole.gxt.client.property.project.LeaderProperties;
import com.voole.gxt.shared.entity.project.ProjectLeader;

public class LeaderGrid extends CannaGrid<ProjectLeader> {
	private final static LeaderProperties props = GWT
			.create(LeaderProperties.class);

	public LeaderGrid() {
		super(props);
	}

	@Override
	protected void initColumnModel() {
		ColumnConfig<ProjectLeader, String> ccName = new ColumnConfig<ProjectLeader, String>(
				props.name(), 200, "姓名");
		ColumnConfig<ProjectLeader, String> ccMobile = new ColumnConfig<ProjectLeader, String>(
				props.mobile(), 200, "手机号");
		ColumnConfig<ProjectLeader, String> ccExtNum = new ColumnConfig<ProjectLeader, String>(
				props.extNum(), 200, "分机号");
		ColumnConfig<ProjectLeader, String> ccEmail = new ColumnConfig<ProjectLeader, String>(
				props.email(), 200, "email");

		ccList.add(ccName);
		ccList.add(ccMobile);
		ccList.add(ccExtNum);
		ccList.add(ccEmail);
	}
}
