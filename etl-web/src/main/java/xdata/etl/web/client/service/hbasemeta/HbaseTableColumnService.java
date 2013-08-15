/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.service.hbasemeta;

import xdata.etl.web.client.service.RpcService;
import xdata.etl.web.shared.entity.hbase.HbaseTableColumn;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author XuehuiHe
 * @date 2013年8月15日
 */
@RemoteServiceRelativePath("rpc/hbase_table_column.rpc")
public interface HbaseTableColumnService extends
		RpcService<Integer, HbaseTableColumn>, RemoteService {

}
