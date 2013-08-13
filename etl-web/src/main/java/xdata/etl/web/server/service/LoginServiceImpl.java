/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xdata.etl.web.client.service.LoginService;
import xdata.etl.web.server.service.open.AccountService;

/**
 * @author XuehuiHe
 * @date 2013年8月13日
 */
@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	private AccountService accountService;

	public LoginServiceImpl() {
	}

	@Override
	public Integer login(String username, String password) {
		return accountService.login(username, password);
	}

	@Override
	public Boolean isLogin() {
		return accountService.isLogin();
	}

	@Override
	public void logout() {
		accountService.logout();
	}

	public AccountService getAccountService() {
		return accountService;
	}

	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

}
