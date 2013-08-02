package com.voole.gxt.client.service.project;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.voole.gxt.client.service.CannaServiceAsync;
import com.voole.gxt.shared.entity.project.ProjectLeader;

public interface ProjectLeaderServiceAsync extends
		CannaServiceAsync<ProjectLeader> {

	void getLeadersForComBox(AsyncCallback<List<ProjectLeader>> callback);

}
