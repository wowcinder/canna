package com.voole.gxt.client.property.authority;

import java.util.List;

import com.sencha.gxt.core.client.ValueProvider;
import com.voole.gxt.client.property.CannaPropertyAccess;
import com.voole.gxt.shared.entity.authority.AuthorityRpcGroup;
import com.voole.gxt.shared.entity.authority.AuthorityRpcMethod;

public interface AuthorityRpcGroupProperties extends
		CannaPropertyAccess<AuthorityRpcGroup> {
	
	ValueProvider<AuthorityRpcGroup, Long> id();
	ValueProvider<AuthorityRpcGroup, String> name();
	ValueProvider<AuthorityRpcGroup, List<AuthorityRpcMethod>> authorityRpcMethods();
	

}
