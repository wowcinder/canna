package com.voole.gxt.client.authority.rpcgroup;

import java.util.List;

import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutPack;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.voole.gxt.client.rpcclient.callback.GetCallback;
import com.voole.gxt.shared.entity.authority.AuthorityRpcGroup;

public class AuthorityRpcGroupGridContainerWindow extends Window {
	AuthorityRpcGroupGridContainer gridCon = new AuthorityRpcGroupGridContainer();
	TextButton addBt = new TextButton("Add");
	TextButton cancelBt = new TextButton("Cancel");

	private GetCallback<List<AuthorityRpcGroup>> callback;

	public AuthorityRpcGroupGridContainerWindow() {
		super();
		setModal(true);
		setWidth(750);
		setHeight(500);
		setMinHeight(500);
		setWidget(gridCon);

		setButtonAlign(BoxLayoutPack.END);
		addButton(addBt);
		addButton(cancelBt);

		addBt.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				getCallback().postGet(
						gridCon.getSelectionModel().getSelectedItems());
				hide();
			}
		});

		cancelBt.addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				hide();
			}
		});
	}

	public GetCallback<List<AuthorityRpcGroup>> getCallback() {
		return callback;
	}

	public void setCallback(GetCallback<List<AuthorityRpcGroup>> callback) {
		this.callback = callback;
	}

}
