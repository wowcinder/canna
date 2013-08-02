package com.voole.gxt.server.action;

import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.struts2.convention.annotation.Action;

import com.google.gwt.user.client.rpc.SerializationException;
import com.voole.gxt.client.service.menu.MenuGroupService;
import com.voole.gxt.client.service.menu.MenuService;

public class MenuAction extends BaseAction {
	@Action("/rpc/menu")
	public void rpcMenuEntryPoint() throws ServletException, IOException,
			SerializationException {
		getGWTHelper(MenuService.class).execute();
	}

	@Action("/rpc/menuGroup")
	public void rpcMenuGroupEntryPoint() throws ServletException, IOException,
			SerializationException {
		getGWTHelper(MenuGroupService.class).execute();
	}
}
