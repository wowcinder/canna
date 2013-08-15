/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.property;

import java.util.Date;
import java.util.List;

import xdata.etl.web.shared.entity.hbasemeta.HbaseTable;
import xdata.etl.web.shared.entity.hbasemeta.HbaseTableVersion;

import com.google.gwt.core.shared.GWT;
import com.sencha.gxt.core.client.ValueProvider;

/**
 *
 *
 */
public interface HbaseTableProperty extends
		RpcEntityPropertyAccess<Integer, HbaseTable> {

	public static final HbaseTableProperty INSTANCE = GWT
			.create(HbaseTableProperty.class);

	ValueProvider<HbaseTable, String> name();

	ValueProvider<HbaseTable, String> shortname();

	ValueProvider<HbaseTable, Date> timestamp();

	ValueProvider<HbaseTable, String> desc();

	ValueProvider<HbaseTable, List<HbaseTableVersion>> versions();

	ValueProvider<HbaseTable, Date> createTime();
}
