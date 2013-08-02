package com.voole.gxt.server.dao.project.impl;

import org.springframework.stereotype.Repository;

import com.voole.gxt.server.dao.AbstractCannaDao;
import com.voole.gxt.server.dao.project.ProjectPartnerDao;
import com.voole.gxt.shared.entity.project.ProjectPartner;

@Repository
public class ProjectPartnerDaoImpl extends AbstractCannaDao<ProjectPartner>
		implements ProjectPartnerDao {

	public ProjectPartnerDaoImpl() {
		super(ProjectPartner.class);
	}

}
