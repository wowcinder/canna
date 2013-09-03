/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.shared.service.businessmeta;

import xdata.etl.web.shared.entity.businessmeta.Business;
import xdata.etl.web.shared.service.RpcService;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author XuehuiHe
 * @date 2013年9月3日
 */
@RemoteServiceRelativePath("rpc/business.rpc")
public interface BusinessRpcService extends RpcService<Integer, Business>,
		RemoteService {

}
