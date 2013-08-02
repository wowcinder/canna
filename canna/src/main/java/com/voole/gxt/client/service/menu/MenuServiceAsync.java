package com.voole.gxt.client.service.menu;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.voole.gxt.client.service.CannaServiceAsync;
import com.voole.gxt.shared.entity.menu.Menu;

public interface MenuServiceAsync extends CannaServiceAsync<Menu> {

	void saveOrder(List<Long> ids, AsyncCallback<Void> callback);

}
