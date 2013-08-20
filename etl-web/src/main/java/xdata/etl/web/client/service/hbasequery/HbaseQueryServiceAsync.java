/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.service.hbasequery;

import java.util.Date;
import java.util.List;

import org.hibernate.validator.engine.ValidationSupport;

import xdata.etl.web.shared.hbasequery.HbaseRecord;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author XuehuiHe
 * @date 2013年8月16日
 */
public interface HbaseQueryServiceAsync {

	void getData(String table, String[] versions,
			AsyncCallback<List<HbaseRecord<String>>> callback);

	void dummyInteger(AsyncCallback<Integer> callback);

	void dummy(AsyncCallback<ValidationSupport> callback);

	void dummyBoolean(AsyncCallback<Boolean> callback);

	void dummyShort(AsyncCallback<Short> callback);

	void dummyLong(AsyncCallback<Long> callback);

	void dummyDate(AsyncCallback<Date> callback);

	void dummyCharacter(AsyncCallback<Character> callback);

}
