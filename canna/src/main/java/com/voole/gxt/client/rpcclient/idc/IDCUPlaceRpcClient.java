package com.voole.gxt.client.rpcclient.idc;

import com.voole.gxt.client.rpcclient.CannaRpcClient;
import com.voole.gxt.client.service.ClientServiceFactory;
import com.voole.gxt.client.service.idc.IDCUPlaceService;
import com.voole.gxt.client.service.idc.IDCUPlaceServiceAsync;
import com.voole.gxt.shared.entity.idc.IDCUPlace;

public class IDCUPlaceRpcClient extends CannaRpcClient<IDCUPlace> {
	private static final IDCUPlaceServiceAsync service = ClientServiceFactory
			.getService(IDCUPlaceService.class);

	@Override
	public IDCUPlaceServiceAsync getService() {
		return service;
	}

}
