/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui.hbasequery;

import java.util.ArrayList;
import java.util.List;

import xdata.etl.web.client.ui.CenterView;
import xdata.etl.web.shared.annotations.MenuToken;
import xdata.etl.web.shared.entity.menu.MenuNode;
import xdata.etl.web.shared.service.menu.MenuNodeRpcService;
import xdata.etl.web.shared.service.menu.MenuNodeRpcServiceAsync;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.core.client.resources.ThemeStyles;
import com.sencha.gxt.data.client.loader.RpcProxy;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoader;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.Resizable;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.LiveGridView;
import com.sencha.gxt.widget.core.client.grid.LiveToolItem;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

/**
 * @author XuehuiHe
 * @date 2013年8月30日
 */
@MenuToken(name = "hbase查询测试", token = "hbase_query_test", group = "hbase查询")
public class HbaseQueryTestView extends VerticalLayoutContainer implements
		CenterView {
	public interface MenuNodeProps extends PropertyAccess<MenuNode> {
		@Path("id")
		ModelKeyProvider<MenuNode> id();

		ValueProvider<MenuNode, String> name();
	}

	public HbaseQueryTestView() {
		final MenuNodeRpcServiceAsync service = GWT
				.create(MenuNodeRpcService.class);

		RpcProxy<PagingLoadConfig, PagingLoadResult<MenuNode>> proxy = new RpcProxy<PagingLoadConfig, PagingLoadResult<MenuNode>>() {
			@Override
			public void load(PagingLoadConfig loadConfig,
					AsyncCallback<PagingLoadResult<MenuNode>> callback) {
				service.getMenuNodes(loadConfig, callback);
			}
		};

		MenuNodeProps props = GWT.create(MenuNodeProps.class);

		ListStore<MenuNode> store = new ListStore<MenuNode>(props.id());

		final PagingLoader<PagingLoadConfig, PagingLoadResult<MenuNode>> gridLoader = new PagingLoader<PagingLoadConfig, PagingLoadResult<MenuNode>>(
				proxy);
		gridLoader.setRemoteSort(true);

		ColumnConfig<MenuNode, String> nameColumn = new ColumnConfig<MenuNode, String>(
				props.name(), 150, "name");

		List<ColumnConfig<MenuNode, ?>> l = new ArrayList<ColumnConfig<MenuNode, ?>>();
		l.add(nameColumn);

		final LiveGridView<MenuNode> liveGridView = new LiveGridView<MenuNode>();
		liveGridView.setForceFit(true);

		ColumnModel<MenuNode> cm = new ColumnModel<MenuNode>(l);

		Grid<MenuNode> view = new Grid<MenuNode>(store, cm) {
			@Override
			protected void onAfterFirstAttach() {
				super.onAfterFirstAttach();
				Scheduler.get().scheduleDeferred(new ScheduledCommand() {
					@Override
					public void execute() {
						gridLoader.load(0, liveGridView.getCacheSize());
					}
				});
			}
		};

		view.setLoadMask(true);
		view.setLoader(gridLoader);

		view.setView(liveGridView);

		setBorders(true);
		add(view, new VerticalLayoutData(1, 1));

		ToolBar toolBar = new ToolBar();
		toolBar.add(new LiveToolItem(view));
		toolBar.addStyleName(ThemeStyles.getStyle().borderTop());
		toolBar.getElement().getStyle().setProperty("borderBottom", "none");

		add(toolBar, new VerticalLayoutData(1, 25));

		// FramedPanel root = new FramedPanel();
		// root.setCollapsible(true);
		// root.setHeadingText("Live Grid Example");
		// root.setPixelSize(600, 390);
		// root.addStyleName("margin-10");
		// new Resizable(root);
		// root.setWidget(con);

		// RootPanel.get().add(root);
	}

	private CenterViewConfig centerViewConfig;

	@Override
	public CenterViewConfig getCenterViewConfig() {
		return centerViewConfig;
	}

	@Override
	public void setCenterViewConfig(CenterViewConfig centerViewConfig) {
		this.centerViewConfig = centerViewConfig;
	}
}
