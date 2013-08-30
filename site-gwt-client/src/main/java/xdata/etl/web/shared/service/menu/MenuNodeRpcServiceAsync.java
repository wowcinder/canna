/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.shared.service.menu;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;

import xdata.etl.web.shared.entity.menu.MenuNode;
import xdata.etl.web.shared.service.RpcServiceAsync;

/**
 * @author XuehuiHe
 * @date 2013年8月8日
 */
public interface MenuNodeRpcServiceAsync extends RpcServiceAsync<Integer, MenuNode> {

	void update(Integer parentId, Integer prevId, List<MenuNode> nodes,
			AsyncCallback<List<MenuNode>> callback);

	/**
	 * @param loadConfig
	 * @param callback
	 */
	void getMenuNodes(PagingLoadConfig loadConfig,
			AsyncCallback<PagingLoadResult<MenuNode>> callback);

}
