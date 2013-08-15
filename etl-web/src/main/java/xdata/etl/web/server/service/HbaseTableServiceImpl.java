/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xdata.etl.web.client.service.hbasemeta.HbaseTableService;
import xdata.etl.web.server.dao.hbasemeta.HbaseTableDao;
import xdata.etl.web.shared.entity.hbase.HbaseTable;

/**
 * @author XuehuiHe
 * @date 2013年8月15日
 */
@Service
public class HbaseTableServiceImpl extends RpcServiceImpl<Integer, HbaseTable>
		implements HbaseTableService {
	public HbaseTableServiceImpl() {
	}

	@Autowired
	public HbaseTableServiceImpl(HbaseTableDao dao) {
		super(dao);
	}
}
