/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xdata.etl.web.client.service.hbasemeta.HbaseTableVersionService;
import xdata.etl.web.server.annotations.AccessAuthorityGroup;
import xdata.etl.web.server.dao.hbasemeta.HbaseTableVersionDao;
import xdata.etl.web.shared.entity.hbasemeta.HbaseTableVersion;

/**
 * @author XuehuiHe
 * @date 2013年8月15日
 */
@Service
@AccessAuthorityGroup("hbase_meta")
public class HbaseTableVersionServiceImpl extends
		RpcServiceImpl<Integer, HbaseTableVersion> implements
		HbaseTableVersionService {

	public HbaseTableVersionServiceImpl() {
	}

	@Autowired
	public HbaseTableVersionServiceImpl(HbaseTableVersionDao dao) {
		super(dao);
	}
}
