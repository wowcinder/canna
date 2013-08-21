/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui.menu.combox;

import xdata.etl.web.client.ServiceUtil;
import xdata.etl.web.client.common.combox.EtlComBox;
import xdata.etl.web.client.property.menu.MenuGroupProperty;
import xdata.etl.web.client.ui.menu.editor.MenuGroupEditor;
import xdata.etl.web.shared.entity.menu.MenuGroup;

import com.sencha.gxt.data.shared.LabelProvider;

/**
 * @author XuehuiHe
 * @date 2013年8月12日
 * 
 */
public class MenuGroupComBox extends EtlComBox<MenuGroup> {

	public MenuGroupComBox() {
		super(MenuGroupProperty.INSTANCE.key(), new LabelProvider<MenuGroup>() {

			@Override
			public String getLabel(MenuGroup item) {
				return item.getName();
			}
		});
		MenuGroupEditor addEditor = new MenuGroupEditor();
		setAddEditor(addEditor, new AddItem<MenuGroup>() {
			@Override
			protected MenuGroup createAddItem() {
				MenuGroup mg = new MenuGroup();
				mg.setId(-1);
				mg.setName("添加...");
				return mg;
			}
		});

		setDataInitor(new EtlComBoxDataInitor<MenuGroup>() {
			@Override
			protected void run() {
				ServiceUtil.MenuGroupRpcCaller.get(getInitCallBack());
			}
		});

	}

}
