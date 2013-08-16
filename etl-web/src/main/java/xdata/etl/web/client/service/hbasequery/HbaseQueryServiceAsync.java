/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.service.hbasequery;

import java.util.List;

import xdata.etl.web.shared.hbasemeta.HbaseRecord;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author XuehuiHe
 * @date 2013年8月16日
 */
public interface HbaseQueryServiceAsync {

	void getData(String table, String[] versions,
			AsyncCallback<List<HbaseRecord<String>>> callback);

	void dummyInteger(AsyncCallback<Integer> callback);

}
