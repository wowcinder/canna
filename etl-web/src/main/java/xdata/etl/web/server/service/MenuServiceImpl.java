/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xdata.etl.web.client.service.menu.MenuService;
import xdata.etl.web.server.dao.menu.MenuDao;
import xdata.etl.web.server.service.open.AccountService;
import xdata.etl.web.shared.annotations.AccessAuthority;
import xdata.etl.web.shared.annotations.AccessAuthorityGroup;
import xdata.etl.web.shared.entity.menu.Menu;
import xdata.etl.web.shared.exception.SharedException;

/**
 * @author XuehuiHe
 * @date 2013年8月8日
 */
@Service
@AccessAuthorityGroup("菜单")
public class MenuServiceImpl extends RpcServiceImpl<Integer, Menu> implements
		MenuService {
	private AccountService accountService;

	public MenuServiceImpl() {
	}

	@Autowired
	public MenuServiceImpl(MenuDao dao, AccountService accountService) {
		super(dao);
		this.accountService = accountService;
	}

	@Override
	@AccessAuthority("查询")
	public List<Menu> get() throws SharedException {
		return getRpcDao().getUserMenu(getAccountService().getUserId());
	}

	public AccountService getAccountService() {
		return accountService;
	}

	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	@Override
	public MenuDao getRpcDao() {
		return (MenuDao) super.getRpcDao();
	}
}
