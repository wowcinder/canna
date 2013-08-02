package com.voole.gxt.server.dao.project.impl;

import java.text.MessageFormat;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;

import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoadResultBean;
import com.voole.gxt.client.canna.gridcontainer.paging.CannaPagingLoadConfigBean;
import com.voole.gxt.server.dao.AbstractCannaDao;
import com.voole.gxt.server.dao.project.ProjectDao;
import com.voole.gxt.shared.entity.project.Project;
import com.voole.gxt.shared.entity.project.ProjectRemark;

@Repository
public class ProjectDaoImpl extends AbstractCannaDao<Project> implements
		ProjectDao {

	public ProjectDaoImpl() {
		super(Project.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public PagingLoadResult<Project> get(CannaPagingLoadConfigBean config) {
		Session s = getSession();

		PagingLoadResultBean<Project> pr = new PagingLoadResultBean<Project>();
		Criteria criteria = findPagingCriteria(config);
		long rowCount = (Long) criteria.setProjection(Projections.rowCount())
				.uniqueResult();
		String sql = "select new Project(p.id,p.name,l.id,l.name,m.id,m.name) from {0} p left join p.leader l left join p.manager m";
		sql = MessageFormat.format(sql, getQueryName());
		Query query = s.createQuery(sql);
		
		pr.setOffset(config.getOffset());
		pr.setTotalLength((int) rowCount);
		
		
		addPagingConfig(config, query);
		pr.setData(query.list());
		return pr;
	}

	@Override
	public void update(Project l) {
		Session s = getSession();
		String sql = "select new {0}(pr.id) from {0} pr where pr.project = ? ";
		sql = MessageFormat.format(sql, ProjectRemark.class.getName());
		@SuppressWarnings("unchecked")
		List<ProjectRemark> remarks = s.createQuery(sql).setEntity(0, l).list();
		for (ProjectRemark pr : remarks) {
			if (!l.getRemarks().contains(pr)) {
				s.delete(pr);
			}
		}
		s.flush();
		s.clear();
		s.update(l);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProjectRemark> getRemarks(Long l) {
		Session s = getSession();
		Project p = new Project();
		p.setId(l);
		String sql = "select new {0}(pr.id, pr.remark, pt.id, pt.name, pt.position, pt.mobile, pt.email, pt.remark, pj.id) from {0} pr inner join pr.partner pt inner join pr.project pj where pr.project = ?";
		sql = MessageFormat.format(sql, ProjectRemark.class.getName());
		return s.createQuery(sql).setEntity(0, p).list();
	}

}
