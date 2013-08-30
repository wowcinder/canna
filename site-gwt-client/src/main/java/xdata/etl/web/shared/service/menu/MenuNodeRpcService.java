/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.shared.service.menu;

import java.util.List;

import xdata.etl.web.shared.entity.menu.MenuNode;
import xdata.etl.web.shared.service.RpcService;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;

/**
 * @author XuehuiHe
 * @date 2013年8月8日
 */
@RemoteServiceRelativePath("rpc/menu_node.rpc")
public interface MenuNodeRpcService extends RpcService<Integer, MenuNode>,
		RemoteService {
	List<MenuNode> update(Integer parentId, Integer prevId, List<MenuNode> nodes);

	PagingLoadResult<MenuNode> getMenuNodes(PagingLoadConfig loadConfig);
}
