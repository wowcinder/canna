/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui.authority.editor;

import xdata.etl.web.client.common.editer.RpcEntitySimpleEditor;
import xdata.etl.web.client.service.ServiceUtil;
import xdata.etl.web.shared.entity.authority.AuthorityGroup;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;

/**
 * @author XuehuiHe
 * @date 2013年8月12日
 * 
 */
public class AuthorityGroupEditor extends
		RpcEntitySimpleEditor<Integer, AuthorityGroup> {
	interface AuthorityGroupDriver extends
			SimpleBeanEditorDriver<AuthorityGroup, AuthorityGroupEditor> {
	}

	TextField name;

	public AuthorityGroupEditor() {
		super(GWT.<AuthorityGroupDriver> create(AuthorityGroupDriver.class),
				"权限组", ServiceUtil.AuthorityGroupRpcCaller);
	}

	@Override
	protected void initView() {
		super.initView();
		name = new TextField();
		layoutContainer.add(new FieldLabel(name, "name"), vd);
	}

	@Override
	protected AuthorityGroup newInstance() {
		return new AuthorityGroup();
	}

}
