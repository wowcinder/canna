/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.service.menu;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import xdata.etl.web.server.dao.menu.MenuDao;
import xdata.etl.web.server.service.RpcDelegateServiceImpl;
import xdata.etl.web.shared.entity.menu.Menu;
import xdata.etl.web.shared.exception.SharedException;

/**
 * @author XuehuiHe
 * @date 2013年8月8日
 */
@Service
public class MenuServiceImpl extends
		RpcDelegateServiceImpl<Integer, Menu, MenuDao> implements MenuService {

	public MenuServiceImpl() {
	}

	@Override
	public List<Menu> get() throws SharedException {
		return getDao().getUserMenu(
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
	public List<Menu> getUserMenu(Integer userId) {
		return getDao().getUserMenu(userId);
	}
}
