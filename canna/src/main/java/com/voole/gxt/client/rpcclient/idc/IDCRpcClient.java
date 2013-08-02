package com.voole.gxt.client.rpcclient.idc;

import com.voole.gxt.client.rpcclient.CannaRpcClient;
import com.voole.gxt.client.service.ClientServiceFactory;
import com.voole.gxt.client.service.idc.IDCService;
import com.voole.gxt.client.service.idc.IDCServiceAsync;
import com.voole.gxt.shared.entity.idc.IDC;

public class IDCRpcClient extends CannaRpcClient<IDC> {

	private static final IDCServiceAsync service = ClientServiceFactory
			.getService(IDCService.class);

	@Override
	public IDCServiceAsync getService() {
		return service;
	}
}
