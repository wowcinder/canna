/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xdata.etl.web.client.event.CenterVievChangeEvent;
import xdata.etl.web.client.service.RpcAsyncCallback;
import xdata.etl.web.client.service.menu.MenuService;
import xdata.etl.web.client.service.menu.MenuServiceAsync;
import xdata.etl.web.shared.entity.menu.Menu;

import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.text.shared.SafeHtmlRenderer;
import com.google.gwt.text.shared.SimpleSafeHtmlRenderer;
import com.google.gwt.user.client.ui.Composite;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.sencha.gxt.cell.core.client.SimpleSafeHtmlCell;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.widget.core.client.container.Viewport;
import com.sencha.gxt.widget.core.client.tree.Tree;

/**
 * @author XuehuiHe
 * @date 2013年8月9日
 */
public class MenuViewImpl extends Composite implements MenuView {
	private static MenuServiceAsync service = GWT.create(MenuService.class);

	@Inject
	private EventBus eventBus;

	private Tree<MenuNode, MenuNode> tree;

	class KeyProvider implements ModelKeyProvider<MenuNode> {
		@Override
		public String getKey(MenuNode item) {
			return item.getToken();
		}
	}

	private Viewport c;

	public MenuViewImpl() {
		c = new Viewport();
		initWidget(c);
	}

	@Override
	public void init() {
		TreeStore<MenuNode> store = new TreeStore<MenuViewImpl.MenuNode>(
				new KeyProvider());
		tree = new Tree<MenuViewImpl.MenuNode, MenuNode>(store,
				new ValueProvider<MenuNode, MenuNode>() {

					@Override
					public MenuNode getValue(MenuNode object) {
						return object;
					}

					@Override
					public void setValue(MenuNode object, MenuNode value) {
					}

					@Override
					public String getPath() {
						return "name";
					}
				});
		SimpleSafeHtmlCell<MenuNode> cell = new SimpleSafeHtmlCell<MenuViewImpl.MenuNode>(
				new SafeHtmlRenderer<MenuViewImpl.MenuNode>() {

					@Override
					public SafeHtml render(MenuNode object) {
						return SimpleSafeHtmlRenderer.getInstance().render(
								object.getName());
					}

					@Override
					public void render(MenuNode object, SafeHtmlBuilder builder) {
						SimpleSafeHtmlRenderer.getInstance().render(
								object.getName(), builder);

					}
				}, "click") {
			@Override
			public void onBrowserEvent(
					com.google.gwt.cell.client.Cell.Context context,
					Element parent, MenuNode value, NativeEvent event,
					ValueUpdater<MenuNode> valueUpdater) {
				super.onBrowserEvent(context, parent, value, event,
						valueUpdater);
				if ("click".equals(event.getType()) && !value.isFolder()) {
					eventBus.fireEvent(new CenterVievChangeEvent(
							CenterVievChangeEvent.From.LEFT, value.getToken()));
				}
			}

		};
		tree.setCell(cell);
		tree.setWidth(300);
		tree.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		c.setWidget(tree);

		getData();

	}

	@Override
	public void showMenu(String token) {
		MenuNode mn = tree.getStore().findModelWithKey(token);
		if (mn != null) {
			MenuNode parent = tree.getStore().getParent(mn);
			if (parent != null) {
				tree.setExpanded(parent, true);
			}
			// tree.scrollIntoView(mn);
			// TODO
			tree.getSelectionModel().select(mn, true);
		} else {
			tree.getSelectionModel().deselectAll();
		}

	}

	private void setToStore(List<Menu> list) {
		Map<String, List<Menu>> map = new HashMap<String, List<Menu>>();
		for (Menu menu : list) {
			String mgName = null;
			if (menu.getMenuGroup() != null) {
				mgName = menu.getMenuGroup().getName();
			} else {
				mgName = "none";
			}
			if (!map.containsKey(mgName)) {
				map.put(mgName, new ArrayList<Menu>());
			}
			map.get(mgName).add(menu);
		}

		TreeStore<MenuNode> store = tree.getStore();
		for (String mgName : map.keySet()) {
			if (mgName.equals("none")) {
				continue;
			}
			MenuNode group = new MenuNode();
			group.setName(mgName);
			group.setToken(mgName + "_group");
			group.setFolder(true);
			store.add(group);
			for (Menu menu : map.get(mgName)) {
				MenuNode mn = new MenuNode();
				mn.setName(menu.getName());
				mn.setToken(menu.getToken());
				store.add(group, mn);
			}
		}
		if (map.get("none") != null) {
			for (Menu menu : map.get("none")) {
				MenuNode mn = new MenuNode();
				mn.setName(menu.getName());
				mn.setToken(menu.getToken());
				store.add(mn);
			}
		}
	}

	private void getData() {
		service.get(new RpcAsyncCallback<List<Menu>>() {
			@Override
			public void _onSuccess(List<Menu> t) {
				setToStore(t);
			}
		});
	}

	public static class MenuNode {
		private boolean isFolder = false;
		private String name;
		private String token;

		public boolean isFolder() {
			return isFolder;
		}

		public void setFolder(boolean isFolder) {
			this.isFolder = isFolder;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getToken() {
			return token;
		}

		public void setToken(String token) {
			this.token = token;
		}
	}
}
