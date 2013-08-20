/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui.hbasemeta.combox;

import xdata.etl.web.client.ServiceUtil;
import xdata.etl.web.client.common.combox.EtlComBox;
import xdata.etl.web.client.property.hbasemeta.HbaseTableProperty;
import xdata.etl.web.client.ui.hbasemeta.editor.HbaseTableEditor;
import xdata.etl.web.shared.entity.hbasemeta.HbaseTable;

import com.sencha.gxt.data.shared.LabelProvider;

/**
 * @author XuehuiHe
 * @date 2013年8月15日
 */
public class HbaseTableComBox extends EtlComBox<HbaseTable> {

	public HbaseTableComBox() {
		super(HbaseTableProperty.INSTANCE.key(),
				new LabelProvider<HbaseTable>() {

					@Override
					public String getLabel(HbaseTable item) {
						if (item != null) {
							return item.getName();
						}
						return null;
					}
				});

		HbaseTableEditor addEditor = new HbaseTableEditor();
		setAddEditor(addEditor, new AddItem<HbaseTable>() {

			@Override
			protected HbaseTable createAddItem() {
				HbaseTable item = new HbaseTable();
				item.setId(-1);
				item.setName("添加...");
				return item;
			}
		});

		setDataInitor(new EtlComBoxDataInitor<HbaseTable>() {
			@Override
			protected void run() {
				ServiceUtil.HbaseTableRpcCaller.get(getInitBack());
			}
		});

	}
}
