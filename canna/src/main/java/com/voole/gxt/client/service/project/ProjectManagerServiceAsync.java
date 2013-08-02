package com.voole.gxt.client.service.project;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.voole.gxt.client.service.CannaServiceAsync;
import com.voole.gxt.shared.entity.project.ProjectManager;

public interface ProjectManagerServiceAsync extends
		CannaServiceAsync<ProjectManager> {

	void getManagersFroComBox(AsyncCallback<List<ProjectManager>> callback);

}
