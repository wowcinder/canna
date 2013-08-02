package com.voole.gxt.server.service.user;

import java.util.List;

import org.springframework.stereotype.Service;

import com.voole.gxt.client.canna.exception.CannaException;
import com.voole.gxt.client.service.user.UserGroupService;
import com.voole.gxt.server.dao.user.UserGroupDao;
import com.voole.gxt.server.service.BaseService;
import com.voole.gxt.server.util.HibernateBeanReplicatorUtil;
import com.voole.gxt.shared.entity.authority.AuthorityRpcGroup;
import com.voole.gxt.shared.entity.user.UserGroup;

@Service
public class UserGroupServiceImpl extends BaseService<UserGroup> implements
		UserGroupService {

	@Override
	public UserGroupDao getDao() {
		return userGroupDao;
	}

	@Override
	public List<AuthorityRpcGroup> getAuthorityRpcGroups(Long id)
			throws CannaException {
		return HibernateBeanReplicatorUtil.excludeCollectionCopy(getDao()
				.getAuthorityRpcGroups(id));
	}

}
