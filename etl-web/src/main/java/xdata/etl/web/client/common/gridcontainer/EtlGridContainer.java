/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.common.gridcontainer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import xdata.etl.web.client.common.grid.EtlGrid;
import xdata.etl.web.client.gwt.GwtCallBack;
import xdata.etl.web.client.rpc.EntityRpcCaller;
import xdata.etl.web.client.service.RpcServiceAsync;
import xdata.etl.web.shared.entity.RpcEntity;

import com.google.gwt.user.client.Window;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

/**
 * @author XuehuiHe
 * @date 2013年8月10日
 * 
 */
public class EtlGridContainer<K extends Serializable, V extends RpcEntity<K>>
		extends VerticalLayoutContainer {

	private final EtlGrid<K, V> grid;
	private final RpcServiceAsync<K, V> service;
	private final EntityRpcCaller<K, V> rpcCaller;

	private TextButton addBt;
	private TextButton deleteBt;
	protected ToolBar bar;

	public EtlGridContainer(EtlGrid<K, V> grid, RpcServiceAsync<K, V> service) {
		this.grid = grid;
		this.service = service;
		this.rpcCaller = new EntityRpcCaller<K, V>(service);
	}

	public void initAddBt() {
		addBt = new TextButton("添加");
	}

	public void initDeleteBt() {
		deleteBt = new TextButton("删除");
	}

	public void initBtBar() {
		if (addBt == null && deleteBt == null) {
			return;
		}
		bar = new ToolBar();
		if (addBt != null) {
			bar.add(addBt);
		}
		if (deleteBt != null) {
			bar.add(deleteBt);
		}
		this.add(bar, new VerticalLayoutData(1, -1));
	}

	public void initView() {
		this.getElement().setHeight("100%");
		this.setBorders(true);
		this.add(grid, new VerticalLayoutData(1, 1));
		this.rpcCaller.get(new GwtCallBack<List<V>>() {
			@Override
			public void call(List<V> t) {
				grid.getStore().clear();
				grid.getStore().addAll(t);
			}
		});
	}

	public void delete(final List<V> list) {
		if (list.size() == 0) {
			Window.alert("请选择要删除记录!");
			getDeleteBt().enable();
			return;
		}
		List<K> ids = new ArrayList<K>();
		for (V v : list) {
			ids.add(v.getId());
		}
		rpcCaller.delete(ids, new GwtCallBack<Void>() {
			@Override
			public void call(Void t) {
				ListStore<V> store = getGrid().getStore();
				for (V v : list) {
					store.remove(v);
				}
				getDeleteBt().enable();
			}
		});
	}

	public EtlGrid<K, V> getGrid() {
		return grid;
	}

	public RpcServiceAsync<K, V> getService() {
		return service;
	}

	public EntityRpcCaller<K, V> getRpcCaller() {
		return rpcCaller;
	}

	public TextButton getAddBt() {
		return addBt;
	}

	public void setAddBt(TextButton addBt) {
		this.addBt = addBt;
	}

	public TextButton getDeleteBt() {
		return deleteBt;
	}

	public void setDeleteBt(TextButton deleteBt) {
		this.deleteBt = deleteBt;
	}

}
