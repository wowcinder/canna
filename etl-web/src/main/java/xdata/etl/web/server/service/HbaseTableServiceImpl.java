/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xdata.etl.web.client.service.hbasemeta.HbaseTableService;
import xdata.etl.web.server.dao.hbasemeta.HbaseTableDao;
import xdata.etl.web.shared.annotations.AccessAuthority;
import xdata.etl.web.shared.annotations.AccessAuthorityGroup;
import xdata.etl.web.shared.entity.hbasemeta.HbaseTable;
import xdata.etl.web.shared.entity.hbasemeta.HbaseTableColumn;

/**
 * @author XuehuiHe
 * @date 2013年8月15日
 */
@Service
@AccessAuthorityGroup("hbase_meta")
public class HbaseTableServiceImpl extends RpcServiceImpl<Integer, HbaseTable>
		implements HbaseTableService {
	public HbaseTableServiceImpl() {
	}

	@Autowired
	public HbaseTableServiceImpl(HbaseTableDao dao) {
		super(dao);
	}

	@Override
	public HbaseTableDao getRpcDao() {
		return (HbaseTableDao) super.getRpcDao();
	}

	@Override
	@AccessAuthority("查询")
	public List<HbaseTableColumn> get(String table, String[] versions) {
		return getRpcDao().get(table, versions);
	}
}
