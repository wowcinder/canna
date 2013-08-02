package com.voole.gxt.client.service.project;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.voole.gxt.client.canna.exception.CannaException;
import com.voole.gxt.client.service.CannaService;
import com.voole.gxt.client.service.ServiceConfig;
import com.voole.gxt.shared.entity.project.ProjectLeader;

@ServiceConfig(path = "rpc/project/leader.do", name = "负责人管理")
public interface ProjectLeaderService extends CannaService<ProjectLeader>,
		RemoteService {
	List<ProjectLeader> getLeadersForComBox() throws CannaException;
}
