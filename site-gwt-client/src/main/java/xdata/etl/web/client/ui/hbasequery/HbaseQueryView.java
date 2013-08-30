/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui.hbasequery;

import xdata.etl.web.client.common.paging.EtlPagingLoader;
import xdata.etl.web.client.gwt.GwtCallBack;
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
import com.sencha.gxt.core.client.resources.ThemeStyles;
import com.sencha.gxt.data.client.loader.RpcProxy;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.LiveGridView;
import com.sencha.gxt.widget.core.client.grid.LiveToolItem;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

/**
 * @author XuehuiHe
 * @date 2013年8月15日
 * 
 */
@MenuToken(name = "hbase查询", token = "hbase_query", group = "hbase查询")
public class HbaseQueryView extends VerticalLayoutContainer implements
		CenterView {
	private static final HbaseQueryRpcServiceAsync servie2 = GWT
			.<HbaseQueryRpcServiceAsync> create(HbaseQueryRpcService.class);

	private Grid<HbaseRecord<String>> grid;

	private CenterViewConfig centerViewConfig;

	public HbaseQueryView() {

		GwtCallBack<Void> gridCallBack = new GwtCallBack<Void>() {

			@Override
			protected void _call(Void t) {
				refreshGrid();
			}
		};
		HbaseQueryGridBuild build = new HbaseQueryGridBuild("msg_v3a_user_auth") {
			@Override
			public Grid<HbaseRecord<String>> create() {
				initColumnModel();
				final Grid<HbaseRecord<String>> grid = new Grid<HbaseRecord<String>>(
						new ListStore<HbaseRecord<String>>(getKeyProvider()),
						new ColumnModel<HbaseRecord<String>>(getColumnConfigs())) {
					@Override
					protected void onAfterFirstAttach() {
						super.onAfterFirstAttach();
						Scheduler.get().scheduleDeferred(
								new ScheduledCommand() {
									@Override
									public void execute() {
										((EtlPagingLoader) HbaseQueryView.this.grid
												.getLoader()).load(0, 80);
									}
								});
					}
				};
				grid.setLoadMask(true);
				return grid;
			}
		};
		grid = build.create();

		RpcProxy<EtlPagingLoadConfigBean, PagingLoadResult<HbaseRecord<String>>> proxy = new RpcProxy<EtlPagingLoadConfigBean, PagingLoadResult<HbaseRecord<String>>>() {
			@Override
			public void load(
					EtlPagingLoadConfigBean loadConfig,
					AsyncCallback<PagingLoadResult<HbaseRecord<String>>> callback) {
				servie2.get(loadConfig, callback);
			}
		};

		EtlPagingLoader<HbaseRecord<String>> loader = new EtlPagingLoader<HbaseRecord<String>>(
				proxy);
		loader.setRemoteSort(true);

		HbaseQueryPagingCondition condition = new HbaseQueryPagingCondition();
		condition.setTableName("msg_v3a_user_auth");
		loader.setCondition(condition);

		grid.setLoader(loader);
		LiveGridView<HbaseRecord<String>> liveGridView = new LiveGridView<HbaseRecord<String>>();
//		liveGridView.setForceFit(true);
		liveGridView.setCacheSize(80);
		grid.setView(liveGridView);

		ToolBar toolBar = new ToolBar();
		toolBar.add(new LiveToolItem(grid));
		toolBar.addStyleName(ThemeStyles.getStyle().borderTop());
		toolBar.getElement().getStyle().setProperty("borderBottom", "none");

		add(grid, new VerticalLayoutData(1, 1));
		add(toolBar, new VerticalLayoutData(1, 25));
		// build.getInitColumns(gridCallBack);
	}

	public void refreshGrid() {
		grid.getView().refresh(true);
		/*
		 * servie2.getData("msg_v3a_user_auth", null, new
		 * RpcAsyncCallback<List<HbaseRecord<String>>>() {
		 * 
		 * @Override public void _onSuccess(List<HbaseRecord<String>> t) { if (t
		 * != null) { grid.getStore().addAll(t); } } });
		 */
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
