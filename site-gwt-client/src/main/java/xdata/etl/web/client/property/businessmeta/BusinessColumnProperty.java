/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.property.businessmeta;

import xdata.etl.web.client.property.RpcEntityPropertyAccess;
import xdata.etl.web.shared.BusinessType;
import xdata.etl.web.shared.BusinessType.BusinessColumnType;
import xdata.etl.web.shared.entity.businessmeta.BusinessColumn;
import xdata.etl.web.shared.entity.businessmeta.BusinessToHbaseTableMapping;

import com.sencha.gxt.core.client.ValueProvider;

/**
 * @author XuehuiHe
 * @date 2013年9月4日
 */
public interface BusinessColumnProperty<T extends BusinessColumn> extends
		RpcEntityPropertyAccess<Integer, T> {

	ValueProvider<T, BusinessToHbaseTableMapping> mapping();

	ValueProvider<T, String> desc();

	ValueProvider<T, BusinessColumnType> columnType();

	ValueProvider<T, BusinessType> businessType();

	ValueProvider<T, T> self();
}
