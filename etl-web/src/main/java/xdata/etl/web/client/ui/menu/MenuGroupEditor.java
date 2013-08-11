/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui.menu;

import xdata.etl.web.client.common.editer.EtlSimpleEditor;
import xdata.etl.web.shared.entity.menu.MenuGroup;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;

/**
 * @author XuehuiHe
 * @date 2013年8月11日
 * 
 */
public class MenuGroupEditor extends EtlSimpleEditor<Integer, MenuGroup> {
	interface MenuGroupDriver extends
			SimpleBeanEditorDriver<MenuGroup, MenuGroupEditor> {
	}

	private static MenuGroupDriver menuGroupDriver = GWT
			.create(MenuGroupDriver.class);

	TextField name;

	public MenuGroupEditor() {
		super(menuGroupDriver);
	}

	@Override
	protected void initView() {
		super.initView();
		name = new TextField();
		layoutContainer.add(new FieldLabel(name, "name"), vd);
	}

	@Override
	protected MenuGroup newInstance() {
		return new MenuGroup();
	}

}
