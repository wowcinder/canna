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
import xdata.etl.web.client.ui.businessmeta.BusinessView.BusinessColumnTypeSelectWindow;
import xdata.etl.web.client.ui.businessmeta.editor.BusinessToHbaseTableMappingEditor;
import xdata.etl.web.client.ui.businessmeta.grid.BusinessColumnGrid.BusinessColGird;
import xdata.etl.web.client.ui.businessmeta.grid.CTypeBusinessColumnGrid;
import xdata.etl.web.shared.BusinessType.BusinessColumnType;
import xdata.etl.web.shared.entity.businessmeta.c.CTypeBusinessColumn;
import xdata.etl.web.shared.entity.businessmeta.c.CTypeBusinessToHbaseTableMapping;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
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
	RpcEntityGridContainer<CTypeBusinessColumn> gridContainer;

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
		final CTypeBusinessColumnAdapterEditor editor = new CTypeBusinessColumnAdapterEditor();
		RpcEntityGridContainerBuilder<Integer, CTypeBusinessColumn> gridContainerBuilder = new RpcEntityGridContainerBuilder<Integer, CTypeBusinessColumn>();
		gridContainerBuilder.setGrid(grid);
		gridContainerBuilder.setPaging(false);
		gridContainerBuilder.setAutoInitData(false);
		gridContainerBuilder
				.setDeleteAction(new DeleteAction<CTypeBusinessColumn>() {

					@Override
					protected void doDelete(List<CTypeBusinessColumn> list,
							GwtCallBack<Void> deleteCallback) {
						deleteCallback.call(null);
					}
				});
		gridContainerBuilder.setAddEditor(editor);
		gridContainerBuilder.setUpdateEditor(editor);
		gridContainerBuilder.setAddBtHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				BusinessColumnTypeSelectWindow selectWindow = new BusinessColumnTypeSelectWindow();
				selectWindow.setCallBack(new GwtCallBack<BusinessColumnType>() {
					@Override
					protected void _call(BusinessColumnType t) {
						editor.setType(t);
						editor.add();
					}
				});
				selectWindow.show();
			}
		});
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
