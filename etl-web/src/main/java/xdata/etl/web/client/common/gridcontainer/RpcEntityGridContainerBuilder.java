/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.common.gridcontainer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import xdata.etl.web.client.common.editer.RpcEntitySimpleEditor;
import xdata.etl.web.client.gwt.GwtCallBack;
import xdata.etl.web.client.gwt.GwtCallBack.DelayCallback;
import xdata.etl.web.client.rpc.EntityRpcCaller;
import xdata.etl.web.shared.entity.RpcEntity;

import com.google.gwt.user.client.Window;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.CellDoubleClickEvent;
import com.sencha.gxt.widget.core.client.event.CellDoubleClickEvent.CellDoubleClickHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.grid.Grid;

/**
 * @author XuehuiHe
 * @date 2013年8月19日
 */
public class RpcEntityGridContainerBuilder<K extends Serializable, V extends RpcEntity<K>> {

	public RpcEntityGridContainer<V> create() {
		gridContainer = new RpcEntityGridContainer<V>(grid);
		initAddBt();
		initDeleteBt();
		initUpdate();
		initAutoDataConfig();
		return gridContainer;
	}

	public void initContainer(RpcEntityGridContainer<V> container) {
		gridContainer = container;
		grid = gridContainer.getGrid();
		initAddBt();
		initDeleteBt();
		initUpdate();
		initAutoDataConfig();
	}

	private Grid<V> grid;
	private RpcEntityGridContainer<V> gridContainer;

	public Grid<V> getGrid() {
		return grid;
	}

	public void setGrid(Grid<V> grid) {
		this.grid = grid;
	}

	// ADD BT
	private boolean isShowAddBt = true;
	private SelectHandler addBtHandler = new SelectHandler() {
		@Override
		public void onSelect(SelectEvent event) {
			addEditor.add();
		}
	};
	private GwtCallBack<V> addCallBack = new GwtCallBack<V>() {
		@Override
		protected void _call(V t) {
			grid.getStore().add(0, t);
		}
	};
	private RpcEntitySimpleEditor<K, V> addEditor;

	public void initAddBt() {
		if (isShowAddBt) {
			TextButton tb = new TextButton("添加");
			gridContainer.addToHeaderBar(tb);

			tb.addSelectHandler(addBtHandler);
			if (addEditor != null && addCallBack != null) {
				addEditor.setAddCallBack(addCallBack);
			}
		}
	}

	public boolean isShowAddBt() {
		return isShowAddBt;
	}

	public void setShowAddBt(boolean isShowAddBt) {
		this.isShowAddBt = isShowAddBt;
	}

	public SelectHandler getAddBtHandler() {
		return addBtHandler;
	}

	public void setAddBtHandler(SelectHandler addBtHandler) {
		this.addBtHandler = addBtHandler;
	}

	public GwtCallBack<V> getAddCallBack() {
		return addCallBack;
	}

	public void setAddCallBack(GwtCallBack<V> addCallBack) {
		this.addCallBack = addCallBack;
	}

	public RpcEntitySimpleEditor<K, V> getAddEditor() {
		return addEditor;
	}

	public void setAddEditor(RpcEntitySimpleEditor<K, V> addEditor) {
		this.addEditor = addEditor;
	}

	// UPDATE
	private boolean isUpdateEnabled = true;
	private CellDoubleClickHandler updateHandler = new CellDoubleClickHandler() {

		@Override
		public void onCellClick(CellDoubleClickEvent event) {
			if (updateEditor != null) {
				updateEditor.edit(grid.getStore().get(event.getRowIndex()));
			}
		}
	};
	private GwtCallBack<V> updateCallBack = new GwtCallBack<V>() {
		@Override
		protected void _call(V t) {
			grid.getStore().update(t);
		}
	};
	private RpcEntitySimpleEditor<K, V> updateEditor;

	public void initUpdate() {
		if (isUpdateEnabled) {
			getGrid().addCellDoubleClickHandler(updateHandler);
			if (updateEditor != null && updateCallBack != null) {
				updateEditor.setUpdateCallBack(updateCallBack);
			}
		}
	}

	public boolean isUpdateEnabled() {
		return isUpdateEnabled;
	}

	public void setUpdateEnabled(boolean isUpdateEnabled) {
		this.isUpdateEnabled = isUpdateEnabled;
	}

	public CellDoubleClickHandler getUpdateHandler() {
		return updateHandler;
	}

