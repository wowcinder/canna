/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui.hbasemeta;

import xdata.etl.web.client.annotations.MenuToken;
import xdata.etl.web.client.common.editer.RpcEntitySimpleEditor;
import xdata.etl.web.client.common.gridcontainer.SimpleRpcEntityGridContainer;
import xdata.etl.web.client.rpc.EntityRpcCaller;
import xdata.etl.web.client.service.ServiceUtil;
import xdata.etl.web.client.ui.CenterView;
import xdata.etl.web.client.ui.hbasemeta.editor.HbaseTableEditor;
import xdata.etl.web.client.ui.hbasemeta.grid.HbaseTableGrid;
import xdata.etl.web.shared.entity.hbasemeta.HbaseTable;

/**
 * @author XuehuiHe
 * @date 2013年8月15日
 */
@MenuToken(name = "表管理", token = "hbase_table")
public class HbaseTableView extends
		SimpleRpcEntityGridContainer<Integer, HbaseTable> implements CenterView {

	private static final HbaseTableEditor editor = new HbaseTableEditor();

	public HbaseTableView() {
		super(new HbaseTableGrid().create());

	}

	@Override
	protected RpcEntitySimpleEditor<Integer, HbaseTable> getAddEditor() {
		return editor;
	}

	@Override
	protected RpcEntitySimpleEditor<Integer, HbaseTable> getUpdateEditor() {
		return editor;
	}

	@Override
	protected EntityRpcCaller<Integer, HbaseTable> getRpcCaller() {
		return ServiceUtil.HbaseTableRpcCaller;
	}
}
