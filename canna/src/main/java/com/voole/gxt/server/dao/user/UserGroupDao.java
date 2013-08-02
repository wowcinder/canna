package com.voole.gxt.server.dao.user;

import java.util.List;

import com.voole.gxt.server.dao.IBaseDao;
import com.voole.gxt.shared.entity.authority.AuthorityRpcGroup;
import com.voole.gxt.shared.entity.user.UserGroup;

public interface UserGroupDao extends IBaseDao<UserGroup> {
	List<AuthorityRpcGroup> getAuthorityRpcGroups(Long id);
}
