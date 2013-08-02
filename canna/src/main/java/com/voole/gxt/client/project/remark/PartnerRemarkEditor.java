package com.voole.gxt.client.project.remark;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextArea;
import com.voole.gxt.client.canna.editor.CannaSimpleEditor;
import com.voole.gxt.shared.entity.project.ProjectRemark;

public class PartnerRemarkEditor extends CannaSimpleEditor<ProjectRemark> {

	interface PartnerRemarkDriver extends
			SimpleBeanEditorDriver<ProjectRemark, PartnerRemarkEditor> {
	}

	private PartnerRemarkDriver driver = GWT.create(PartnerRemarkDriver.class);

	TextArea remark = new TextArea();

	public PartnerRemarkEditor() {
		super();
		vc.add(new FieldLabel(remark, "备注2"), vd);
		driver.initialize(this);
	}

	@Override
	protected void doAdd() {

	}

	@Override
	protected void doUpdate() {
		postUpdate(flush());
	}

	@Override
	public void toAdd() {

	}

	@Override
	public void doEdit(ProjectRemark t) {
		driver.edit(t);
	}

	@Override
	public String getBaseHeadingText() {
		return "项目备注";
	}

	@Override
	protected ProjectRemark flush() {
		return driver.flush();
	}

}
