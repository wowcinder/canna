/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui;

import java.util.List;

import xdata.etl.web.client.RpcAsyncCallback;
import xdata.etl.web.client.event.CenterVievChangeEvent;
import xdata.etl.web.shared.entity.menu.Menu;
import xdata.etl.web.shared.entity.menu.MenuGroup;
import xdata.etl.web.shared.entity.menu.MenuNode;
import xdata.etl.web.shared.service.menu.MenuNodeRpcService;
import xdata.etl.web.shared.service.menu.MenuNodeRpcServiceAsync;

import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.text.shared.SafeHtmlRenderer;
import com.google.gwt.text.shared.SimpleSafeHtmlRenderer;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.sencha.gxt.cell.core.client.SimpleSafeHtmlCell;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.widget.core.client.Composite;
import com.sencha.gxt.widget.core.client.container.Viewport;
import com.sencha.gxt.widget.core.client.tree.Tree;

/**
 * @author XuehuiHe
 * @date 2013年8月9日
 */
public class MenuViewImpl extends Composite implements MenuView {
	private static MenuNodeRpcServiceAsync service = GWT
			.create(MenuNodeRpcService.class);

	@Inject
	private EventBus eventBus;

	private Tree<MenuNode, MenuNode> tree;

	private TreeStore<MenuNode> store;

	class KeyProvider implements ModelKeyProvider<MenuNode> {
		@Override
		public String getKey(MenuNode item) {
			return item.getId() + "";
		}
	}

	private Viewport c;

	public MenuViewImpl() {
		c = new Viewport();
		initWidget(c);
	}

	@Override
	public void init() {
		store = new TreeStore<MenuNode>(new KeyProvider());
		tree = new Tree<MenuNode, MenuNode>(store,
				new ValueProvider<MenuNode, MenuNode>() {

					@Override
					public MenuNode getValue(MenuNode object) {
						return object;
					}

					@Override
					public void setValue(MenuNode object, MenuNode value) {
						object = value;
					}

					@Override
					public String getPath() {
						return "name";
					}
				});
		SimpleSafeHtmlCell<MenuNode> cell = new SimpleSafeHtmlCell<MenuNode>(
				new SafeHtmlRenderer<MenuNode>() {

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
				if ("click".equals(event.getType())) {
					if (value instanceof Menu) {

						eventBus.fireEvent(new CenterVievChangeEvent(
								CenterVievChangeEvent.From.LEFT, ((Menu) value)
										.getToken()));
					}
				}
			}

		};
		tree.setCell(cell);
		tree.setWidth(300);
		tree.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		c.setWidget(tree);

		getData();

	}

	protected void initData(List<MenuNode> result) {
		for (MenuNode menuNode : result) {
			initData(null, menuNode);
		}

	}

	protected void initData(MenuNode parent, MenuNode menuNode) {
		if (menuNode == null) {
			return;
		}
		if (parent == null) {
			store.add(menuNode);
		} else {
			store.add(parent, menuNode);
		}
		if (menuNode instanceof MenuGroup) {
			List<MenuNode> nodes = ((MenuGroup) menuNode).getNodes();
			if (nodes == null || nodes.size() == 0) {
				return;
			}
			for (MenuNode node : nodes) {
				initData(menuNode, node);
			}
		}
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

	private void getData() {
		service.get(new RpcAsyncCallback<List<MenuNode>>() {
			@Override
			public void _onSuccess(List<MenuNode> t) {
				initData(t);
			}
		});
	}

}
