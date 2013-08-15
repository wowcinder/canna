/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.property;

import java.util.Date;

import xdata.etl.web.shared.entity.hbasemeta.HbaseTableColumn;
import xdata.etl.web.shared.entity.hbasemeta.HbaseTableColumn.HbaseTableColumnType;
import xdata.etl.web.shared.entity.hbasemeta.HbaseTableVersion;

import com.google.gwt.core.shared.GWT;
import com.sencha.gxt.core.client.ValueProvider;

/**
 *
 *
 */
public interface HbaseTableColumnProperty extends
		RpcEntityPropertyAccess<Integer, HbaseTableColumn> {

	public static final HbaseTableColumnProperty INSTANCE = GWT
			.create(HbaseTableColumnProperty.class);

	ValueProvider<HbaseTableColumn, String> name();

	ValueProvider<HbaseTableColumn, String> shortname();

	ValueProvider<HbaseTableColumn, HbaseTableColumnType> type();

	ValueProvider<HbaseTableColumn, Integer> pos();

	ValueProvider<HbaseTableColumn, HbaseTableVersion> version();

	ValueProvider<HbaseTableColumn, Date> timestamp();

	ValueProvider<HbaseTableColumn, String> desc();

	ValueProvider<HbaseTableColumn, Date> createTime();
}
