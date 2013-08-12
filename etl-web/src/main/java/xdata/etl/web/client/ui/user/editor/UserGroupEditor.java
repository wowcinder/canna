/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui.user.editor;

import xdata.etl.web.client.common.editer.EtlSimpleEditor;
import xdata.etl.web.shared.entity.user.UserGroup;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;

/**
 * @author XuehuiHe
 * @date 2013年8月12日
 * 
 */
public class UserGroupEditor extends EtlSimpleEditor<Integer, UserGroup> {
	interface UserGroupDriver extends
			SimpleBeanEditorDriver<UserGroup, UserGroupEditor> {
	}

	TextField name;

	public UserGroupEditor() {
		super(GWT.<UserGroupDriver> create(UserGroupDriver.class));
	}

	@Override
	protected UserGroup newInstance() {
		return new UserGroup();
	}

	protected void initView() {
		super.initView();
		name = new TextField();
		layoutContainer.add(new FieldLabel(name, "name"), vd);
	};

}
