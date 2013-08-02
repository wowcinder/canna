package com.voole.gxt.client.user.combox;

import com.voole.gxt.client.canna.combox.CannaComBox;
import com.voole.gxt.client.canna.editor.CannaEditor;
import com.voole.gxt.client.rpcclient.user.UserGroupRpcClient;
import com.voole.gxt.client.user.group.UserGroupEditor;
import com.voole.gxt.client.user.group.UserGroupGrid;
import com.voole.gxt.shared.entity.user.UserGroup;

public class UserGroupComBox extends CannaComBox<UserGroup> {

	private static final UserGroupRpcClient rpc = new UserGroupRpcClient();

	public UserGroupComBox() {
		super(UserGroupGrid.props.label());
	}

	@Override
	public void autoGetStore() {
		rpc.get(this);
	}

	@Override
	public UserGroup getAddItem() {
		UserGroup ug = new UserGroup();
		ug.setName(ADD_TEXT);
		ug.setId(ADD_ID);
		return ug;
	}

	@Override
	public CannaEditor<UserGroup> getEditor() {
		return new UserGroupEditor();
	}

	@Override
	public boolean isAddItem(UserGroup t) {
		if (t.getId() == ADD_ID) {
			return true;
		}
		return false;
	}

}
