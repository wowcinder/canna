package com.voole.gxt.client.idc.leasetype;

import com.google.gwt.core.shared.GWT;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.voole.gxt.client.canna.grid.CannaGrid;
import com.voole.gxt.client.property.idc.IDCLeaseTypeProperties;
import com.voole.gxt.shared.entity.idc.IDCLeaseType;

public class IDCLeaseTypeGrid extends CannaGrid<IDCLeaseType> {
	private static final IDCLeaseTypeProperties props = GWT
			.create(IDCLeaseTypeProperties.class);

	public IDCLeaseTypeGrid() {
		super(props);
	}

	@Override
	protected void initColumnModel() {
		ColumnConfig<IDCLeaseType, Integer> idCC = new ColumnConfig<IDCLeaseType, Integer>(
				props.value(), 200, "value");
		ColumnConfig<IDCLeaseType, String> nameCC = new ColumnConfig<IDCLeaseType, String>(
				props.name(), 200, "name");

		ccList.add(nameCC);
		ccList.add(idCC);
	}

}
