/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xdata.etl.web.client.service.hbasemeta.HbaseTableColumnService;
import xdata.etl.web.server.dao.hbasemeta.HbaseTableColumnDao;
import xdata.etl.web.shared.entity.hbase.HbaseTableColumn;

/**
 * @author XuehuiHe
 * @date 2013年8月15日
 */
@Service
public class HbaseTableColumnServiceImpl extends
		RpcServiceImpl<Integer, HbaseTableColumn> implements
		HbaseTableColumnService {
	public HbaseTableColumnServiceImpl() {
	}

	@Autowired
	public HbaseTableColumnServiceImpl(HbaseTableColumnDao dao) {
		super(dao);
	}
}
