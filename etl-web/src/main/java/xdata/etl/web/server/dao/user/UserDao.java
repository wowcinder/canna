/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.dao.user;

import java.util.Set;

import xdata.etl.web.server.dao.IRpcDao;
import xdata.etl.web.shared.entity.user.User;

/**
 * @author XuehuiHe
 * @date 2013年8月8日
 */
public interface UserDao extends IRpcDao<Integer, User> {
	public void changePassword(Integer id, String password);

	public Integer login(String username, String password);

	public Set<String>  getUserAuthorities(Integer uid);
}
