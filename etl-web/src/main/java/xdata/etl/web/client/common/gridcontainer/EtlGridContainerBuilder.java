/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.common.gridcontainer;

import java.io.Serializable;
import java.util.List;

import xdata.etl.web.client.common.editer.EtlEditor;
import xdata.etl.web.client.common.grid.EtlGrid;
import xdata.etl.web.client.gwt.GwtCallBack;
import xdata.etl.web.client.service.RpcServiceAsync;
import xdata.etl.web.shared.entity.RpcEntity;

import com.sencha.gxt.widget.core.client.event.CellDoubleClickEvent;
import com.sencha.gxt.widget.core.client.event.CellDoubleClickEvent.CellDoubleClickHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;

/**
 * @author XuehuiHe
 * @date 2013年8月10日
 * 
 */
public class EtlGridContainerBuilder<K extends Serializable, V extends RpcEntity<K>> {/*
	private boolean isShowAddBt = true;
	private boolean isShowRemoveBt = true;
	private boolean isUpdateEnabled = true;
	private boolean isInitData = true;
	private EtlEditor<K, V> addEditor;
	private EtlEditor<K, V> updateEditor;

	private final EtlGrid<K, V> grid;
	private final RpcServiceAsync<K, V> service;
	private final EtlGridContainer<K, V> gridContainer;

	private GwtCallBack<SelectEvent> addCancelCallBack = new GwtCallBack<SelectEvent>() {

		@Override
		public void _call(SelectEvent t) {
			gridContainer.getAddBt().enable();
		}
	};
	private GwtCallBack<V> addCallBack = new GwtCallBack<V>() {
		@Override
		public void _call(final V t) {
			grid.getStore().add(0, t);
		}
	};

	private GwtCallBack<V> realAddCallBack = new GwtCallBack<V>() {
		@Override
		public void _call(final V t) {
			addCallBack._call(t);
			gridContainer.getAddBt().enable();
		}
	};

	private GwtCallBack<V> updateCallBack = new GwtCallBack<V>() {
		@Override
		public void _call(final V t) {
			grid.getStore().update(t);
		}
	};

	private GwtCallBack<V> realUpdateCallBack = new GwtCallBack<V>() {
		@Override
		public void _call(final V t) {
			updateCallBack._call(t);
		}
	};

	private SelectHandler addBtHandler = new SelectHandler() {
		@Override
		public void onSelect(SelectEvent event) {
			addEditor.add();
		}
	};

	private SelectHandler realAddBtHandler = new SelectHandler() {
		@Override
		public void onSelect(SelectEvent event) {
			gridContainer.getAddBt().disable();
			addBtHandler.onSelect(event);
		}
	};

	private SelectHandler deleteBtHandler = new SelectHandler() {
		@Override
		public void onSelect(SelectEvent event) {
			List<V> list = grid.getSelectionModel().getSelectedItems();
			gridContainer.delete(list);
		}
	};

	private SelectHandler realDeleteBtHandler = new SelectHandler() {
		@Override
		public void onSelect(SelectEvent event) {
			gridContainer.getDeleteBt().disable();
			deleteBtHandler.onSelect(event);
		}
	};

	protected CellDoubleClickHandler updateHandler = new CellDoubleClickHandler() {
		@Override
		public void onCellClick(CellDoubleClickEvent event) {
			updateEditor.edit(grid.getStore().get(event.getRowIndex()));
		}
	};

	private boolean isPaging = true;
	private int pageItemCount = 50;

	public EtlGridContainerBuilder(EtlGridContainer<K, V> gridContainer) {
		this.grid = gridContainer.getGrid();
		this.service = gridContainer.getService();
		this.gridContainer = gridContainer;
	}

	public EtlGridContainer<K, V> build() {
		if (isShowAddBt()) {
			if (addEditor != null) {
				addEditor.setAddCancelCallBack(getAddCancelCallBack());
				addEditor.setAddCallBack(getRealAddCallBack());
			}
			gridContainer.initAddBt();
			gridContainer.getAddBt().addSelectHandler(getRealAddBtHandler());
		}
		if (isShowRemoveBt()) {
			gridContainer.initDeleteBt();
			gridContainer.getDeleteBt().addSelectHandler(
					getRealDeleteBtHandler());
		}
		if (isUpdateEnabled()) {
			if (updateEditor != null) {
				updateEditor.setUpdateCallBack(getUpdateCallBack());
			}
			grid.addCellDoubleClickHandler(getUpdateHandler());
		}
		if (isPaging()) {
			gridContainer.initPaging(getPageItemCount());
		}
		gridContainer.initBtBar();
		gridContainer.initView();
		if(isInitData()){
			gridContainer.initData();
		}
		return gridContainer;
	}

	public boolean isShowAddBt() {
		return isShowAddBt;
	}

	public void setShowAddBt(boolean isShowAddBt) {
		this.isShowAddBt = isShowAddBt;
	}

	public boolean isShowRemoveBt() {
		return isShowRemoveBt;
	}

	public void setShowRemoveBt(boolean isShowRemoveBt) {
		this.isShowRemoveBt = isShowRemoveBt;
	}

	public boolean isUpdateEnabled() {
		return isUpdateEnabled;
	}

	public void setUpdateEnabled(boolean isUpdateEnabled) {
		this.isUpdateEnabled = isUpdateEnabled;
	}

	public EtlEditor<K, V> getAddEditor() {
		return addEditor;
	}

	public void setAddEditor(EtlEditor<K, V> addEditor) {
		this.addEditor = addEditor;
	}

	public EtlEditor<K, V> getUpdateEditor() {
		return updateEditor;
	}

	public void setUpdateEditor(EtlEditor<K, V> updateEditor) {
		this.updateEditor = updateEditor;
	}

	public GwtCallBack<SelectEvent> getAddCancelCallBack() {
		return addCancelCallBack;
	}

	public void setAddCancelCallBack(GwtCallBack<SelectEvent> addCancelCallBack) {
		this.addCancelCallBack = addCancelCallBack;
	}

	public GwtCallBack<V> getAddCallBack() {
		return addCallBack;
	}

	public void setAddCallBack(GwtCallBack<V> addCallBack) {
		this.addCallBack = addCallBack;
	}

	public GwtCallBack<V> getRealAddCallBack() {
		return realAddCallBack;
	}

	public void setRealAddCallBack(GwtCallBack<V> realAddCallBack) {
		this.realAddCallBack = realAddCallBack;
	}

	public GwtCallBack<V> getUpdateCallBack() {
		return updateCallBack;
	}

	public void setUpdateCallBack(GwtCallBack<V> updateCallBack) {
		this.updateCallBack = updateCallBack;
	}

	public GwtCallBack<V> getRealUpdateCallBack() {
		return realUpdateCallBack;
	}

	public void setRealUpdateCallBack(GwtCallBack<V> realUpdateCallBack) {
		this.realUpdateCallBack = realUpdateCallBack;
	}

	public SelectHandler getAddBtHandler() {
		return addBtHandler;
	}

	public void setAddBtHandler(SelectHandler addBtHandler) {
		this.addBtHandler = addBtHandler;
	}

	public SelectHandler getRealAddBtHandler() {
		return realAddBtHandler;
	}

	public void setRealAddBtHandler(SelectHandler realAddBtHandler) {
		this.realAddBtHandler = realAddBtHandler;
	}

	public SelectHandler getDeleteBtHandler() {
		return deleteBtHandler;
	}

	public void setDeleteBtHandler(SelectHandler deleteBtHandler) {
		this.deleteBtHandler = deleteBtHandler;
	}

	public SelectHandler getRealDeleteBtHandler() {
		return realDeleteBtHandler;
	}

	public void setRealDeleteBtHandler(SelectHandler realDeleteBtHandler) {
		this.realDeleteBtHandler = realDeleteBtHandler;
	}

	public CellDoubleClickHandler getUpdateHandler() {
		return updateHandler;
	}

	public void setUpdateHandler(CellDoubleClickHandler updateHandler) {
		this.updateHandler = updateHandler;
	}

	public boolean isPaging() {
		return isPaging;
	}
	public void setPaging(boolean isPaging) {
		this.isPaging = isPaging;
	}

	public int getPageItemCount() {
		return pageItemCount;
	}

	public void setPageItemCount(int pageItemCount) {
		this.pageItemCount = pageItemCount;
	}

	public EtlGrid<K, V> getGrid() {
		return grid;
	}

	public RpcServiceAsync<K, V> getService() {
		return service;
	}

	public EtlGridContainer<K, V> getGridContainer() {
		return gridContainer;
	}
	public boolean isInitData() {
		return isInitData;
	}
	public void setInitData(boolean isInitData) {
		this.isInitData = isInitData;
	}
*/
}
