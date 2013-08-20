/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui.hbasequery;

import java.util.List;

import xdata.etl.web.client.common.grid.GridBuilder;
import xdata.etl.web.client.gwt.GwtCallBack;
import xdata.etl.web.client.service.RpcAsyncCallback;
import xdata.etl.web.client.service.hbasemeta.HbaseTableService;
import xdata.etl.web.client.service.hbasemeta.HbaseTableServiceAsync;
import xdata.etl.web.shared.entity.hbasemeta.HbaseTableColumn;
import xdata.etl.web.shared.hbasequery.HbaseRecord;

import com.google.gwt.core.shared.GWT;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;

/**
 * @author XuehuiHe
 * @date 2013年8月15日
 * 
 */
public class HbaseQueryGridBuild extends GridBuilder<HbaseRecord<String>> {
	private static final HbaseTableServiceAsync servie = GWT
			.<HbaseTableServiceAsync> create(HbaseTableService.class);

	private String table;
	private String[] versions;

	public HbaseQueryGridBuild(String table, String... versions) {
		super(false);
		this.table = table;
		this.versions = versions;

		setKeyProvider(new ModelKeyProvider<HbaseRecord<String>>() {
			@Override
			public String getKey(HbaseRecord<String> item) {
				if (item != null) {
					return item.getKey();
				}
				return null;
			}
		});
	}

	protected void initColumnModel() {
		getColumnConfigs().add(
				new ColumnConfig<HbaseRecord<String>, String>(
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
						}, 200, "key"));
	}

	public void getInitColumns(final GwtCallBack<Void> callback) {
		servie.get(table, versions,
				new RpcAsyncCallback<List<HbaseTableColumn>>() {
					@Override
					public void _onSuccess(List<HbaseTableColumn> t) {
						for (HbaseTableColumn hbaseTableColumn : t) {
							getColumnConfigs().add(
									new HbaseColumnConfig<String>(
											hbaseTableColumn));
						}
						callback.call(null);
					}
				});
	}

}
