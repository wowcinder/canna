/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui.authority;

import xdata.etl.web.client.common.gridcontainer.EtlGridContainer;
import xdata.etl.web.client.common.gridcontainer.EtlGridContainerBuilder;
import xdata.etl.web.client.service.authority.AuthorityGroupService;
import xdata.etl.web.client.service.authority.AuthorityGroupServiceAsync;
import xdata.etl.web.client.ui.CenterView;
import xdata.etl.web.client.ui.authority.editor.AuthorityGroupEditor;
import xdata.etl.web.client.ui.authority.grid.AuthorityGroupGrid;
import xdata.etl.web.shared.annotations.MenuToken;
import xdata.etl.web.shared.entity.authority.AuthorityGroup;

import com.google.gwt.core.shared.GWT;

/**
 * @author XuehuiHe
 * @date 2013年8月12日
 * 
 */
@MenuToken(name = "权限组管理", token = "authority_group_manager")
public class AuthorityGroupView extends CenterView {

	public AuthorityGroupView() {
		super();

		EtlGridContainer<Integer, AuthorityGroup> gridContainer = new EtlGridContainer<Integer, AuthorityGroup>(
				new AuthorityGroupGrid(),
				GWT.<AuthorityGroupServiceAsync> create(AuthorityGroupService.class));

		EtlGridContainerBuilder<Integer, AuthorityGroup> builder = new EtlGridContainerBuilder<Integer, AuthorityGroup>(
				gridContainer);

		AuthorityGroupEditor editor = new AuthorityGroupEditor();
		editor.setParent(gridContainer);
		builder.setAddEditor(editor);
		builder.setUpdateEditor(editor);
		builder.build();
		con.setWidget(gridContainer);
	}

}
