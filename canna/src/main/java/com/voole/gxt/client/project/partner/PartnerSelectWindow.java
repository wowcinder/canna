package com.voole.gxt.client.project.partner;

import java.util.List;

import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.box.AlertMessageBox;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutPack;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.voole.gxt.client.rpcclient.callback.GetCallback;
import com.voole.gxt.client.rpcclient.project.ProjectPartnerRpcClient;
import com.voole.gxt.shared.entity.project.ProjectPartner;

public class PartnerSelectWindow extends Window implements GetCallback<List<ProjectPartner>> {

	public static interface PartnerSelectCallBack {
		void post(List<ProjectPartner> list);
	}

	public static final ProjectPartnerRpcClient rpc = new ProjectPartnerRpcClient();

	private PartnerGridContainer gridCon;
	private TextButton addBt;
	private TextButton cancelBt;

	private PartnerSelectCallBack callback;

	public PartnerSelectWindow(PartnerSelectCallBack callback) {
		super();
		setHeadingText("合作伙伴");
		setModal(true);
		this.callback = callback;
		gridCon = new PartnerGridContainer();
		gridCon.setHeight(200);
		addBt = new TextButton("Add");
		cancelBt = new TextButton("Cancel");
		setButtonAlign(BoxLayoutPack.END);
		addButton(addBt);
		addButton(cancelBt);

		setWidget(gridCon);

		cancelBt.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				hide();
			}
		});

		addBt.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				List<ProjectPartner> list = gridCon.getSelectionModel()
						.getSelectedItems();
				if (list.size() < 1) {
					AlertMessageBox d = new AlertMessageBox("添加",
							"请选择要添加的合作伙伴!");
					d.show();
					return;
				}
				getCallback().post(list);
				hide();
			}
		});
		rpc.get(this);
	}

	@Override
	public void postGet(List<ProjectPartner> list) {
		gridCon.getStore().addAll(list);

	}

	public PartnerSelectCallBack getCallback() {
		return callback;
	}

}
