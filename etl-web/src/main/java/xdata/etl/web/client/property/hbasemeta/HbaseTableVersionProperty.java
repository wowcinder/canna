/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.property.hbasemeta;

import java.util.Date;
import java.util.List;

import xdata.etl.web.client.property.RpcEntityPropertyAccess;
import xdata.etl.web.shared.entity.hbasemeta.HbaseTable;
import xdata.etl.web.shared.entity.hbasemeta.HbaseTableColumn;
import xdata.etl.web.shared.entity.hbasemeta.HbaseTableVersion;

import com.google.gwt.core.shared.GWT;
import com.sencha.gxt.core.client.ValueProvider;

/**
 *
 *
 */
public interface HbaseTableVersionProperty extends
		RpcEntityPropertyAccess<Integer, HbaseTableVersion> {

	public static final HbaseTableVersionProperty INSTANCE = GWT
			.create(HbaseTableVersionProperty.class);

	ValueProvider<HbaseTableVersion, String> version();

	ValueProvider<HbaseTableVersion, Date> timestamp();

	ValueProvider<HbaseTableVersion, String> desc();

	ValueProvider<HbaseTableVersion, HbaseTable> table();

	ValueProvider<HbaseTableVersion, List<HbaseTableColumn>> columns();

	ValueProvider<HbaseTableVersion, Date> createTime();
}
