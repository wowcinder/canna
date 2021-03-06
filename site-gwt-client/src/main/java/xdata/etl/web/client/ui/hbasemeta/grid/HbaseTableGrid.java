/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui.hbasemeta.grid;

import xdata.etl.web.client.common.grid.RpcEntityGridBuilder;
import xdata.etl.web.client.property.hbasemeta.HbaseTableProperty;
import xdata.etl.web.shared.entity.hbasemeta.HbaseTable;

import com.sencha.gxt.widget.core.client.grid.ColumnConfig;

/**
 * @author XuehuiHe
 * @date 2013年8月15日
 */
public class HbaseTableGrid extends
		RpcEntityGridBuilder<Integer, HbaseTable, HbaseTableProperty> {

	public HbaseTableGrid() {
		super(HbaseTableProperty.INSTANCE);
	}

	@Override
	protected void initColumnModel() {

		ColumnConfig<HbaseTable, String> nameCC = new ColumnConfig<HbaseTable, String>(
				getProps().name(), 200, "表名");
		ColumnConfig<HbaseTable, String> shortnameCC = new ColumnConfig<HbaseTable, String>(
				getProps().shortname(), 200, "中文名");
		ColumnConfig<HbaseTable, String> descCC = new ColumnConfig<HbaseTable, String>(
				getProps().desc(), 200, "描述");

		getColumnConfigs().add(nameCC);
		getColumnConfigs().add(shortnameCC);
		getColumnConfigs().add(descCC);
	}

}
