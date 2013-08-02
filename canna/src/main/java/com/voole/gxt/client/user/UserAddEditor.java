package com.voole.gxt.client.user;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.voole.gxt.shared.entity.user.User;

public class UserAddEditor extends UserUpdateEditor {

	interface UserAddEditorDriver extends
			SimpleBeanEditorDriver<User, UserAddEditor> {

	}

	private UserAddEditorDriver driver;

	protected TextField password;

	public UserAddEditor() {
		super();
	}

	@SuppressWarnings("unchecked")
	protected void init() {
		driver = GWT.create(UserAddEditorDriver.class);
		password = new TextField();
		vc.add(new FieldLabel(email, "email"), vd);
		vc.add(new FieldLabel(password, "password"), vd);
		vc.add(new FieldLabel(name, "name"), vd);
		vc.add(new FieldLabel(mobile, "mobile"), vd);
		vc.add(new FieldLabel(extNum, "extNum"), vd);
		vc.add(new FieldLabel(group, "group"), vd);
		getDriver().initialize(this);
	}

	@SuppressWarnings("rawtypes")
	public SimpleBeanEditorDriver getDriver() {
		return this.driver;
	}

}
