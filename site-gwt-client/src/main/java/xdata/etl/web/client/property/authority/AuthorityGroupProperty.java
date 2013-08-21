/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.property.authority;

import xdata.etl.web.client.property.RpcEntityPropertyAccess;
import xdata.etl.web.shared.entity.authority.AuthorityGroup;

import com.google.gwt.core.shared.GWT;
import com.sencha.gxt.core.client.ValueProvider;

/**
 * @author XuehuiHe
 * @date 2013年8月12日
 * 
 */
public interface AuthorityGroupProperty extends
		RpcEntityPropertyAccess<Integer, AuthorityGroup> {
	public static final AuthorityGroupProperty INSTANCE = GWT
			.create(AuthorityGroupProperty.class);

	ValueProvider<AuthorityGroup, String> name();

	ValueProvider<AuthorityGroup, Integer> displayOrder();
}
