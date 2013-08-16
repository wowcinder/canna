/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui.hbasequery;

import java.util.List;

import xdata.etl.web.client.gwt.GwtCallBack;
import xdata.etl.web.client.service.RpcAsyncCallback;
import xdata.etl.web.client.service.hbasequery.HbaseQueryService;
import xdata.etl.web.client.service.hbasequery.HbaseQueryServiceAsync;
import xdata.etl.web.client.ui.CenterView;
import xdata.etl.web.shared.annotations.MenuToken;
import xdata.etl.web.shared.hbasemeta.HbaseRecord;

import com.google.gwt.core.shared.GWT;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;

/**
 * @author XuehuiHe
 * @date 2013年8月15日
 * 
 */
@MenuToken(name = "hbase查询", token = "hbase_query")
public class HbaseQueryView extends CenterView {
	private static final HbaseQueryServiceAsync servie2 = GWT
			.<HbaseQueryServiceAsync> create(HbaseQueryService.class);

	public HbaseQueryView() {
		super();

		final VerticalLayoutContainer v = new VerticalLayoutContainer();
		HbaseQueryGridBuild build = new HbaseQueryGridBuild("msg_v3a_user_auth");
		build.create(new GwtCallBack<HbaseQueryGrid>() {

			@Override
			public void call(final HbaseQueryGrid grid) {
				v.add(grid);

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
		});

		con.setWidget(v);
	}
}
