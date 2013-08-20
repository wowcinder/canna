/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xdata.etl.web.server.annotations.AccessAuthorities;
import xdata.etl.web.server.annotations.AccessAuthority;
import xdata.etl.web.server.annotations.AccessAuthorityGroup;
import xdata.etl.web.server.dao.menu.MenuGroupDao;
import xdata.etl.web.shared.entity.menu.MenuGroup;
import xdata.etl.web.shared.exception.SharedException;
import xdata.etl.web.shared.service.menu.MenuGroupService;

/**
 * @author XuehuiHe
 * @date 2013年8月8日
 */
@Service
@AccessAuthorityGroup("菜单组")
public class MenuGroupServiceImpl extends RpcServiceImpl<Integer, MenuGroup>
		implements MenuGroupService {
	public MenuGroupServiceImpl() {
	}

	@Autowired
	public MenuGroupServiceImpl(MenuGroupDao dao) {
		super(dao);
	}

	@Override
	@AccessAuthorities({ @AccessAuthority("查询"),
			@AccessAuthority(group = "菜单", value = "查询") })
	public List<MenuGroup> get() throws SharedException {
		return super.get();
	}
}
