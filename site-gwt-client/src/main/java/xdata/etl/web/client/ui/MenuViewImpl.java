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
import xdata.etl.web.shared.service.login.LoginRpcService;
import xdata.etl.web.shared.service.login.LoginRpcServiceAsync;
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
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.sencha.gxt.cell.core.client.SimpleSafeHtmlCell;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.widget.core.client.tree.Tree;

/**
 * @author XuehuiHe
 * @date 2013年8月9日
 */
public class MenuViewImpl extends Tree<MenuNode, MenuNode> implements MenuView {
	private static final MenuNodeRpcServiceAsync service = GWT
			.create(MenuNodeRpcService.class);
	private static final LoginRpcServiceAsync loginService = GWT
			.create(LoginRpcService.class);

	@Inject
	private EventBus eventBus;

	public static class KeyProvider implements ModelKeyProvider<MenuNode> {
		@Override
		public String getKey(MenuNode item) {
			return item.getId() + "";
		}
	}

	public MenuViewImpl() {
		super(new TreeStore<MenuNode>(new KeyProvider()),
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
					} else if (value instanceof LogoutMenu) {
						loginService.logout(new AsyncCallback<Void>() {

							@Override
							public void onSuccess(Void result) {
								Window.Location.reload();
							}

							@Override
							public void onFailure(Throwable caught) {

							}
						});
					}
				}
			}

		};
		setCell(cell);
		setWidth(300);
		getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

		init();
	}

	@Override
	public void init() {
		getData();
	}

	protected void initData(List<MenuNode> result) {
		store.clear();
		for (MenuNode menuNode : result) {
			initData(null, menuNode);
		}
		store.add(new LogoutMenu());
	}

	protected void initData(MenuNode parent, MenuNode menuNode) {
		if (menuNode == null) {
			return;
		}
		if (menuNode instanceof MenuGroup) {
			List<MenuNode> nodes = ((MenuGroup) menuNode).getNodes();
			if (nodes == null || nodes.size() == 0) {
				return;
			}
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
		MenuNode mn = getStore().findModelWithKey(token);
		if (mn != null) {
			MenuNode parent = getStore().getParent(mn);
			if (parent != null) {
				setExpanded(parent, true);
			}
			// tree.scrollIntoView(mn);
			// TODO
			getSelectionModel().select(mn, true);
		} else {
			getSelectionModel().deselectAll();
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

	public class LogoutMenu extends MenuNode {
		private static final long serialVersionUID = 6258185941078205551L;

		public LogoutMenu() {
			setName("登出");
			setId(-1);
		}
	}

}
