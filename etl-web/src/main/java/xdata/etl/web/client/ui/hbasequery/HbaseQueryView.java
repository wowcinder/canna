/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui.hbasequery;

import java.util.List;

import xdata.etl.web.client.RpcAsyncCallback;
import xdata.etl.web.client.gwt.GwtCallBack;
import xdata.etl.web.client.ui.CenterView;
import xdata.etl.web.shared.annotations.MenuToken;
import xdata.etl.web.shared.hbasequery.HbaseRecord;
import xdata.etl.web.shared.service.hbasequery.HbaseQueryService;
import xdata.etl.web.shared.service.hbasequery.HbaseQueryServiceAsync;

import com.google.gwt.core.shared.GWT;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.grid.Grid;

/**
 * @author XuehuiHe
 * @date 2013年8月15日
 * 
 */
@MenuToken(name = "hbase查询", token = "hbase_query")
public class HbaseQueryView extends VerticalLayoutContainer implements
		CenterView {
	private static final HbaseQueryServiceAsync servie2 = GWT
			.<HbaseQueryServiceAsync> create(HbaseQueryService.class);

	private Grid<HbaseRecord<String>> grid;

	private CenterViewConfig centerViewConfig;

	public HbaseQueryView() {

		GwtCallBack<Void> gridCallBack = new GwtCallBack<Void>() {

			@Override
			protected void _call(Void t) {
				refreshGrid();
			}
		};
		HbaseQueryGridBuild build = new HbaseQueryGridBuild("msg_v3a_user_auth");
		grid = build.create();
		add(grid, new VerticalLayoutData(1, 1));
		build.getInitColumns(gridCallBack);
	}

	public void refreshGrid() {
		grid.getView().refresh(true);
		servie2.getData("msg_v3a_user_auth", null,
				new RpcAsyncCallback<List<HbaseRecord<String>>>() {
					@Override
					public void _onSuccess(List<HbaseRecord<String>> t) {
						if (t != null) {
							grid.getStore().addAll(t);
						}
					}
				});
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
