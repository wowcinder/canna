/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.property.businessmeta;

import xdata.etl.web.shared.entity.businessmeta.c.CTypeBusinessColumn;

import com.google.gwt.core.shared.GWT;
import com.sencha.gxt.core.client.ValueProvider;

/**
 * @author XuehuiHe
 * @date 2013年9月4日
 */
public interface CTypeBusinessColumnProperty extends
		BusinessColumnProperty<CTypeBusinessColumn> {
	
	public static final CTypeBusinessColumnProperty INSTANCE = GWT
			.create(BusinessColumnProperty.class);
	
	ValueProvider<CTypeBusinessColumn, Integer> pos();
}
