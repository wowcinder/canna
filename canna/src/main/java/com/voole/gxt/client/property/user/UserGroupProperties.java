package com.voole.gxt.client.property.user;

import java.util.List;

import com.sencha.gxt.core.client.ValueProvider;
import com.voole.gxt.client.property.CannaPropertyAccess;
import com.voole.gxt.shared.entity.authority.AuthorityRpcGroup;
import com.voole.gxt.shared.entity.user.UserGroup;

public interface UserGroupProperties extends CannaPropertyAccess<UserGroup> {
	ValueProvider<UserGroup, String> name();

	ValueProvider<UserGroup,  List<AuthorityRpcGroup>> authGroups();
}
