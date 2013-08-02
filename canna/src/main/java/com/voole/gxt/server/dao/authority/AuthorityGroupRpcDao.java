package com.voole.gxt.server.dao.authority;

import java.util.List;

import com.voole.gxt.server.dao.IBaseDao;
import com.voole.gxt.shared.entity.authority.AuthorityRpcGroup;
import com.voole.gxt.shared.entity.authority.AuthorityRpcMethod;

public interface AuthorityGroupRpcDao extends IBaseDao<AuthorityRpcGroup> {

	List<AuthorityRpcMethod> get(AuthorityRpcGroup g);

	List<AuthorityRpcMethod> get(Long gid);
}
