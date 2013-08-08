/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xdata.etl.web.client.service.menu.MenuGroupService;
import xdata.etl.web.server.dao.menu.MenuGroupDao;
import xdata.etl.web.shared.annotations.AuthenticationService;
import xdata.etl.web.shared.entity.menu.MenuGroup;

/**
 * @author XuehuiHe
 * @date 2013年8月8日
 */
@Service
@AuthenticationService("菜单组")
public class MenuGroupServiceImpl extends RpcServiceImpl<Integer, MenuGroup>
		implements MenuGroupService {
	public MenuGroupServiceImpl() {
	}

	@Autowired
	public MenuGroupServiceImpl(MenuGroupDao dao) {
		super(dao);
	}
}
