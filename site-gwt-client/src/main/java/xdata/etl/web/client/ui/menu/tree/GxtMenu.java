/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui.menu.tree;

import xdata.etl.web.client.RpcAsyncCallback;
import xdata.etl.web.client.gwt.GwtCallBack;
import xdata.etl.web.client.ui.menu.editor.MenuEditor;
import xdata.etl.web.client.ui.menu.editor.MenuGroupEditor;
import xdata.etl.web.shared.Provider;
import xdata.etl.web.shared.entity.menu.Menu;
import xdata.etl.web.shared.entity.menu.MenuGroup;
import xdata.etl.web.shared.entity.menu.MenuNode;
import xdata.etl.web.shared.service.menu.MenuNodeRpcService;
import xdata.etl.web.shared.service.menu.MenuNodeRpcServiceAsync;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.sencha.gxt.widget.core.client.event.BeforeShowEvent;
import com.sencha.gxt.widget.core.client.event.BeforeShowEvent.BeforeShowHandler;
import com.sencha.gxt.widget.core.client.event.HideEvent;
import com.sencha.gxt.widget.core.client.event.HideEvent.HideHandler;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.MenuItem;
import com.sencha.gxt.widget.core.client.menu.SeparatorMenuItem;

/**
 * @author XuehuiHe
 * @date 2013年8月23日
 */
public class GxtMenu extends com.sencha.gxt.widget.core.client.menu.Menu {
	private static final MenuNodeRpcServiceAsync service = GWT
			.create(MenuNodeRpcService.class);

	private MenuItem insertMenu;
	private MenuItem insertMenuGroup;
	private MenuItem addMenu;
	private MenuItem addMenuGroup;
	private MenuItem delete;
	private MenuItem modify;

	private final MenuTree tree;

	private final MenuEditor menuEditor;
	private final MenuGroupEditor menuGroupEditor;

	public GxtMenu(MenuTree tree2) {
		this.tree = tree2;
		menuEditor = new MenuEditor();
		menuGroupEditor = new MenuGroupEditor();

		insertMenu = new MenuItem("插入菜单");
		insertMenuGroup = new MenuItem("插入菜单组");
		addMenu = new MenuItem("添加菜单");
		addMenuGroup = new MenuItem("添加菜单组");
		delete = new MenuItem("删除");
		modify = new MenuItem("修改 ");

		add(insertMenu);
		add(insertMenuGroup);
		add(new SeparatorMenuItem());
		add(addMenu);
		add(addMenuGroup);
		add(new SeparatorMenuItem());
		add(modify);
		add(delete);

		initInsertMenu();
		initInsertMenuGroup();
		initAddMenu();
		initAddMenuGroup();
		initDelete();
		initModify();

		this.addBeforeShowHandler(new BeforeShowHandler() {
			@Override
			public void onBeforeShow(BeforeShowEvent event) {
				MenuNode selectItem = tree.getSelectionModel()
						.getSelectedItem();
				if (selectItem == null) {
					GxtMenu.this.disable();
				}
				if (selectItem instanceof Menu) {
					addMenu.disable();
					addMenuGroup.disable();
				}
			}
		});

		this.addHideHandler(new HideHandler() {

			@Override
			public void onHide(HideEvent event) {
				GxtMenu.this.enable();
				addMenu.enable();
				addMenuGroup.enable();
			}
		});
	}

	private void initModify() {
		modify.addSelectionHandler(new SelectionHandler<Item>() {
			@Override
			public void onSelection(SelectionEvent<Item> event) {
				final MenuNode node = tree.getSelectionModel()
						.getSelectedItem();
				if (node instanceof Menu) {
					menuEditor.setUpdateCallBack(new GwtCallBack<Menu>() {
						@Override
						protected void _call(Menu t) {
							tree.getStore().update(t);
						}
					});
					menuEditor.edit((Menu) node);
				} else {
					menuGroupEditor
							.setUpdateCallBack(new GwtCallBack<MenuGroup>() {
								@Override
								protected void _call(MenuGroup t) {
									tree.getStore().update(t);
								}
							});
					menuGroupEditor.edit((MenuGroup) node);
				}
			}
		});
	}

	protected void initDelete() {
		delete.addSelectionHandler(new SelectionHandler<Item>() {
			@Override
			public void onSelection(SelectionEvent<Item> event) {
				final MenuNode node = tree.getSelectionModel()
						.getSelectedItem();
				service.delete(new Provider<Integer>(node.getId()),
						new RpcAsyncCallback<Void>() {
							@Override
							public void _onSuccess(Void t) {
								tree.getStore().remove(node);
							}

							@Override
							public void _onFailure(Throwable caught) {
								super._onFailure(caught);
								tree.reset();
							}
						});
			}
		});
	}

