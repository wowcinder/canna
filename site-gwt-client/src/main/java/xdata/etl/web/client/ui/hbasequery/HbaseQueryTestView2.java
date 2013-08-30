/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui.hbasequery;

import java.util.ArrayList;
import java.util.List;

import xdata.etl.web.client.common.paging.EtlPagingLoader;
import xdata.etl.web.client.ui.CenterView;
import xdata.etl.web.shared.annotations.MenuToken;
import xdata.etl.web.shared.common.paging.EtlPagingLoadConfigBean;
import xdata.etl.web.shared.common.paging.HbaseQueryPagingCondition;
import xdata.etl.web.shared.hbasequery.HbaseRecord;
import xdata.etl.web.shared.service.hbasequery.HbaseQueryRpcService;
import xdata.etl.web.shared.service.hbasequery.HbaseQueryRpcServiceAsync;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.core.client.resources.ThemeStyles;
import com.sencha.gxt.data.client.loader.RpcProxy;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
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
@MenuToken(name = "hbase查询测试2", token = "hbase_query_test2", group = "hbase查询")
public class HbaseQueryTestView2 extends VerticalLayoutContainer implements
		CenterView {
	private static final HbaseQueryRpcServiceAsync service = GWT
			.<HbaseQueryRpcServiceAsync> create(HbaseQueryRpcService.class);

	public HbaseQueryTestView2() {

		RpcProxy<EtlPagingLoadConfigBean, PagingLoadResult<HbaseRecord<String>>> proxy = new RpcProxy<EtlPagingLoadConfigBean, PagingLoadResult<HbaseRecord<String>>>() {
			@Override
			public void load(
					EtlPagingLoadConfigBean loadConfig,
					AsyncCallback<PagingLoadResult<HbaseRecord<String>>> callback) {
				service.get(loadConfig, callback);
			}
		};

		ListStore<HbaseRecord<String>> store = new ListStore<HbaseRecord<String>>(
				new ModelKeyProvider<HbaseRecord<String>>() {
					@Override
					public String getKey(HbaseRecord<String> item) {
						return item.getKey();
					}
				});

		final EtlPagingLoader<HbaseRecord<String>> gridLoader = new EtlPagingLoader<HbaseRecord<String>>(
				proxy);
		HbaseQueryPagingCondition condition = new HbaseQueryPagingCondition();
		condition.setTableName("msg_v3a_user_auth");
		gridLoader.setCondition(condition);
		gridLoader.setRemoteSort(true);

		ColumnConfig<HbaseRecord<String>, String> nameColumn = new ColumnConfig<HbaseRecord<String>, String>(
				new ValueProvider<HbaseRecord<String>, String>() {
					@Override
					public String getValue(HbaseRecord<String> object) {
						return object.getKey();
					}

					@Override
					public void setValue(HbaseRecord<String> object,
							String value) {

					}

					@Override
					public String getPath() {
						return null;
					}
				}, 200, "key");

		List<ColumnConfig<HbaseRecord<String>, ?>> l = new ArrayList<ColumnConfig<HbaseRecord<String>, ?>>();
		l.add(nameColumn);

		final LiveGridView<HbaseRecord<String>> liveGridView = new LiveGridView<HbaseRecord<String>>();
		liveGridView.setForceFit(true);

		ColumnModel<HbaseRecord<String>> cm = new ColumnModel<HbaseRecord<String>>(
				l);

		Grid<HbaseRecord<String>> view = new Grid<HbaseRecord<String>>(store,
				cm) {
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
