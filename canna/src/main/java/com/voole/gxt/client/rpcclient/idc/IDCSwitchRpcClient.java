package com.voole.gxt.client.rpcclient.idc;

import com.voole.gxt.client.rpcclient.CannaRpcClient;
import com.voole.gxt.client.service.ClientServiceFactory;
import com.voole.gxt.client.service.idc.IDCSwitchService;
import com.voole.gxt.client.service.idc.IDCSwitchServiceAsync;
import com.voole.gxt.shared.entity.idc.IDCSwitch;

public class IDCSwitchRpcClient extends CannaRpcClient<IDCSwitch> {
	private static final IDCSwitchServiceAsync service = ClientServiceFactory
			.getService(IDCSwitchService.class);

	@Override
	public IDCSwitchServiceAsync getService() {
		return service;
	}

}
