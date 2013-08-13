/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.service.open;

import java.util.Collection;
import java.util.Set;

/**
 * @author XuehuiHe
 * @date 2013年8月13日
 */
public interface AccountService extends OpenService {

	Integer login(String username, String password);

	void logout();

	Boolean isLogin();

	Boolean isAdmin();

	Integer getUserId();

	Set<String> getUserAuthorities();

	boolean isAccessAbled(Collection<String> tokens);
}
