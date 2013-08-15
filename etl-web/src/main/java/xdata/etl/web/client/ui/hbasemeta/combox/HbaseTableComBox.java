/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui.hbasemeta.combox;

import xdata.etl.web.client.common.combox.EtlComBox;
import xdata.etl.web.client.property.HbaseTableProperty;
import xdata.etl.web.client.service.hbasemeta.HbaseTableService;
import xdata.etl.web.client.ui.hbasemeta.editor.HbaseTableEditor;
import xdata.etl.web.shared.entity.hbasemeta.HbaseTable;

import com.sencha.gxt.data.shared.LabelProvider;

/**
 * @author XuehuiHe
 * @date 2013年8月15日
 */
public class HbaseTableComBox extends EtlComBox<Integer, HbaseTable> {

	public HbaseTableComBox() {
		super(HbaseTableProperty.INSTANCE, new LabelProvider<HbaseTable>() {

			@Override
			public String getLabel(HbaseTable item) {
				if (item != null) {
					return item.getName();
				}
				return null;
			}
		});

		HbaseTableEditor addEditor = new HbaseTableEditor();
		addEditor.setRpcCaller(HbaseTableService.RPC_CALLER);
		setAddEditor(addEditor, new AddItem<HbaseTable>() {

			@Override
			public HbaseTable getAddItem() {
				HbaseTable item = new HbaseTable();
				item.setId(-1);
				item.setName("添加...");
				return item;
			}

			@Override
			public boolean isAddItem(HbaseTable v) {
				return v.getId().equals(-1);
			}
		});

		setDataInitor(new EtlComBoxDataInitor<HbaseTable>() {
			@Override
			protected void run() {
				HbaseTableService.RPC_CALLER.get(getInitBack());
			}
		});

	}
}
