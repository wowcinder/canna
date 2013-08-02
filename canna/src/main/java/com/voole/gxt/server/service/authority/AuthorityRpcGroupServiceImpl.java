package com.voole.gxt.server.service.authority;

import java.util.List;

import org.springframework.stereotype.Service;

import com.voole.gxt.client.canna.exception.CannaException;
import com.voole.gxt.client.service.authority.AuthorityRpcGroupService;
import com.voole.gxt.server.dao.authority.AuthorityGroupRpcDao;
import com.voole.gxt.server.service.BaseService;
import com.voole.gxt.server.util.HibernateBeanReplicatorUtil;
import com.voole.gxt.shared.entity.authority.AuthorityRpcGroup;
import com.voole.gxt.shared.entity.authority.AuthorityRpcMethod;

@Service
public class AuthorityRpcGroupServiceImpl extends
		BaseService<AuthorityRpcGroup> implements AuthorityRpcGroupService {

	@Override
	public AuthorityGroupRpcDao getDao() {
		return authorityRpcGroupDao;
	}

	@Override
	public List<AuthorityRpcMethod> get(AuthorityRpcGroup g)
			throws CannaException{
		return HibernateBeanReplicatorUtil.excludeCollectionCopy(getDao()
				.get(g));
	}

}
