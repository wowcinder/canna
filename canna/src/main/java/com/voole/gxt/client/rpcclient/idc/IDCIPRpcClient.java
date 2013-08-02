package com.voole.gxt.client.rpcclient.idc;

import com.voole.gxt.client.rpcclient.CannaRpcClient;
import com.voole.gxt.client.service.ClientServiceFactory;
import com.voole.gxt.client.service.idc.IDCIPService;
import com.voole.gxt.client.service.idc.IDCIPServiceAsync;
import com.voole.gxt.shared.entity.idc.IDCIP;

public class IDCIPRpcClient extends CannaRpcClient<IDCIP> {
	private static final IDCIPServiceAsync service = ClientServiceFactory
			.getService(IDCIPService.class);

	@Override
	public IDCIPServiceAsync getService() {
		return service;
	}
}
