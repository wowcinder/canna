package com.voole.gxt.client.rpcclient.project;

import java.util.List;

import com.voole.gxt.client.rpcclient.CannaRpcClient;
import com.voole.gxt.client.rpcclient.callback.GetCallback;
import com.voole.gxt.client.service.CannaAsyncCallback;
import com.voole.gxt.client.service.ClientServiceFactory;
import com.voole.gxt.client.service.project.ProjectLeaderService;
import com.voole.gxt.client.service.project.ProjectLeaderServiceAsync;
import com.voole.gxt.shared.entity.project.ProjectLeader;

public class ProjectLeaderRpcClient extends CannaRpcClient<ProjectLeader> {
	public static final ProjectLeaderServiceAsync service = ClientServiceFactory
			.getService(ProjectLeaderService.class);

	@Override
	public ProjectLeaderServiceAsync getService() {
		return service;
	}

	public void getLeadersForComBox(
			final GetCallback<List<ProjectLeader>> callback) {
		getService().getLeadersForComBox(
				new CannaAsyncCallback<List<ProjectLeader>>() {
					@Override
					public void onSuccess(List<ProjectLeader> result) {
						callback.postGet(result);
					}
				});
	}

}
