package com.voole.gxt.server.action;

import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.struts2.convention.annotation.Action;

import com.google.gwt.user.client.rpc.SerializationException;
import com.voole.gxt.client.service.idc.IDCCabinetService;
import com.voole.gxt.client.service.idc.IDCHostingService;
import com.voole.gxt.client.service.idc.IDCIPService;
import com.voole.gxt.client.service.idc.IDCLeaseTypeService;
import com.voole.gxt.client.service.idc.IDCPartnerService;
import com.voole.gxt.client.service.idc.IDCService;
import com.voole.gxt.client.service.idc.IDCSwitchService;
import com.voole.gxt.client.service.idc.IDCUPlaceService;

public class IDCAction extends BaseAction {
	@Action("/rpc/idc/uplace")
	public void rpcIDCUPlaceEntryPoint() throws ServletException, IOException,
			SerializationException {
		getGWTHelper(IDCUPlaceService.class).execute();
	}

	@Action("/rpc/idc/switch")
	public void rpcIDCSwitchEntryPoint() throws ServletException, IOException,
			SerializationException {
		getGWTHelper(IDCSwitchService.class).execute();
	}

	@Action("/rpc/idc/partner")
	public void rpcIDCIDCPartnerEntryPoint() throws ServletException,
			IOException, SerializationException {
		getGWTHelper(IDCPartnerService.class).execute();
	}

	@Action("/rpc/idc/leasetype")
	public void rpcIDCLeaseTypeEntryPoint() throws ServletException,
			IOException, SerializationException {
		getGWTHelper(IDCLeaseTypeService.class).execute();
	}

	@Action("/rpc/idc/ip")
	public void rpcIDCIPEntryPoint() throws ServletException, IOException,
			SerializationException {
		getGWTHelper(IDCIPService.class).execute();
	}

	@Action("/rpc/idc/hosting")
	public void rpcIDCHostingEntryPoint() throws ServletException, IOException,
			SerializationException {
		getGWTHelper(IDCHostingService.class).execute();
	}

	@Action("/rpc/idc/cabinet")
	public void rpcIDCCabinetEntryPoint() throws ServletException, IOException,
			SerializationException {
		getGWTHelper(IDCCabinetService.class).execute();
	}

	@Action("/rpc/idc")
	public void rpcIDCEntryPoint() throws ServletException, IOException,
			SerializationException {
		getGWTHelper(IDCService.class).execute();
	}
}
