/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.property.user;

import java.util.List;

import xdata.etl.web.client.property.RpcEntityPropertyAccess;
import xdata.etl.web.shared.entity.authority.Authority;
import xdata.etl.web.shared.entity.user.User;
import xdata.etl.web.shared.entity.user.UserGroup;

import com.google.gwt.core.client.GWT;
import com.sencha.gxt.core.client.ValueProvider;

/**
 * @author XuehuiHe
 * @date 2013年8月12日
 * 
 */
public interface UserGroupProperty extends
		RpcEntityPropertyAccess<Integer, UserGroup> {

	public static final UserGroupProperty INSTANCE = GWT
			.create(UserGroupProperty.class);

	ValueProvider<UserGroup, String> name();

	ValueProvider<UserGroup, List<User>> users();

	ValueProvider<UserGroup, List<Authority>> authorities();

}
