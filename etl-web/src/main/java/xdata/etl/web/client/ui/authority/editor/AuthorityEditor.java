/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui.authority.editor;

import xdata.etl.web.client.common.editer.EtlSimpleEditor;
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
public class AuthorityEditor extends EtlSimpleEditor<Integer, Authority> {

	interface AuthorityDriver extends
			SimpleBeanEditorDriver<Authority, AuthorityEditor> {

	}

	TextField name;
	public AuthorityEditor() {
		super(GWT.<AuthorityDriver> create(AuthorityDriver.class));
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
