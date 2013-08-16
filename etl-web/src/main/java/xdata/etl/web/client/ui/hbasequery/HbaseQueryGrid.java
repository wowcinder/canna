/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui.hbasequery;

import java.util.ArrayList;
import java.util.List;

import xdata.etl.web.shared.entity.hbasemeta.HbaseTableColumn;
import xdata.etl.web.shared.hbasemeta.HbaseColumnConfig;
import xdata.etl.web.shared.hbasemeta.HbaseRecord;

import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;

/**
 * @author XuehuiHe
 * @date 2013年8月15日
 * 
 */
public class HbaseQueryGrid extends Grid<HbaseRecord<String>> {

	private static class HbaseQueryColumnModel extends
			ColumnModel<HbaseRecord<String>> {
		private List<ColumnConfig<HbaseRecord<String>, ?>> cc;

		public HbaseQueryColumnModel(
				final List<ColumnConfig<HbaseRecord<String>, ?>> list) {
			super(list);
			cc = list;
		}

		public List<ColumnConfig<HbaseRecord<String>, ?>> getCc() {
			return cc;
		}
	}

	private List<ColumnConfig<HbaseRecord<String>, ?>> columnConfigs;

	public HbaseQueryGrid(List<HbaseTableColumn> columns) {
		super(new ListStore<HbaseRecord<String>>(
				new ModelKeyProvider<HbaseRecord<String>>() {
					@Override
					public String getKey(HbaseRecord<String> item) {
						if (item != null) {
							return item.getKey();
						}
						return null;
					}
				}), new HbaseQueryColumnModel(
				new ArrayList<ColumnConfig<HbaseRecord<String>, ?>>()));
		this.columnConfigs = getColumnModel().getCc();
		columnConfigs.add(new ColumnConfig<HbaseRecord<String>, String>(
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
		initTableColumn(columns);
		setLoadMask(true);
		getView().setForceFit(true);
		getView().setAutoFill(true);
	}

	protected void initTableColumn(List<HbaseTableColumn> list) {
		for (HbaseTableColumn hbaseTableColumn : list) {
			getColumnConfigs().add(
					new HbaseColumnConfig<String>(hbaseTableColumn));
		}
	}

	@Override
	public HbaseQueryColumnModel getColumnModel() {
		return (HbaseQueryColumnModel) super.getColumnModel();
	}

	public List<ColumnConfig<HbaseRecord<String>, ?>> getColumnConfigs() {
		return columnConfigs;
	}

	public void setColumnConfigs(
			List<ColumnConfig<HbaseRecord<String>, ?>> columnConfigs) {
		this.columnConfigs = columnConfigs;
	}

}
