/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.dao.hbasemeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import xdata.etl.web.server.dao.RpcDao;
import xdata.etl.web.shared.entity.hbasemeta.HbaseTable;
import xdata.etl.web.shared.entity.hbasemeta.HbaseTableColumn;
import xdata.etl.web.shared.entity.hbasemeta.HbaseTableVersion;

/**
 * @author XuehuiHe
 * @date 2013年8月15日
 */
@Repository
public class HbaseTableDaoImpl extends RpcDao<Integer, HbaseTable> implements
		HbaseTableDao {

	@Override
	public List<HbaseTableColumn> get(String table, String... versions) {
		Session s = getSession();
		Criteria criteria = s
				.createCriteria(HbaseTableColumn.class)
				.createAlias("version", "v")
				.createAlias("v.table", "t")
				.add(Restrictions.eq("t.name", table))
				.setResultTransformer(
						CriteriaSpecification.DISTINCT_ROOT_ENTITY)
				.addOrder(Order.desc("v.version")).addOrder(Order.asc("pos"));
		if (versions != null && versions.length > 0) {
			criteria.add(Restrictions.in("v.version", versions));
		}
		@SuppressWarnings("unchecked")
		List<HbaseTableColumn> list = criteria.list();
		List<HbaseTableColumn> result = new ArrayList<HbaseTableColumn>();
		Set<String> names = new HashSet<String>();
		for (HbaseTableColumn column : list) {
			if (!names.contains(column.getName())) {
				result.add(column);
				names.add(column.getName());
			}
		}

		return result;
	}

	@Override
	public Map<String, Set<HbaseTableColumn>> getMetaForQuery(String tableName,
			String[] versions) {
		Map<String, Set<HbaseTableColumn>> map = new HashMap<String, Set<HbaseTableColumn>>();
		Session s = getSession();
		Criteria criteria = s
				.createCriteria(HbaseTableVersion.class)
				.createAlias("table", "t")
				.add(Restrictions.eq("t.name", tableName))
				.setResultTransformer(
						CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		if (versions == null || versions.length == 0) {// 所有
		} else {
			criteria.add(Restrictions.in("version", versions));
		}
		@SuppressWarnings("unchecked")
		List<HbaseTableVersion> tableVersions = criteria.list();
		for (HbaseTableVersion hbaseTableVersion : tableVersions) {
			Set<HbaseTableColumn> set = new HashSet<HbaseTableColumn>();
			set.addAll(hbaseTableVersion.getColumns());
			map.put(hbaseTableVersion.getVersion(), set);
		}

		return map;
	}
}
