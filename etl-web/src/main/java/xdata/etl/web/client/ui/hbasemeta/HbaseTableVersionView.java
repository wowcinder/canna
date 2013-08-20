/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui.hbasemeta;

import xdata.etl.web.client.ServiceUtil;
import xdata.etl.web.client.annotations.MenuToken;
import xdata.etl.web.client.common.editer.RpcEntitySimpleEditor;
import xdata.etl.web.client.common.gridcontainer.SimpleRpcEntityGridContainer;
import xdata.etl.web.client.rpc.EntityRpcCaller;
import xdata.etl.web.client.ui.CenterView;
import xdata.etl.web.client.ui.hbasemeta.editor.HbaseTableVersionEditor;
import xdata.etl.web.client.ui.hbasemeta.grid.HbaseTableVersionGrid;
import xdata.etl.web.shared.entity.hbasemeta.HbaseTableVersion;

/**
 * @author XuehuiHe
 * @date 2013年8月15日
 */
@MenuToken(name = "表版本管理", token = "hbase_table_version")
public class HbaseTableVersionView extends
		SimpleRpcEntityGridContainer<Integer, HbaseTableVersion> implements
		CenterView {

	private static final HbaseTableVersionEditor editor = new HbaseTableVersionEditor();

	public HbaseTableVersionView() {
		super(new HbaseTableVersionGrid().create());

	}

	@Override
	protected RpcEntitySimpleEditor<Integer, HbaseTableVersion> getAddEditor() {
		return editor;
	}

	@Override
	protected RpcEntitySimpleEditor<Integer, HbaseTableVersion> getUpdateEditor() {
		return editor;
	}

	@Override
	protected EntityRpcCaller<Integer, HbaseTableVersion> getRpcCaller() {
		return ServiceUtil.HbaseTableVersionRpcCaller;
	}
}
