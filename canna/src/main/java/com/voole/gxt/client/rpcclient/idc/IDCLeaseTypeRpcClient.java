package com.voole.gxt.client.rpcclient.idc;

import com.voole.gxt.client.rpcclient.CannaRpcClient;
import com.voole.gxt.client.service.ClientServiceFactory;
import com.voole.gxt.client.service.idc.IDCLeaseTypeService;
import com.voole.gxt.client.service.idc.IDCLeaseTypeServiceAsync;
import com.voole.gxt.shared.entity.idc.IDCLeaseType;

public class IDCLeaseTypeRpcClient extends CannaRpcClient<IDCLeaseType> {

	private static final IDCLeaseTypeServiceAsync service = ClientServiceFactory
			.getService(IDCLeaseTypeService.class);

	@Override
	public IDCLeaseTypeServiceAsync getService() {
		return service;
	}

}
