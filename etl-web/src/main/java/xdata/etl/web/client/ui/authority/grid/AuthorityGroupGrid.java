/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui.authority.grid;

import xdata.etl.web.client.common.grid.EtlGrid;
import xdata.etl.web.client.property.AuthorityGroupProperty;
import xdata.etl.web.shared.entity.authority.AuthorityGroup;

import com.google.gwt.core.shared.GWT;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;

/**
 * @author XuehuiHe
 * @date 2013年8月12日
 * 
 */
public class AuthorityGroupGrid extends EtlGrid<Integer, AuthorityGroup> {

	public AuthorityGroupGrid() {
		super(GWT.<AuthorityGroupProperty> create(AuthorityGroupProperty.class));
	}

	@Override
	protected void initColumnModel() {
		ColumnConfig<AuthorityGroup, String> nameCC = new ColumnConfig<AuthorityGroup, String>(
				getProps().name(), 200, "name");
		getColumnConfigs().add(nameCC);
	}

	public AuthorityGroupProperty getProps() {
		return (AuthorityGroupProperty) props;
	}

}
