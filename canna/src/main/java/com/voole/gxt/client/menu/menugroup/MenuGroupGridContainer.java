package com.voole.gxt.client.menu.menugroup;

import java.util.ArrayList;
import java.util.List;

import com.sencha.gxt.widget.core.client.box.AlertMessageBox;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.voole.gxt.client.canna.editor.CannaEditor.EditorAddHanlder;
import com.voole.gxt.client.canna.gridcontainer.CannaGridContainer;
import com.voole.gxt.client.canna.gridcontainer.CannaGridContainerConfig;
import com.voole.gxt.client.rpcclient.callback.SaveOrderCallback;
import com.voole.gxt.client.rpcclient.menu.MenuGroupRpcClient;
import com.voole.gxt.shared.entity.menu.MenuGroup;

public class MenuGroupGridContainer extends CannaGridContainer<MenuGroup>
		implements SaveOrderCallback {
	protected TextButton saveOrderBt;
	
	private static CannaGridContainerConfig gridConfig;
	static{
		gridConfig = new CannaGridContainerConfig();
		gridConfig.isPaging = false;
	}

	public MenuGroupGridContainer() {
		super(new MenuGroupGrid(), new MenuGroupRpcClient(),gridConfig);
		this.editorAddHanlder = new EditorAddHanlder<MenuGroup>() {
			@Override
			public void postAdd(MenuGroup t) {
				getStore().add(t);
			}
		};
		setEditor(new MenuGroupEditor());

		saveOrderBt = new TextButton("保存菜单组顺序");
		bar.add(saveOrderBt);
		saveOrderBt.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				saveOrder();
			}
		});
	}

	protected void saveOrder() {
		List<MenuGroup> list = getStore().getAll();
		List<Long> ids = new ArrayList<Long>();
		for (MenuGroup mg : list) {
			ids.add(mg.getId());
		}
		saveOrderBt.disable();
		getRpc().saveOrder(ids, this);
	}

	@Override
	public void postSaveOrder() {
		saveOrderBt.enable();
	}

	@Override
	public MenuGroupRpcClient getRpc() {
		return (MenuGroupRpcClient) super.getRpc();
	}

	@Override
	protected void noFoundDeleteItems() {
		AlertMessageBox d = new AlertMessageBox("WARN", "请选中删除的菜单组");
		d.show();
	}

}
