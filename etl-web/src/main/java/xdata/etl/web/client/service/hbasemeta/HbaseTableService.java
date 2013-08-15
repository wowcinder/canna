/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.service.hbasemeta;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import xdata.etl.web.client.service.RpcService;
import xdata.etl.web.shared.entity.hbase.HbaseTable;

/**
 * @author XuehuiHe
 * @date 2013年8月15日
 */
@RemoteServiceRelativePath("rpc/hbase_table.rpc")
public interface HbaseTableService extends RpcService<Integer, HbaseTable>,
		RemoteService {

}
