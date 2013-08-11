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
public class EtlGridContainerBuilder<K extends Serializable, V extends RpcEntity<K>> {
	private boolean isShowAddBt = true;
	private boolean isShowRemoveBt = true;
	private boolean isUpdateEnabled = true;
	private EtlEditor<K, V> addEditor;
	private EtlEditor<K, V> updateEditor;

	private final EtlGrid<K, V> grid;
	private final RpcServiceAsync<K, V> service;
	private final EtlGridContainer<K, V> gridContainer;

	private GwtCallBack<SelectEvent> addCancelCallBack = new GwtCallBack<SelectEvent>() {

		@Override
		public void call(SelectEvent t) {
			gridContainer.getAddBt().enable();
		}
	};
	private GwtCallBack<V> addCallBack = new GwtCallBack<V>() {
		@Override
		public void call(final V t) {
			grid.getStore().add(0, t);
		}
	};

	private GwtCallBack<V> realAddCallBack = new GwtCallBack<V>() {
		@Override
		public void call(final V t) {
			addCallBack.call(t);
			gridContainer.getAddBt().enable();
		}
	};

	private GwtCallBack<V> updateCallBack = new GwtCallBack<V>() {
		@Override
		public void call(final V t) {
			grid.getStore().update(t);
		}
	};

	private GwtCallBack<V> realUpdateCallBack = new GwtCallBack<V>() {
		@Override
		public void call(final V t) {
			updateCallBack.call(t);
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
			addEditor.setAddCancelCallBack(getAddCancelCallBack());
			addEditor.setAddCallBack(getRealAddCallBack());
			gridContainer.initAddBt();
			gridContainer.getAddBt().addSelectHandler(getRealAddBtHandler());
		}
		if (isShowRemoveBt()) {
			gridContainer.initDeleteBt();
			gridContainer.getDeleteBt().addSelectHandler(
					getRealDeleteBtHandler());
		}
		if (isUpdateEnabled()) {
			updateEditor.setUpdateCallBack(getUpdateCallBack());
			grid.addCellDoubleClickHandler(getUpdateHandler());
		}
		if (isPaging()) {
			gridContainer.initPaging(getPageItemCount());
		}
		gridContainer.initBtBar();
		gridContainer.initView();
		gridContainer.initData();
		return gridContainer;
	}

	/**
	 * @return the isShowAddBt
	 */
	public boolean isShowAddBt() {
		return isShowAddBt;
	}

	/**
	 * @param isShowAddBt
	 *            the isShowAddBt to set
	 */
	public void setShowAddBt(boolean isShowAddBt) {
		this.isShowAddBt = isShowAddBt;
	}

	/**
	 * @return the isShowRemoveBt
	 */
	public boolean isShowRemoveBt() {
		return isShowRemoveBt;
	}

	/**
	 * @param isShowRemoveBt
	 *            the isShowRemoveBt to set
	 */
	public void setShowRemoveBt(boolean isShowRemoveBt) {
		this.isShowRemoveBt = isShowRemoveBt;
	}

	/**
	 * @return the isUpdateEnabled
	 */
	public boolean isUpdateEnabled() {
		return isUpdateEnabled;
	}

	/**
	 * @param isUpdateEnabled
	 *            the isUpdateEnabled to set
	 */
	public void setUpdateEnabled(boolean isUpdateEnabled) {
		this.isUpdateEnabled = isUpdateEnabled;
	}

	/**
	 * @return the addEditor
	 */
	public EtlEditor<K, V> getAddEditor() {
		return addEditor;
	}

	/**
	 * @param addEditor
	 *            the addEditor to set
	 */
	public void setAddEditor(EtlEditor<K, V> addEditor) {
		this.addEditor = addEditor;
	}

	/**
	 * @return the updateEditor
	 */
	public EtlEditor<K, V> getUpdateEditor() {
		return updateEditor;
	}

	/**
	 * @param updateEditor
	 *            the updateEditor to set
	 */
	public void setUpdateEditor(EtlEditor<K, V> updateEditor) {
		this.updateEditor = updateEditor;
	}

	/**
	 * @return the addCancelCallBack
	 */
	public GwtCallBack<SelectEvent> getAddCancelCallBack() {
		return addCancelCallBack;
	}

	/**
	 * @param addCancelCallBack
	 *            the addCancelCallBack to set
	 */
	public void setAddCancelCallBack(GwtCallBack<SelectEvent> addCancelCallBack) {
		this.addCancelCallBack = addCancelCallBack;
	}

