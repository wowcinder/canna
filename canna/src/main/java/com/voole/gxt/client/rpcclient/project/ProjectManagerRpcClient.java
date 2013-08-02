package com.voole.gxt.client.rpcclient.project;

import java.util.List;

import com.voole.gxt.client.rpcclient.CannaRpcClient;
import com.voole.gxt.client.rpcclient.callback.GetCallback;
import com.voole.gxt.client.service.CannaAsyncCallback;
import com.voole.gxt.client.service.ClientServiceFactory;
import com.voole.gxt.client.service.project.ProjectManagerService;
import com.voole.gxt.client.service.project.ProjectManagerServiceAsync;
import com.voole.gxt.shared.entity.project.ProjectManager;

public class ProjectManagerRpcClient extends CannaRpcClient<ProjectManager> {
	public static final ProjectManagerServiceAsync service = ClientServiceFactory
			.getService(ProjectManagerService.class);

	@Override
	public ProjectManagerServiceAsync getService() {
		return service;
	}

	public void getManagersFroComBox(
			final GetCallback<List<ProjectManager>> callback) {
		getService().getManagersFroComBox(
				new CannaAsyncCallback<List<ProjectManager>>() {
					@Override
					public void onSuccess(List<ProjectManager> result) {
						callback.postGet(result);
					}
				});
	}
}
