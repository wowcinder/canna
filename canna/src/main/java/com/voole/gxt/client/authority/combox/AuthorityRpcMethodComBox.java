package com.voole.gxt.client.authority.combox;

import com.sencha.gxt.data.shared.LabelProvider;
import com.voole.gxt.client.canna.combox.CannaComBox;
import com.voole.gxt.client.canna.editor.CannaEditor;
import com.voole.gxt.client.rpcclient.authority.AuthorityRpcMethodRpcClient;
import com.voole.gxt.shared.entity.authority.AuthorityRpcMethod;

public class AuthorityRpcMethodComBox extends
		CannaComBox<AuthorityRpcMethod> {

	public AuthorityRpcMethodComBox() {
		super(true, false, new LabelProvider<AuthorityRpcMethod>() {
			@Override
			public String getLabel(AuthorityRpcMethod item) {
				return item.getLabel();
			}
		});
	}

	@Override
	public void autoGetStore() {
		new AuthorityRpcMethodRpcClient().getAuthorityRpcMethodsForComBox(this);
	}

	@Override
	public AuthorityRpcMethod getAddItem() {
		return null;
	}

	@Override
	public CannaEditor<AuthorityRpcMethod> getEditor() {
		return null;
	}

	@Override
	public boolean isAddItem(AuthorityRpcMethod t) {
		return false;
	}

}
