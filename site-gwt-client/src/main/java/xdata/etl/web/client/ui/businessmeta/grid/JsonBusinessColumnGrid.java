/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui.businessmeta.grid;

import xdata.etl.web.client.property.businessmeta.JsonBusinessColumnProperty;
import xdata.etl.web.shared.entity.businessmeta.json.JsonBusinessColumn;

import com.sencha.gxt.widget.core.client.grid.ColumnConfig;

/**
 * @author XuehuiHe
 * @date 2013年9月4日
 */
public class JsonBusinessColumnGrid extends
		BusinessColumnGrid<JsonBusinessColumn, JsonBusinessColumnProperty> {

	public JsonBusinessColumnGrid() {
		super(JsonBusinessColumnProperty.INSTANCE);
	}

	@Override
	protected void initColumnModel() {
		ColumnConfig<JsonBusinessColumn, String> pathCC = new ColumnConfig<JsonBusinessColumn, String>(
				getProps().path(), 200, "path");
		getColumnConfigs().add(pathCC);
		super.initColumnModel();
	}

}
