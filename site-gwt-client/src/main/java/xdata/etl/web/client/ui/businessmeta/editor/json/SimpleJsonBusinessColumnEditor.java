/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui.businessmeta.editor.json;

import xdata.etl.web.client.rpc.RpcCaller;
import xdata.etl.web.client.ui.businessmeta.combox.HbaseTableColumnCombox;
import xdata.etl.web.client.ui.businessmeta.editor.BusinessColumnEditor;
import xdata.etl.web.shared.entity.businessmeta.json.SimpleJsonBusinessColumn;
import xdata.etl.web.shared.entity.hbasemeta.HbaseTableVersion;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;

/**
 * @author XuehuiHe
 * @date 2013年9月4日
 */
public class SimpleJsonBusinessColumnEditor extends
		BusinessColumnEditor<SimpleJsonBusinessColumn> {

	private static final SimpleJsonBusinessColumnDriver driver = GWT
			.create(SimpleJsonBusinessColumnDriver.class);

	interface SimpleJsonBusinessColumnDriver
			extends
			SimpleBeanEditorDriver<SimpleJsonBusinessColumn, SimpleJsonBusinessColumnEditor> {
	}

	TextField path;
	HbaseTableColumnCombox columns;

	public SimpleJsonBusinessColumnEditor(
			RpcCaller<Integer, SimpleJsonBusinessColumn> rpcCaller) {
		super(driver, rpcCaller);
	}

	@Override
	protected void initView() {
		super.initView();

		path = new TextField();
		columns = new HbaseTableColumnCombox();

		layoutContainer.add(new FieldLabel(path, "path"), vd);
		layoutContainer.add(new FieldLabel(columns, "hbase字段"), vd);
		afterInitView();
	}

	@Override
	protected SimpleJsonBusinessColumn newInstance() {
		SimpleJsonBusinessColumn column = new SimpleJsonBusinessColumn();
		column.setMapping(getMapping());
		return column;
	}

	public void setHbaseTableVersion(HbaseTableVersion version) {
		columns.setHbaseTableVersion(version);
	}

}
