/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.shared.service.businessmeta;

import xdata.etl.web.shared.entity.businessmeta.BusinessToHbaseTableMapping;
import xdata.etl.web.shared.service.RpcService;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author XuehuiHe
 * @date 2013年9月4日
 */
@RemoteServiceRelativePath("rpc/business_to_hbase_table_mapping.rpc")
public interface BusinessToHbaseTableMappingRpcService extends
		RpcService<Integer, BusinessToHbaseTableMapping>, RemoteService {

}
