/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.service.menu;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import xdata.etl.web.server.dao.menu.MenuNodeDao;
import xdata.etl.web.server.service.RpcDelegateServiceImpl;
import xdata.etl.web.shared.annotations.MenuToken;
import xdata.etl.web.shared.entity.menu.MenuNode;
import xdata.etl.web.shared.exception.SharedException;

/**
 * @author XuehuiHe
 * @date 2013年8月8日
 */
@Service
public class MenuNodeServiceImpl extends
		RpcDelegateServiceImpl<Integer, MenuNode, MenuNodeDao> implements
		MenuNodeService {

	public MenuNodeServiceImpl() {
	}

	@Override
	public List<MenuNode> get() throws SharedException {
		// (Integer) getSession().getAttribute("userId")
		return getDao().getUserMenus();
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
	public void initMenuConfig(List<MenuToken> tokens) {
		getDao().insertMenuConfig(tokens);
	}
}
