package com.voole.gxt.client.user;

import com.google.gwt.core.client.GWT;
import com.sencha.gxt.cell.core.client.TextButtonCell;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.voole.gxt.client.canna.editor.CannaEditor.EditorUpdateHanlder;
import com.voole.gxt.client.canna.grid.AbstractCannaSafeHtmlRenderer;
import com.voole.gxt.client.canna.grid.CannaGrid;
import com.voole.gxt.client.canna.grid.CannaSafeHtmlCell;
import com.voole.gxt.client.property.user.UserProperties;
import com.voole.gxt.shared.entity.user.User;
import com.voole.gxt.shared.entity.user.UserGroup;

public class UserGrid extends CannaGrid<User> {
	private final static UserProperties props = GWT
			.create(UserProperties.class);

	private UserModifyPasswordEditor passwordEditor = new UserModifyPasswordEditor();

	public UserGrid() {
		super(props);
		passwordEditor.setUpdateHanlder(new EditorUpdateHanlder<User>() {
			@Override
			public void postUpdate(User t) {
				getStore().update(t);
			}
		});
	}

	@Override
	protected void initColumnModel() {
		ColumnConfig<User, String> email = new ColumnConfig<User, String>(
				props.email(), 200, "email");

		ColumnConfig<User, String> mobile = new ColumnConfig<User, String>(
				props.mobile(), 200, "mobile");

		ColumnConfig<User, String> name = new ColumnConfig<User, String>(
				props.name(), 200, "name");
		ColumnConfig<User, String> extNum = new ColumnConfig<User, String>(
				props.extNum(), 200, "分机号");

		ColumnConfig<User, UserGroup> group = new ColumnConfig<User, UserGroup>(
				props.group(), 200, "用户组");
		group.setCell(new CannaSafeHtmlCell<UserGroup>(
				new AbstractCannaSafeHtmlRenderer<UserGroup>() {
					@Override
					public String getRenderStr(UserGroup t) {
						return t.getName();
					}
				}));

		ColumnConfig<User, String> op = new ColumnConfig<User, String>(
				new ValueProvider<User, String>() {
					@Override
					public String getValue(User object) {
						return "修改密码";
					}

					@Override
					public void setValue(User object, String value) {
					}

					@Override
					public String getPath() {
						return null;
					}

				}, 200, "操作");
		TextButtonCell button = new TextButtonCell();
		button.addSelectHandler(new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				modifyPassword(event.getContext().getIndex());
			}
		});
		op.setCell(button);

		ccList.add(email);
		ccList.add(name);
		ccList.add(mobile);
		ccList.add(extNum);
		ccList.add(group);
		ccList.add(op);
	}

	private void modifyPassword(final int index) {
		User u = getStore().get(index);
		passwordEditor.edit(u);
	}
}
