/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.common.gridcontainer;

import java.io.Serializable;

import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;

import xdata.etl.web.client.common.grid.EtlGrid;
import xdata.etl.web.client.service.RpcServiceAsync;
import xdata.etl.web.shared.entity.RpcEntity;

/**
 * @author XuehuiHe
 * @date 2013年8月10日
 * 
 */
public class EtlGridContainerBuilder<K extends Serializable, V extends RpcEntity<K>> {
	private boolean isShowAddBt = true;
	private boolean isShowRemoveBt = true;
	private boolean isUpdateEnabled = true;

	private SelectHandler addBtHandler = new SelectHandler() {
		@Override
		public void onSelect(SelectEvent event) {

		}
	};

	private boolean isPaging = true;
	private int pageItemCount = 50;

	private EtlGrid<K, V> grid;
	private RpcServiceAsync<K, V> service;

	public EtlGridContainerBuilder(EtlGrid<K, V> grid,
			RpcServiceAsync<K, V> service) {
		this.grid = grid;
		this.service = service;
	}

}
