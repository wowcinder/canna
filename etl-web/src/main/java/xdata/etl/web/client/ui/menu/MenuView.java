/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui.menu;

import xdata.etl.web.client.common.gridcontainer.EtlGridContainer;
import xdata.etl.web.client.common.gridcontainer.EtlGridContainerBuilder;
import xdata.etl.web.client.service.menu.MenuService;
import xdata.etl.web.client.service.menu.MenuServiceAsync;
import xdata.etl.web.client.ui.CenterView;
import xdata.etl.web.shared.annotations.MenuToken;
import xdata.etl.web.shared.entity.menu.Menu;

import com.google.gwt.core.shared.GWT;

/**
 * @author XuehuiHe
 * @date 2013年8月9日
 */
@MenuToken(name = "菜单管理", token = "menu_manager")
public class MenuView extends CenterView {
	public MenuView() {
		super();
		EtlGridContainer<Integer, Menu> gridContainer = new EtlGridContainer<Integer, Menu>(
				new MenuGrid(),
				(MenuServiceAsync) GWT.create(MenuService.class));
		EtlGridContainerBuilder<Integer, Menu> builder = new EtlGridContainerBuilder<Integer, Menu>(
				gridContainer);

		MenuEditor editor = new MenuEditor();
		editor.setParent(gridContainer);
		builder.setAddEditor(editor);
		builder.setUpdateEditor(editor);
		builder.setPaging(false);
		builder.build();
		con.setWidget(gridContainer);
	}

}
