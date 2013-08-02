package com.voole.gxt.server.service.menu;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.stereotype.Service;

import com.voole.gxt.client.canna.exception.CannaException;
import com.voole.gxt.client.service.menu.MenuService;
import com.voole.gxt.server.dao.menu.MenuDao;
import com.voole.gxt.server.service.BaseService;
import com.voole.gxt.shared.entity.menu.Menu;

@Service
public class MenuServiceImpl extends BaseService<Menu> implements MenuService {
	@Override
	public MenuDao getDao() {
		return menuDao;
	}

	@Override
	public void saveOrder(List<Long> ids) throws CannaException,
			ConstraintViolationException {
		getDao().saveOrder(ids);
	}
}
