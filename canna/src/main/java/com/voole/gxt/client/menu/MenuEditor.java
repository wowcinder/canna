package com.voole.gxt.client.menu;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.SimpleComboBox;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.voole.gxt.client.authority.combox.AuthorityRpcMethodComBox;
import com.voole.gxt.client.canna.editor.CannaSimpleEditor;
import com.voole.gxt.client.menu.combox.MenuGroupComBox;
import com.voole.gxt.client.rpcclient.menu.MenuRpcClient;
import com.voole.gxt.shared.entity.authority.AuthorityRpcMethod;
import com.voole.gxt.shared.entity.menu.Menu;
import com.voole.gxt.shared.entity.menu.MenuGroup;

public class MenuEditor extends CannaSimpleEditor<Menu> {

	interface MenuDriver extends SimpleBeanEditorDriver<Menu, MenuEditor> {
	}

	private MenuDriver driver = GWT.create(MenuDriver.class);
	TextField name = new TextField();
	TextField token = new TextField();
	SimpleComboBox<MenuGroup> menuGroup;
	SimpleComboBox<AuthorityRpcMethod> method;

	public MenuEditor() {
		super();
		this.menuGroup = new MenuGroupComBox();
		this.method = new AuthorityRpcMethodComBox();
		vc.add(new FieldLabel(name, "name"), vd);
		vc.add(new FieldLabel(token, "token"), vd);
		vc.add(new FieldLabel(menuGroup, "菜单组"), vd);
		vc.add(new FieldLabel(method, "所需权限"), vd);
		this.rpc = new MenuRpcClient();
		driver.initialize(this);
	}

	@Override
	public void toAdd() {
		Menu t = new Menu();
		doEdit(t);
	}

	@Override
	public void doEdit(Menu t) {
		driver.edit(t);
	}

	@Override
	public String getBaseHeadingText() {
		return "菜单";
	}

	@Override
	protected Menu flush() {
		return driver.flush();
	}

}
