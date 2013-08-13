/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui.user.editor;

import java.util.ArrayList;
import java.util.List;

import xdata.etl.web.client.common.editer.EtlSimpleEditor;
import xdata.etl.web.client.common.gridcontainer.EtlGridContainer;
import xdata.etl.web.client.common.gridcontainer.EtlGridContainerBuilder;
import xdata.etl.web.client.gwt.GwtCallBack;
import xdata.etl.web.client.ui.authority.grid.AuthorityGrid;
import xdata.etl.web.client.ui.user.combox.AuthoritySelector;
import xdata.etl.web.shared.entity.authority.Authority;
import xdata.etl.web.shared.entity.user.UserGroup;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.data.client.editor.ListStoreEditor;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.FormPanel.LabelAlign;
import com.sencha.gxt.widget.core.client.form.TextField;

/**
 * @author XuehuiHe
 * @date 2013年8月12日
 * 
 */
public class UserGroupEditor extends EtlSimpleEditor<Integer, UserGroup> {
	interface UserGroupDriver extends
			SimpleBeanEditorDriver<UserGroup, UserGroupEditor> {
	}

	TextField name;

	ListStoreEditor<Authority> authorities;
	@Ignore
	AuthorityGrid authGrid;
	@Ignore
	AuthoritySelector authoritySelector;

	public UserGroupEditor() {
		super(GWT.<UserGroupDriver> create(UserGroupDriver.class));
	}

	@Override
	protected UserGroup newInstance() {
		UserGroup ug = new UserGroup();
		ug.setAuthorities(new ArrayList<Authority>());
		return ug;
	}

	protected void initView() {
		super.initView();
		name = new TextField();
		layoutContainer.add(new FieldLabel(name, "name"), vd);

		authGrid = new AuthorityGrid();
		authorities = new ListStoreEditor<Authority>(authGrid.getStore());
		authGrid.setHeight(150);
		getRoot().setHeight(350);
		EtlGridContainer<Integer, Authority> container = new EtlGridContainer<Integer, Authority>(
				authGrid, null) {
			@Override
			protected void rpcDelete(final List<Authority> list) {
				ListStore<Authority> store = getGrid().getStore();
				for (Authority v : list) {
					store.remove(v);
				}
				getDeleteBt().enable();
			}
		};
		EtlGridContainerBuilder<Integer, Authority> builder = new EtlGridContainerBuilder<Integer, Authority>(
				container);
		builder.setPaging(false);
		builder.setUpdateEnabled(false);

		authoritySelector = new AuthoritySelector();
		authoritySelector.setCallback(new GwtCallBack<List<Authority>>() {
			@Override
			public void call(List<Authority> t) {
				ListStore<Authority> store = authGrid.getStore();
				for (Authority item : t) {
					if (store.indexOf(item) == -1) {
						store.add(item);
					}
				}
			}
		});

		builder.setRealAddBtHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				authoritySelector.show();
			}
		});
		builder.build();
		FieldLabel authGridLabel = new FieldLabel(container, "权限");
		authGridLabel.setLabelAlign(LabelAlign.TOP);
		layoutContainer.add(authGridLabel, vd);
	};

}
