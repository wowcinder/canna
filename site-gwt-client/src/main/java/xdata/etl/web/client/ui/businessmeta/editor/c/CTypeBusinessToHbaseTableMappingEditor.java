/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui.businessmeta.editor.c;

import java.util.List;

import xdata.etl.web.client.common.gridcontainer.RpcEntityGridContainer;
import xdata.etl.web.client.common.gridcontainer.RpcEntityGridContainerBuilder;
import xdata.etl.web.client.common.gridcontainer.RpcEntityGridContainerBuilder.DeleteAction;
import xdata.etl.web.client.gwt.GwtCallBack;
import xdata.etl.web.client.rpc.RpcCaller;
import xdata.etl.web.client.ui.businessmeta.combox.HbaseTableVersionCombox;
import xdata.etl.web.client.ui.businessmeta.editor.BusinessToHbaseTableMappingEditor;
import xdata.etl.web.client.ui.businessmeta.grid.BusinessColumnGrid.BusinessColGird;
import xdata.etl.web.client.ui.businessmeta.grid.CTypeBusinessColumnGrid;
import xdata.etl.web.client.ui.hbasemeta.editor.HbaseTableColumnEditor;
import xdata.etl.web.client.ui.hbasemeta.grid.HbaseTableColumnGrid;
import xdata.etl.web.shared.entity.businessmeta.c.CTypeBusinessToHbaseTableMapping;
import xdata.etl.web.shared.entity.hbasemeta.HbaseTableColumn;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.editor.client.Editor.Ignore;
import com.sencha.gxt.data.client.editor.ListStoreEditor;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextArea;
import com.sencha.gxt.widget.core.client.form.FormPanel.LabelAlign;

/**
 * @author XuehuiHe
 * @date 2013年9月4日
 */
public class CTypeBusinessToHbaseTableMappingEditor extends
		BusinessToHbaseTableMappingEditor<CTypeBusinessToHbaseTableMapping> {

	private static final CTypeBusinessToHbaseTableMappingDriver driver = GWT
			.create(CTypeBusinessToHbaseTableMappingDriver.class);

	interface CTypeBusinessToHbaseTableMappingDriver
			extends
			SimpleBeanEditorDriver<CTypeBusinessToHbaseTableMapping, CTypeBusinessToHbaseTableMappingEditor> {
	}

	@Ignore
	@SuppressWarnings("rawtypes")
	BusinessColGird grid;

	@Ignore
	RpcEntityGridContainer<HbaseTableColumn> gridContainer;

	public CTypeBusinessToHbaseTableMappingEditor(
			RpcCaller<Integer, CTypeBusinessToHbaseTableMapping> rpcCaller) {
		super(driver, rpcCaller);
	}

	@Override
	protected void initView() {
		super.initView();

		layoutContainer.add(
				new FieldLabel(hbaseTableVersions, "hbase_versions"), vd);
		layoutContainer.add(new FieldLabel(desc, "mapping"), vd);

		initGrid();

	}

	@SuppressWarnings("rawtypes")
	private void initGrid() {
		grid = (BusinessColGird) new CTypeBusinessColumnGrid().create();

		HbaseTableColumnEditor editor = new HbaseTableColumnEditor() {
			@Override
			protected void update(HbaseTableColumn v,
					GwtCallBack<HbaseTableColumn> callback) {
				callback.call(v);
			}

			@Override
			protected void save(HbaseTableColumn v,
					GwtCallBack<HbaseTableColumn> callback) {
				v.setVersion(getSelf());
				callback.call(v);
			}
		};

		RpcEntityGridContainerBuilder<Integer, HbaseTableColumn> gridContainerBuilder = new RpcEntityGridContainerBuilder<Integer, HbaseTableColumn>();
		gridContainerBuilder.setGrid(grid);
		gridContainerBuilder.setPaging(false);
		gridContainerBuilder.setAutoInitData(false);
		gridContainerBuilder
				.setDeleteAction(new DeleteAction<HbaseTableColumn>() {

					@Override
					protected void doDelete(List<HbaseTableColumn> list,
							GwtCallBack<Void> deleteCallback) {
						deleteCallback.call(null);
					}
				});
		gridContainerBuilder.setAddEditor(editor);
		gridContainerBuilder.setUpdateEditor(editor);
		gridContainer = gridContainerBuilder.create();
		gridContainer.setHeight(300);

		FieldLabel columnsLabel = new FieldLabel(gridContainer, "hbase字段");
		columnsLabel.setLabelAlign(LabelAlign.TOP);
		layoutContainer.add(columnsLabel, vd);

	}

	@Override
	protected CTypeBusinessToHbaseTableMapping newInstance() {
		CTypeBusinessToHbaseTableMapping mapping = new CTypeBusinessToHbaseTableMapping();
		return mapping;
	}

}
