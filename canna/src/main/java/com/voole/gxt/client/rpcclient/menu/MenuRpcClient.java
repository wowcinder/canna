package com.voole.gxt.client.rpcclient.menu;

import java.util.List;

import com.voole.gxt.client.rpcclient.CannaRpcClient;
import com.voole.gxt.client.rpcclient.callback.SaveOrderCallback;
import com.voole.gxt.client.service.CannaAsyncCallback;
import com.voole.gxt.client.service.ClientServiceFactory;
import com.voole.gxt.client.service.menu.MenuService;
import com.voole.gxt.client.service.menu.MenuServiceAsync;
import com.voole.gxt.shared.entity.menu.Menu;

public class MenuRpcClient extends CannaRpcClient<Menu> {

	private static MenuServiceAsync service = ClientServiceFactory
			.getService(MenuService.class);

	@Override
	public MenuServiceAsync getService() {
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
