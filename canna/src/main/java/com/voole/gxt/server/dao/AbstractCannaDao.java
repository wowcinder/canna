package com.voole.gxt.server.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;

import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoadResultBean;
import com.voole.gxt.client.canna.gridcontainer.paging.CannaPagingLoadConfigBean;
import com.voole.gxt.server.util.HibernateUtil;
import com.voole.gxt.shared.entity.CannaEntity;

public abstract class AbstractCannaDao<T> extends BaseDao implements
		IBaseDao<T> {

	protected Class<T> clazz;

	public AbstractCannaDao(Class<T> clazz) {
		this.clazz = clazz;
	}

	public void validateBean(T t) {
		Set<ConstraintViolation<T>> violations = validator.validate(t);
		if (!violations.isEmpty()) {
			Set<ConstraintViolation<?>> temp = new HashSet<ConstraintViolation<?>>(
					violations);
			throw new ConstraintViolationException(temp);
		}
	}

	public void preSave(T t) {
	}

	public void preUpdate(T t) {
	}

	@Override
	public T saveReturnObjct(T t) {
		preSave(t);
		validateBean(t);
		Session s = getSession();
		s.persist(t);
		return t;
	}

	@Override
	public Long save(T t) {
		t = saveReturnObjct(t);
		return ((CannaEntity) t).getId();
	}

	@Override
	public void update(T t) {
		preUpdate(t);
		validateBean(t);
		Session s = getSession();
		s.update(t);
	}

	@Override
	public void delete(List<Long> ids) {
		Session s = getSession();
		for (Long id : ids) {
			@SuppressWarnings("unchecked")
			T lt = (T) s.load(clazz, id);
			s.delete(lt);
		}

	}

	@Override
	public void delete(Long id) {
		Session s = getSession();
		@SuppressWarnings("unchecked")
		T lt = (T) s.load(clazz, id);
		s.delete(lt);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> get() {
		Criteria criteria = findGetCriteria();
		return criteria.list();
	}

	public Criteria findGetCriteria() {
		return getSession().createCriteria(clazz);
	}

	@SuppressWarnings("unchecked")
	@Override
	public PagingLoadResult<T> get(CannaPagingLoadConfigBean config) {
		PagingLoadResultBean<T> pr = new PagingLoadResultBean<T>();
		Criteria criteria = findPagingCriteria(config);
		long rowCount = (Long) criteria.setProjection(Projections.rowCount())
				.uniqueResult();
		pr.setOffset(config.getOffset());
		pr.setTotalLength((int) rowCount);
		addPagingConfig(config, criteria);
		pr.setData(criteria.list());
		return pr;
	}

	public void addPagingConfig(CannaPagingLoadConfigBean config,
			Criteria criteria) {
		criteria.setProjection(null);
		criteria.setFirstResult(config.getOffset());
		criteria.setMaxResults(config.getLimit());
	}

	public void addPagingConfig(CannaPagingLoadConfigBean config, Query query) {
		query.setFirstResult(config.getOffset());
		query.setMaxResults(config.getLimit());
	}

	public Criteria findPagingCriteria(CannaPagingLoadConfigBean config) {
		return getSession().createCriteria(clazz);
	}

	public String getQueryName() {
		return HibernateUtil.getQueryName(clazz);
	}

	public String getTableName() {
		return HibernateUtil.getTableName(clazz);
	}

}
