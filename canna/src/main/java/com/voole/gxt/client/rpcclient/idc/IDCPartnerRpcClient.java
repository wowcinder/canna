package com.voole.gxt.client.rpcclient.idc;

import com.voole.gxt.client.rpcclient.CannaRpcClient;
import com.voole.gxt.client.service.ClientServiceFactory;
import com.voole.gxt.client.service.idc.IDCPartnerService;
import com.voole.gxt.client.service.idc.IDCPartnerServiceAsync;
import com.voole.gxt.shared.entity.idc.IDCPartner;

public class IDCPartnerRpcClient extends CannaRpcClient<IDCPartner> {

	private static final IDCPartnerServiceAsync service = ClientServiceFactory
			.getService(IDCPartnerService.class);

	@Override
	public IDCPartnerServiceAsync getService() {
		return service;
	}

}
