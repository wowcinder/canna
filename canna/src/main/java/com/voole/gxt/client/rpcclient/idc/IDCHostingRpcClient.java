package com.voole.gxt.client.rpcclient.idc;

import com.voole.gxt.client.rpcclient.CannaRpcClient;
import com.voole.gxt.client.service.ClientServiceFactory;
import com.voole.gxt.client.service.idc.IDCHostingService;
import com.voole.gxt.client.service.idc.IDCHostingServiceAsync;
import com.voole.gxt.shared.entity.idc.IDCHosting;

public class IDCHostingRpcClient extends CannaRpcClient<IDCHosting> {

	private static final IDCHostingServiceAsync service = ClientServiceFactory
			.getService(IDCHostingService.class);

	@Override
	public IDCHostingServiceAsync getService() {
		return service;
	}

}
