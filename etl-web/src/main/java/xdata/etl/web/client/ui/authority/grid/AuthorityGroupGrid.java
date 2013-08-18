/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui.authority.grid;

import xdata.etl.web.client.common.grid.RpcEntityGridBuilder;
import xdata.etl.web.client.property.authority.AuthorityGroupProperty;
import xdata.etl.web.shared.entity.authority.AuthorityGroup;

import com.sencha.gxt.widget.core.client.grid.ColumnConfig;

/**
 * @author XuehuiHe
 * @date 2013年8月12日
 * 
 */
public class AuthorityGroupGrid extends
		RpcEntityGridBuilder<Integer, AuthorityGroup, AuthorityGroupProperty> {

	public AuthorityGroupGrid() {
		super(AuthorityGroupProperty.INSTANCE);
	}

	@Override
	protected void initColumnModel() {
		ColumnConfig<AuthorityGroup, String> nameCC = new ColumnConfig<AuthorityGroup, String>(
				getProps().name(), 200, "name");
		getColumnConfigs().add(nameCC);
	}

}
