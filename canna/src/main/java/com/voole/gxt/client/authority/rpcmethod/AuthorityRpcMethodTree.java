package com.voole.gxt.client.authority.rpcmethod;

import java.util.List;

import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.voole.gxt.client.canna.tree.CannaCheckBoxTree;
import com.voole.gxt.client.rpcclient.callback.GetCallback;
import com.voole.gxt.shared.entity.CannaTreeNode;
import com.voole.gxt.shared.entity.authority.AuthorityRpcMethod;
import com.voole.gxt.shared.entity.authority.AuthorityRpcService;

public class AuthorityRpcMethodTree extends CannaCheckBoxTree implements
		GetCallback<List<AuthorityRpcService>> {

	public static class AuthorityRpcMethodKeyProvider implements
			ModelKeyProvider<CannaTreeNode> {
		@Override
		public String getKey(CannaTreeNode item) {
			String prefix = "service-";
			if (item instanceof AuthorityRpcMethod) {
				prefix = "method-";
			}
			return prefix + item.getId();
		}
	}

	public AuthorityRpcMethodTree() {
		super(new AuthorityRpcMethodKeyProvider());
	}

	@Override
	public void postGet(List<AuthorityRpcService> t) {
		for (AuthorityRpcService service : t) {
			store.add(service);
			for (AuthorityRpcMethod method : service.getChildren()) {
				store.add(service, method);
			}
		}
		expandAll();
	}

}
