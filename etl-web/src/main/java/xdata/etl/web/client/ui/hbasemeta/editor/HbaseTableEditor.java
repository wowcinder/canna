/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui.hbasemeta.editor;

import xdata.etl.web.client.common.editer.EtlSimpleEditor;
import xdata.etl.web.shared.entity.hbasemeta.HbaseTable;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.ui.TextArea;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;

/**
 * @author XuehuiHe
 * @date 2013年8月15日
 */
public class HbaseTableEditor extends EtlSimpleEditor<Integer, HbaseTable> {

	interface HbaseTableDriver extends
			SimpleBeanEditorDriver<HbaseTable, HbaseTableEditor> {

	}

	public HbaseTableEditor() {
		super(GWT.<HbaseTableDriver> create(HbaseTableDriver.class));
	}

	@Override
	protected HbaseTable newInstance() {
		return new HbaseTable();
	}

	TextField name;
	TextField shortname;
	TextArea desc;

	@Override
	protected void initView() {
		super.initView();

		name = new TextField();
		shortname = new TextField();
		desc = new TextArea();

		layoutContainer.add(new FieldLabel(name, "表名"));
		layoutContainer.add(new FieldLabel(shortname, "中文名"));
		layoutContainer.add(new FieldLabel(desc, "描述"));
	}

}
