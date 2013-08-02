package com.voole.gxt.server.dao.project.impl;

import java.text.MessageFormat;
import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.voole.gxt.server.dao.AbstractCannaDao;
import com.voole.gxt.server.dao.project.ProjectLeaderDao;
import com.voole.gxt.shared.entity.project.ProjectLeader;

@Repository
public class ProjectLeaderDaoImpl extends AbstractCannaDao<ProjectLeader>
		implements ProjectLeaderDao {

	public ProjectLeaderDaoImpl() {
		super(ProjectLeader.class);
	}

	@Override
	public List<ProjectLeader> getLeadersForComBox() {
		Session s = getSession();
		String sql = "select new {0}(l.id,l.name) from {0} l";
		sql = MessageFormat.format(sql, ProjectLeader.class.getName());
		@SuppressWarnings("unchecked")
		List<ProjectLeader> list = s.createQuery(sql).list();
		return list;
	}

}
