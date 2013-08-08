/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.dao.user;

import org.hibernate.Session;

import xdata.etl.web.server.dao.RpcDao;
import xdata.etl.web.server.util.PasswordUtil;
import xdata.etl.web.shared.entity.user.User;
import xdata.etl.web.shared.exception.SharedException;

/**
 * @author XuehuiHe
 * @date 2013年8月8日
 */
public class UserDaoImpl extends RpcDao<Integer, User> implements UserDao {
	@Override
	public User saveAndReturn(User v) throws SharedException {
		v.setPassword(PasswordUtil.getHexPassword(v.getPassword()));
		return super.saveAndReturn(v);
	}

	@Override
	public void changePassword(Integer id, String password) {
		Session s = getSession();
		User v = (User) s.load(User.class, id);
		v.setPassword(PasswordUtil.getHexPassword(password));
		s.update(v);
	}
}
