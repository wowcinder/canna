package com.voole.gxt.server.service.user;

import org.springframework.stereotype.Service;

import com.voole.gxt.client.canna.exception.CannaException;
import com.voole.gxt.client.service.user.UserService;
import com.voole.gxt.server.dao.user.UserDao;
import com.voole.gxt.server.service.BaseService;
import com.voole.gxt.shared.entity.user.User;

@Service
public class UserServiceImpl extends BaseService<User> implements UserService {

	@Override
	public UserDao getDao() {
		return userDao;
	}

	@Override
	public String modifyPassword(Long id, String newPassword)
			throws CannaException {
		return getDao().modifyPassword(id, newPassword);
	}

}
