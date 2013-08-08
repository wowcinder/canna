/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.dao.user;

import xdata.etl.web.server.dao.IRpcDao;
import xdata.etl.web.shared.entity.user.User;

/**
 * @author XuehuiHe
 * @date 2013年8月8日
 */
public interface UserDao extends IRpcDao<Integer, User> {
	public void changePassword(Integer id, String password);
}
