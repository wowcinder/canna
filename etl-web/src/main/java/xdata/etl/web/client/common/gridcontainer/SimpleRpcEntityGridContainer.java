/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.common.gridcontainer;

import java.io.Serializable;

import xdata.etl.web.client.common.editer.RpcEntitySimpleEditor;
import xdata.etl.web.client.rpc.EntityRpcCaller;
import xdata.etl.web.client.ui.CenterView;
import xdata.etl.web.shared.entity.RpcEntity;

import com.sencha.gxt.widget.core.client.grid.Grid;

/**
 * @author XuehuiHe
 * @date 2013年8月19日
 */
public abstract class SimpleRpcEntityGridContainer<K extends Serializable, V extends RpcEntity<K>>
		extends RpcEntityGridContainer<V> implements CenterView {
	private CenterViewConfig centerViewConfig;

	public SimpleRpcEntityGridContainer(Grid<V> grid) {
		super(grid);
		createBuilder().initContainer(this);
	}

	protected RpcEntityGridContainerBuilder<K, V> createBuilder() {
		RpcEntityGridContainerBuilder<K, V> builder = new RpcEntityGridContainerBuilder<K, V>();
		builder.setAddEditor(getAddEditor());
		builder.setUpdateEditor(getUpdateEditor());
		builder.setRpcCaller(getRpcCaller());
		return builder;
	}

	protected abstract RpcEntitySimpleEditor<K, V> getAddEditor();

	protected abstract RpcEntitySimpleEditor<K, V> getUpdateEditor();

	protected abstract EntityRpcCaller<K, V> getRpcCaller();

	@Override
	public CenterViewConfig getCenterViewConfig() {
		return centerViewConfig;
	}

	@Override
	public void setCenterViewConfig(CenterViewConfig centerViewConfig) {
		this.centerViewConfig = centerViewConfig;
	}
}