	/**
	 * @return the addCallBack
	 */
	public GwtCallBack<V> getAddCallBack() {
		return addCallBack;
	}

	/**
	 * @param addCallBack
	 *            the addCallBack to set
	 */
	public void setAddCallBack(GwtCallBack<V> addCallBack) {
		this.addCallBack = addCallBack;
	}

	/**
	 * @return the realAddCallBack
	 */
	public GwtCallBack<V> getRealAddCallBack() {
		return realAddCallBack;
	}

	/**
	 * @param realAddCallBack
	 *            the realAddCallBack to set
	 */
	public void setRealAddCallBack(GwtCallBack<V> realAddCallBack) {
		this.realAddCallBack = realAddCallBack;
	}

	/**
	 * @return the updateCallBack
	 */
	public GwtCallBack<V> getUpdateCallBack() {
		return updateCallBack;
	}

	/**
	 * @param updateCallBack
	 *            the updateCallBack to set
	 */
	public void setUpdateCallBack(GwtCallBack<V> updateCallBack) {
		this.updateCallBack = updateCallBack;
	}

	/**
	 * @return the realUpdateCallBack
	 */
	public GwtCallBack<V> getRealUpdateCallBack() {
		return realUpdateCallBack;
	}

	/**
	 * @param realUpdateCallBack
	 *            the realUpdateCallBack to set
	 */
	public void setRealUpdateCallBack(GwtCallBack<V> realUpdateCallBack) {
		this.realUpdateCallBack = realUpdateCallBack;
	}

	/**
	 * @return the addBtHandler
	 */
	public SelectHandler getAddBtHandler() {
		return addBtHandler;
	}

	/**
	 * @param addBtHandler
	 *            the addBtHandler to set
	 */
	public void setAddBtHandler(SelectHandler addBtHandler) {
		this.addBtHandler = addBtHandler;
	}

	/**
	 * @return the realAddBtHandler
	 */
	public SelectHandler getRealAddBtHandler() {
		return realAddBtHandler;
	}

	/**
	 * @param realAddBtHandler
	 *            the realAddBtHandler to set
	 */
	public void setRealAddBtHandler(SelectHandler realAddBtHandler) {
		this.realAddBtHandler = realAddBtHandler;
	}

	/**
	 * @return the deleteBtHandler
	 */
	public SelectHandler getDeleteBtHandler() {
		return deleteBtHandler;
	}

	/**
	 * @param deleteBtHandler
	 *            the deleteBtHandler to set
	 */
	public void setDeleteBtHandler(SelectHandler deleteBtHandler) {
		this.deleteBtHandler = deleteBtHandler;
	}

	/**
	 * @return the realDeleteBtHandler
	 */
	public SelectHandler getRealDeleteBtHandler() {
		return realDeleteBtHandler;
	}

	/**
	 * @param realDeleteBtHandler
	 *            the realDeleteBtHandler to set
	 */
	public void setRealDeleteBtHandler(SelectHandler realDeleteBtHandler) {
		this.realDeleteBtHandler = realDeleteBtHandler;
	}

	/**
	 * @return the updateHandler
	 */
	public CellDoubleClickHandler getUpdateHandler() {
		return updateHandler;
	}

	/**
	 * @param updateHandler
	 *            the updateHandler to set
	 */
	public void setUpdateHandler(CellDoubleClickHandler updateHandler) {
		this.updateHandler = updateHandler;
	}

	/**
	 * @return the isPaging
	 */
	public boolean isPaging() {
		return isPaging;
	}

	/**
	 * @param isPaging
	 *            the isPaging to set
	 */
	public void setPaging(boolean isPaging) {
		this.isPaging = isPaging;
	}

	/**
	 * @return the pageItemCount
	 */
	public int getPageItemCount() {
		return pageItemCount;
	}

	/**
	 * @param pageItemCount
	 *            the pageItemCount to set
	 */
	public void setPageItemCount(int pageItemCount) {
		this.pageItemCount = pageItemCount;
	}

	/**
	 * @return the grid
	 */
	public EtlGrid<K, V> getGrid() {
		return grid;
	}

	/**
	 * @return the service
	 */
	public RpcServiceAsync<K, V> getService() {
		return service;
	}

	/**
	 * @return the gridContainer
	 */
	public EtlGridContainer<K, V> getGridContainer() {
		return gridContainer;
	}

}
