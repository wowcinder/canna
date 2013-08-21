/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.service.authority;

import org.springframework.stereotype.Service;

import xdata.etl.web.server.dao.authority.AuthorityDao;
import xdata.etl.web.server.service.RpcDelegateServiceImpl;
import xdata.etl.web.shared.entity.authority.Authority;

/**
 * @author XuehuiHe
 * @date 2013年8月2日
 */
@Service
public class AuthorityServiceImpl extends
		RpcDelegateServiceImpl<Integer, Authority, AuthorityDao> implements
		AuthorityService {
	public AuthorityServiceImpl() {
	}

	@Override
	public Authority queryByName(Integer agId, String name) {
		return getDao().queryByName(agId, name);
	}
}
