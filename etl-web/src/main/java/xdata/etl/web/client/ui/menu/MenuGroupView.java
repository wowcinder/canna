/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui.menu;

import xdata.etl.web.client.common.gridcontainer.EtlGridContainer;
import xdata.etl.web.client.common.gridcontainer.EtlGridContainerBuilder;
import xdata.etl.web.client.service.menu.MenuGroupService;
import xdata.etl.web.client.service.menu.MenuGroupServiceAsync;
import xdata.etl.web.client.ui.CenterView;
import xdata.etl.web.client.ui.menu.editor.MenuGroupEditor;
import xdata.etl.web.client.ui.menu.grid.MenuGroupGrid;
import xdata.etl.web.shared.annotations.MenuToken;
import xdata.etl.web.shared.entity.menu.MenuGroup;

import com.google.gwt.core.shared.GWT;

/**
 * @author XuehuiHe
 * @date 2013年8月11日
 * 
 */
@MenuToken(name = "菜单组管理", token = "menu_group_manager")
public class MenuGroupView extends CenterView {
	public MenuGroupView() {
		super();
		EtlGridContainer<Integer, MenuGroup> gridContainer = new EtlGridContainer<Integer, MenuGroup>(
				new MenuGroupGrid(),
				(MenuGroupServiceAsync) GWT.create(MenuGroupService.class));
		EtlGridContainerBuilder<Integer, MenuGroup> builder = new EtlGridContainerBuilder<Integer, MenuGroup>(
				gridContainer);

		MenuGroupEditor editor = new MenuGroupEditor();
		editor.setParent(gridContainer);
		builder.setAddEditor(editor);
		builder.setUpdateEditor(editor);
		builder.setPaging(false);
		builder.build();
		con.setWidget(gridContainer);
	}
}
