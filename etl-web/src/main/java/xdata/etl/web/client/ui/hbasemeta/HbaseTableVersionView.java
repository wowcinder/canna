/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui.hbasemeta;

import xdata.etl.web.client.common.gridcontainer.EtlGridContainer;
import xdata.etl.web.client.common.gridcontainer.EtlGridContainerBuilder;
import xdata.etl.web.client.service.hbasemeta.HbaseTableVersionService;
import xdata.etl.web.client.service.hbasemeta.HbaseTableVersionServiceAsync;
import xdata.etl.web.client.ui.CenterView;
import xdata.etl.web.client.ui.hbasemeta.editor.HbaseTableVersionEditor;
import xdata.etl.web.client.ui.hbasemeta.grid.HbaseTableVersionGrid;
import xdata.etl.web.shared.annotations.MenuToken;
import xdata.etl.web.shared.entity.hbasemeta.HbaseTableVersion;

import com.google.gwt.core.client.GWT;

/**
 * @author XuehuiHe
 * @date 2013年8月15日
 */
@MenuToken(name = "表版本管理", token = "hbase_table_version")
public class HbaseTableVersionView extends CenterView {
	public HbaseTableVersionView() {
		super();

		HbaseTableVersionGrid grid = new HbaseTableVersionGrid();
		EtlGridContainer<Integer, HbaseTableVersion> gridContainer = new EtlGridContainer<Integer, HbaseTableVersion>(
				grid,
				GWT.<HbaseTableVersionServiceAsync> create(HbaseTableVersionService.class));

		EtlGridContainerBuilder<Integer, HbaseTableVersion> builder = new EtlGridContainerBuilder<Integer, HbaseTableVersion>(
				gridContainer);

		HbaseTableVersionEditor editor = new HbaseTableVersionEditor();
		editor.setRpcCaller(gridContainer.getRpcCaller());

		builder.setAddEditor(editor);
		builder.setUpdateEditor(editor);

		builder.build();

		con.setWidget(gridContainer);

	}
}
