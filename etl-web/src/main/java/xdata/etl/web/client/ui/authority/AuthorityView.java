/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui.authority;

import xdata.etl.web.client.common.gridcontainer.EtlGridContainer;
import xdata.etl.web.client.common.gridcontainer.EtlGridContainerBuilder;
import xdata.etl.web.client.service.authority.AuthorityService;
import xdata.etl.web.client.service.authority.AuthorityServiceAsync;
import xdata.etl.web.client.ui.CenterView;
import xdata.etl.web.client.ui.authority.editor.AuthorityEditor;
import xdata.etl.web.client.ui.authority.grid.AuthorityGrid;
import xdata.etl.web.shared.annotations.MenuToken;
import xdata.etl.web.shared.entity.authority.Authority;

import com.google.gwt.core.shared.GWT;

/**
 * @author XuehuiHe
 * @date 2013年8月12日
 * 
 */
@MenuToken(name = "权限管理", token = "authority_manager")
public class AuthorityView extends CenterView {
	public AuthorityView() {
		super();

		EtlGridContainer<Integer, Authority> gridContainer = new EtlGridContainer<Integer, Authority>(
				new AuthorityGrid(),
				GWT.<AuthorityServiceAsync> create(AuthorityService.class));

		EtlGridContainerBuilder<Integer, Authority> builder = new EtlGridContainerBuilder<Integer, Authority>(
				gridContainer);

		AuthorityEditor editor = new AuthorityEditor();
		editor.setParent(gridContainer);
		builder.setAddEditor(editor);
		builder.setUpdateEditor(editor);
		builder.build();
		con.setWidget(gridContainer);
	}

}
