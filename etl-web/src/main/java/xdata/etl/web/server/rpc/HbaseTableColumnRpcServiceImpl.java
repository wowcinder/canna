/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.rpc;

import org.springframework.stereotype.Service;

import xdata.etl.web.server.annotations.AccessAuthorityGroup;
import xdata.etl.web.server.service.hbasemeta.HbaseTableColumnService;
import xdata.etl.web.shared.entity.hbasemeta.HbaseTableColumn;
import xdata.etl.web.shared.service.hbasemeta.HbaseTableColumnRpcService;

/**
 * @author XuehuiHe
 * @date 2013年8月15日
 */
@Service
@AccessAuthorityGroup("hbase_meta")
public class HbaseTableColumnRpcServiceImpl extends
		RpcServiceImpl<Integer, HbaseTableColumn, HbaseTableColumnService>
		implements HbaseTableColumnRpcService {
	public HbaseTableColumnRpcServiceImpl() {
	}
}
