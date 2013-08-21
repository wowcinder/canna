/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.rpc;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import xdata.etl.web.server.annotations.AccessAuthority;
import xdata.etl.web.server.annotations.AccessAuthorityGroup;
import xdata.etl.web.server.service.menu.MenuService;
import xdata.etl.web.shared.entity.menu.Menu;
import xdata.etl.web.shared.exception.SharedException;
import xdata.etl.web.shared.service.menu.MenuRpcService;

/**
 * @author XuehuiHe
 * @date 2013年8月8日
 */
@Service
@AccessAuthorityGroup("菜单")
public class MenuRpcServiceImpl extends
		RpcServiceImpl<Integer, Menu, MenuService> implements MenuRpcService {

	public MenuRpcServiceImpl() {
	}

	@Override
	@AccessAuthority(value = "查询", isOpen = true)
	public List<Menu> get() throws SharedException {
		return getDelegateService().getUserMenu(
				(Integer) getSession().getAttribute("userId"));
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
