/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui.menu.editor;

import xdata.etl.web.client.ServiceUtil;
import xdata.etl.web.client.common.editer.RpcEntitySimpleEditor;
import xdata.etl.web.client.gwt.GwtCallBack;
import xdata.etl.web.client.ui.menu.combox.AuthoritySelector;
import xdata.etl.web.shared.entity.authority.Authority;
import xdata.etl.web.shared.entity.menu.Menu;
import xdata.etl.web.shared.entity.menu.MenuNode;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.widget.client.TextButton;
import com.sencha.gxt.cell.core.client.form.TextInputCell.TextFieldAppearance;
import com.sencha.gxt.cell.core.client.form.ValueBaseInputCell;
import com.sencha.gxt.cell.core.client.form.ViewData;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer.HorizontalLayoutData;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.form.ValueBaseField;

/**
 * @author XuehuiHe
 * @date 2013年8月10日
 * 
 */
public class MenuEditor extends RpcEntitySimpleEditor<Integer, Menu> {

	interface MenuDriver extends SimpleBeanEditorDriver<Menu, MenuEditor> {
	}

	private static final MenuDriver menuDriver = GWT.create(MenuDriver.class);

	TextField name;
	TextField token;
	ValueBaseField<Authority> requireAuthority;
	@Ignore
	TextButton modifyAuthority;

	public MenuEditor() {
		super(menuDriver, "菜单", null);
	}

	@Override
	protected void save(Menu v, final GwtCallBack<Menu> callback) {
		ServiceUtil.MenuNodeRpcCaller.saveAndReturn(v,
				new GwtCallBack<MenuNode>() {
					@Override
					protected void _call(MenuNode t) {
						callback.call((Menu) t);
					}

					@Override
					public void clean() {
						callback.clean();
					}
				});
	}

	@Override
	protected void update(Menu v, final GwtCallBack<Menu> callback) {
		ServiceUtil.MenuNodeRpcCaller.update(v, new GwtCallBack<MenuNode>() {
			@Override
			protected void _call(MenuNode t) {
				callback.call((Menu) t);
			}

			@Override
			public void clean() {
				callback.clean();
			}
		});
	}

	@Override
	protected void initView() {
		super.initView();
		name = new TextField();
		token = new TextField();
		requireAuthority = new ValueBaseField<Authority>(
				new ValueBaseInputCell<Authority>(
						GWT.<TextFieldAppearance> create(TextFieldAppearance.class)) {
					@Override
					public void render(
							com.google.gwt.cell.client.Cell.Context context,
							Authority value, SafeHtmlBuilder sb) {
						String str = "";
						if (value != null) {
							str = value.getGroup().getName() + "_"
									+ value.getName();
						}
						ViewData viewData = checkViewData(context, str);
						String s = (viewData != null) ? viewData
								.getCurrentValue() : str;

						FieldAppearanceOptions options = new FieldAppearanceOptions(
								getWidth(), getHeight(), isReadOnly(),
								getEmptyText());
						options.setName(getName());
						options.setDisabled(isDisabled());
						((TextFieldAppearance) getAppearance()).render(sb,
								"text", s == null ? "" : s, options);
					}
				}) {

		};

		requireAuthority.setReadOnly(true);
		layoutContainer.add(new FieldLabel(name, "name"), vd);
		layoutContainer.add(new FieldLabel(token, "token"), vd);

		modifyAuthority = new TextButton("修改权限");

		HorizontalLayoutContainer hbox = new HorizontalLayoutContainer();
		hbox.add(requireAuthority, new HorizontalLayoutData(-1, 1));
		hbox.add(modifyAuthority, new HorizontalLayoutData(-1, 1));

		hbox.setHeight(20);
		FieldLabel f = new FieldLabel(hbox, "所需权限");

		layoutContainer.add(f, vd);
		layoutContainer.setWidth(350);

		modifyAuthority.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				AuthoritySelector selector = new AuthoritySelector();
				selector.setCallback(new GwtCallBack<Authority>() {
					@Override
					public void _call(Authority t) {
						requireAuthority.setValue(t);
					}
				});
				selector.show();
			}
		});
	}

	@Override
	protected Menu newInstance() {
		return new Menu();
	}

}
