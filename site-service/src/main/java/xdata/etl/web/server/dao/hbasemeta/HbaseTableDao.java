/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.dao.hbasemeta;

import java.util.List;
import java.util.Map;
import java.util.Set;

import xdata.etl.web.server.dao.IRpcDao;
import xdata.etl.web.shared.entity.hbasemeta.HbaseTable;
import xdata.etl.web.shared.entity.hbasemeta.HbaseTableColumn;

/**
 * @author XuehuiHe
 * @date 2013年8月15日
 */
public interface HbaseTableDao extends IRpcDao<Integer, HbaseTable> {
	public List<HbaseTableColumn> get(String table, String... versions);

	/**
	 * @param tableName
	 * @param versions
	 * @return
	 */
	public Map<String, Set<HbaseTableColumn>> getMetaForQuery(String tableName,
			String[] versions);
}
