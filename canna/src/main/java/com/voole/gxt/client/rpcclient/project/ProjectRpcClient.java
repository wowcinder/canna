package com.voole.gxt.client.rpcclient.project;

import java.util.List;

import com.voole.gxt.client.rpcclient.CannaRpcClient;
import com.voole.gxt.client.rpcclient.callback.GetCallback;
import com.voole.gxt.client.service.CannaAsyncCallback;
import com.voole.gxt.client.service.ClientServiceFactory;
import com.voole.gxt.client.service.project.ProjectService;
import com.voole.gxt.client.service.project.ProjectServiceAsync;
import com.voole.gxt.shared.entity.project.Project;
import com.voole.gxt.shared.entity.project.ProjectRemark;

public class ProjectRpcClient extends CannaRpcClient<Project> {

	public static final ProjectServiceAsync service = ClientServiceFactory
			.getService(ProjectService.class);

	@Override
	public ProjectServiceAsync getService() {
		return service;
	}

	public void getRemarks(Long l,
			final GetCallback<List<ProjectRemark>> callback) {
		getService().getRemarks(l,
				new CannaAsyncCallback<List<ProjectRemark>>() {
					@Override
					public void onSuccess(List<ProjectRemark> result) {
						callback.postGet(result);
					}
				});
	}
}
