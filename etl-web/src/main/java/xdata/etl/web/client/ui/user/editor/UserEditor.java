/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui.user.editor;

import java.util.ArrayList;
import java.util.List;

import xdata.etl.web.client.common.editer.RpcEntitySimpleEditor;
import xdata.etl.web.client.common.gridcontainer.RpcEntityGridContainerBuilder;
import xdata.etl.web.client.common.gridcontainer.RpcEntityGridContainerBuilder.DeleteAction;
import xdata.etl.web.client.gwt.GwtCallBack;
import xdata.etl.web.client.rpc.EntityRpcCaller;
import xdata.etl.web.client.service.ServiceUtil;
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
import com.sencha.gxt.widget.core.client.form.FormPanel.LabelAlign;
import com.sencha.gxt.widget.core.client.form.PasswordField;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.Grid;

/**
 * @author XuehuiHe
 * @date 2013年8月12日
 * 
 */
public class UserEditor extends RpcEntitySimpleEditor<Integer, User> {
	interface UserDriver extends SimpleBeanEditorDriver<User, UserEditor> {
	}

	public UserEditor() {
		super(GWT.<UserDriver> create(UserDriver.class), "用户",
				ServiceUtil.UserRpcCaller);

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
	Grid<Authority> authGrid;
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

		authGrid = new AuthorityGrid().create();
		extraAuthorities = new ListStoreEditor<Authority>(authGrid.getStore());
		authGrid.setHeight(150);
		getRoot().setHeight(350);
		final RpcEntityGridContainerBuilder<Integer, Authority> gridContainerBuilder = new RpcEntityGridContainerBuilder<Integer, Authority>();
		gridContainerBuilder.setUpdateEnabled(false);
		gridContainerBuilder.setAutoInitData(false);
		gridContainerBuilder.setPaging(false);
		gridContainerBuilder.setDeleteAction(new DeleteAction<Authority>() {
			@Override
			protected void doDelete(List<Authority> list,
					GwtCallBack<Void> deleteCallback) {
				ListStore<Authority> store = gridContainerBuilder.getGrid()
						.getStore();
				for (Authority authority : list) {
					store.remove(authority);
				}
				deleteCallback.call(null);
			}
		});

		authoritySelector = new AuthoritySelector();
		authoritySelector.setCallback(new GwtCallBack<List<Authority>>() {
			@Override
			public void _call(List<Authority> t) {
				ListStore<Authority> store = authGrid.getStore();
				for (Authority item : t) {
					if (store.indexOf(item) == -1) {
						store.add(item);
					}
				}
			}
		});

		gridContainerBuilder.setAddBtHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				authoritySelector.show();
			}
		});
		FieldLabel authGridLabel = new FieldLabel(
				gridContainerBuilder.create(), "额外权限");
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
