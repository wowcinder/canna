package com.voole.gxt.server.service.menu;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.stereotype.Service;

import com.voole.gxt.client.canna.exception.CannaException;
import com.voole.gxt.client.service.menu.MenuGroupService;
import com.voole.gxt.server.dao.menu.MenuGroupDao;
import com.voole.gxt.server.service.BaseService;
import com.voole.gxt.server.util.HibernateBeanReplicatorUtil;
import com.voole.gxt.shared.entity.menu.MenuGroup;

@Service
public class MenuGroupServiceImpl extends BaseService<MenuGroup> implements
		MenuGroupService {
	@Override
	public MenuGroupDao getDao() {
		return menuGroupDao;
	}

	@Override
	public void saveOrder(List<Long> ids) throws CannaException,
			ConstraintViolationException {
		getDao().saveOrder(ids);
	}

	@Override
	public List<MenuGroup> getMenuGroupsForMenuView() throws CannaException {
		return HibernateBeanReplicatorUtil.simpleCopy(getDao()
				.getMenuGroupsForMenuView());
	}
}
