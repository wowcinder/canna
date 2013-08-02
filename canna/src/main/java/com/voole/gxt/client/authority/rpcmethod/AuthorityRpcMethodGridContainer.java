package com.voole.gxt.client.authority.rpcmethod;

import java.util.List;

import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.voole.gxt.client.canna.gridcontainer.CannaGridContainer;
import com.voole.gxt.client.canna.gridcontainer.CannaGridContainerConfig;
import com.voole.gxt.client.rpcclient.authority.AuthorityRpcMethodRpcClient;
import com.voole.gxt.client.rpcclient.callback.UpdateCallback;
import com.voole.gxt.shared.entity.authority.AuthorityRpcMethod;

public class AuthorityRpcMethodGridContainer extends
		CannaGridContainer<AuthorityRpcMethod> {
	public static final CannaGridContainerConfig config;
	static {
		config = new CannaGridContainerConfig();
		config.isAddAbled = false;
		config.isDeleteAbled = false;
		config.isUpdateAbled = false;
	}

	private TextButton passBt = new TextButton("不验证权限");
	private TextButton noPassBt = new TextButton("验证权限");

	public AuthorityRpcMethodGridContainer() {
		super(new AuthorityRpcMethodGrid(), new AuthorityRpcMethodRpcClient(),
				config);
		bar.add(passBt);
		bar.add(noPassBt);
		passBt.addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				passBt.disable();
				updatePassStatus(true);
			}
		});
		noPassBt.addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				noPassBt.disable();
				updatePassStatus(false);
			}
		});
	}

	protected void updatePassStatus(boolean b) {
		getRpc().updatePassStatus(
				getGrid().getSelectionModel().getSelectedItems(), b,
				new UpdateCallback<List<AuthorityRpcMethod>>() {
					@Override
					public void postUpdate(List<AuthorityRpcMethod> t) {
						ListStore<AuthorityRpcMethod> store = getGrid()
								.getStore();
						for (AuthorityRpcMethod method : t) {
							store.update(method);
						}
						passBt.enable();
						noPassBt.enable();
					}
				});
	}

	protected void checkToolBarStatus() {
		isExistToolBar = true;
	}

	@Override
	public AuthorityRpcMethodRpcClient getRpc() {
		return (AuthorityRpcMethodRpcClient) this.rpc;
	}

}
