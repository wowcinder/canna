/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.service.hbasemeta;

import org.springframework.stereotype.Service;

import xdata.etl.web.server.dao.hbasemeta.HbaseTableVersionDao;
import xdata.etl.web.server.service.RpcDelegateServiceImpl;
import xdata.etl.web.shared.entity.hbasemeta.HbaseTableVersion;

/**
 * @author XuehuiHe
 * @date 2013年8月15日
 */
@Service
public class HbaseTableVersionServiceImpl
		extends
		RpcDelegateServiceImpl<Integer, HbaseTableVersion, HbaseTableVersionDao>
		implements HbaseTableVersionService {

	public HbaseTableVersionServiceImpl() {
	}

}
