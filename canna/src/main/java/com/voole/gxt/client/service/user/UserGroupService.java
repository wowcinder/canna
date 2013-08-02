package com.voole.gxt.client.service.user;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.voole.gxt.client.canna.exception.CannaException;
import com.voole.gxt.client.service.CannaService;
import com.voole.gxt.client.service.ServiceConfig;
import com.voole.gxt.shared.entity.authority.AuthorityRpcGroup;
import com.voole.gxt.shared.entity.user.UserGroup;

@ServiceConfig(name = "用户组管理", path = "rpc/user/group.do")
public interface UserGroupService extends CannaService<UserGroup>,
		RemoteService {
	List<AuthorityRpcGroup> getAuthorityRpcGroups(Long id)
			throws CannaException;

}
