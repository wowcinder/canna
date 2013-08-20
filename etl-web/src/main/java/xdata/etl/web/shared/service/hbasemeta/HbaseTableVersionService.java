/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.shared.service.hbasemeta;

import xdata.etl.web.shared.entity.hbasemeta.HbaseTableVersion;
import xdata.etl.web.shared.service.RpcService;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author XuehuiHe
 * @date 2013年8月15日
 */
@RemoteServiceRelativePath("rpc/hbase_table_version.rpc")
public interface HbaseTableVersionService extends
		RpcService<Integer, HbaseTableVersion>, RemoteService {

}
