/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.common.gridcontainer;

import java.io.Serializable;

import xdata.etl.web.client.common.grid.EtlGrid;
import xdata.etl.web.client.rpc.EntityRpcCaller;
import xdata.etl.web.client.service.RpcServiceAsync;
import xdata.etl.web.shared.entity.RpcEntity;

import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;

/**
 * @author XuehuiHe
 * @date 2013年8月10日
 * 
 */
public class EtlGridContainer<K extends Serializable, V extends RpcEntity<K>>
		extends VerticalLayoutContainer {

	private EtlGrid<K, V> grid;
	private RpcServiceAsync<K, V> service;
	private EntityRpcCaller<K, V> rpcCaller;

	public EtlGridContainer(EtlGrid<K, V> grid, RpcServiceAsync<K, V> service) {
		this.grid = grid;
		this.service = service;
		this.rpcCaller = new EntityRpcCaller<K, V>(service);
	}

	public EtlGrid<K, V> getGrid() {
		return grid;
	}

	public void setGrid(EtlGrid<K, V> grid) {
		this.grid = grid;
	}

	public RpcServiceAsync<K, V> getService() {
		return service;
	}

	public void setService(RpcServiceAsync<K, V> service) {
		this.service = service;
	}

	public EntityRpcCaller<K, V> getRpcCaller() {
		return rpcCaller;
	}

	public void setRpcCaller(EntityRpcCaller<K, V> rpcCaller) {
		this.rpcCaller = rpcCaller;
	}

}
