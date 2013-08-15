/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui.hbasemeta.combox;

import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.widget.core.client.form.SimpleComboBox;

/**
 * @author XuehuiHe
 * @date 2013年8月15日
 */
public class HbaseTableColumnTypeCombox extends
		SimpleComboBox<HbaseTableColumnTypeCombox> {

	/**
	 * @param labelProvider
	 */
	public HbaseTableColumnTypeCombox(
			LabelProvider<? super HbaseTableColumnTypeCombox> labelProvider) {
		super(labelProvider);
	}

}
