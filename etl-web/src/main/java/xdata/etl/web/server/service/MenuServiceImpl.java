/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import xdata.etl.web.client.service.menu.MenuService;
import xdata.etl.web.server.annotations.AccessAuthority;
import xdata.etl.web.server.annotations.AccessAuthorityGroup;
import xdata.etl.web.server.dao.menu.MenuDao;
import xdata.etl.web.shared.entity.menu.Menu;
import xdata.etl.web.shared.exception.SharedException;

/**
 * @author XuehuiHe
 * @date 2013年8月8日
 */
@Service
@AccessAuthorityGroup("菜单")
public class MenuServiceImpl extends RpcServiceImpl<Integer, Menu> implements
		MenuService {

	public MenuServiceImpl() {
	}

	@Autowired
	public MenuServiceImpl(MenuDao dao) {
		super(dao);
	}

	@Override
	@AccessAuthority(value = "查询", isOpen = true)
	public List<Menu> get() throws SharedException {
		return getRpcDao().getUserMenu(
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

	@Override
	public MenuDao getRpcDao() {
		return (MenuDao) super.getRpcDao();
	}
}
