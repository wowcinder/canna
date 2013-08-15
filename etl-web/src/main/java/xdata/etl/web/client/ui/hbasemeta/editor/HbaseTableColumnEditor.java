/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui.hbasemeta.editor;

import xdata.etl.web.client.common.combox.EnumComboBox;
import xdata.etl.web.client.common.editer.EtlSimpleEditor;
import xdata.etl.web.shared.entity.hbasemeta.HbaseTableColumn;
import xdata.etl.web.shared.entity.hbasemeta.HbaseTableColumn.HbaseTableColumnType;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.ui.TextArea;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;

/**
 * @author XuehuiHe
 * @date 2013年8月15日
 */
public class HbaseTableColumnEditor extends
		EtlSimpleEditor<Integer, HbaseTableColumn> {
	interface HbaseTableColumnDriver extends
			SimpleBeanEditorDriver<HbaseTableColumn, HbaseTableColumnEditor> {

	}

	public HbaseTableColumnEditor() {
		super(GWT.<HbaseTableColumnDriver> create(HbaseTableColumnDriver.class));
	}

	TextField name;
	TextField shortname;
	ComboBox<HbaseTableColumnType> type;
	TextArea desc;

	@Override
	protected void initView() {
		super.initView();
		name = new TextField();
		shortname = new TextField();
		type = new EnumComboBox<HbaseTableColumnType>(
				HbaseTableColumnType.values());
		desc = new TextArea();
		layoutContainer.add(new FieldLabel(name, "name"));
		layoutContainer.add(new FieldLabel(shortname, "中文名"));
		layoutContainer.add(new FieldLabel(type, "type"));
		layoutContainer.add(new FieldLabel(desc, "描述"));
	}

	@Override
	protected HbaseTableColumn newInstance() {
		return new HbaseTableColumn();
	}

}
