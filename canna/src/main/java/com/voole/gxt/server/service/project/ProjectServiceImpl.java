package com.voole.gxt.server.service.project;

import java.util.List;

import org.springframework.stereotype.Service;

import com.voole.gxt.client.canna.exception.CannaException;
import com.voole.gxt.client.service.project.ProjectService;
import com.voole.gxt.server.dao.project.ProjectDao;
import com.voole.gxt.server.service.BaseService;
import com.voole.gxt.shared.entity.project.Project;
import com.voole.gxt.shared.entity.project.ProjectRemark;

@Service
public class ProjectServiceImpl extends BaseService<Project> implements
		ProjectService {

	@Override
	public ProjectDao getDao() {
		return projectDao;
	}

	@Override
	public List<ProjectRemark> getRemarks(Long id) throws CannaException{
		return getDao().getRemarks(id);
	}

}
