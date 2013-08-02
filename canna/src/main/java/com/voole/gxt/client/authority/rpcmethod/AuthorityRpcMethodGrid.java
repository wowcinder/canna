package com.voole.gxt.client.authority.rpcmethod;

import com.google.gwt.core.client.GWT;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.voole.gxt.client.canna.grid.AbstractCannaSafeHtmlRenderer;
import com.voole.gxt.client.canna.grid.CannaGrid;
import com.voole.gxt.client.canna.grid.CannaSafeHtmlCell;
import com.voole.gxt.client.property.authority.AuthorityRpcMethodProperties;
import com.voole.gxt.shared.entity.authority.AuthorityRpcMethod;
import com.voole.gxt.shared.entity.authority.AuthorityRpcService;

public class AuthorityRpcMethodGrid extends CannaGrid<AuthorityRpcMethod> {
	private static final AuthorityRpcMethodProperties props = GWT
			.create(AuthorityRpcMethodProperties.class);

	public AuthorityRpcMethodGrid() {
		super(props);
	}

	@Override
	protected void initColumnModel() {
		ColumnConfig<AuthorityRpcMethod, String> nameCC = new ColumnConfig<AuthorityRpcMethod, String>(
				props.name(), 200, "name");
		ColumnConfig<AuthorityRpcMethod, String> parsStrCC = new ColumnConfig<AuthorityRpcMethod, String>(
				props.parsTypeStr(), 200, "parsStr");
		ColumnConfig<AuthorityRpcMethod, String> tokenCC = new ColumnConfig<AuthorityRpcMethod, String>(
				props.token(), 200, "token");
		ColumnConfig<AuthorityRpcMethod, AuthorityRpcService> pathCC = new ColumnConfig<AuthorityRpcMethod, AuthorityRpcService>(
				props.service(), 200, "path");
		ColumnConfig<AuthorityRpcMethod, AuthorityRpcService> serviceCC = new ColumnConfig<AuthorityRpcMethod, AuthorityRpcService>(
				props.service(), 200, "service");
		ColumnConfig<AuthorityRpcMethod, Boolean> isAlwaysPassCC = new ColumnConfig<AuthorityRpcMethod, Boolean>(
				props.isAlwaysPass(), 200, "不验证权限");
		pathCC.setCell(new CannaSafeHtmlCell<AuthorityRpcService>(
				new AbstractCannaSafeHtmlRenderer<AuthorityRpcService>() {
					@Override
					public String getRenderStr(AuthorityRpcService t) {
						return t.getPath();
					}
				}));
		serviceCC.setCell(new CannaSafeHtmlCell<AuthorityRpcService>(
				new AbstractCannaSafeHtmlRenderer<AuthorityRpcService>() {
					@Override
					public String getRenderStr(AuthorityRpcService t) {
						return t.getName();
					}
				}));
		ccList.add(nameCC);
		ccList.add(parsStrCC);
		ccList.add(tokenCC);
		ccList.add(pathCC);
		ccList.add(serviceCC);
		ccList.add(isAlwaysPassCC);

	}

}