	public void setUpdateHandler(CellDoubleClickHandler updateHandler) {
		this.updateHandler = updateHandler;
	}

	public GwtCallBack<V> getUpdateCallBack() {
		return updateCallBack;
	}

	public void setUpdateCallBack(GwtCallBack<V> updateCallBack) {
		this.updateCallBack = updateCallBack;
	}

	public RpcEntitySimpleEditor<K, V> getUpdateEditor() {
		return updateEditor;
	}

	public void setUpdateEditor(RpcEntitySimpleEditor<K, V> updateEditor) {
		this.updateEditor = updateEditor;
	}

	// DELETE

	private boolean isShowDeleteBt = true;
	private TextButton deleteBt;
	private EntityRpcCaller<K, V> rpcCaller;
	private SelectHandler deleteHandler = new SelectHandler() {
		@Override
		public void onSelect(SelectEvent event) {
			deleteBt.disable();
			final List<V> list = getGrid().getSelectionModel()
					.getSelectedItems();
			if (deleteAction != null) {
				deleteAction.delete(list, new GwtCallBack<Void>() {
					@Override
					protected void _call(Void t) {
						ListStore<V> store = grid.getStore();
						for (V v : list) {
							store.remove(v);
						}
					}

					public void clean() {
						deleteBt.enable();
					};
				});
			}
		}
	};
	private DeleteAction<V> deleteAction = new DeleteAction<V>() {
		@Override
		public void doDelete(List<V> list, GwtCallBack<Void> deleteCallback) {
			if (rpcCaller != null) {
				List<K> ids = new ArrayList<K>();
				for (V v : list) {
					ids.add(v.getId());
				}
				rpcCaller.delete(ids, deleteCallback);
			} else {
				deleteCallback.clean();
			}
		}
	};

	public void initDeleteBt() {
		if (isShowDeleteBt) {
			deleteBt = new TextButton("删除");
			gridContainer.addToHeaderBar(deleteBt);
			if (deleteHandler != null) {
				deleteBt.addSelectHandler(deleteHandler);
			}
		}
	}

	public static abstract class DeleteAction<V> {
		public void delete(List<V> list, GwtCallBack<Void> deleteCallback) {
			if (list == null || list.size() == 0) {
				Window.alert("请选择要删除的记录");
				deleteCallback.clean();
			} else {
				doDelete(list, deleteCallback);
			}

		}

		protected abstract void doDelete(List<V> list,
				GwtCallBack<Void> deleteCallback);
	}

	public boolean isShowDeleteBt() {
		return isShowDeleteBt;
	}

	public void setShowDeleteBt(boolean isShowDeleteBt) {
		this.isShowDeleteBt = isShowDeleteBt;
	}

	public EntityRpcCaller<K, V> getRpcCaller() {
		return rpcCaller;
	}

	public void setRpcCaller(EntityRpcCaller<K, V> rpcCaller) {
		this.rpcCaller = rpcCaller;
	}

	public SelectHandler getDeleteHandler() {
		return deleteHandler;
	}

	public void setDeleteHandler(SelectHandler deleteHandler) {
		this.deleteHandler = deleteHandler;
	}

	public DeleteAction<V> getDeleteAction() {
		return deleteAction;
	}

	public void setDeleteAction(DeleteAction<V> deleteAction) {
		this.deleteAction = deleteAction;
	}

	// initData
	private boolean autoInitData = true;
	private boolean isPaging = true;
	private int itemsPerPage = 50;

	public void initAutoDataConfig() {
		if (!autoInitData) {
			return;
		}
		if (isPaging) {
			gridContainer.setPagingLoader(
					getRpcCaller().getPagingLoader(grid.getStore()),
					getItemsPerPage());
		} else {
			gridContainer
					.setInitDataDelayCallback(new DelayCallback<List<V>>() {

						@Override
						public void run(final GwtCallBack<List<V>> callback) {
							getRpcCaller().get(callback);
						}
					});
		}

	}

	public boolean isAutoInitData() {
		return autoInitData;
	}

	public void setAutoInitData(boolean autoInitData) {
		this.autoInitData = autoInitData;
	}

	public boolean isPaging() {
		return isPaging;
	}

	public void setPaging(boolean isPaging) {
		this.isPaging = isPaging;
	}

	public int getItemsPerPage() {
		return itemsPerPage;
	}

	public void setItemsPerPage(int itemsPerPage) {
		this.itemsPerPage = itemsPerPage;
	}

}
