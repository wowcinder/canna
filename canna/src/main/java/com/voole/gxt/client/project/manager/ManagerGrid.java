package com.voole.gxt.client.project.manager;

import com.google.gwt.core.shared.GWT;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.voole.gxt.client.canna.grid.CannaGrid;
import com.voole.gxt.client.property.project.ManagerProperties;
import com.voole.gxt.shared.entity.project.ProjectManager;

public class ManagerGrid extends CannaGrid<ProjectManager> {
	private final static ManagerProperties props = GWT
			.create(ManagerProperties.class);

	public ManagerGrid() {
		super(props);
	}

	@Override
	protected void initColumnModel() {
		ColumnConfig<ProjectManager, String> ccName = new ColumnConfig<ProjectManager, String>(
				props.name(), 200, "姓名");
		ColumnConfig<ProjectManager, String> ccMobile = new ColumnConfig<ProjectManager, String>(
				props.mobile(), 200, "手机号");
		ColumnConfig<ProjectManager, String> ccExtNum = new ColumnConfig<ProjectManager, String>(
				props.extNum(), 200, "分机号");
		ColumnConfig<ProjectManager, String> ccEmail = new ColumnConfig<ProjectManager, String>(
				props.email(), 200, "email");

		ccList.add(ccName);
		ccList.add(ccMobile);
		ccList.add(ccExtNum);
		ccList.add(ccEmail);
	}

}
