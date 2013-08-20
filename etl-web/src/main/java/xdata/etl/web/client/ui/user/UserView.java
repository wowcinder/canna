/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui.user;

import xdata.etl.web.client.annotations.MenuToken;
import xdata.etl.web.client.common.editer.RpcEntitySimpleEditor;
import xdata.etl.web.client.common.gridcontainer.SimpleRpcEntityGridContainer;
import xdata.etl.web.client.rpc.EntityRpcCaller;
import xdata.etl.web.client.service.ServiceUtil;
import xdata.etl.web.client.ui.CenterView;
import xdata.etl.web.client.ui.user.editor.UserEditor;
import xdata.etl.web.client.ui.user.grid.UserGrid;
import xdata.etl.web.shared.entity.user.User;

/**
 * @author XuehuiHe
 * @date 2013年8月12日
 * 
 */

@MenuToken(name = "用户管理", token = "user_manager")
public class UserView extends SimpleRpcEntityGridContainer<Integer, User>
		implements CenterView {

	private static final UserEditor editor = new UserEditor();

	public UserView() {
		super(new UserGrid().create());
	}

	@Override
	protected RpcEntitySimpleEditor<Integer, User> getAddEditor() {
		return editor;
	}

	@Override
	protected RpcEntitySimpleEditor<Integer, User> getUpdateEditor() {
		return editor;
	}

	@Override
	protected EntityRpcCaller<Integer, User> getRpcCaller() {
		return ServiceUtil.UserRpcCaller;
	}

}
