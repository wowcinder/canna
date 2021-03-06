package xdata.etl.testweb.client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import xdata.etl.testweb.shared.entity.menu.MenuNode;

import com.google.gwt.cell.client.DateCell;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.core.client.resources.ThemeStyles;
import com.sencha.gxt.data.client.loader.RpcProxy;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoader;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.Resizable;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.LiveGridView;
import com.sencha.gxt.widget.core.client.grid.LiveToolItem;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

public class Etl implements EntryPoint {

	@Override
	public void onModuleLoad() {
		final MenuServiceAsync service = GWT.create(MenuService.class);

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

		VerticalLayoutContainer con = new VerticalLayoutContainer();
		con.setBorders(true);
		con.add(view, new VerticalLayoutData(1, 1));

		ToolBar toolBar = new ToolBar();
		toolBar.add(new LiveToolItem(view));
		toolBar.addStyleName(ThemeStyles.getStyle().borderTop());
		toolBar.getElement().getStyle().setProperty("borderBottom", "none");

		con.add(toolBar, new VerticalLayoutData(1, 25));

		FramedPanel root = new FramedPanel();
		root.setCollapsible(true);
		root.setHeadingText("Live Grid Example");
		root.setPixelSize(600, 390);
		root.addStyleName("margin-10");
		new Resizable(root);
		root.setWidget(con);
		
		RootPanel.get().add(root);

	}

	public static class TestRecord implements Serializable {
		private static final long serialVersionUID = 190991400971013644L;
		private String key;

		public TestRecord() {
		}

		public TestRecord(String key) {
			this.key = key;
		}

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

	}
}
