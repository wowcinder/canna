package com.voole.gxt.client.authority.rpcgroup;

import java.util.List;

import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.voole.gxt.client.canna.gridcontainer.CannaGridContainer;
import com.voole.gxt.client.canna.gridcontainer.CannaGridContainerConfig;
import com.voole.gxt.client.rpcclient.authority.AuthorityRpcGroupRcpClient;
import com.voole.gxt.client.rpcclient.callback.GetCallback;
import com.voole.gxt.shared.entity.authority.AuthorityRpcGroup;

public class AuthorityRpcGroupGridContainer extends
		CannaGridContainer<AuthorityRpcGroup> {
	private static final CannaGridContainerConfig config = new CannaGridContainerConfig();
	static {
		config.isPaging = false;
		config.isUpdateAbled = false;
	}

	private AuthorityRpcGroupGridContainerWindow gridWindow;

	public AuthorityRpcGroupGridContainer() {
		super(new AuthorityRpcGroupGrid(), new AuthorityRpcGroupRcpClient());
		setEditor(new AuthorityRpcGroupEditor());
	}

	public AuthorityRpcGroupGridContainer(boolean simple) {
		super(new AuthorityRpcGroupGrid(), null, config);
		gridWindow = new AuthorityRpcGroupGridContainerWindow();
		gridWindow.setCallback(new GetCallback<List<AuthorityRpcGroup>>() {
			@Override
			public void postGet(List<AuthorityRpcGroup> t) {
				simpleAdd(t);
			}
		});

		setAddHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				gridWindow.show();
			}
		});
		setDeleteHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				List<AuthorityRpcGroup> selects = getSelectionModel()
						.getSelectedItems();
				for (AuthorityRpcGroup g : selects) {
					getStore().remove(g);
				}
			}
		});
	}

	public void simpleAdd(List<AuthorityRpcGroup> t) {
		List<AuthorityRpcGroup> store = getStore().getAll();
		for (AuthorityRpcGroup g : t) {
			if (!store.contains(g)) {
				getStore().add(g);
			}
		}
	}
}
