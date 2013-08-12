/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui.user;

import xdata.etl.web.client.common.gridcontainer.EtlGridContainer;
import xdata.etl.web.client.common.gridcontainer.EtlGridContainerBuilder;
import xdata.etl.web.client.service.user.UserGroupService;
import xdata.etl.web.client.service.user.UserGroupServiceAsync;
import xdata.etl.web.client.ui.CenterView;
import xdata.etl.web.client.ui.user.editor.UserGroupEditor;
import xdata.etl.web.client.ui.user.grid.UserGroupGrid;
import xdata.etl.web.shared.annotations.MenuToken;
import xdata.etl.web.shared.entity.user.UserGroup;

import com.google.gwt.core.shared.GWT;

/**
 * @author XuehuiHe
 * @date 2013年8月12日
 * 
 */
@MenuToken(name = "用户组管理", token = "user_group_manager")
public class UserGroupView extends CenterView {
	public UserGroupView() {
		super();

		UserGroupGrid grid = new UserGroupGrid();
		EtlGridContainer<Integer, UserGroup> gridContainer = new EtlGridContainer<Integer, UserGroup>(
				grid,
				GWT.<UserGroupServiceAsync> create(UserGroupService.class));
		EtlGridContainerBuilder<Integer, UserGroup> builder = new EtlGridContainerBuilder<Integer, UserGroup>(
				gridContainer);

		UserGroupEditor editor = new UserGroupEditor();
		editor.setParent(gridContainer);

		builder.setAddEditor(editor);
		builder.setUpdateEditor(editor);
		builder.build();

		con.setWidget(gridContainer);
	}
}
