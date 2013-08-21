/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.service.account;

import java.util.Collection;
import java.util.Set;

import xdata.etl.web.server.rpc.open.OpenRpcService;

/**
 * @author XuehuiHe
 * @date 2013年8月13日
 */
public interface AccountService extends OpenRpcService {

	Integer login(String username, String password);

	void logout();

	Boolean isLogin();

	Boolean isAdmin();

	Integer getUserId();

	Set<String> getUserAuthorities();

	Boolean isOpen(Set<String> tokens);

	boolean isAccessAbled(Collection<String> tokens);
}
