package com.voole.gxt.server.service.project;

import java.util.List;

import org.springframework.stereotype.Service;

import com.voole.gxt.client.canna.exception.CannaException;
import com.voole.gxt.client.service.project.ProjectManagerService;
import com.voole.gxt.server.dao.project.ProjectManagerDao;
import com.voole.gxt.server.service.BaseService;
import com.voole.gxt.shared.entity.project.ProjectManager;

@Service
public class ProjectManagerServiceImpl extends BaseService<ProjectManager>
		implements ProjectManagerService {

	@Override
	public ProjectManagerDao getDao() {
		return projectManagerDao;
	}

	@Override
	public List<ProjectManager> getManagersFroComBox()
			throws CannaException {
		return getDao().getManagersFroComBox();
	}

}
