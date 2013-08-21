/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.service.menu;

import java.util.List;

import org.springframework.stereotype.Service;

import xdata.etl.web.server.dao.menu.MenuGroupDao;
import xdata.etl.web.server.service.RpcDelegateServiceImpl;
import xdata.etl.web.shared.entity.menu.MenuGroup;
import xdata.etl.web.shared.exception.SharedException;

/**
 * @author XuehuiHe
 * @date 2013年8月8日
 */
@Service
public class MenuGroupServiceImpl extends
		RpcDelegateServiceImpl<Integer, MenuGroup, MenuGroupDao> implements
		MenuGroupService {
	public MenuGroupServiceImpl() {
	}

	@Override
	public List<MenuGroup> get() throws SharedException {
		return super.get();
	}
}
