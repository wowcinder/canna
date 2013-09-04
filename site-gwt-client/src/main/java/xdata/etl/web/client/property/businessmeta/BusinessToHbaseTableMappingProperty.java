/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.property.businessmeta;

import java.util.List;

import xdata.etl.web.client.property.RpcEntityPropertyAccess;
import xdata.etl.web.shared.BusinessType;
import xdata.etl.web.shared.entity.businessmeta.BusinessColumn;
import xdata.etl.web.shared.entity.businessmeta.BusinessToHbaseTableMapping;
import xdata.etl.web.shared.entity.hbasemeta.HbaseTableVersion;

import com.google.gwt.core.shared.GWT;
import com.sencha.gxt.core.client.ValueProvider;

/**
 * @author XuehuiHe
 * @date 2013年9月4日
 */
public interface BusinessToHbaseTableMappingProperty extends
		RpcEntityPropertyAccess<Integer, BusinessToHbaseTableMapping> {
	
	public static final BusinessToHbaseTableMappingProperty INSTANCE = GWT
			.create(BusinessToHbaseTableMappingProperty.class);
	
	ValueProvider<BusinessToHbaseTableMapping, HbaseTableVersion> hbaseTableVersion();

	ValueProvider<BusinessToHbaseTableMapping, List<BusinessColumn>> columns();

	ValueProvider<BusinessToHbaseTableMapping, BusinessType> type();

	ValueProvider<BusinessToHbaseTableMapping, String> desc();
}
