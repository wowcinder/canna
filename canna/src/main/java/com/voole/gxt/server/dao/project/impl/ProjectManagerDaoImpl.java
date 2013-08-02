package com.voole.gxt.server.dao.project.impl;

import java.text.MessageFormat;
import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.voole.gxt.server.dao.AbstractCannaDao;
import com.voole.gxt.server.dao.project.ProjectManagerDao;
import com.voole.gxt.shared.entity.project.ProjectManager;

@Repository
public class ProjectManagerDaoImpl extends AbstractCannaDao<ProjectManager>
		implements ProjectManagerDao {

	public ProjectManagerDaoImpl() {
		super(ProjectManager.class);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<ProjectManager> getManagersFroComBox() {
		Session s = getSession();
		String sql = "Select new {0}(m.id,m.name) from {0} m ";
		sql = MessageFormat.format(sql, ProjectManager.class.getName());
		return s.createQuery(sql).list();
	}

}
