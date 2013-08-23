/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui.menu.tree;

import java.util.ArrayList;
import java.util.List;

import xdata.etl.web.client.gwt.GwtCallBack;
import xdata.etl.web.client.ui.menu.editor.MenuEditor;
import xdata.etl.web.client.ui.menu.editor.MenuGroupEditor;
import xdata.etl.web.shared.entity.menu.Menu;
import xdata.etl.web.shared.entity.menu.MenuNode;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.data.shared.TreeStore.TreeNode;
import com.sencha.gxt.widget.core.client.event.BeforeShowEvent;
import com.sencha.gxt.widget.core.client.event.BeforeShowEvent.BeforeShowHandler;
import com.sencha.gxt.widget.core.client.event.HideEvent;
import com.sencha.gxt.widget.core.client.event.HideEvent.HideHandler;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.MenuItem;
import com.sencha.gxt.widget.core.client.menu.SeparatorMenuItem;

/**
 * @author XuehuiHe
 * @date 2013年8月23日
 */
public class GxtMenu extends com.sencha.gxt.widget.core.client.menu.Menu {
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
						MenuNode parent = selectItem.getParent();
						int index = 0;
						if (parent == null) {
							index = tree.getStore().indexOf(selectItem) + 2;
						} else {
							index = tree.getStore()
									.getChildren(selectItem.getParent())
									.indexOf(selectItem) + 1;
						}
						List<TreeStore.TreeNode<MenuNode>> list = new ArrayList<TreeStore.TreeNode<MenuNode>>();
						TreeStore.TreeNode<MenuNode> node = new TreeStore.TreeNode<MenuNode>() {

							@Override
							public List<? extends TreeNode<MenuNode>> getChildren() {
								return null;
							}

							@Override
							public MenuNode getData() {
								return t;
							}
						};
						list.add(node);
						if (parent == null) {
							tree.getStore().addSubTree(index, list);
						} else {
							tree.getStore().addSubTree(parent, index, list);
						}

					}
				});
			}
		});

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
