/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui.hbasemeta;

import xdata.etl.web.client.common.gridcontainer.EtlGridContainer;
import xdata.etl.web.client.common.gridcontainer.EtlGridContainerBuilder;
import xdata.etl.web.client.service.hbasemeta.HbaseTableService;
import xdata.etl.web.client.service.hbasemeta.HbaseTableServiceAsync;
import xdata.etl.web.client.ui.CenterView;
import xdata.etl.web.client.ui.hbasemeta.editor.HbaseTableEditor;
import xdata.etl.web.client.ui.hbasemeta.grid.HbaseTableGrid;
import xdata.etl.web.shared.annotations.MenuToken;
import xdata.etl.web.shared.entity.hbasemeta.HbaseTable;

import com.google.gwt.core.client.GWT;

/**
 * @author XuehuiHe
 * @date 2013年8月15日
 */
@MenuToken(name = "表管理", token = "hbase_table")
public class HbaseTableView extends CenterView {
	public HbaseTableView() {
		super();

		HbaseTableGrid grid = new HbaseTableGrid();
		EtlGridContainer<Integer, HbaseTable> gridContainer = new EtlGridContainer<Integer, HbaseTable>(
				grid,
				GWT.<HbaseTableServiceAsync> create(HbaseTableService.class));

		EtlGridContainerBuilder<Integer, HbaseTable> builder = new EtlGridContainerBuilder<Integer, HbaseTable>(
				gridContainer);

		HbaseTableEditor editor = new HbaseTableEditor();
		editor.setRpcCaller(gridContainer.getRpcCaller());

		builder.setAddEditor(editor);
		builder.setUpdateEditor(editor);

		builder.build();

		con.setWidget(gridContainer);

	}
}
