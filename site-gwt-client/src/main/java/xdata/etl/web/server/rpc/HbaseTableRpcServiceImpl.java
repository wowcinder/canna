/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.rpc;

import java.util.List;

import org.springframework.stereotype.Service;

import xdata.etl.web.server.annotations.AccessAuthority;
import xdata.etl.web.server.annotations.AccessAuthorityGroup;
import xdata.etl.web.server.service.hbasemeta.HbaseTableService;
import xdata.etl.web.shared.entity.hbasemeta.HbaseTable;
import xdata.etl.web.shared.entity.hbasemeta.HbaseTableColumn;
import xdata.etl.web.shared.service.hbasemeta.HbaseTableRpcService;

/**
 * @author XuehuiHe
 * @date 2013年8月15日
 */
@Service
@AccessAuthorityGroup("hbase_meta")
public class HbaseTableRpcServiceImpl extends
		RpcServiceImpl<Integer, HbaseTable, HbaseTableService> implements
		HbaseTableRpcService {
	public HbaseTableRpcServiceImpl() {
	}

	@Override
	@AccessAuthority("查询")
	public List<HbaseTableColumn> get(String table, String[] versions) {
		return getDelegateService().get(table, versions);
	}
}
