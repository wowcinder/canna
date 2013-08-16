/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.service.hbasequery;

import java.util.List;

import xdata.etl.web.shared.hbasemeta.HbaseRecord;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author XuehuiHe
 * @date 2013年8月16日
 */
@RemoteServiceRelativePath("rpc/hbase_query.rpc")
public interface HbaseQueryService extends RemoteService {
	List<HbaseRecord<String>> getData(String table, String[] versions);
}
