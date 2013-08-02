package com.voole.gxt.client.project.manager;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.voole.gxt.client.canna.editor.CannaSimpleEditor;
import com.voole.gxt.client.rpcclient.project.ProjectManagerRpcClient;
import com.voole.gxt.shared.entity.project.ProjectManager;

public class ManagerEditor extends CannaSimpleEditor<ProjectManager> {
	interface ManagerDriver extends
			SimpleBeanEditorDriver<ProjectManager, ManagerEditor> {
	}

	private ManagerDriver driver = GWT.create(ManagerDriver.class);

	TextField name = new TextField();
	TextField mobile = new TextField();
	TextField extNum = new TextField();
	TextField email = new TextField();

	public ManagerEditor() {
		this.rpc = new ProjectManagerRpcClient();

		vc.add(new FieldLabel(name, "姓名"), vd);
		vc.add(new FieldLabel(mobile, "手机号"), vd);
		vc.add(new FieldLabel(extNum, "分机号"), vd);
		vc.add(new FieldLabel(email, "email"), vd);
		driver.initialize(this);
	}

	@Override
	public void toAdd() {
		ProjectManager m = new ProjectManager();
		doEdit(m);
	}

	@Override
	public void doEdit(ProjectManager t) {
		driver.edit(t);
	}

	@Override
	public String getBaseHeadingText() {
		return "项目经理";
	}

	@Override
	protected ProjectManager flush() {
		return driver.flush();
	}
}
