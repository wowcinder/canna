/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.property.businessmeta;

import xdata.etl.web.shared.entity.businessmeta.json.JsonBusinessColumn;

import com.google.gwt.core.shared.GWT;
import com.sencha.gxt.core.client.ValueProvider;

/**
 * @author XuehuiHe
 * @date 2013年9月4日
 */
public interface JsonBusinessColumnProperty extends
		BusinessColumnProperty<JsonBusinessColumn> {

	public static final JsonBusinessColumnProperty INSTANCE = GWT
			.create(JsonBusinessColumnProperty.class);

	ValueProvider<JsonBusinessColumn, String> path();
}
