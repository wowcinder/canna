/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui.businessmeta.combox;

import xdata.etl.web.client.property.businessmeta.BusinessToHbaseTableMappingProperty;
import xdata.etl.web.shared.BusinessType;
import xdata.etl.web.shared.entity.businessmeta.BusinessToHbaseTableMapping;
import xdata.etl.web.shared.entity.hbasemeta.HbaseTableVersion;

import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.form.ComboBox;

/**
 * @author XuehuiHe
 * @date 2013年9月4日
 */
public class BusinessToHbaseTableMappingComBox extends
		ComboBox<BusinessToHbaseTableMapping> {
	private BusinessType type;

	public BusinessToHbaseTableMappingComBox() {
		super(new ListStore<BusinessToHbaseTableMapping>(
				BusinessToHbaseTableMappingProperty.INSTANCE.key()),
				new LabelProvider<BusinessToHbaseTableMapping>() {
					@Override
					public String getLabel(BusinessToHbaseTableMapping item) {
						HbaseTableVersion version = item.getHbaseTableVersion();
						return version.getTable().getName() + "#"
								+ version.getVersion();
					}

				});
	}

	public void refresh() {
		getStore().clear();
		// TODO
	}

	public BusinessType getType() {
		return type;
	}

	public void setType(BusinessType type) {
		this.type = type;
		refresh();
	}

}
