package com.voole.gxt.client.service.menu;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.voole.gxt.client.service.CannaServiceAsync;
import com.voole.gxt.shared.entity.menu.MenuGroup;

public interface MenuGroupServiceAsync extends CannaServiceAsync<MenuGroup> {

	void saveOrder(List<Long> ids, AsyncCallback<Void> callback);

	void getMenuGroupsForMenuView(AsyncCallback<List<MenuGroup>> callback);
}
