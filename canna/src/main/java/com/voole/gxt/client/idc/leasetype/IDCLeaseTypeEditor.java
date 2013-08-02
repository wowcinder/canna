package com.voole.gxt.client.idc.leasetype;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.NumberField;
import com.sencha.gxt.widget.core.client.form.NumberPropertyEditor.IntegerPropertyEditor;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.voole.gxt.client.canna.editor.CannaSimpleEditor;
import com.voole.gxt.client.rpcclient.idc.IDCLeaseTypeRpcClient;
import com.voole.gxt.shared.entity.idc.IDCLeaseType;

public class IDCLeaseTypeEditor extends CannaSimpleEditor<IDCLeaseType> {

	interface IDCLeaseTypeDriver extends
			SimpleBeanEditorDriver<IDCLeaseType, IDCLeaseTypeEditor> {

	}

	private IDCLeaseTypeDriver driver = GWT.create(IDCLeaseTypeDriver.class);

	NumberField<Integer> value;
	TextField name;

	public IDCLeaseTypeEditor(IDCLeaseTypeRpcClient rpc) {
		super();
		this.rpc = rpc;

		value = new NumberField<Integer>(new IntegerPropertyEditor());
		name = new TextField();
		vc.add(new FieldLabel(value, "value"), vd);
		vc.add(new FieldLabel(name, "name"), vd);
		driver.initialize(this);
	}

	@Override
	protected IDCLeaseType flush() {
		return driver.flush();
	}

	@Override
	public void toAdd() {
		IDCLeaseType t = new IDCLeaseType();
		driver.edit(t);
	}

	@Override
	public void doEdit(IDCLeaseType t) {
		driver.edit(t);
	}

	@Override
	public String getBaseHeadingText() {
		return "租赁方式";
	}

}
