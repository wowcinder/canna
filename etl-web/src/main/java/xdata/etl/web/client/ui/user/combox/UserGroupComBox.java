/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui.user.combox;

import xdata.etl.web.client.common.combox.RpcEntityComBox;
import xdata.etl.web.client.property.user.UserGroupProperty;
import xdata.etl.web.client.rpc.EntityRpcCaller;
import xdata.etl.web.client.ui.user.editor.UserGroupEditor;
import xdata.etl.web.shared.entity.user.UserGroup;

import com.google.gwt.core.shared.GWT;
import com.sencha.gxt.data.shared.LabelProvider;

/**
 * @author XuehuiHe
 * @date 2013年8月12日
 * 
 */
public class UserGroupComBox extends RpcEntityComBox<Integer, UserGroup> {

	public UserGroupComBox(final EntityRpcCaller<Integer, UserGroup> rpcCaller) {
		super(GWT.<UserGroupProperty> create(UserGroupProperty.class),
				new LabelProvider<UserGroup>() {
					@Override
					public String getLabel(UserGroup item) {
						if (item != null) {
							return item.getName();
						}
						return null;
					}
				});

		UserGroupEditor addEditor = new UserGroupEditor();
		setAddEditor(addEditor, new AddItem<UserGroup>() {

			@Override
			public UserGroup getAddItem() {
				UserGroup addItem = new UserGroup();
				addItem.setId(-1);
				addItem.setName("添加...");
				return addItem;
			}

			@Override
			public boolean isAddItem(UserGroup v) {
				return v.getId().equals(-1);
			}
		});

		setDataInitor(new EtlComBoxDataInitor<UserGroup>() {
			@Override
			protected void run() {
				rpcCaller.get(getInitCallBack());
			}
		});
	}

}
