/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui.user.editor;

import xdata.etl.web.client.common.editer.EtlSimpleEditor;
import xdata.etl.web.client.rpc.EntityRpcCaller;
import xdata.etl.web.client.service.user.UserGroupService;
import xdata.etl.web.client.service.user.UserGroupServiceAsync;
import xdata.etl.web.client.ui.user.combox.UserGroupComBox;
import xdata.etl.web.shared.entity.user.User;
import xdata.etl.web.shared.entity.user.UserGroup;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.widget.core.client.event.ShowEvent;
import com.sencha.gxt.widget.core.client.event.ShowEvent.ShowHandler;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;

/**
 * @author XuehuiHe
 * @date 2013年8月12日
 * 
 */
public class UserEditor extends EtlSimpleEditor<Integer, User> {
	interface UserDriver extends SimpleBeanEditorDriver<User, UserEditor> {
	}

	public UserEditor() {
		super(GWT.<UserDriver> create(UserDriver.class));

		getRoot().addShowHandler(new ShowHandler() {
			@Override
			public void onShow(ShowEvent event) {
				userGroup.fireEvent(event);
			}
		});
	}

	TextField email;
	UserGroupComBox userGroup;

	@Override
	protected void initView() {
		super.initView();
		email = new TextField();
		userGroup = new UserGroupComBox(
				new EntityRpcCaller<Integer, UserGroup>(GWT
						.<UserGroupServiceAsync> create(UserGroupService.class)));

		layoutContainer.add(new FieldLabel(email, "email"), vd);
		layoutContainer.add(new FieldLabel(userGroup, "用户组"), vd);

	}

	@Override
	protected User newInstance() {
		return new User();
	}

}