	protected void initAddMenuGroup() {
		addMenuGroup.addSelectionHandler(new SelectionHandler<Item>() {
			@Override
			public void onSelection(SelectionEvent<Item> event) {
				final MenuGroup selectItem = (MenuGroup) tree
						.getSelectionModel().getSelectedItem();
				menuGroupEditor
						.setMenuGroupNewInstance(menuGroupEditor.new MenuGroupNewInstance() {
							@Override
							public MenuGroup _get() {
								MenuGroup mg = new MenuGroup();
								mg.setParent(selectItem);
								MenuNode prev = tree.getStore().getLastChild(
										selectItem);
								mg.setPrev(prev);
								return mg;
							}
						});
				menuGroupEditor.add();
				menuGroupEditor.setAddCallBack(new GwtCallBack<MenuGroup>() {
					@Override
					protected void _call(final MenuGroup t) {
						tree.getStore().add(selectItem, t);
					}
				});
			}
		});
	}

	protected void initAddMenu() {
		addMenu.addSelectionHandler(new SelectionHandler<Item>() {
			@Override
			public void onSelection(SelectionEvent<Item> event) {
				final MenuGroup selectItem = (MenuGroup) tree
						.getSelectionModel().getSelectedItem();
				menuEditor.setMenuNewInstance(menuEditor.new MenuNewInstance() {
					@Override
					public Menu _get() {
						Menu menu = new Menu();
						menu.setParent(selectItem);
						MenuNode prev = tree.getStore()
								.getLastChild(selectItem);
						menu.setPrev(prev);
						return menu;
					}
				});
				menuEditor.add();
				menuEditor.setAddCallBack(new GwtCallBack<Menu>() {
					@Override
					protected void _call(final Menu t) {
						tree.getStore().add(selectItem, t);
					}
				});
			}
		});
	}

	protected void initInsertMenuGroup() {
		insertMenuGroup.addSelectionHandler(new SelectionHandler<Item>() {
			@Override
			public void onSelection(SelectionEvent<Item> event) {
				final MenuNode selectItem = tree.getSelectionModel()
						.getSelectedItem();
				menuGroupEditor
						.setMenuGroupNewInstance(menuGroupEditor.new MenuGroupNewInstance() {
							@Override
							public MenuGroup _get() {
								MenuGroup mg = new MenuGroup();
								mg.setParent(selectItem.getParent());
								mg.setPrev(selectItem);
								return mg;
							}
						});
				menuGroupEditor.add();
				menuGroupEditor.setAddCallBack(new GwtCallBack<MenuGroup>() {
					@Override
					protected void _call(final MenuGroup t) {
						MenuGroup parent = (MenuGroup) tree.getStore()
								.findModel(selectItem.getParent());
						int index = tree.getStore().indexOf(selectItem) + 1;
						if (parent == null) {
							tree.getStore().insert(index, t);
						} else {
							tree.getStore().insert(parent, index, t);
						}
					}
				});
			}
		});
	}

	protected void initInsertMenu() {
		insertMenu.addSelectionHandler(new SelectionHandler<Item>() {
			@Override
			public void onSelection(SelectionEvent<Item> event) {
				final MenuNode selectItem = tree.getSelectionModel()
						.getSelectedItem();
				menuEditor.setMenuNewInstance(menuEditor.new MenuNewInstance() {
					@Override
					public Menu _get() {
						Menu menu = new Menu();
						menu.setParent(selectItem.getParent());
						menu.setPrev(selectItem);
						return menu;
					}
				});
				menuEditor.add();
				menuEditor.setAddCallBack(new GwtCallBack<Menu>() {
					@Override
					protected void _call(final Menu t) {
						MenuGroup parent = (MenuGroup) tree.getStore()
								.findModel(selectItem.getParent());
						int index = tree.getStore().indexOf(selectItem) + 1;
						if (parent == null) {
							tree.getStore().insert(index, t);
						} else {
							tree.getStore().insert(parent, index, t);
						}
					}
				});
			}
		});
	}

	public MenuItem getInsertMenu() {
		return insertMenu;
	}

	public void setInsertMenu(MenuItem insertMenu) {
		this.insertMenu = insertMenu;
	}

	public MenuItem getInsertMenuGroup() {
		return insertMenuGroup;
	}

	public void setInsertMenuGroup(MenuItem insertMenuGroup) {
		this.insertMenuGroup = insertMenuGroup;
	}

	public MenuItem getAddMenu() {
		return addMenu;
	}

	public void setAddMenu(MenuItem addMenu) {
		this.addMenu = addMenu;
	}

	public MenuItem getAddMenuGroup() {
		return addMenuGroup;
	}

	public void setAddMenuGroup(MenuItem addMenuGroup) {
		this.addMenuGroup = addMenuGroup;
	}

	public MenuItem getDelete() {
		return delete;
	}

	public void setDelete(MenuItem delete) {
		this.delete = delete;
	}

}
