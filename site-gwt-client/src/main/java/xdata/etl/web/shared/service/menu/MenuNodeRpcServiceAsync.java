/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.shared.service.menu;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import xdata.etl.web.shared.entity.menu.MenuNode;
import xdata.etl.web.shared.service.RpcServiceAsync;

/**
 * @author XuehuiHe
 * @date 2013年8月8日
 */
public interface MenuNodeRpcServiceAsync extends RpcServiceAsync<Integer, MenuNode> {

	void update(Integer parentId, Integer prevId, List<MenuNode> nodes,
			AsyncCallback<List<MenuNode>> callback);

}
