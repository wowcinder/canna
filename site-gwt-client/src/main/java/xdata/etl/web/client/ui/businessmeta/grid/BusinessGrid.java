/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui.businessmeta.grid;

import xdata.etl.web.client.common.grid.RpcEntityGridBuilder;
import xdata.etl.web.client.property.businessmeta.BusinessProperty;
import xdata.etl.web.shared.entity.businessmeta.Business;

import com.sencha.gxt.widget.core.client.grid.ColumnConfig;

/**
 * @author XuehuiHe
 * @date 2013年9月3日
 */
public class BusinessGrid extends
		RpcEntityGridBuilder<Integer, Business, BusinessProperty> {

	/**
	 * @param props
	 */
	public BusinessGrid() {
		super(BusinessProperty.INSTANCE);
	}

	@Override
	protected void initColumnModel() {
		super.initColumnModel();
		ColumnConfig<Business, String> nameCC = new ColumnConfig<Business, String>(
				getProps().name(), 200, "name");
		ColumnConfig<Business, String> descCC = new ColumnConfig<Business, String>(
				getProps().desc(), 200, "desc");
		getColumnConfigs().add(nameCC);
		getColumnConfigs().add(descCC);
	}

}
