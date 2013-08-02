package com.voole.gxt.client.project.leader;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.voole.gxt.client.canna.editor.CannaSimpleEditor;
import com.voole.gxt.client.rpcclient.project.ProjectLeaderRpcClient;
import com.voole.gxt.shared.entity.project.ProjectLeader;

public class LeaderEditor extends CannaSimpleEditor<ProjectLeader> {
	interface LeaderDriver extends SimpleBeanEditorDriver<ProjectLeader, LeaderEditor> {
	}

	private LeaderDriver driver = GWT.create(LeaderDriver.class);

	TextField name = new TextField();
	TextField mobile = new TextField();
	TextField extNum = new TextField();
	TextField email = new TextField();

	public LeaderEditor() {
		super();
		vc.add(new FieldLabel(name, "姓名"), vd);
		vc.add(new FieldLabel(mobile, "手机号"), vd);
		vc.add(new FieldLabel(extNum, "分机号"), vd);
		vc.add(new FieldLabel(email, "email"), vd);

		this.rpc = new ProjectLeaderRpcClient();

		driver.initialize(this);
	}

	@Override
	public void toAdd() {
		ProjectLeader l = new ProjectLeader();
		doEdit(l);
	}

	@Override
	public void doEdit(ProjectLeader t) {
		driver.edit(t);
	}

	@Override
	public String getBaseHeadingText() {
		return "负责人";
	}

	@Override
	protected ProjectLeader flush() {
		return driver.flush();
	}

}
