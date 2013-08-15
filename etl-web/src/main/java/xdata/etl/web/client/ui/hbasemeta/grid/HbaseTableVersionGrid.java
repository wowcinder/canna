/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui.hbasemeta.grid;

import xdata.etl.web.client.common.grid.EtlGrid;
import xdata.etl.web.client.property.HbaseTableVersionProperty;
import xdata.etl.web.shared.entity.hbasemeta.HbaseTable;
import xdata.etl.web.shared.entity.hbasemeta.HbaseTableVersion;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.text.shared.AbstractSafeHtmlRenderer;
import com.sencha.gxt.cell.core.client.SimpleSafeHtmlCell;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;

/**
 * @author XuehuiHe
 * @date 2013年8月15日
 */
public class HbaseTableVersionGrid extends EtlGrid<Integer, HbaseTableVersion> {

	public HbaseTableVersionGrid() {
		super(HbaseTableVersionProperty.INSTANCE);
	}

	@Override
	protected void initColumnModel() {
		ColumnConfig<HbaseTableVersion, HbaseTable> tableCC = new ColumnConfig<HbaseTableVersion, HbaseTable>(
				getProps().table(), 200, "表名");
		tableCC.setCell(new SimpleSafeHtmlCell<HbaseTable>(
				new AbstractSafeHtmlRenderer<HbaseTable>() {

					@Override
					public SafeHtml render(HbaseTable t) {
						if (t != null && t.getName() != null) {
							return SafeHtmlUtils.fromString(t.getName());
						}
						return null;
					}
				}));

		ColumnConfig<HbaseTableVersion, String> versionCC = new ColumnConfig<HbaseTableVersion, String>(
				getProps().version(), 200, "版本");
		ColumnConfig<HbaseTableVersion, String> descCC = new ColumnConfig<HbaseTableVersion, String>(
				getProps().desc(), 200, "描述");

		getColumnConfigs().add(tableCC);
		getColumnConfigs().add(versionCC);
		getColumnConfigs().add(descCC);

	}

	@Override
	public HbaseTableVersionProperty getProps() {
		return (HbaseTableVersionProperty) super.getProps();
	}

}
