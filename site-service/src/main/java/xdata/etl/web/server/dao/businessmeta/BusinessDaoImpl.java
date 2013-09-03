/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.dao.businessmeta;

import org.springframework.stereotype.Repository;

import xdata.etl.web.server.dao.RpcDao;
import xdata.etl.web.shared.entity.businessmeta.Business;

/**
 * @author XuehuiHe
 * @date 2013年9月3日
 */
@Repository
public class BusinessDaoImpl extends RpcDao<Integer, Business> implements
		BusinessDao {

}
