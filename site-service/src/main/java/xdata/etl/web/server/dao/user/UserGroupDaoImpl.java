/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.dao.user;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;

import xdata.etl.web.server.dao.RpcDao;
import xdata.etl.web.shared.common.paging.EtlPagingLoadConfigBean;
import xdata.etl.web.shared.entity.user.User;
import xdata.etl.web.shared.entity.user.UserGroup;
import xdata.etl.web.shared.exception.SharedException;

import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoadResultBean;

/**
 * @author XuehuiHe
 * @date 2013年8月8日
 */
@Repository
public class UserGroupDaoImpl extends RpcDao<Integer, UserGroup> implements
		UserGroupDao {
	@Override
	public void delete(Integer k) throws SharedException {
		UserGroup ug = (UserGroup) getSession().load(UserGroup.class, k);
		try {
			for (User u : ug.getUsers()) {
				u.setUserGroup(null);
			}
			getSession().delete(ug);
		} catch (ObjectNotFoundException e) {
		}
	}

	@Override
	public void delete(List<Integer> ids) throws SharedException {
		for (Integer k : ids) {
			this.delete(k);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public PagingLoadResult<UserGroup> get(EtlPagingLoadConfigBean config)
			throws SharedException {
		Session s = getSession();
		PagingLoadResultBean<UserGroup> pr = new PagingLoadResultBean<UserGroup>();
		Criteria criteria = s.createCriteria(clazz);
		long rowCount = (Long) criteria.setProjection(Projections.rowCount())
				.uniqueResult();
		pr.setOffset(config.getOffset());
		pr.setTotalLength((int) rowCount);
		addPagingConfig(config, criteria);
		criteriaLimit(criteria);
		criteria.setFetchMode("authorities", FetchMode.JOIN);
		criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		pr.setData((List<UserGroup>) criteria.list());
		return pr;
	}
}
