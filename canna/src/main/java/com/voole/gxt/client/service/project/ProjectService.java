package com.voole.gxt.client.service.project;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.voole.gxt.client.canna.exception.CannaException;
import com.voole.gxt.client.service.CannaService;
import com.voole.gxt.client.service.ServiceConfig;
import com.voole.gxt.shared.entity.project.Project;
import com.voole.gxt.shared.entity.project.ProjectRemark;

@ServiceConfig(path = "rpc/project.do", name = "项目管理")
public interface ProjectService extends CannaService<Project>, RemoteService {
	List<ProjectRemark> getRemarks(Long id) throws CannaException;
}
