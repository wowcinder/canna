/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui.businessmeta.combox;

import xdata.etl.web.client.property.hbasemeta.HbaseTableColumnProperty;
import xdata.etl.web.shared.entity.hbasemeta.HbaseTableColumn;
import xdata.etl.web.shared.entity.hbasemeta.HbaseTableVersion;

import com.sencha.gxt.cell.core.client.form.ComboBoxCell.TriggerAction;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.form.ComboBox;

/**
 * @author XuehuiHe
 * @date 2013年9月4日
 */
public class HbaseTableColumnCombox extends ComboBox<HbaseTableColumn> {

	/**
	 * @param store
	 * @param labelProvider
	 */
	public HbaseTableColumnCombox() {
		super(new ListStore<HbaseTableColumn>(
				HbaseTableColumnProperty.INSTANCE.key()),
				new LabelProvider<HbaseTableColumn>() {
					@Override
					public String getLabel(HbaseTableColumn item) {
						return item.getName();
					}
				});
		setEditable(false);
		setForceSelection(true);
		setTriggerAction(TriggerAction.ALL);
	}

	public void setHbaseTableVersion(HbaseTableVersion version) {
		getStore().clear();
		// TODO
	}

}
