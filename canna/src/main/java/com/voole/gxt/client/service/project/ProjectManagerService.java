package com.voole.gxt.client.service.project;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.voole.gxt.client.canna.exception.CannaException;
import com.voole.gxt.client.service.CannaService;
import com.voole.gxt.client.service.ServiceConfig;
import com.voole.gxt.shared.entity.project.ProjectManager;

@ServiceConfig(path = "rpc/project/manager.do", name = "项目经理管理")
public interface ProjectManagerService extends CannaService<ProjectManager>,
		RemoteService {
	List<ProjectManager> getManagersFroComBox() throws CannaException;
}
