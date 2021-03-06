/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.shared.service.hbasequery;

import java.util.Date;

import org.hibernate.validator.engine.ValidationSupport;

import xdata.etl.web.shared.common.paging.EtlPagingLoadConfigBean;
import xdata.etl.web.shared.exception.SharedException;
import xdata.etl.web.shared.hbasequery.HbaseRecord;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;

/**
 * @author XuehuiHe
 * @date 2013年8月16日
 */
@RemoteServiceRelativePath("rpc/hbase_query.rpc")
public interface HbaseQueryRpcService extends RemoteService {

	PagingLoadResult<HbaseRecord<String>> get(EtlPagingLoadConfigBean config)
			throws SharedException;

	Short dummyShort();

	Double dummyDouble();
	
	Integer dummyInteger();

	Long dummyLong();

	Boolean dummyBoolean();

	Date dummyDate();

	Character dummyCharacter();

	ValidationSupport dummy();
}
