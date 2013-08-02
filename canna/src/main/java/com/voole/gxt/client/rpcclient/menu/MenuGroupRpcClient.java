package com.voole.gxt.client.rpcclient.menu;

import java.util.List;

import com.voole.gxt.client.rpcclient.CannaRpcClient;
import com.voole.gxt.client.rpcclient.callback.SaveOrderCallback;
import com.voole.gxt.client.service.CannaAsyncCallback;
import com.voole.gxt.client.service.ClientServiceFactory;
import com.voole.gxt.client.service.menu.MenuGroupService;
import com.voole.gxt.client.service.menu.MenuGroupServiceAsync;
import com.voole.gxt.shared.entity.menu.MenuGroup;

public class MenuGroupRpcClient extends CannaRpcClient<MenuGroup> {
	public static MenuGroupServiceAsync service = ClientServiceFactory
			.getService(MenuGroupService.class);

	@Override
	public MenuGroupServiceAsync getService() {
		return service;
	}

	public void saveOrder(final List<Long> ids, final SaveOrderCallback callback) {
		getService().saveOrder(ids, new CannaAsyncCallback<Void>() {
			@Override
			public void onSuccess(Void result) {
				callback.postSaveOrder();
			}
		});
	}

}
