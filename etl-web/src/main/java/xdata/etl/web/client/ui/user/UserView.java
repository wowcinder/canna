/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui.user;

import com.google.gwt.core.shared.GWT;

import xdata.etl.web.client.common.gridcontainer.EtlGridContainer;
import xdata.etl.web.client.common.gridcontainer.EtlGridContainerBuilder;
import xdata.etl.web.client.service.user.UserService;
import xdata.etl.web.client.service.user.UserServiceAsync;
import xdata.etl.web.client.ui.CenterView;
import xdata.etl.web.client.ui.user.editor.UserEditor;
import xdata.etl.web.client.ui.user.grid.UserGrid;
import xdata.etl.web.shared.annotations.MenuToken;
import xdata.etl.web.shared.entity.user.User;

/**
 * @author XuehuiHe
 * @date 2013年8月12日
 * 
 */

@MenuToken(name = "用户管理", token = "user_manager")
public class UserView extends CenterView {
	public UserView() {
		super();

		UserGrid grid = new UserGrid();
		EtlGridContainer<Integer, User> gridContainer = new EtlGridContainer<Integer, User>(
				grid, GWT.<UserServiceAsync> create(UserService.class));

		EtlGridContainerBuilder<Integer, User> builder = new EtlGridContainerBuilder<Integer, User>(
				gridContainer);

		UserEditor editor = new UserEditor();
		editor.setParent(gridContainer);

		builder.setAddEditor(editor);
		builder.setUpdateEditor(editor);

		builder.build();
		con.setWidget(gridContainer);
	}

}
