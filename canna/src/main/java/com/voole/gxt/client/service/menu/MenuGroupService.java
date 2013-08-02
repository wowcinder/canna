package com.voole.gxt.client.service.menu;

import java.util.List;

import javax.validation.ConstraintViolationException;

import com.google.gwt.user.client.rpc.RemoteService;
import com.voole.gxt.client.canna.exception.CannaException;
import com.voole.gxt.client.service.CannaService;
import com.voole.gxt.client.service.ServiceConfig;
import com.voole.gxt.shared.entity.menu.MenuGroup;

@ServiceConfig(path = "rpc/menuGroup.do", name = "菜单组管理")
public interface MenuGroupService extends CannaService<MenuGroup>,
		RemoteService {
	public void saveOrder(List<Long> ids) throws CannaException,
			ConstraintViolationException;

	List<MenuGroup> getMenuGroupsForMenuView() throws CannaException;
}
