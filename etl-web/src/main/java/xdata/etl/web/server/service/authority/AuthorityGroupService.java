/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.service.authority;

import xdata.etl.web.server.service.RpcDelegateService;
import xdata.etl.web.shared.entity.authority.AuthorityGroup;

/**
 * @author XuehuiHe
 * @date 2013年8月4日
 * 
 */
public interface AuthorityGroupService extends
		RpcDelegateService<Integer, AuthorityGroup> {
	Integer queryByName(String name);
}
