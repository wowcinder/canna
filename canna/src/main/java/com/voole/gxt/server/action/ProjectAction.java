package com.voole.gxt.server.action;

import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.struts2.convention.annotation.Action;

import com.google.gwt.user.client.rpc.SerializationException;
import com.voole.gxt.client.service.project.ProjectLeaderService;
import com.voole.gxt.client.service.project.ProjectManagerService;
import com.voole.gxt.client.service.project.ProjectPartnerService;
import com.voole.gxt.client.service.project.ProjectService;

public class ProjectAction extends BaseAction {
	@Action("/rpc/project/leader")
	public void rpcLeaderEntryPoint() throws ServletException, IOException,
			SerializationException {
		getGWTHelper(ProjectLeaderService.class).execute();
	}

	@Action("/rpc/project/manager")
	public void rpcManagerEntryPoint() throws ServletException, IOException,
			SerializationException {
		getGWTHelper(ProjectManagerService.class).execute();
	}

	@Action("/rpc/project")
	public void rpcProjectEntryPoint() throws ServletException, IOException,
			SerializationException {
		getGWTHelper(ProjectService.class).execute();
	}

	@Action("/rpc/project/partner")
	public void rpcPartnerEntryPoint() throws ServletException, IOException,
			SerializationException {
		getGWTHelper(ProjectPartnerService.class).execute();
	}
}
