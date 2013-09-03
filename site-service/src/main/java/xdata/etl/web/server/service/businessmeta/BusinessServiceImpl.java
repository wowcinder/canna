/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.service.businessmeta;

import org.springframework.stereotype.Service;

import xdata.etl.web.server.dao.businessmeta.BusinessDao;
import xdata.etl.web.server.service.RpcDelegateServiceImpl;
import xdata.etl.web.shared.entity.businessmeta.Business;

/**
 * @author XuehuiHe
 * @date 2013年9月3日
 */
@Service
public class BusinessServiceImpl extends
		RpcDelegateServiceImpl<Integer, Business, BusinessDao> implements
		BusinessService {

}
