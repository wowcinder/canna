/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.service.authority;

import xdata.etl.web.server.service.RpcDelegateService;
import xdata.etl.web.shared.entity.authority.Authority;

/**
 * @author XuehuiHe
 * @date 2013年8月2日
 */
public interface AuthorityService extends RpcDelegateService<Integer, Authority>{
	Authority queryByName(Integer agId,String name);
}
