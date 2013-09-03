/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.rpc;

import org.springframework.stereotype.Service;

import xdata.etl.web.server.annotations.AccessAuthorityGroup;
import xdata.etl.web.server.service.businessmeta.BusinessServiceImpl;
import xdata.etl.web.shared.entity.businessmeta.Business;
import xdata.etl.web.shared.service.businessmeta.BusinessRpcService;

/**
 * @author XuehuiHe
 * @date 2013年9月3日
 */
@Service
@AccessAuthorityGroup("business")
public class BusinessRpcServiceImpl extends
		RpcServiceImpl<Integer, Business, BusinessServiceImpl> implements
		BusinessRpcService {

}
