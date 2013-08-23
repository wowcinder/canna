/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui.menu;

import xdata.etl.web.client.common.editer.RpcEntitySimpleEditor;
import xdata.etl.web.client.common.gridcontainer.SimpleRpcEntityGridContainer;
import xdata.etl.web.client.rpc.RpcCaller;
import xdata.etl.web.client.ui.CenterView;
import xdata.etl.web.client.ui.menu.editor.MenuEditor;
import xdata.etl.web.client.ui.menu.grid.MenuGrid;
import xdata.etl.web.shared.annotations.MenuToken;
import xdata.etl.web.shared.entity.menu.Menu;

/**
 * @author XuehuiHe
 * @date 2013年8月9日
 */
@MenuToken(name = "菜单管理", token = "menu_manager", group = "菜单管理")
public class MenuView extends SimpleRpcEntityGridContainer<Integer, Menu>
		implements CenterView {

	private static final MenuEditor editor = new MenuEditor();

	public MenuView() {
		super(new MenuGrid().create());
	}

	@Override
	protected RpcEntitySimpleEditor<Integer, Menu> getAddEditor() {
		return editor;
	}

	@Override
	protected RpcEntitySimpleEditor<Integer, Menu> getUpdateEditor() {
		return editor;
	}

	@Override
	protected RpcCaller<Integer, Menu> getRpcCaller() {
		return null;
	}

}
