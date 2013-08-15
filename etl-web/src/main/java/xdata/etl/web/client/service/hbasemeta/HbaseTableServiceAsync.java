/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.service.hbasemeta;

import java.util.List;

import xdata.etl.web.client.service.RpcServiceAsync;
import xdata.etl.web.shared.entity.hbasemeta.HbaseTable;
import xdata.etl.web.shared.entity.hbasemeta.HbaseTableColumn;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author XuehuiHe
 * @date 2013年8月15日
 */
public interface HbaseTableServiceAsync extends
		RpcServiceAsync<Integer, HbaseTable> {

	void get(String table, String[] versions,
			AsyncCallback<List<HbaseTableColumn>> callback);

}
