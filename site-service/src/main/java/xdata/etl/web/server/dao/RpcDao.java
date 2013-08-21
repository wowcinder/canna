/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.Validation;
import javax.validation.Validator;

import org.hibernate.Criteria;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import xdata.etl.web.shared.common.paging.EtlPagingLoadConfigBean;
import xdata.etl.web.shared.entity.RpcEntity;
import xdata.etl.web.shared.exception.SharedException;

import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoadResultBean;

/**
 * @author XuehuiHe
 * @date 2013年8月2日
 */
public class RpcDao<K extends Serializable, V extends RpcEntity<K>> extends
		HibernateDaoSupport implements IRpcDao<K, V> {

	public final static Validator validator = Validation
			.buildDefaultValidatorFactory().getValidator();

	protected Class<V> clazz;

	@SuppressWarnings("unchecked")
	public RpcDao() {
		Type type = this.getClass().getGenericSuperclass();
		if (type instanceof ParameterizedType) {
			ParameterizedType pt = (ParameterizedType) type;
			this.clazz = (Class<V>) pt.getActualTypeArguments()[1];
		}
	}

	public void validateBean(V t) {
		return;
		/*
		 * Set<ConstraintViolation<V>> violations = validator.validate(t); if
		 * (!violations.isEmpty()) { Set<ConstraintViolation<?>> temp = new
		 * HashSet<ConstraintViolation<?>>( violations); throw new
		 * ConstraintViolationException(temp); }
		 */
	}

	@Override
	public V saveAndReturn(V v) throws SharedException {
		validateBean(v);
		Session s = getSession();
		s.persist(v);
		return v;
	}

	@Override
	public K save(V v) throws SharedException {
		v = saveAndReturn(v);
		return v.getId();
	}

	@Override
	public V update(V v) throws SharedException {
		validateBean(v);
		Session s = getSession();
		s.update(v);
		return v;
	}

	@Override
	public void delete(List<K> ids) throws SharedException {
		Session s = getSession();
		try {
			for (K id : ids) {
				@SuppressWarnings("unchecked")
				V lt = (V) s.load(clazz, id);
				s.delete(lt);
			}
		} catch (ObjectNotFoundException e) {
		}

	}

	@Override
	public void delete(K k) throws SharedException {
		Session s = getSession();
		@SuppressWarnings("unchecked")
		V v = (V) s.load(clazz, k);
		try {
			s.delete(v);
		} catch (ObjectNotFoundException e) {
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<V> get() throws SharedException {
		Session s = getSession();
		return s.createCriteria(clazz)
				.setResultTransformer(CriteriaSpecification.ROOT_ENTITY).list();
	}

	protected Criteria findGetCriteria() {
		return getSession().createCriteria(clazz);
	}

	@Resource
	public void setSessionFactory2(SessionFactory sf) {
		super.setSessionFactory(sf);
	}

	@Override
	public V get(K k) throws SharedException {
		Session s = getSession();
		@SuppressWarnings("unchecked")
		V lt = (V) s.get(clazz, k);
		return lt;
	}

	protected void criteriaLimit(Criteria criteria) {

	}

	@SuppressWarnings("unchecked")
	@Override
	public PagingLoadResult<V> get(EtlPagingLoadConfigBean config)
			throws SharedException {
		Session s = getSession();
		PagingLoadResultBean<V> pr = new PagingLoadResultBean<V>();
		Criteria criteria = s.createCriteria(clazz);
		long rowCount = (Long) criteria.setProjection(Projections.rowCount())
				.uniqueResult();
		pr.setOffset(config.getOffset());
		pr.setTotalLength((int) rowCount);
		addPagingConfig(config, criteria);
		criteriaLimit(criteria);
		criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		pr.setData((List<V>) criteria.list());
		return pr;
	}

	public void addPagingConfig(EtlPagingLoadConfigBean config,
			Criteria criteria) {
		criteria.setProjection(null);
		criteria.setFirstResult(config.getOffset());
		criteria.setMaxResults(config.getLimit());
	}

	public void addPagingConfig(EtlPagingLoadConfigBean config, Query query) {
		query.setFirstResult(config.getOffset());
		query.setMaxResults(config.getLimit());
	}

}
