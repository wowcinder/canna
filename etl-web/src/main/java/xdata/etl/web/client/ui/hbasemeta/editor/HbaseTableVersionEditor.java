/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui.hbasemeta.editor;

import xdata.etl.web.client.common.combox.EnumComboBox;
import xdata.etl.web.client.common.editer.EtlSimpleEditor;
import xdata.etl.web.client.ui.hbasemeta.combox.HbaseTableComBox;
import xdata.etl.web.shared.entity.hbasemeta.HbaseTableColumn.HbaseTableColumnType;
import xdata.etl.web.shared.entity.hbasemeta.HbaseTableVersion;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.ui.TextArea;
import com.sencha.gxt.widget.core.client.event.ShowEvent;
import com.sencha.gxt.widget.core.client.event.ShowEvent.ShowHandler;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
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
	@Ignore
	ComboBox<HbaseTableColumnType> types;

	@Override
	protected void initView() {
		super.initView();
		table = new HbaseTableComBox();
		version = new TextField();
		desc = new TextArea();

		types = new EnumComboBox<HbaseTableColumnType>(
				HbaseTableColumnType.values());

		layoutContainer.add(new FieldLabel(table, "表"));
		layoutContainer.add(new FieldLabel(version, "版本"));
		layoutContainer.add(new FieldLabel(desc, "描述"));
		layoutContainer.add(new FieldLabel(types, "测试type"));

	}

	@Override
	protected HbaseTableVersion newInstance() {
		return new HbaseTableVersion();
	}

}
