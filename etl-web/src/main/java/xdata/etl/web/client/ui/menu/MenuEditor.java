/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui.menu;

import xdata.etl.web.client.common.editer.EtlSimpleEditor;
import xdata.etl.web.shared.entity.menu.Menu;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;

/**
 * @author XuehuiHe
 * @date 2013年8月10日
 * 
 */
public class MenuEditor extends EtlSimpleEditor<Integer, Menu> {

	interface MenuDriver extends SimpleBeanEditorDriver<Menu, MenuEditor> {
	}

	private static MenuDriver menuDriver = GWT.create(MenuDriver.class);

	TextField name;
	TextField token;

	public MenuEditor() {
		super(menuDriver);

	}

	@Override
	protected void initView() {
		super.initView();
		name = new TextField();
		token = new TextField();
		layoutContainer.add(new FieldLabel(name, "name"), vd);
		layoutContainer.add(new FieldLabel(token, "token"), vd);
	}

	@Override
	protected Menu newInstance() {
		return new Menu();
	}

}
