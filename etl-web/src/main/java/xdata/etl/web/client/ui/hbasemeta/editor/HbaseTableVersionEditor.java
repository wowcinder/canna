/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui.hbasemeta.editor;

import java.util.ArrayList;
import java.util.List;

import xdata.etl.web.client.ServiceUtil;
import xdata.etl.web.client.common.editer.RpcEntitySimpleEditor;
import xdata.etl.web.client.common.gridcontainer.RpcEntityGridContainerBuilder;
import xdata.etl.web.client.common.gridcontainer.RpcEntityGridContainerBuilder.DeleteAction;
import xdata.etl.web.client.gwt.GwtCallBack;
import xdata.etl.web.client.ui.hbasemeta.combox.HbaseTableComBox;
import xdata.etl.web.client.ui.hbasemeta.grid.HbaseTableColumnGrid;
import xdata.etl.web.shared.entity.hbasemeta.HbaseTableColumn;
import xdata.etl.web.shared.entity.hbasemeta.HbaseTableVersion;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.ui.TextArea;
import com.sencha.gxt.data.client.editor.ListStoreEditor;
import com.sencha.gxt.widget.core.client.event.ShowEvent;
import com.sencha.gxt.widget.core.client.event.ShowEvent.ShowHandler;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.FormPanel.LabelAlign;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.Grid;

/**
 * @author XuehuiHe
 * @date 2013年8月15日
 */
public class HbaseTableVersionEditor extends
		RpcEntitySimpleEditor<Integer, HbaseTableVersion> {

	interface HbaseTableVersionDriver extends
			SimpleBeanEditorDriver<HbaseTableVersion, HbaseTableVersionEditor> {

	}

	public HbaseTableVersionEditor() {
		super(
				GWT.<HbaseTableVersionDriver> create(HbaseTableVersionDriver.class),
				"版本", ServiceUtil.HbaseTableVersionRpcCaller);

		getRoot().addShowHandler(new ShowHandler() {

			@Override
			public void onShow(ShowEvent event) {
				table.fireEvent(event);
			}
		});
	}

	HbaseTableComBox table;
	TextField version;
	TextArea desc;
	ListStoreEditor<HbaseTableColumn> columns;
	@Ignore
	Grid<HbaseTableColumn> grid;
	@Ignore
	HbaseTableVersion self;

	@Override
	protected void initView() {
		super.initView();
		table = new HbaseTableComBox();
		version = new TextField();
		desc = new TextArea();

		version.setValue("");

		layoutContainer.add(new FieldLabel(table, "表"), vd);
		layoutContainer.add(new FieldLabel(version, "版本"), vd);
		layoutContainer.add(new FieldLabel(desc, "描述"), vd);

		initGrid();
	}

	private void initGrid() {
		grid = new HbaseTableColumnGrid().create();
		columns = new ListStoreEditor<HbaseTableColumn>(grid.getStore());

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

		FieldLabel columnsLabel = new FieldLabel(gridContainerBuilder.create(),
				"字段");
		columnsLabel.setLabelAlign(LabelAlign.TOP);
		layoutContainer.add(columnsLabel, vd);

	}

	@Override
	protected void _edit(HbaseTableVersion v) {
		setSelf(v);
		super._edit(v);
	}

	@Override
	protected HbaseTableVersion newInstance() {
		HbaseTableVersion v = new HbaseTableVersion();
		v.setColumns(new ArrayList<HbaseTableColumn>());
		return v;
	}

	public HbaseTableVersion getSelf() {
		return self;
	}

	public void setSelf(HbaseTableVersion self) {
		this.self = self;
	}

}
