package com.voole.gxt.client.service.menu;

import java.util.List;

import javax.validation.ConstraintViolationException;

import com.google.gwt.user.client.rpc.RemoteService;
import com.voole.gxt.client.canna.exception.CannaException;
import com.voole.gxt.client.service.CannaService;
import com.voole.gxt.client.service.ServiceConfig;
import com.voole.gxt.shared.entity.menu.Menu;

@ServiceConfig(path = "rpc/menu.do", name = "菜单管理")
public interface MenuService extends CannaService<Menu>, RemoteService {
	public void saveOrder(List<Long> ids) throws CannaException,
			ConstraintViolationException;
}
