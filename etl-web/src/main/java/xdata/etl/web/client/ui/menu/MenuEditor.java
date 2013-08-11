/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui.menu;

import xdata.etl.web.client.common.editer.EtlSimpleEditor;
import xdata.etl.web.client.rpc.EntityRpcCaller;
import xdata.etl.web.client.service.menu.MenuGroupService;
import xdata.etl.web.client.service.menu.MenuGroupServiceAsync;
import xdata.etl.web.shared.entity.menu.Menu;
import xdata.etl.web.shared.entity.menu.MenuGroup;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.widget.core.client.event.ShowEvent;
import com.sencha.gxt.widget.core.client.event.ShowEvent.ShowHandler;
import com.sencha.gxt.widget.core.client.form.ComboBox;
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
	ComboBox<MenuGroup> menuGroup;

	public MenuEditor() {
		super(menuDriver);
		getRoot().addShowHandler(new ShowHandler() {
			@Override
			public void onShow(ShowEvent event) {
				menuGroup.fireEvent(event);
			}
		});
	}

	@Override
	protected void initView() {
		super.initView();
		name = new TextField();
		token = new TextField();
		menuGroup = new MenuGroupComBox(
				new EntityRpcCaller<Integer, MenuGroup>(GWT
						.<MenuGroupServiceAsync> create(MenuGroupService.class)));
		layoutContainer.add(new FieldLabel(name, "name"), vd);
		layoutContainer.add(new FieldLabel(token, "token"), vd);
		layoutContainer.add(new FieldLabel(menuGroup, "菜单组"), vd);
	}

	@Override
	protected Menu newInstance() {
		return new Menu();
	}

}
