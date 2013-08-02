package com.voole.gxt.client.service.user;

import com.google.gwt.user.client.rpc.RemoteService;
import com.voole.gxt.client.canna.exception.CannaException;
import com.voole.gxt.client.service.CannaService;
import com.voole.gxt.client.service.ServiceConfig;
import com.voole.gxt.shared.entity.user.User;

@ServiceConfig(name = "用户管理", path = "rpc/user/user.do")
public interface UserService extends CannaService<User>, RemoteService {
	String modifyPassword(Long id, String newPassword)
			throws CannaException;
}
