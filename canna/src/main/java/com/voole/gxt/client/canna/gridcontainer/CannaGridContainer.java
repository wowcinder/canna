package com.voole.gxt.client.canna.gridcontainer;

import java.util.List;

import com.google.gwt.event.shared.HandlerRegistration;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.box.AlertMessageBox;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.CellDoubleClickEvent;
import com.sencha.gxt.widget.core.client.event.CellDoubleClickEvent.CellDoubleClickHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.grid.CheckBoxSelectionModel;
import com.sencha.gxt.widget.core.client.toolbar.PagingToolBar;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;
import com.voole.gxt.client.canna.editor.CannaEditor;
import com.voole.gxt.client.canna.editor.CannaEditor.EditorAddHanlder;
import com.voole.gxt.client.canna.editor.CannaEditor.EditorUpdateHanlder;
import com.voole.gxt.client.canna.grid.CannaGrid;
import com.voole.gxt.client.canna.gridcontainer.paging.CannaPagingLoader;
import com.voole.gxt.client.rpcclient.CannaRpcClient;
import com.voole.gxt.client.rpcclient.callback.DeleteCallback;
import com.voole.gxt.client.rpcclient.callback.GetCallback;

public abstract class CannaGridContainer<T> extends VerticalLayoutContainer
		implements DeleteCallback<List<T>>, GetCallback<List<T>> {

	public int pageSize = 50;
	protected CannaEditor<T> addEditor;
	protected CannaEditor<T> updateEditor;

	// config
	protected CannaGridContainerConfig config;
	protected boolean isExistToolBar = true;
	protected CannaGrid<T> grid;

	protected ToolBar bar;
	protected TextButton addBt;
	protected TextButton deleteBt;

	protected PagingToolBar pagingBar;

	protected CannaRpcClient<T> rpc;
	protected CannaPagingLoader<T> loader;

	protected HandlerRegistration addHr;
	protected HandlerRegistration deleteHr;
	protected HandlerRegistration updateHr;
	protected SelectHandler addHandler = new SelectHandler() {
		@Override
		public void onSelect(SelectEvent event) {
			addEditor.add();
		}
	};
	protected SelectHandler deleteHandler = new SelectHandler() {
		@Override
		public void onSelect(SelectEvent event) {
			List<T> list = getGrid().getSelectionModel().getSelectedItems();
			if (list.size() < 1) {
				noFoundDeleteItems();
				return;
			}
			deleteBt.disable();
			delete(list);
		}
	};
	protected CellDoubleClickHandler updateHandler = new CellDoubleClickHandler() {
		@Override
		public void onCellClick(CellDoubleClickEvent event) {
			updateEditor.edit(getGrid().getStore().get(event.getRowIndex()));
		}
	};

	protected EditorAddHanlder<T> editorAddHanlder = new EditorAddHanlder<T>() {
		@Override
		public void postAdd(T t) {
			getGrid().getStore().add(0, t);
		}
	};

	protected EditorUpdateHanlder<T> editorUpdateHanlder = new EditorUpdateHanlder<T>() {
		@Override
		public void postUpdate(T t) {
			getGrid().getStore().update(t);
		}
	};

	public CannaGridContainer(CannaGrid<T> grid, CannaRpcClient<T> rpc) {
		this(grid, rpc, new CannaGridContainerConfig());
	}

	public CannaGridContainer(CannaGrid<T> grid, CannaRpcClient<T> rpc,
			CannaGridContainerConfig config) {
		super();
		this.rpc = rpc;
		this.grid = grid;
		this.config = config;
		if (this.rpc != null) {
			if (config.isPaging) {
				this.loader = rpc.getPagingLoader(grid.getStore());
				grid.setLoader(loader);
				initPagingToolBar();
				loader.load();
			} else {
				rpcGet();
			}
		}
		initView();
	}

	public void rpcGet() {
		this.rpc.get(this);
	}

	public void initPagingToolBar() {
		if (config.isPaging) {
			pagingBar = new PagingToolBar(getPageSize());
			pagingBar.getElement().getStyle()
					.setProperty("borderBottom", "none");
			pagingBar.bind(loader);
		}
	}

	public void postGet(List<T> list) {
		getGrid().getStore().clear();
		getGrid().getStore().addAll(list);
	}

	protected void delete(List<T> list) {
		rpc.delete(list, this);
	}

	protected void noFoundDeleteItems() {
		AlertMessageBox d = new AlertMessageBox("删除", "请选择要删除的记录!");
		d.show();
	}

	@Override
	public void postDelete(List<T> list) {
		if (list != null) {
			ListStore<T> store = getGrid().getStore();
			for (T t : list) {
				store.remove(t);
			}
		}
		deleteBt.enable();
	}

	public void initView() {
		initToolbar();
		this.getElement().setHeight("100%");
		this.setBorders(true);
		if (isExistToolBar) {
			this.add(bar, new VerticalLayoutData(1, -1));
		}
		this.add(grid, new VerticalLayoutData(1, 1));
		if (config.isUpdateAbled) {
			updateHr = grid.addCellDoubleClickHandler(updateHandler);
		}
		if (config.isPaging && this.rpc != null) {
			this.add(pagingBar, new VerticalLayoutData(1, -1));
		}
	}

	private void initToolbar() {
		checkToolBarStatus();
		if (!isExistToolBar) {
			return;
		}
		bar = new ToolBar();
		if (config.isAddAbled) {
			addBt = new TextButton("add");
			addHr = addBt.addSelectHandler(addHandler);
			bar.add(addBt);
		}
		if (config.isDeleteAbled) {
			deleteBt = new TextButton("delete");
			deleteHr = deleteBt.addSelectHandler(deleteHandler);
			bar.add(deleteBt);
		}
	}

	public void setEditor(CannaEditor<T> editor) {
		editor.setAddHanlder(this.editorAddHanlder);
		editor.setUpdateHanlder(this.editorUpdateHanlder);
		setAddEditor(editor);
		setUpdateEditor(editor);
	}

	public void setAddEditor(CannaEditor<T> addEditor) {
		if (addEditor.getAddHanlder() == null) {
			addEditor.setAddHanlder(this.editorAddHanlder);
		}
		this.addEditor = addEditor;
	}

	public void setUpdateEditor(CannaEditor<T> updateEditor) {
		if (updateEditor.getUpdateHanlder() == null) {
			updateEditor.setUpdateHanlder(this.editorUpdateHanlder);
		}
		this.updateEditor = updateEditor;
	}

	protected void checkToolBarStatus() {
		if (!config.isAddAbled && !config.isDeleteAbled) {
			isExistToolBar = false;
			return;
		}
		isExistToolBar = true;
	}

	public CannaGrid<T> getGrid() {
		return grid;
	}

	public void setGrid(CannaGrid<T> grid) {
		this.grid = grid;
	}

	public void setAddHandler(SelectHandler addHandler) {
		if (config.isAddAbled) {
			if (addHr != null) {
				addHr.removeHandler();
			}
			addBt.addSelectHandler(addHandler);
		}
		this.addHandler = addHandler;
	}

	public void setDeleteHandler(SelectHandler deleteHandler) {
		this.deleteHandler = deleteHandler;
		if (config.isDeleteAbled) {
			if (deleteHr != null) {
				deleteHr.removeHandler();
			}
			deleteHr = deleteBt.addSelectHandler(deleteHandler);
		}
	}

	public void setUpdateHandler(CellDoubleClickHandler updateHandler) {
		this.updateHandler = updateHandler;
		if (config.isUpdateAbled) {
			if (deleteHr != null) {
				deleteHr.removeHandler();
			}
			deleteHr = getGrid().addCellDoubleClickHandler(updateHandler);
		}
	}

	public CheckBoxSelectionModel<T> getSelectionModel() {
		return getGrid().getSelectionModel();
	}

	public ListStore<T> getStore() {
		return getGrid().getStore();
	}

	public CannaRpcClient<T> getRpc() {
		return rpc;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
		pagingBar.setPageSize(pageSize);
	}

}
