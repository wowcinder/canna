/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.service.authority;

import org.springframework.stereotype.Service;

import xdata.etl.web.server.dao.authority.AuthorityGroupDao;
import xdata.etl.web.server.service.RpcDelegateServiceImpl;
import xdata.etl.web.shared.entity.authority.AuthorityGroup;

/**
 * @author XuehuiHe
 * @date 2013年8月4日
 * 
 */
@Service
public class AuthorityGroupServiceImpl extends
		RpcDelegateServiceImpl<Integer, AuthorityGroup, AuthorityGroupDao>
		implements AuthorityGroupService {
	public AuthorityGroupServiceImpl() {
	}

	public Integer queryByName(String name) {
		return getDao().queryByName(name);
	}

}
