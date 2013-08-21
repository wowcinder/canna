/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.shared.service.hbasequery;

import java.util.Date;
import java.util.List;

import org.hibernate.validator.engine.ValidationSupport;

import xdata.etl.web.shared.hbasequery.HbaseRecord;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author XuehuiHe
 * @date 2013年8月16日
 */
@RemoteServiceRelativePath("rpc/hbase_query.rpc")
public interface HbaseQueryRpcService extends RemoteService {
	List<HbaseRecord<String>> getData(String table, String[] versions);
	Short dummyShort();
	
	Integer dummyInteger();
	
	Long dummyLong();

	Boolean dummyBoolean();

	Date dummyDate();
	
	Character dummyCharacter();

	ValidationSupport dummy();
}
