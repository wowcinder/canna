/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.rpc;

import org.springframework.stereotype.Service;

import xdata.etl.web.server.annotations.AccessAuthority;
import xdata.etl.web.server.annotations.AccessAuthorityGroup;
import xdata.etl.web.server.service.authority.AuthorityGroupService;
import xdata.etl.web.shared.entity.authority.AuthorityGroup;
import xdata.etl.web.shared.service.authority.AuthorityGroupRpcService;

/**
 * @author XuehuiHe
 * @date 2013年8月4日
 * 
 */
@Service
@AccessAuthorityGroup("权限组")
public class AuthorityGroupRpcServiceImpl extends
		RpcServiceImpl<Integer, AuthorityGroup, AuthorityGroupService>
		implements AuthorityGroupRpcService {
	public AuthorityGroupRpcServiceImpl() {
	}

	@Override
	@AccessAuthority(value = "检查权限组名", isOpen = true)
	public Integer queryByName(String name) {
		return getDelegateService().queryByName(name);
	}
}
