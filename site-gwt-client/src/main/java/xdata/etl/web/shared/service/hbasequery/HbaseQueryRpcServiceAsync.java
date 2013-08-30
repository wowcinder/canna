/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.shared.service.hbasequery;

import java.util.Date;
import java.util.List;

import org.hibernate.validator.engine.ValidationSupport;

import xdata.etl.web.shared.common.paging.EtlPagingLoadConfigBean;
import xdata.etl.web.shared.hbasequery.HbaseRecord;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;

/**
 * @author XuehuiHe
 * @date 2013年8月16日
 */
public interface HbaseQueryRpcServiceAsync {

	void getData(String table, String[] versions,
			AsyncCallback<List<HbaseRecord<String>>> callback);

	void dummyInteger(AsyncCallback<Integer> callback);

	void dummy(AsyncCallback<ValidationSupport> callback);

	void dummyBoolean(AsyncCallback<Boolean> callback);

	void dummyShort(AsyncCallback<Short> callback);

	void dummyLong(AsyncCallback<Long> callback);

	void dummyDate(AsyncCallback<Date> callback);

	void dummyCharacter(AsyncCallback<Character> callback);

	void get(EtlPagingLoadConfigBean config,
			AsyncCallback<PagingLoadResult<HbaseRecord<String>>> callback);

	/**
	 * @param loadConfig
	 * @param callback
	 */
	void getRecords(PagingLoadConfig loadConfig,
			AsyncCallback<PagingLoadResult<HbaseRecord<String>>> callback);

}
