/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.dao.hbasemeta;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoadResultBean;

import xdata.etl.web.server.dao.RpcDao;
import xdata.etl.web.shared.common.paging.EtlPagingLoadConfigBean;
import xdata.etl.web.shared.entity.hbasemeta.HbaseTableColumn;
import xdata.etl.web.shared.entity.hbasemeta.HbaseTableVersion;
import xdata.etl.web.shared.exception.SharedException;

/**
 * @author XuehuiHe
 * @date 2013年8月15日
 */
@Repository
public class HbaseTableVersionDaoImpl extends
		RpcDao<Integer, HbaseTableVersion> implements HbaseTableVersionDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<HbaseTableVersion> get() throws SharedException {
		Session s = getSession();
		return s.createCriteria(clazz)
				.setResultTransformer(
						CriteriaSpecification.DISTINCT_ROOT_ENTITY).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public PagingLoadResult<HbaseTableVersion> get(
			EtlPagingLoadConfigBean config) throws SharedException {
		Session s = getSession();
		PagingLoadResultBean<HbaseTableVersion> pr = new PagingLoadResultBean<HbaseTableVersion>();
		Criteria criteria = s.createCriteria(clazz);
		criteria.setFetchMode("columns", FetchMode.SELECT);
		long rowCount = (Long) criteria.setProjection(Projections.rowCount())
				.uniqueResult();
		pr.setOffset(config.getOffset());
		pr.setTotalLength((int) rowCount);
		addPagingConfig(config, criteria);
		criteriaLimit(criteria);
		pr.setData((List<HbaseTableVersion>) criteria.list());
		return pr;
	}

	@Override
	protected void criteriaLimit(Criteria criteria) {
		criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
	}

	@SuppressWarnings("unchecked")
	@Override
	public HbaseTableVersion update(HbaseTableVersion v) throws SharedException {
		v = super.update(v);
		Session s = getSession();
		List<Integer> ids = new ArrayList<Integer>();
		for (HbaseTableColumn hbaseTableColumn : v.getColumns()) {
			ids.add(hbaseTableColumn.getId());
		}
		Criteria criteria = s.createCriteria(HbaseTableColumn.class).add(
				Restrictions.eq("version", v));
		if (ids.size() > 0) {
			criteria.add(Restrictions.not(Restrictions.in("id", ids)));
		}
		List<HbaseTableColumn> toDeletes = criteria.list();
		for (HbaseTableColumn hbaseTableColumn : toDeletes) {
			s.delete(hbaseTableColumn);
		}
		return v;
	}
}
