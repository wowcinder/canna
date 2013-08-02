package com.voole.gxt.server.dao.authority;

import java.util.List;

import com.voole.gxt.server.dao.IBaseDao;
import com.voole.gxt.shared.entity.authority.AuthorityRpcMethod;
import com.voole.gxt.shared.entity.authority.AuthorityRpcService;

public interface AuthorityRpcMethodDao extends IBaseDao<AuthorityRpcMethod> {
	List<AuthorityRpcService> getService();

	void updatePassStatus(List<Long> ids, Boolean isAlwaysPass);

	List<AuthorityRpcMethod> getAuthorityRpcMethodsForComBox();
}
