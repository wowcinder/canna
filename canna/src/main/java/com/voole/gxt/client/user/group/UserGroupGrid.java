package com.voole.gxt.client.user.group;

import com.google.gwt.core.client.GWT;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.voole.gxt.client.canna.grid.CannaGrid;
import com.voole.gxt.client.property.user.UserGroupProperties;
import com.voole.gxt.shared.entity.user.UserGroup;

public class UserGroupGrid extends CannaGrid<UserGroup> {
	public static final UserGroupProperties props = GWT
			.create(UserGroupProperties.class);

	public UserGroupGrid() {
		super(props);
	}

	@Override
	protected void initColumnModel() {
		ColumnConfig<UserGroup, String> name = new ColumnConfig<UserGroup, String>(
				props.name(), 200, "name");
		ccList.add(name);
	}

}
