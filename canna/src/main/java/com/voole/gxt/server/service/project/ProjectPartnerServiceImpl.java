package com.voole.gxt.server.service.project;

import org.springframework.stereotype.Service;

import com.voole.gxt.client.service.project.ProjectPartnerService;
import com.voole.gxt.server.dao.project.ProjectPartnerDao;
import com.voole.gxt.server.service.BaseService;
import com.voole.gxt.shared.entity.project.ProjectPartner;
@Service
public class ProjectPartnerServiceImpl extends BaseService<ProjectPartner>
		implements ProjectPartnerService {

	@Override
	public ProjectPartnerDao getDao() {
		return projectPartnerDao;
	}

}
