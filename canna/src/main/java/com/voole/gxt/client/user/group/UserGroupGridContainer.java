package com.voole.gxt.client.user.group;

import com.voole.gxt.client.canna.gridcontainer.CannaGridContainer;
import com.voole.gxt.client.rpcclient.user.UserGroupRpcClient;
import com.voole.gxt.shared.entity.user.UserGroup;

public class UserGroupGridContainer extends CannaGridContainer<UserGroup> {

	public UserGroupGridContainer() {
		super(new UserGroupGrid(), new UserGroupRpcClient());
		setEditor(new UserGroupEditor());
	}

}
