/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui.hbasequery;

import xdata.etl.web.client.RpcAsyncCallback;
import xdata.etl.web.client.common.paging.EtlPagingLoader;
import xdata.etl.web.client.gwt.GwtCallBack;
import xdata.etl.web.shared.common.paging.EtlPagingLoadConfigBean;
import xdata.etl.web.shared.common.paging.HbaseQueryPagingCondition;
import xdata.etl.web.shared.hbasequery.HbaseRecord;
import xdata.etl.web.shared.service.hbasequery.HbaseQueryRpcService;
import xdata.etl.web.shared.service.hbasequery.HbaseQueryRpcServiceAsync;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.data.client.loader.RpcProxy;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.loader.LoadResultListStoreBinding;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.GridView;

/**
 * @author XuehuiHe
 * @date 2013年8月15日
 * 
 */
public class HbaseQueryView extends VerticalLayoutContainer {
	private static final HbaseQueryRpcServiceAsync servie2 = GWT
			.<HbaseQueryRpcServiceAsync> create(HbaseQueryRpcService.class);

	private Grid<HbaseRecord<String>> grid;
	private GwtCallBack<Void> headerCallBack;
	final private int cacheSize;
	final private String tableName;
	private EtlPagingLoader<HbaseRecord<String>> loader;

	public HbaseQueryView(String tableName) {
		this(tableName, 80);
	}

	public HbaseQueryView(String tableName, int cacheSize) {
		this.tableName = tableName;
		this.cacheSize = cacheSize;

		headerCallBack = new GwtCallBack<Void>() {
			@Override
			protected void _call(Void t) {
				refreshGridHeader();
			}
		};
		HbaseQueryGridBuild build = new HbaseQueryGridBuild(tableName) {
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
										getInitColumns(headerCallBack);
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

		loader = new EtlPagingLoader<HbaseRecord<String>>(proxy) {
			protected void onLoadSuccess(
					EtlPagingLoadConfigBean loadConfig,
					com.sencha.gxt.data.shared.loader.PagingLoadResult<xdata.etl.web.shared.hbasequery.HbaseRecord<String>> result) {
				super.onLoadSuccess(loadConfig, result);
				EtlPagingLoadConfigBean config = ((EtlPagingLoadConfigBean) grid
						.getLoader().getLastLoadConfig());
				HbaseQueryPagingLoadResultBean<HbaseRecord<String>> pr = (HbaseQueryPagingLoadResultBean<HbaseRecord<String>>) result;
				((HbaseQueryPagingCondition) config.getCondition())
						.setLastRow(pr.getLastRow());
			};
		};
		loader.setRemoteSort(true);

		grid.setLoader(loader);
		HbaseQueryPagingCondition condition = new HbaseQueryPagingCondition();
		condition.setTableName(tableName);
		loader.setCondition(condition);

		loader.addLoadHandler(new LoadResultListStoreBinding<EtlPagingLoadConfigBean, HbaseRecord<String>, PagingLoadResult<HbaseRecord<String>>>(
				grid.getStore()));
		GridView<HbaseRecord<String>> gridView = new HbaseGridView<HbaseRecord<String>>() {

			@Override
			protected void _doLoadNext() {
				EtlPagingLoadConfigBean config = loader.getLastLoadConfig();
				config.setOffset(config.getOffset() + config.getLimit());
				servie2.get(
						config,
						new RpcAsyncCallback<PagingLoadResult<HbaseRecord<String>>>() {

							@Override
							public void _onSuccess(
									PagingLoadResult<HbaseRecord<String>> t) {
								getDoLoadNextCallBack().call(t);
							}
						});
			}
		};
		grid.setView(gridView);
		add(grid, new VerticalLayoutData(1, 1));

	}

	public void refreshGridHeader() {
		grid.getView().refresh(true);
		loader.load(0, getCacheSize());
	}

	public String getTableName() {
		return tableName;
	}

	public int getCacheSize() {
		return cacheSize;
	}

}
