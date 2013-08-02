package com.voole.gxt.client.project.partner;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.voole.gxt.client.canna.editor.CannaSimpleEditor;
import com.voole.gxt.client.rpcclient.project.ProjectPartnerRpcClient;
import com.voole.gxt.shared.entity.project.ProjectPartner;

public class PartnerEditor extends CannaSimpleEditor<ProjectPartner> {

	interface PartnerDriver extends
			SimpleBeanEditorDriver<ProjectPartner, PartnerEditor> {
	}

	private PartnerDriver driver = GWT.create(PartnerDriver.class);

	TextField name = new TextField();
	TextField position = new TextField();
	TextField mobile = new TextField();
	TextField email = new TextField();
	TextField telphone = new TextField();
	TextField remark = new TextField();

	public PartnerEditor() {
		super();
		this.rpc = new ProjectPartnerRpcClient();
		vc.add(new FieldLabel(name, "姓名"), vd);
		vc.add(new FieldLabel(position, "职务"), vd);
		vc.add(new FieldLabel(mobile, "手机号"), vd);
		vc.add(new FieldLabel(telphone, "座机号"), vd);
		vc.add(new FieldLabel(email, "email"), vd);
		vc.add(new FieldLabel(remark, "备注"), vd);

		driver.initialize(this);
	}

	@Override
	public void toAdd() {
		ProjectPartner p = new ProjectPartner();
		doEdit(p);

	}

	@Override
	public void doEdit(ProjectPartner t) {
		driver.edit(t);
	}

	@Override
	public String getBaseHeadingText() {
		return "合作伙伴";
	}

	@Override
	protected ProjectPartner flush() {
		return driver.flush();
	}
}
