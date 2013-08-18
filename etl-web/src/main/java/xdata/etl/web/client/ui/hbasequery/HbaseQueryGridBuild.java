/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui.hbasequery;

import java.util.List;

import xdata.etl.web.client.gwt.GwtCallBack;
import xdata.etl.web.client.service.RpcAsyncCallback;
import xdata.etl.web.client.service.hbasemeta.HbaseTableService;
import xdata.etl.web.client.service.hbasemeta.HbaseTableServiceAsync;
import xdata.etl.web.shared.entity.hbasemeta.HbaseTableColumn;

import com.google.gwt.core.shared.GWT;

/**
 * @author XuehuiHe
 * @date 2013年8月15日
 * 
 */
public class HbaseQueryGridBuild {
	private static final HbaseTableServiceAsync servie = GWT
			.<HbaseTableServiceAsync> create(HbaseTableService.class);

	private String table;
	private String[] versions;

	public HbaseQueryGridBuild(String table, String... versions) {
		this.table = table;
		this.versions = versions;
	}

	public void create(final GwtCallBack<HbaseQueryGrid> callback) {
		servie.get(table, versions,
				new RpcAsyncCallback<List<HbaseTableColumn>>() {
					@Override
					public void _onSuccess(List<HbaseTableColumn> t) {
						callback._call(new HbaseQueryGrid(t));
					}
				});
	}
}
