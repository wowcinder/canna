package com.voole.gxt.client.project;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.text.shared.AbstractSafeHtmlRenderer;
import com.sencha.gxt.cell.core.client.SimpleSafeHtmlCell;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.voole.gxt.client.canna.grid.CannaGrid;
import com.voole.gxt.client.property.project.ProjectProperties;
import com.voole.gxt.shared.entity.project.Operator;
import com.voole.gxt.shared.entity.project.Project;
import com.voole.gxt.shared.entity.project.ProjectLeader;
import com.voole.gxt.shared.entity.project.ProjectManager;

public class ProjectGrid extends CannaGrid<Project> {

	private final static ProjectProperties props = GWT
			.create(ProjectProperties.class);

	public ProjectGrid() {
		super(props);
	}

	@Override
	protected void initColumnModel() {
		ColumnConfig<Project, String> ccName = new ColumnConfig<Project, String>(
				props.name(), 200, "项目名称");
		ColumnConfig<Project, ProjectLeader> ccLeader = new ColumnConfig<Project, ProjectLeader>(
				props.leader(), 200, "负责人");
		ccLeader.setCell(new SimpleSafeHtmlCell<ProjectLeader>(
				new AbstractSafeHtmlRenderer<ProjectLeader>() {
					@Override
					public SafeHtml render(ProjectLeader l) {
						return SafeHtmlUtils.fromString(l.getName());
					}
				}));
		ColumnConfig<Project, ProjectManager> ccManager = new ColumnConfig<Project, ProjectManager>(
				props.manager(), 200, "经理");
		ccManager.setCell(new SimpleSafeHtmlCell<ProjectManager>(
				new AbstractSafeHtmlRenderer<ProjectManager>() {
					@Override
					public SafeHtml render(ProjectManager l) {
						return SafeHtmlUtils.fromString(l.getName());
					}
				}));
		ColumnConfig<Project, Operator> ccOp = new ColumnConfig<Project, Operator>(
				props.operator(), 200, "操作员");
		ccOp.setCell(new SimpleSafeHtmlCell<Operator>(
				new AbstractSafeHtmlRenderer<Operator>() {
					@Override
					public SafeHtml render(Operator l) {
						return SafeHtmlUtils.fromString(l.getName());
					}
				}));
		ccList.add(ccName);
		ccList.add(ccLeader);
		ccList.add(ccManager);
		ccList.add(ccOp);

	}

}
