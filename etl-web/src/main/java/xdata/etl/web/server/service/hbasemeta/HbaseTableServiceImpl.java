/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.service.hbasemeta;

import java.util.List;

import org.springframework.stereotype.Service;

import xdata.etl.web.server.dao.hbasemeta.HbaseTableDao;
import xdata.etl.web.server.service.RpcDelegateServiceImpl;
import xdata.etl.web.shared.entity.hbasemeta.HbaseTable;
import xdata.etl.web.shared.entity.hbasemeta.HbaseTableColumn;

/**
 * @author XuehuiHe
 * @date 2013年8月15日
 */
@Service
public class HbaseTableServiceImpl extends
		RpcDelegateServiceImpl<Integer, HbaseTable, HbaseTableDao> implements
		HbaseTableService {
	public HbaseTableServiceImpl() {
	}

	@Override
	public List<HbaseTableColumn> get(String table, String[] versions) {
		return getDao().get(table, versions);
	}
}
