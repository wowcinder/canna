/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.rpc;

import org.springframework.stereotype.Service;

import xdata.etl.web.server.annotations.AccessAuthority;
import xdata.etl.web.server.annotations.AccessAuthorityGroup;
import xdata.etl.web.server.service.authority.AuthorityService;
import xdata.etl.web.shared.entity.authority.Authority;
import xdata.etl.web.shared.service.authority.AuthorityRpcService;

/**
 * @author XuehuiHe
 * @date 2013年8月2日
 */
@Service
@AccessAuthorityGroup("权限")
public class AuthorityRpcServiceImpl extends
		RpcServiceImpl<Integer, Authority, AuthorityService> implements
		AuthorityRpcService {
	public AuthorityRpcServiceImpl() {
	}

	@Override
	@AccessAuthority(value = "检查权限名", isOpen = true)
	public Authority queryByName(Integer agId, String name) {
		return getDelegateService().queryByName(agId, name);
	}
}
