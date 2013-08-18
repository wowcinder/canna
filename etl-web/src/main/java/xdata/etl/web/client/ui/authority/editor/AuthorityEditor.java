/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui.authority.editor;

import xdata.etl.web.client.common.editer.RpcEntitySimpleEditor;
import xdata.etl.web.client.service.ServiceUtil;
import xdata.etl.web.shared.entity.authority.Authority;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;

/**
 * @author XuehuiHe
 * @date 2013年8月12日
 * 
 */
public class AuthorityEditor extends RpcEntitySimpleEditor<Integer, Authority> {

	interface AuthorityDriver extends
			SimpleBeanEditorDriver<Authority, AuthorityEditor> {
	}

	TextField name;

	public AuthorityEditor() {
		super(GWT.<AuthorityDriver> create(AuthorityDriver.class), "权限",
				ServiceUtil.AuthorityRpcCaller);
	}

	@Override
	protected void initView() {
		super.initView();
		name = new TextField();
		layoutContainer.add(new FieldLabel(name, "name"), vd);
	}

	@Override
	protected Authority newInstance() {
		return new Authority();
	}
}
