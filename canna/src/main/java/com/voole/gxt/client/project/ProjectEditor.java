package com.voole.gxt.client.project;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.data.client.editor.ListStoreEditor;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutData;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer.HBoxLayoutAlign;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.FormPanel.LabelAlign;
import com.sencha.gxt.widget.core.client.form.SimpleComboBox;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.tips.QuickTip;
import com.voole.gxt.client.canna.editor.CannaSimpleEditor;
import com.voole.gxt.client.project.remark.PartnerRemarkGridContainer;
import com.voole.gxt.client.rpcclient.callback.GetCallback;
import com.voole.gxt.client.rpcclient.project.ProjectRpcClient;
import com.voole.gxt.shared.entity.project.Project;
import com.voole.gxt.shared.entity.project.ProjectLeader;
import com.voole.gxt.shared.entity.project.ProjectManager;
import com.voole.gxt.shared.entity.project.ProjectRemark;

public class ProjectEditor extends CannaSimpleEditor<Project> {

	interface ProjectDriver extends
			SimpleBeanEditorDriver<Project, ProjectEditor> {
	}

	private ProjectDriver driver = GWT.create(ProjectDriver.class);

	TextField name = new TextField();
	SimpleComboBox<ProjectLeader> leader;
	SimpleComboBox<ProjectManager> manager;
	ListStoreEditor<ProjectRemark> remarks;

	@Ignore
	PartnerRemarkGridContainer gridCon;
	@Ignore
	private FramedPanel gridPanel;

	public ProjectEditor(SimpleComboBox<ProjectLeader> leader,
			SimpleComboBox<ProjectManager> manager) {
		super();
		this.rpc = new ProjectRpcClient();
		init(leader, manager);
		driver.initialize(this);
	}

	public void init(SimpleComboBox<ProjectLeader> leader,
			SimpleComboBox<ProjectManager> manager) {
		this.leader = leader;
		this.manager = manager;

		root.setWidth(750);
		fp.setLabelAlign(LabelAlign.TOP);
		gridCon = new PartnerRemarkGridContainer();
		remarks = new ListStoreEditor<ProjectRemark>(gridCon.getGrid()
				.getStore());
		gridPanel = new FramedPanel();
		gridPanel.getElement().setMargins(new Margins(15, 0, 0, 0));
		gridPanel.setHeadingText("合作伙伴");
		new QuickTip(gridCon.getGrid());
		gridCon.getElement().setHeight(150);
		gridPanel.setWidget(gridCon);

		BoxLayoutData ld = new BoxLayoutData(new Margins(5));
		ld.setFlex(3);

		HBoxLayoutContainer hb = new HBoxLayoutContainer(HBoxLayoutAlign.MIDDLE);
		hb.setHeight(50);
		hb.add(new FieldLabel(name, "运营商名称"), ld);
		hb.add(new FieldLabel(manager, "相关经理"), ld);
		vc.add(hb, vd);

		hb = new HBoxLayoutContainer(HBoxLayoutAlign.MIDDLE);
		hb.setHeight(50);
		hb.add(new FieldLabel(leader, "相关负责人"), ld);
		hb.add(new FieldLabel(new TextField(), "相关运维师"), ld);
		vc.add(hb, vd);

		vc.add(gridPanel, vd);
	}

	public void doEdit(final Project p) {
		gridCon.setProject(p);
		getRpc().getRemarks(p.getId(), new GetCallback<List<ProjectRemark>>() {
			@Override
			public void postGet(List<ProjectRemark> list) {
				p.setRemarks(list);
				driver.edit(p);
			}
		});
	}

	public void toAdd() {
		Project p = new Project();
		gridCon.setProject(p);
		p.setRemarks(new ArrayList<ProjectRemark>());
		driver.edit(p);
	}

	@Override
	public String getBaseHeadingText() {
		return "项目";
	}

	@Override
	protected Project flush() {
		return driver.flush();
	}

	private ProjectRpcClient getRpc() {
		return (ProjectRpcClient) this.rpc;
	}

}
