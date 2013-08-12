/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui.user.grid;

import xdata.etl.web.client.common.grid.EtlGrid;
import xdata.etl.web.client.property.UserGroupProperty;
import xdata.etl.web.shared.entity.user.UserGroup;

import com.google.gwt.core.shared.GWT;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;

/**
 * @author XuehuiHe
 * @date 2013年8月12日
 * 
 */
public class UserGroupGrid extends EtlGrid<Integer, UserGroup> {

	public UserGroupGrid() {
		super(GWT.<UserGroupProperty> create(UserGroupProperty.class));
	}

	@Override
	protected void initColumnModel() {
		ColumnConfig<UserGroup, String> nameCC = new ColumnConfig<UserGroup, String>(
				getProps().name(), 200, "name");
		getColumnConfigs().add(nameCC);
	}

	@Override
	public UserGroupProperty getProps() {
		return (UserGroupProperty) super.getProps();
	}

}
