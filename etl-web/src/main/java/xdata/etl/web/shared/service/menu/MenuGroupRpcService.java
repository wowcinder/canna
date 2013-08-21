/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.shared.service.menu;

import xdata.etl.web.shared.entity.menu.MenuGroup;
import xdata.etl.web.shared.service.RpcService;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author XuehuiHe
 * @date 2013年8月8日
 */
@RemoteServiceRelativePath("rpc/menu_group.rpc")
public interface MenuGroupRpcService extends RpcService<Integer, MenuGroup>,
		RemoteService {

}
