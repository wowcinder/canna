package com.voole.gxt.server.dao.authority.impl;

import java.text.MessageFormat;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import com.sencha.gxt.data.shared.SortDir;
import com.sencha.gxt.data.shared.SortInfoBean;
import com.voole.gxt.client.canna.gridcontainer.paging.CannaPagingLoadConfigBean;
import com.voole.gxt.server.dao.AbstractCannaDao;
import com.voole.gxt.server.dao.authority.AuthorityRpcMethodDao;
import com.voole.gxt.server.util.HibernateUtil;
import com.voole.gxt.shared.entity.authority.AuthorityRpcMethod;
import com.voole.gxt.shared.entity.authority.AuthorityRpcService;

@Repository
public class AuthorityRpcMethodDaoImpl extends
		AbstractCannaDao<AuthorityRpcMethod> implements AuthorityRpcMethodDao {

	public AuthorityRpcMethodDaoImpl() {
		super(AuthorityRpcMethod.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AuthorityRpcService> getService() {
		Session s = getSession();
		String queryName = HibernateUtil
				.getQueryName(AuthorityRpcService.class);
		String sql = "select distinct service from {0} service inner join fetch service.methods sm where sm.isAlwaysPass = ? order by service.fullname";
		sql = MessageFormat.format(sql, queryName);
		return s.createQuery(sql).setParameter(0, false).list();
	}

	@Override
	public void updatePassStatus(List<Long> ids, Boolean isAlwaysPass) {
		Session s = getSession();
		String queryName = getQueryName();
		String sql = "update {0} m set m.isAlwaysPass =:isAlwaysPass where m.id =:id";
		sql = MessageFormat.format(sql, queryName);
		for (Long id : ids) {
			s.createQuery(sql).setParameter("isAlwaysPass", isAlwaysPass)
					.setParameter("id", id).executeUpdate();
		}
	}

	public Criteria findPagingCriteria(CannaPagingLoadConfigBean config) {
		Criteria c = getSession().createCriteria(clazz)
				.setFetchMode("service", FetchMode.SELECT)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		for (SortInfoBean sortInfo : config.getSortInfo()) {
			if (sortInfo.getSortDir().equals(SortDir.ASC)) {
				c.addOrder(Order.asc(sortInfo.getSortField()));
			} else {
				c.addOrder(Order.desc(sortInfo.getSortField()));
			}
		}

		return c;
	}

	@Override
	public List<AuthorityRpcMethod> getAuthorityRpcMethodsForComBox() {
		Session s = getSession();
		String sql = "select new {0}(m.id, m.name, m.parsTypeStr,s.id,s.simpleName) from {1} m inner join m.service s where m.isAlwaysPass = ? and m.name=? and m.parsTypeStr = ? order by s.simpleName ";
		sql = MessageFormat.format(sql, AuthorityRpcMethod.class.getName(),
				getQueryName());
		@SuppressWarnings("unchecked")
		List<AuthorityRpcMethod> list = s.createQuery(sql)
				.setParameter(0, false).setParameter(1, "get")
				.setParameter(2, "").list();
		return list;
	}
}
