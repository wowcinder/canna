/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui.menu;

import java.util.List;

import xdata.etl.web.client.ServiceUtil;
import xdata.etl.web.client.gwt.GwtCallBack;
import xdata.etl.web.client.ui.CenterView;
import xdata.etl.web.client.ui.menu.tree.MenuTree;
import xdata.etl.web.shared.annotations.MenuToken;
import xdata.etl.web.shared.entity.menu.MenuGroup;
import xdata.etl.web.shared.entity.menu.MenuNode;

import com.google.gwt.dom.client.Style.TextAlign;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;

/**
 * @author XuehuiHe
 * @date 2013年8月9日
 */
@MenuToken(name = "菜单管理", token = "menu_manager", group = "菜单管理")
public class MenuView extends HorizontalLayoutContainer implements CenterView {
	private CenterViewConfig centerViewConfig;

	private MenuTree tree;
	private final TreeStore<MenuNode> store;

	public MenuView() {
		store = new TreeStore<MenuNode>(new ModelKeyProvider<MenuNode>() {
			@Override
			public String getKey(MenuNode item) {
				return item.getId() + "";
			}
		});
		// for test
		// TextButton tb = new TextButton("reset");
		// tb.addSelectHandler(new SelectHandler() {
		// @Override
		// public void onSelect(SelectEvent event) {
		// tree.reset();
		// }
		// });
		// add(tb, new HorizontalLayoutData(-1, 1, new Margins(10)));

		init();
	}

	private void init() {
		ServiceUtil.MenuNodeRpcCaller.get(new GwtCallBack<List<MenuNode>>() {

			@Override
			protected void _call(List<MenuNode> t) {
				initData(t);
			}
		});
	}

	public void initData(List<MenuNode> result) {
		for (MenuNode menuNode : result) {
			initData(null, menuNode);
		}
		tree = new MenuTree(store);
		tree.getElement().getStyle().setTextAlign(TextAlign.LEFT);
		tree.setWidth(300);

		add(tree, new HorizontalLayoutData(-1, 1, new Margins(10)));

		forceLayout();
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
	public CenterViewConfig getCenterViewConfig() {
		return centerViewConfig;
	}

	@Override
	public void setCenterViewConfig(CenterViewConfig centerViewConfig) {
		this.centerViewConfig = centerViewConfig;
	}

}
