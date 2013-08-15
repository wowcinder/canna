/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.dao.hbasemeta;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import xdata.etl.web.server.dao.RpcDao;
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
