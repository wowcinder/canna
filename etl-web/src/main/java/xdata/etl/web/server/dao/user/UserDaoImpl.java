/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.dao.user;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import xdata.etl.web.client.common.paging.EtlPagingLoadConfigBean;
import xdata.etl.web.server.dao.RpcDao;
import xdata.etl.web.server.util.PasswordUtil;
import xdata.etl.web.shared.entity.authority.Authority;
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

	@Override
	public User update(User v) throws SharedException {
		User old = (User) getSession().load(User.class, v.getId());
		if (v.getPassword() == null || v.getPassword().length() == 0) {
			v.setPassword(old.getPassword());
		} else {
			v.setPassword(PasswordUtil.getHexPassword(v.getPassword()));
		}
		getSession().merge(v);
		return v;
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
		criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		pr.setData((List<User>) criteria.list());
		return pr;
	}

	@Override
	public Integer login(String username, String password) {
		Session s = getSession();
		User user = (User) s
				.createCriteria(User.class)
				.add(Restrictions.eq("email", username))
				.add(Restrictions.eq("password",
						PasswordUtil.getHexPassword(password))).uniqueResult();
		if (user != null) {
			return user.getId();
		}
		return null;
	}

	@Override
	public Set<String> getUserAuthorities(Integer uid) {
		Set<String> authorities = new HashSet<String>();

		// 用户的额外权限
		User user = (User) getSession().createCriteria(User.class)
				.setFetchMode("extraAuthorities", FetchMode.JOIN)
				.add(Restrictions.idEq(uid)).uniqueResult();
		if (user != null) {
			for (Authority auth : user.getExtraAuthorities()) {
				authorities.add(auth.getToken());
			}
		}
		// 用户组权限
		UserGroup userGroup = (UserGroup) getSession()
				.createCriteria(UserGroup.class).createAlias("users", "user")
				.add(Restrictions.eq("user.id", uid))
				.setFetchMode("authorities", FetchMode.JOIN).uniqueResult();
		if (userGroup != null) {
			for (Authority auth : userGroup.getAuthorities()) {
				authorities.add(auth.getToken());
			}
		}

		// open 权限
		@SuppressWarnings("unchecked")
		List<Authority> auList = getSession().createCriteria(Authority.class)
				.add(Restrictions.eq("isOpen", Boolean.TRUE))
				.setResultTransformer(CriteriaSpecification.ROOT_ENTITY).list();
		for (Authority authority : auList) {
			authorities.add(authority.getToken());
		}

		return authorities;
	}
}
