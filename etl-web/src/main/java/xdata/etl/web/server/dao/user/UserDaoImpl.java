/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.dao.user;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;

import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoadResultBean;

import xdata.etl.web.client.common.paging.EtlPagingLoadConfigBean;
import xdata.etl.web.server.dao.RpcDao;
import xdata.etl.web.server.util.PasswordUtil;
import xdata.etl.web.shared.entity.user.User;
import xdata.etl.web.shared.exception.SharedException;

/**
 * @author XuehuiHe
 * @date 2013年8月8日
 */
@Repository
public class UserDaoImpl extends RpcDao<Integer, User> implements UserDao {
	@Override
	public User saveAndReturn(User v) throws SharedException {
		v.setPassword(PasswordUtil.getHexPassword(v.getPassword()));
		return super.saveAndReturn(v);
	}

	@Override
	public void changePassword(Integer id, String password) {
		Session s = getSession();
		User v = (User) s.load(User.class, id);
		v.setPassword(PasswordUtil.getHexPassword(password));
		s.update(v);
	}

	@SuppressWarnings("unchecked")
	@Override
	public PagingLoadResult<User> get(EtlPagingLoadConfigBean config)
			throws SharedException {
		Session s = getSession();
		PagingLoadResultBean<User> pr = new PagingLoadResultBean<User>();
		Criteria criteria = s.createCriteria(clazz);
		long rowCount = (Long) criteria.setProjection(Projections.rowCount())
				.uniqueResult();
		pr.setOffset(config.getOffset());
		pr.setTotalLength((int) rowCount);
		addPagingConfig(config, criteria);
		criteriaLimit(criteria);
		criteria.setFetchMode("extraAuthorities", FetchMode.JOIN);
		criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		pr.setData((List<User>) criteria.list());
		return pr;
	}
}
