/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.shared.service.hbasemeta;

import java.util.List;

import xdata.etl.web.shared.entity.hbasemeta.HbaseTable;
import xdata.etl.web.shared.entity.hbasemeta.HbaseTableColumn;
import xdata.etl.web.shared.service.RpcService;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author XuehuiHe
 * @date 2013年8月15日
 */
@RemoteServiceRelativePath("rpc/hbase_table.rpc")
public interface HbaseTableRpcService extends RpcService<Integer, HbaseTable>,
		RemoteService {

	public List<HbaseTableColumn> get(String table, String[] versions);

}
