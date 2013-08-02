package com.voole.gxt.server.dao.user;

import com.voole.gxt.server.dao.IBaseDao;
import com.voole.gxt.shared.entity.user.User;

public interface UserDao extends IBaseDao<User> {
	String modifyPassword(Long id, String newPassword);

	boolean login(String email, String password);

	boolean isPermit(String email, String token);
}
