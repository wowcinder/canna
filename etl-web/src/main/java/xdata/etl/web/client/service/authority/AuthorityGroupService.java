/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.service.authority;

import xdata.etl.web.client.service.RpcService;
import xdata.etl.web.shared.entity.authority.AuthorityGroup;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author XuehuiHe
 * @date 2013年8月4日
 * 
 */
@RemoteServiceRelativePath("authority_group")
public interface AuthorityGroupService extends
		RpcService<Integer, AuthorityGroup>, RemoteService {

}