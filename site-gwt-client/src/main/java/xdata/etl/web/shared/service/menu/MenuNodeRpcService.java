/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.shared.service.menu;

import xdata.etl.web.shared.entity.menu.MenuNode;
import xdata.etl.web.shared.service.RpcService;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author XuehuiHe
 * @date 2013年8月8日
 */
@RemoteServiceRelativePath("rpc/menu_node.rpc")
public interface MenuNodeRpcService extends RpcService<Integer, MenuNode>, RemoteService {

}
