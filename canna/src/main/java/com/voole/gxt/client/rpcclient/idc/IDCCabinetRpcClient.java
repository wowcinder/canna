package com.voole.gxt.client.rpcclient.idc;

import com.voole.gxt.client.rpcclient.CannaRpcClient;
import com.voole.gxt.client.service.ClientServiceFactory;
import com.voole.gxt.client.service.idc.IDCCabinetService;
import com.voole.gxt.client.service.idc.IDCCabinetServiceAsync;
import com.voole.gxt.shared.entity.idc.IDCCabinet;

public class IDCCabinetRpcClient extends CannaRpcClient<IDCCabinet> {

	private static final IDCCabinetServiceAsync service = ClientServiceFactory
			.getService(IDCCabinetService.class);

	@Override
	public IDCCabinetServiceAsync getService() {
		return service;
	}

}
