package com.voole.gxt.client.user;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.voole.gxt.client.canna.editor.CannaSimpleEditor;
import com.voole.gxt.client.rpcclient.user.UserRpcClient;
import com.voole.gxt.client.user.combox.UserGroupComBox;
import com.voole.gxt.shared.entity.user.User;

public class UserUpdateEditor extends CannaSimpleEditor<User> {

	interface UserUpdateEditorDriver extends
			SimpleBeanEditorDriver<User, UserUpdateEditor> {

	}

	private UserUpdateEditorDriver driver = GWT
			.create(UserUpdateEditorDriver.class);

	protected UserGroupComBox group = new UserGroupComBox();
	protected TextField email = new TextField();
	protected TextField name = new TextField();
	protected TextField mobile = new TextField();
	protected TextField extNum = new TextField();

	public UserUpdateEditor() {
		this.rpc = new UserRpcClient();
		init();
	}

	@SuppressWarnings("unchecked")
	protected void init() {
		vc.add(new FieldLabel(email, "email"), vd);
		vc.add(new FieldLabel(name, "name"), vd);
		vc.add(new FieldLabel(mobile, "mobile"), vd);
		vc.add(new FieldLabel(extNum, "extNum"), vd);
		vc.add(new FieldLabel(group, "group"), vd);
		getDriver().initialize(this);
	}

	@Override
	protected User flush() {
		return (User) getDriver().flush();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void toAdd() {
		User t = new User();
		getDriver().edit(t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void doEdit(User t) {
		getDriver().edit(t);
	}

	@Override
	public String getBaseHeadingText() {
		return "用户";
	}

	@SuppressWarnings("rawtypes")
	public SimpleBeanEditorDriver getDriver() {
		return this.driver;
	}

}
