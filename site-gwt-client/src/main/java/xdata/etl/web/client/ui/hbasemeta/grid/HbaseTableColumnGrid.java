/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui.hbasemeta.grid;

import xdata.etl.web.client.common.grid.RpcEntityGridBuilder;
import xdata.etl.web.client.property.hbasemeta.HbaseTableColumnProperty;
import xdata.etl.web.shared.entity.hbasemeta.HbaseTableColumn;
import xdata.etl.web.shared.entity.hbasemeta.HbaseTableColumn.HbaseTableColumnType;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.text.shared.AbstractSafeHtmlRenderer;
import com.sencha.gxt.cell.core.client.SimpleSafeHtmlCell;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;

/**
 * @author XuehuiHe
 * @date 2013年8月15日
 */
public class HbaseTableColumnGrid
		extends
		RpcEntityGridBuilder<Integer, HbaseTableColumn, HbaseTableColumnProperty> {

	public HbaseTableColumnGrid() {
		super(HbaseTableColumnProperty.INSTANCE);
	}

	@Override
	protected void initColumnModel() {
		ColumnConfig<HbaseTableColumn, String> nameCC = new ColumnConfig<HbaseTableColumn, String>(
				getProps().name(), 200, "字段");
		ColumnConfig<HbaseTableColumn, String> shortnameCC = new ColumnConfig<HbaseTableColumn, String>(
				getProps().shortname(), 200, "中文名");
		ColumnConfig<HbaseTableColumn, HbaseTableColumnType> typeCC = new ColumnConfig<HbaseTableColumn, HbaseTableColumnType>(
				getProps().type(), 200, "type");
		typeCC.setCell(new SimpleSafeHtmlCell<HbaseTableColumnType>(
				new AbstractSafeHtmlRenderer<HbaseTableColumnType>() {
					@Override
					public SafeHtml render(HbaseTableColumnType t) {
						if (t != null) {
							return SafeHtmlUtils.fromString(t.name());
						}
						return null;
					}
				}));
		ColumnConfig<HbaseTableColumn, String> descCC = new ColumnConfig<HbaseTableColumn, String>(
				getProps().desc(), 200, "字段");

		getColumnConfigs().add(nameCC);
		getColumnConfigs().add(shortnameCC);
		getColumnConfigs().add(typeCC);
		getColumnConfigs().add(descCC);
	}
}
