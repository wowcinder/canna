/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.property;

import java.util.List;

import xdata.etl.web.shared.entity.authority.Authority;
import xdata.etl.web.shared.entity.user.User;
import xdata.etl.web.shared.entity.user.UserGroup;

import com.sencha.gxt.core.client.ValueProvider;

/**
 * @author XuehuiHe
 * @date 2013年8月12日
 * 
 */
public interface UserProperty extends RpcEntityPropertyAccess<Integer, User> {

	ValueProvider<User, String> email();
	ValueProvider<User, UserGroup> userGroup();
	ValueProvider<User, List<Authority>> extraAuthorities();
}
