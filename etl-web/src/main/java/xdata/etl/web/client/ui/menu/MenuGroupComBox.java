/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui.menu;

import xdata.etl.web.client.common.combox.EtlComBox;
import xdata.etl.web.client.property.MenuGroupProperty;
import xdata.etl.web.client.rpc.EntityRpcCaller;
import xdata.etl.web.shared.entity.menu.MenuGroup;

import com.google.gwt.core.shared.GWT;
import com.sencha.gxt.data.shared.LabelProvider;

/**
 * @author XuehuiHe
 * @date 2013年8月12日
 * 
 */
public class MenuGroupComBox extends EtlComBox<Integer, MenuGroup> {

	public MenuGroupComBox(final EntityRpcCaller<Integer, MenuGroup> rpcCaller) {
		super((MenuGroupProperty) GWT.create(MenuGroupProperty.class),
				new LabelProvider<MenuGroup>() {

					@Override
					public String getLabel(MenuGroup item) {
						return item.getName();
					}
				});

		setAddEditor(new MenuGroupEditor(), new AddItem<MenuGroup>() {
			@Override
			public MenuGroup getAddItem() {
				MenuGroup mg = new MenuGroup();
				mg.setId(-1);
				mg.setName("添加...");
				return mg;
			}

			@Override
			public boolean isAddItem(MenuGroup v) {
				return v.getId().equals(-1);
			}
		});

		setDataInitor(new EtlComBoxDataInitor<MenuGroup>() {
			@Override
			protected void run() {
				rpcCaller.get(getInitCallBack());
			}
		});
		
//		init();
	}

}
