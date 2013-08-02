package com.voole.gxt.server.service.project;

import java.util.List;

import org.springframework.stereotype.Service;

import com.voole.gxt.client.canna.exception.CannaException;
import com.voole.gxt.client.service.project.ProjectLeaderService;
import com.voole.gxt.server.dao.project.ProjectLeaderDao;
import com.voole.gxt.server.service.BaseService;
import com.voole.gxt.shared.entity.project.ProjectLeader;

@Service
public class ProjectLeaderServiceImpl extends BaseService<ProjectLeader>
		implements ProjectLeaderService {

	@Override
	public ProjectLeaderDao getDao() {
		return projectLeaderDao;
	}

	@Override
	public List<ProjectLeader> getLeadersForComBox() throws CannaException {
		return getDao().getLeadersForComBox();
	}

}
