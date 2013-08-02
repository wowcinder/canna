package com.voole.gxt.client.user.group;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.data.client.editor.ListStoreEditor;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.voole.gxt.client.authority.rpcgroup.AuthorityRpcGroupGridContainer;
import com.voole.gxt.client.canna.editor.CannaSimpleEditor;
import com.voole.gxt.client.rpcclient.callback.GetCallback;
import com.voole.gxt.client.rpcclient.user.UserGroupRpcClient;
import com.voole.gxt.shared.entity.authority.AuthorityRpcGroup;
import com.voole.gxt.shared.entity.user.UserGroup;

public class UserGroupEditor extends CannaSimpleEditor<UserGroup> {

	interface UserGroupDriver extends
			SimpleBeanEditorDriver<UserGroup, UserGroupEditor> {
	}

	private UserGroupDriver driver = GWT.create(UserGroupDriver.class);

	TextField name = new TextField();
	ListStoreEditor<AuthorityRpcGroup> authGroups;
	@Ignore
	AuthorityRpcGroupGridContainer gridCon;
	@Ignore
	private FramedPanel gridPanel;

	public UserGroupEditor() {
		super();
		this.rpc = new UserGroupRpcClient();

		root.setWidth(750);
		gridCon = new AuthorityRpcGroupGridContainer(true);
		authGroups = new ListStoreEditor<AuthorityRpcGroup>(gridCon.getStore());

		gridPanel = new FramedPanel();
		gridPanel.getElement().setMargins(new Margins(15, 0, 0, 0));
		gridPanel.setHeadingText("权限组");
		gridCon.getElement().setHeight(150);
		gridPanel.setWidget(gridCon);

		vc.add(name, vd);
		vc.add(gridPanel, vd);

		driver.initialize(this);
	}

	@Override
	protected UserGroup flush() {
		return driver.flush();
	}

	@Override
	public void toAdd() {
		UserGroup ug = new UserGroup();
		ug.setAuthGroups(new ArrayList<AuthorityRpcGroup>());
		driver.edit(ug);
	}

	@Override
	public void doEdit(final UserGroup ug) {
		getRpc().getAuthorityRpcGroups(ug.getId(),
				new GetCallback<List<AuthorityRpcGroup>>() {
					@Override
					public void postGet(List<AuthorityRpcGroup> t) {
						ug.setAuthGroups(t);
						driver.edit(ug);
					}
				});

	}

	@Override
	public String getBaseHeadingText() {
		return "用户组";
	}

	private UserGroupRpcClient getRpc() {
		return (UserGroupRpcClient) this.rpc;
	}

}
