/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.property;

import xdata.etl.web.shared.entity.authority.AuthorityGroup;

import com.sencha.gxt.core.client.ValueProvider;

/**
 * @author XuehuiHe
 * @date 2013年8月12日
 * 
 */
public interface AuthorityGroupProperty extends
		RpcEntityPropertyAccess<Integer, AuthorityGroup> {
	ValueProvider<AuthorityGroup, String> name();
	ValueProvider<AuthorityGroup, Integer> displayOrder();
}
