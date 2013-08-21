/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui.user;

import xdata.etl.web.client.ServiceUtil;
import xdata.etl.web.client.common.editer.RpcEntitySimpleEditor;
import xdata.etl.web.client.common.gridcontainer.SimpleRpcEntityGridContainer;
import xdata.etl.web.client.rpc.RpcCaller;
import xdata.etl.web.client.ui.CenterView;
import xdata.etl.web.client.ui.user.editor.UserGroupEditor;
import xdata.etl.web.client.ui.user.grid.UserGroupGrid;
import xdata.etl.web.shared.annotations.MenuToken;
import xdata.etl.web.shared.entity.user.UserGroup;

/**
 * @author XuehuiHe
 * @date 2013年8月12日
 * 
 */
@MenuToken(name = "用户组管理", token = "user_group_manager")
public class UserGroupView extends
		SimpleRpcEntityGridContainer<Integer, UserGroup> implements CenterView {

	private static final UserGroupEditor editor = new UserGroupEditor();

	public UserGroupView() {
		super(new UserGroupGrid().create());
	}

	@Override
	protected RpcEntitySimpleEditor<Integer, UserGroup> getAddEditor() {
		return editor;
	}

	@Override
	protected RpcEntitySimpleEditor<Integer, UserGroup> getUpdateEditor() {
		return editor;
	}

	@Override
	protected RpcCaller<Integer, UserGroup> getRpcCaller() {
		return ServiceUtil.UserGroupRpcCaller;
	}
}
