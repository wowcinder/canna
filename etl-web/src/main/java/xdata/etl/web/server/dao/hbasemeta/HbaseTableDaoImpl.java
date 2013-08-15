/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.dao.hbasemeta;

import org.springframework.stereotype.Repository;

import xdata.etl.web.server.dao.RpcDao;
import xdata.etl.web.shared.entity.hbasemeta.HbaseTable;

/**
 * @author XuehuiHe
 * @date 2013年8月15日
 */
@Repository
public class HbaseTableDaoImpl extends RpcDao<Integer, HbaseTable> implements
		HbaseTableDao {

}
