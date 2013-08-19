/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui.menu;

import xdata.etl.web.client.common.editer.RpcEntitySimpleEditor;
import xdata.etl.web.client.common.gridcontainer.SimpleRpcEntityGridContainer;
import xdata.etl.web.client.rpc.EntityRpcCaller;
import xdata.etl.web.client.service.ServiceUtil;
import xdata.etl.web.client.ui.CenterView;
import xdata.etl.web.client.ui.menu.editor.MenuGroupEditor;
import xdata.etl.web.client.ui.menu.grid.MenuGroupGrid;
import xdata.etl.web.shared.annotations.MenuToken;
import xdata.etl.web.shared.entity.menu.MenuGroup;

/**
 * @author XuehuiHe
 * @date 2013年8月11日
 * 
 */
@MenuToken(name = "菜单组管理", token = "menu_group_manager")
public class MenuGroupView extends
		SimpleRpcEntityGridContainer<Integer, MenuGroup> implements CenterView {

	private static final MenuGroupEditor editor = new MenuGroupEditor();

	public MenuGroupView() {
		super(new MenuGroupGrid().create());
	}

	@Override
	protected RpcEntitySimpleEditor<Integer, MenuGroup> getAddEditor() {
		return editor;
	}

	@Override
	protected RpcEntitySimpleEditor<Integer, MenuGroup> getUpdateEditor() {
		return editor;
	}

	@Override
	protected EntityRpcCaller<Integer, MenuGroup> getRpcCaller() {
		return ServiceUtil.MenuGroupRpcCaller;
	}
}
