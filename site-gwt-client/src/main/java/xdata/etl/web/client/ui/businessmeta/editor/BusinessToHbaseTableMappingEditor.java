/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui.businessmeta.editor;

import xdata.etl.web.client.common.editer.EtlEditor;
import xdata.etl.web.client.common.editer.RpcEntitySimpleEditor;
import xdata.etl.web.client.rpc.RpcCaller;
import xdata.etl.web.client.ui.businessmeta.combox.HbaseTableVersionCombox;
import xdata.etl.web.shared.entity.businessmeta.BusinessToHbaseTableMapping;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.widget.core.client.form.TextArea;

/**
 * @author XuehuiHe
 * @date 2013年9月4日
 */
public abstract class BusinessToHbaseTableMappingEditor<T extends BusinessToHbaseTableMapping>
		extends RpcEntitySimpleEditor<Integer, T> {

	/**
	 * @param driver
	 * @param baseHeadingText
	 * @param rpcCaller
	 */
	public BusinessToHbaseTableMappingEditor(
			SimpleBeanEditorDriver<T, ? extends EtlEditor<T>> driver,
			RpcCaller<Integer, T> rpcCaller) {
		super(driver, "hbase映射", rpcCaller);
	}

	protected HbaseTableVersionCombox hbaseTableVersions;
	protected TextArea desc;

	@Override
	protected void initView() {
		super.initView();

		hbaseTableVersions = new HbaseTableVersionCombox();
		desc = new TextArea();
	}

}
