/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui.businessmeta.editor.c;

import xdata.etl.web.client.rpc.RpcCaller;
import xdata.etl.web.client.ui.businessmeta.combox.HbaseTableColumnCombox;
import xdata.etl.web.client.ui.businessmeta.editor.BusinessColumnEditor;
import xdata.etl.web.shared.entity.businessmeta.c.SimpleCTypeBusinessColumn;
import xdata.etl.web.shared.entity.hbasemeta.HbaseTableVersion;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.widget.core.client.form.FieldLabel;

/**
 * @author XuehuiHe
 * @date 2013年9月4日
 */
public class SimpleCTypeBusinessColumnEditor extends
		BusinessColumnEditor<SimpleCTypeBusinessColumn> {

	public SimpleCTypeBusinessColumnEditor(
			RpcCaller<Integer, SimpleCTypeBusinessColumn> rpcCaller) {
		super(driver, rpcCaller);
	}

	private static final SimpleCTypeBusinessColumnDriver driver = GWT
			.create(SimpleCTypeBusinessColumnDriver.class);

	interface SimpleCTypeBusinessColumnDriver
			extends
			SimpleBeanEditorDriver<SimpleCTypeBusinessColumn, SimpleCTypeBusinessColumnEditor> {
	}

	HbaseTableColumnCombox columns;

	@Override
	protected void initView() {
		super.initView();
		columns = new HbaseTableColumnCombox();
		layoutContainer.add(new FieldLabel(columns, "hbase字段"), vd);
		afterInitView();
	}

	@Override
	protected SimpleCTypeBusinessColumn newInstance() {
		SimpleCTypeBusinessColumn column = new SimpleCTypeBusinessColumn();
		column.setMapping(getMapping());
		return column;
	}

	public void setHbaseTableVersion(HbaseTableVersion version) {
		columns.setHbaseTableVersion(version);
	}

}
