package com.voole.gxt.client.rpcclient.project;

import com.voole.gxt.client.rpcclient.CannaRpcClient;
import com.voole.gxt.client.service.ClientServiceFactory;
import com.voole.gxt.client.service.project.ProjectPartnerService;
import com.voole.gxt.client.service.project.ProjectPartnerServiceAsync;
import com.voole.gxt.shared.entity.project.ProjectPartner;

public class ProjectPartnerRpcClient extends CannaRpcClient<ProjectPartner> {
	public static final ProjectPartnerServiceAsync service = ClientServiceFactory
			.getService(ProjectPartnerService.class);

	@Override
	public ProjectPartnerServiceAsync getService() {
		return service;
	}

}
