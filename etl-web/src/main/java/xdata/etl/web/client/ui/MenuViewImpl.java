/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui;

import xdata.etl.web.client.event.CenterVievChangeEvent;

import com.google.gwt.cell.client.ValueUpdater;
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
		MenuNode data = getData();
		TreeStore<MenuNode> store = new TreeStore<MenuViewImpl.MenuNode>(
				new KeyProvider());
		store.add(data);
		MenuNode mn = new MenuNode();
		mn.setName("测试1");
		mn.setToken("token1");

		store.add(data, mn);

		mn = new MenuNode();
		mn.setName("测试2");
		mn.setToken("token2");

		store.add(data, mn);
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

	}

	@Override
	public void showMenu(String token) {
		MenuNode mn = tree.getStore().findModelWithKey(token);
		if (mn != null) {
			MenuNode parent = tree.getStore().getParent(mn);
			tree.setExpanded(parent, true);
			tree.scrollIntoView(mn);
			tree.getSelectionModel().select(mn, true);
		} else {
			tree.getSelectionModel().deselectAll();
		}

	}

	private MenuNode getData() {
		MenuNode folder = new MenuNode();
		folder.setName("测试组");
		folder.setToken("jsdldjfl");
		folder.setFolder(true);
		return folder;
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
