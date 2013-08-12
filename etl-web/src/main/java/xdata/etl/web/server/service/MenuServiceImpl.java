/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xdata.etl.web.client.service.menu.MenuService;
import xdata.etl.web.server.dao.menu.MenuDao;
import xdata.etl.web.shared.annotations.AccessAuthorityGroup;
import xdata.etl.web.shared.entity.menu.Menu;

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

}
