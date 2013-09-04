/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.service.businessmeta;

import org.springframework.stereotype.Service;

import xdata.etl.web.server.dao.businessmeta.BusinessToHbaseTableMappingDao;
import xdata.etl.web.server.service.RpcDelegateServiceImpl;
import xdata.etl.web.shared.entity.businessmeta.BusinessToHbaseTableMapping;

/**
 * @author XuehuiHe
 * @date 2013年9月4日
 */
@Service
public class BusinessToHbaseTableMappingServiceImpl
		extends
		RpcDelegateServiceImpl<Integer, BusinessToHbaseTableMapping, BusinessToHbaseTableMappingDao>
		implements BusinessToHbaseTableMappingService {

}
