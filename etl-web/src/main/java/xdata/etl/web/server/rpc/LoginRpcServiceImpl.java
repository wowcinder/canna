/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.rpc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xdata.etl.web.server.rpc.open.OpenRpcService;
import xdata.etl.web.server.service.account.AccountService;
import xdata.etl.web.shared.service.login.LoginRpcService;

/**
 * @author XuehuiHe
 * @date 2013年8月13日
 */
@Service
public class LoginRpcServiceImpl implements LoginRpcService, OpenRpcService {
	@Autowired
	private AccountService accountService;

	public LoginRpcServiceImpl() {
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
