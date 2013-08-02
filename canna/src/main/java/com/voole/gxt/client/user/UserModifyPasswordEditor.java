package com.voole.gxt.client.user;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.voole.gxt.client.canna.editor.CannaSimpleEditor;
import com.voole.gxt.client.rpcclient.user.UserRpcClient;
import com.voole.gxt.shared.entity.user.User;

public class UserModifyPasswordEditor extends CannaSimpleEditor<User> {

	interface UserModifyPasswordEditorDriver extends
			SimpleBeanEditorDriver<User, UserModifyPasswordEditor> {

	}

	private UserModifyPasswordEditorDriver driver = GWT
			.create(UserModifyPasswordEditorDriver.class);
	TextField password = new TextField();

	public UserModifyPasswordEditor() {
		vc.add(new FieldLabel(password, "password"), vd);
		this.rpc = new UserRpcClient();
		driver.initialize(this);
	}

	@Override
	protected User flush() {
		return driver.flush();
	}

	@Override
	public void toAdd() {

	}

	@Override
	protected void doUpdate() {
		User t = flush();
		getRpc().modifyPassword(t, this);
	}

	@Override
	public void doEdit(User t) {
		t.setPassword("");
		driver.edit(t);
	}

	@Override
	public String getUpdateHeadingText() {
		return "修改密码";
	}

	@Override
	public String getBaseHeadingText() {
		return null;
	}

	private UserRpcClient getRpc() {
		return (UserRpcClient) this.rpc;
	}

}
