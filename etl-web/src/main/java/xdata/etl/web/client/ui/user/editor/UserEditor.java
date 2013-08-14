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
import xdata.etl.web.client.rpc.EntityRpcCaller;
import xdata.etl.web.client.service.user.UserGroupService;
import xdata.etl.web.client.service.user.UserGroupServiceAsync;
import xdata.etl.web.client.ui.authority.grid.AuthorityGrid;
import xdata.etl.web.client.ui.user.combox.AuthoritySelector;
import xdata.etl.web.client.ui.user.combox.UserGroupComBox;
import xdata.etl.web.shared.entity.authority.Authority;
import xdata.etl.web.shared.entity.user.User;
import xdata.etl.web.shared.entity.user.UserGroup;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.data.client.editor.ListStoreEditor;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.event.ShowEvent;
import com.sencha.gxt.widget.core.client.event.ShowEvent.ShowHandler;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.PasswordField;
import com.sencha.gxt.widget.core.client.form.FormPanel.LabelAlign;
import com.sencha.gxt.widget.core.client.form.TextField;

/**
 * @author XuehuiHe
 * @date 2013年8月12日
 * 
 */
public class UserEditor extends EtlSimpleEditor<Integer, User> {
	interface UserDriver extends SimpleBeanEditorDriver<User, UserEditor> {
	}

	public UserEditor() {
		super(GWT.<UserDriver> create(UserDriver.class));

		getRoot().addShowHandler(new ShowHandler() {
			@Override
			public void onShow(ShowEvent event) {
				userGroup.fireEvent(event);
			}
		});
	}

	TextField email;
	UserGroupComBox userGroup;
	PasswordField password;
	ListStoreEditor<Authority> extraAuthorities;
	@Ignore
	AuthorityGrid authGrid;
	@Ignore
	AuthoritySelector authoritySelector;

	@Override
	protected void initView() {
		super.initView();
		email = new TextField();
		userGroup = new UserGroupComBox(
				new EntityRpcCaller<Integer, UserGroup>(GWT
						.<UserGroupServiceAsync> create(UserGroupService.class)));
		password = new PasswordField();

		layoutContainer.add(new FieldLabel(email, "email"), vd);
		layoutContainer.add(new FieldLabel(userGroup, "用户组"), vd);
		layoutContainer.add(new FieldLabel(password, "密码"), vd);

		authGrid = new AuthorityGrid();
		extraAuthorities = new ListStoreEditor<Authority>(authGrid.getStore());
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
		FieldLabel authGridLabel = new FieldLabel(container, "额外权限");
		authGridLabel.setLabelAlign(LabelAlign.TOP);
		layoutContainer.add(authGridLabel, vd);

	}
	
	@Override
	public void edit(User v) {
		v.setPassword("");
		super.edit(v);
	}

	@Override
	protected User newInstance() {
		User u = new User();
		u.setExtraAuthorities(new ArrayList<Authority>());
		return u;
	}

}
