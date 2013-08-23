/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.rpc;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import xdata.etl.web.server.annotations.AccessAuthorityGroup;
import xdata.etl.web.server.service.menu.MenuNodeService;
import xdata.etl.web.shared.entity.menu.MenuNode;
import xdata.etl.web.shared.service.menu.MenuNodeRpcService;

/**
 * @author XuehuiHe
 * @date 2013年8月8日
 */
@Service
@AccessAuthorityGroup("菜单")
public class MenuNodeRpcServiceImpl extends
		RpcServiceImpl<Integer, MenuNode, MenuNodeService> implements
		MenuNodeRpcService {

	public MenuNodeRpcServiceImpl() {
	}

	protected HttpSession getSession() {
		if (RequestContextHolder.currentRequestAttributes() != null) {
			ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder
					.currentRequestAttributes();
			return attr.getRequest().getSession();
		}
		return null;
	}
}
