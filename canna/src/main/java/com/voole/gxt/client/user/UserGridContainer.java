package com.voole.gxt.client.user;

import com.voole.gxt.client.canna.gridcontainer.CannaGridContainer;
import com.voole.gxt.client.rpcclient.user.UserRpcClient;
import com.voole.gxt.shared.entity.user.User;

public class UserGridContainer extends CannaGridContainer<User> {

	public UserGridContainer() {
		super(new UserGrid(), new UserRpcClient());
		setUpdateEditor(new UserUpdateEditor());
		setAddEditor(new UserAddEditor());
	}

}
