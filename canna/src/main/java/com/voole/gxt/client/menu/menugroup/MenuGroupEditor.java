package com.voole.gxt.client.menu.menugroup;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.voole.gxt.client.canna.editor.CannaSimpleEditor;
import com.voole.gxt.client.rpcclient.menu.MenuGroupRpcClient;
import com.voole.gxt.shared.entity.menu.MenuGroup;

public class MenuGroupEditor extends CannaSimpleEditor<MenuGroup> {
	interface MenuGroupDriver extends
			SimpleBeanEditorDriver<MenuGroup, MenuGroupEditor> {
	}

	private MenuGroupDriver driver = GWT.create(MenuGroupDriver.class);
	TextField name = new TextField();

	public MenuGroupEditor() {
		super();
		this.rpc = new MenuGroupRpcClient();
		vc.add(new FieldLabel(name, "分组名称"), vd);
		driver.initialize(this);
	}

	@Override
	public void toAdd() {
		MenuGroup m = new MenuGroup();
		driver.edit(m);
	}

	@Override
	public void doEdit(MenuGroup t) {
		driver.edit(t);
	}

	@Override
	public String getBaseHeadingText() {
		return "菜单组";
	}

	@Override
	protected MenuGroup flush() {
		return driver.flush();
	}

}
