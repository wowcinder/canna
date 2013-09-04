/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.property.businessmeta;

import xdata.etl.web.client.property.RpcEntityPropertyAccess;
import xdata.etl.web.shared.entity.businessmeta.Business;

import com.google.gwt.core.shared.GWT;
import com.sencha.gxt.core.client.ValueProvider;

/**
 * @author XuehuiHe
 * @date 2013年9月3日
 */
public interface BusinessProperty extends
		RpcEntityPropertyAccess<Integer, Business> {
	public static final BusinessProperty INSTANCE = GWT
			.create(BusinessProperty.class);

	ValueProvider<Business, String> name();

	ValueProvider<Business, String> desc();
}
