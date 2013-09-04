/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui.businessmeta.combox;

import java.util.List;

import xdata.etl.web.client.ServiceUtil;
import xdata.etl.web.client.gwt.GwtCallBack;
import xdata.etl.web.client.property.hbasemeta.HbaseTableVersionProperty;
import xdata.etl.web.shared.entity.hbasemeta.HbaseTableVersion;

import com.sencha.gxt.cell.core.client.form.ComboBoxCell.TriggerAction;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.form.ComboBox;

/**
 * @author XuehuiHe
 * @date 2013年9月4日
 */
public class HbaseTableVersionCombox extends ComboBox<HbaseTableVersion> {

	/**
	 * @param store
	 * @param labelProvider
	 */
	public HbaseTableVersionCombox() {
		super(new ListStore<HbaseTableVersion>(
				HbaseTableVersionProperty.INSTANCE.key()),
				new LabelProvider<HbaseTableVersion>() {
					@Override
					public String getLabel(HbaseTableVersion item) {
						return item.getTable().getName() + "#"
								+ item.getVersion();
					}
				});
		setEditable(false);
		setForceSelection(true);
		setTriggerAction(TriggerAction.ALL);
	}

	@Override
	protected void onAfterFirstAttach() {
		super.onAfterFirstAttach();

		ServiceUtil.HbaseTableVersionRpcCaller
				.get(new GwtCallBack<List<HbaseTableVersion>>() {
					@Override
					protected void _call(List<HbaseTableVersion> t) {
						getStore().addAll(t);
					}
				});
	}

}
