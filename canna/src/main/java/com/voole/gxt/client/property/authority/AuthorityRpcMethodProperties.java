package com.voole.gxt.client.property.authority;

import java.util.List;

import com.sencha.gxt.core.client.ValueProvider;
import com.voole.gxt.client.property.CannaPropertyAccess;
import com.voole.gxt.shared.entity.authority.AuthorityRpcGroup;
import com.voole.gxt.shared.entity.authority.AuthorityRpcMethod;
import com.voole.gxt.shared.entity.authority.AuthorityRpcService;

public interface AuthorityRpcMethodProperties extends
		CannaPropertyAccess<AuthorityRpcMethod> {
	ValueProvider<AuthorityRpcMethod, Long> id();

	ValueProvider<AuthorityRpcMethod, String> name();

	ValueProvider<AuthorityRpcMethod, String> parsTypeStr();

	ValueProvider<AuthorityRpcMethod, String> token();

	ValueProvider<AuthorityRpcMethod, Boolean> isAlwaysPass();

	ValueProvider<AuthorityRpcMethod, AuthorityRpcService> service();

	ValueProvider<AuthorityRpcMethod, List<AuthorityRpcGroup>> authortityRpcGroups();

}
