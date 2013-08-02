package com.voole.gxt.client.authority.rpcgroup;

import com.google.gwt.core.shared.GWT;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.voole.gxt.client.canna.grid.CannaGrid;
import com.voole.gxt.client.property.authority.AuthorityRpcGroupProperties;
import com.voole.gxt.shared.entity.authority.AuthorityRpcGroup;

public class AuthorityRpcGroupGrid extends CannaGrid<AuthorityRpcGroup> {
	private final static AuthorityRpcGroupProperties props = GWT
			.create(AuthorityRpcGroupProperties.class);

	public AuthorityRpcGroupGrid() {
		super(props);
	}

	@Override
	protected void initColumnModel() {
		ColumnConfig<AuthorityRpcGroup, String> nameCC = new ColumnConfig<AuthorityRpcGroup, String>(
				props.name(), 200, "name");
		ccList.add(nameCC);
	}

}
