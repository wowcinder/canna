/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.dao.hbasemeta;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import xdata.etl.web.server.dao.RpcDao;
import xdata.etl.web.shared.entity.hbasemeta.HbaseTableVersion;
import xdata.etl.web.shared.exception.SharedException;

/**
 * @author XuehuiHe
 * @date 2013年8月15日
 */
@Repository
public class HbaseTableVersionDaoImpl extends
		RpcDao<Integer, HbaseTableVersion> implements HbaseTableVersionDao {
	@Override
	public HbaseTableVersion update(HbaseTableVersion v) throws SharedException {
		v = super.update(v);
//		HbaseTableVersion version = (HbaseTableVersion) getSession()
//				.createCriteria(HbaseTableVersion.class)
//				.add(Restrictions.not(Restrictions.in("columns", v.getColumns())))
//				.add(Restrictions.idEq(v.getId())).uniqueResult();
//		for (HbaseTableColumn hbaseTableColumn : version.getColumns()) {
//			getSession().delete(hbaseTableColumn);
//		}
		return v;
	}
}
