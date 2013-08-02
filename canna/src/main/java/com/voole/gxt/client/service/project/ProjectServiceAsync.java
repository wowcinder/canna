package com.voole.gxt.client.service.project;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.voole.gxt.client.service.CannaServiceAsync;
import com.voole.gxt.shared.entity.project.Project;
import com.voole.gxt.shared.entity.project.ProjectRemark;

public interface ProjectServiceAsync extends CannaServiceAsync<Project> {

	void getRemarks(Long id, AsyncCallback<List<ProjectRemark>> callback);

}
