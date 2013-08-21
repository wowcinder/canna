/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.property.authority;

import java.util.Set;

import xdata.etl.web.client.property.RpcEntityPropertyAccess;
import xdata.etl.web.shared.entity.authority.Authority;
import xdata.etl.web.shared.entity.authority.AuthorityGroup;
import xdata.etl.web.shared.entity.menu.Menu;
import xdata.etl.web.shared.entity.user.User;
import xdata.etl.web.shared.entity.user.UserGroup;

import com.google.gwt.core.client.GWT;
import com.sencha.gxt.core.client.ValueProvider;

/**
 * @author XuehuiHe
 * @date 2013年8月12日
 * 
 */
public interface AuthorityProperty extends
		RpcEntityPropertyAccess<Integer, Authority> {

	public static final AuthorityProperty INSTANCE = GWT
			.create(AuthorityProperty.class);

	ValueProvider<Authority, String> token();

	ValueProvider<Authority, String> name();

	ValueProvider<Authority, Integer> displayOrder();

	ValueProvider<Authority, AuthorityGroup> group();

	ValueProvider<Authority, Boolean> isOpen();

	ValueProvider<Authority, Set<Menu>> menus();

	ValueProvider<Authority, Set<User>> users();

	ValueProvider<Authority, Set<UserGroup>> userGroups();

}
