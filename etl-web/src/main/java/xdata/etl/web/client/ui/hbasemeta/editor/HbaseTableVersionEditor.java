/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui.hbasemeta.editor;

import java.util.ArrayList;
import java.util.List;

import xdata.etl.web.client.common.editer.EtlSimpleEditor;
import xdata.etl.web.client.common.gridcontainer.EtlGridContainer;
import xdata.etl.web.client.common.gridcontainer.EtlGridContainerBuilder;
import xdata.etl.web.client.gwt.GwtCallBack;
import xdata.etl.web.client.ui.hbasemeta.combox.HbaseTableComBox;
import xdata.etl.web.client.ui.hbasemeta.grid.HbaseTableColumnGrid;
import xdata.etl.web.shared.entity.hbasemeta.HbaseTableColumn;
import xdata.etl.web.shared.entity.hbasemeta.HbaseTableVersion;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.ui.TextArea;
import com.sencha.gxt.data.client.editor.ListStoreEditor;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.event.ShowEvent;
import com.sencha.gxt.widget.core.client.event.ShowEvent.ShowHandler;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.FormPanel.LabelAlign;
import com.sencha.gxt.widget.core.client.form.TextField;

/**
 * @author XuehuiHe
 * @date 2013年8月15日
 */
public class HbaseTableVersionEditor extends
		EtlSimpleEditor<Integer, HbaseTableVersion> {

	interface HbaseTableVersionDriver extends
			SimpleBeanEditorDriver<HbaseTableVersion, HbaseTableVersionEditor> {

	}

	public HbaseTableVersionEditor() {
		super(
				GWT.<HbaseTableVersionDriver> create(HbaseTableVersionDriver.class));

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
	HbaseTableColumnGrid grid;
	@Ignore
	HbaseTableVersion self;

	@Override
	protected void initView() {
		super.initView();
		table = new HbaseTableComBox();
		version = new TextField();
		desc = new TextArea();

		layoutContainer.add(new FieldLabel(table, "表"), vd);
		layoutContainer.add(new FieldLabel(version, "版本"), vd);
		layoutContainer.add(new FieldLabel(desc, "描述"), vd);

		initGrid();
	}

	private void initGrid() {
		grid = new HbaseTableColumnGrid();
		columns = new ListStoreEditor<HbaseTableColumn>(grid.getStore());

		HbaseTableColumnEditor editor = new HbaseTableColumnEditor() {
			protected void save(HbaseTableColumn v) {
				getSaveOrUpdateCallBackSwap().call(v);
			}

			protected void update(HbaseTableColumn v) {
				getSaveOrUpdateCallBackSwap().call(v);
			}
		};

		EtlGridContainer<Integer, HbaseTableColumn> gridContainer = new EtlGridContainer<Integer, HbaseTableColumn>(
				grid, null) {
			@Override
			protected void rpcDelete(final List<HbaseTableColumn> list) {
				ListStore<HbaseTableColumn> store = getGrid().getStore();
				for (HbaseTableColumn v : list) {
					store.remove(v);
				}
				getDeleteBt().enable();
			}
		};
		EtlGridContainerBuilder<Integer, HbaseTableColumn> builder = new EtlGridContainerBuilder<Integer, HbaseTableColumn>(
				gridContainer);
		builder.setPaging(false);

		builder.setAddEditor(editor);
		builder.setAddCallBack(new GwtCallBack<HbaseTableColumn>() {
			@Override
			public void call(HbaseTableColumn t) {
				t.setVersion(getSelf());
				grid.getStore().add(t);
			}
		});
		builder.setUpdateEditor(editor);
		builder.build();

		FieldLabel columnsLabel = new FieldLabel(gridContainer, "字段");
		columnsLabel.setLabelAlign(LabelAlign.TOP);
		layoutContainer.add(columnsLabel, vd);

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
