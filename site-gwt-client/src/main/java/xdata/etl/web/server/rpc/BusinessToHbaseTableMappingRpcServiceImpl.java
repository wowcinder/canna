/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.rpc;

import org.springframework.stereotype.Service;

import xdata.etl.web.server.service.businessmeta.BusinessToHbaseTableMappingService;
import xdata.etl.web.shared.entity.businessmeta.BusinessToHbaseTableMapping;
import xdata.etl.web.shared.service.businessmeta.BusinessToHbaseTableMappingRpcService;

/**
 * @author XuehuiHe
 * @date 2013年9月4日
 */
@Service
public class BusinessToHbaseTableMappingRpcServiceImpl
		extends
		RpcServiceImpl<Integer, BusinessToHbaseTableMapping, BusinessToHbaseTableMappingService>
		implements BusinessToHbaseTableMappingRpcService {

}
