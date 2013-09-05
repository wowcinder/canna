/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui.businessmeta;

import xdata.etl.web.client.ServiceUtil;
import xdata.etl.web.client.common.combox.EnumComboBox;
import xdata.etl.web.client.common.editer.EditorWindow;
import xdata.etl.web.client.common.editer.RpcEntitySimpleEditor;
import xdata.etl.web.client.common.gridcontainer.RpcEntityGridContainerBuilder;
import xdata.etl.web.client.common.gridcontainer.SimpleRpcEntityGridContainer;
import xdata.etl.web.client.gwt.GwtCallBack;
import xdata.etl.web.client.rpc.RpcCaller;
import xdata.etl.web.client.ui.CenterView;
import xdata.etl.web.client.ui.businessmeta.editor.BusinessEditor;
import xdata.etl.web.client.ui.businessmeta.grid.BusinessGrid;
import xdata.etl.web.shared.BusinessType;
import xdata.etl.web.shared.BusinessType.BusinessColumnType;
import xdata.etl.web.shared.annotations.MenuToken;
import xdata.etl.web.shared.entity.businessmeta.Business;

import com.sencha.gxt.core.client.util.Padding;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutPack;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.FormPanel;

/**
 * @author XuehuiHe
 * @date 2013年9月3日
 */
@MenuToken(name = "业务模型", token = "business", group = "业务模型")
public class BusinessView extends
		SimpleRpcEntityGridContainer<Integer, Business> implements CenterView {

	private static final BusinessEditor editor = new BusinessEditor();

	@Override
	protected RpcEntityGridContainerBuilder<Integer, Business> createBuilder() {
		final RpcEntityGridContainerBuilder<Integer, Business> builder = super
				.createBuilder();
		builder.setAddBtHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				BusinessTypeSelectWindow selectWindow = new BusinessTypeSelectWindow();
				selectWindow.setCallBack(new GwtCallBack<BusinessType>() {
					@Override
					protected void _call(BusinessType t) {
						BusinessEditor editor = (BusinessEditor) builder
								.getAddEditor();
						editor.setType(t);
						editor.add();
					}
				});
				selectWindow.show();
			}
		});
		return builder;
	}

	/**
	 * @param grid
	 */
	public BusinessView() {
		super(new BusinessGrid().create());
	}

	@Override
	protected RpcEntitySimpleEditor<Integer, Business> getAddEditor() {
		return editor;
	}

	@Override
	protected RpcEntitySimpleEditor<Integer, Business> getUpdateEditor() {
		return editor;
	}

	@Override
	protected RpcCaller<Integer, Business> getRpcCaller() {
		return ServiceUtil.BusinessRpcCaller;
	}

	public static class BusinessTypeSelectWindow extends EditorWindow {
		private VerticalLayoutContainer layoutContainer;
		private ComboBox<BusinessType> types;
		private GwtCallBack<BusinessType> callBack;

		public BusinessTypeSelectWindow() {
			setHeadingText("选择类型:");
			TextButton saveOrUpdateBt = new TextButton("确定");
			setButtonAlign(BoxLayoutPack.END);
			setModal(true);
			addButton(saveOrUpdateBt);

			saveOrUpdateBt.addSelectHandler(new SelectHandler() {
				@Override
				public void onSelect(SelectEvent event) {
					if (types.getValue() == null) {
						return;
					}
					callBack.call(types.getValue());
					hide();
				}
			});
			
			FormPanel formPanel = new FormPanel();
			formPanel.getElement().setPadding(new Padding(10));
			formPanel.setBorders(true);

			layoutContainer = new VerticalLayoutContainer();

			types = new EnumComboBox<BusinessType>(BusinessType.values());
			layoutContainer.add(new FieldLabel(types, "类型"));
			formPanel.setWidget(layoutContainer);
			setWidget(formPanel);
		}

		public ComboBox<BusinessType> getTypes() {
			return types;
		}

		public void setTypes(ComboBox<BusinessType> types) {
			this.types = types;
		}

		public GwtCallBack<BusinessType> getCallBack() {
			return callBack;
		}

		public void setCallBack(GwtCallBack<BusinessType> callBack) {
			this.callBack = callBack;
		}

	}
	
	public static class BusinessColumnTypeSelectWindow extends EditorWindow {
		private VerticalLayoutContainer layoutContainer;
		private ComboBox<BusinessColumnType> types;
		private GwtCallBack<BusinessColumnType> callBack;

		public BusinessColumnTypeSelectWindow() {
			setHeadingText("选择类型:");
			TextButton saveOrUpdateBt = new TextButton("确定");
			setButtonAlign(BoxLayoutPack.END);
			setModal(true);
			addButton(saveOrUpdateBt);

			saveOrUpdateBt.addSelectHandler(new SelectHandler() {
				@Override
				public void onSelect(SelectEvent event) {
					if (types.getValue() == null) {
						return;
					}
					callBack.call(types.getValue());
					hide();
				}
			});
			
			FormPanel formPanel = new FormPanel();
			formPanel.getElement().setPadding(new Padding(10));
			formPanel.setBorders(true);

			layoutContainer = new VerticalLayoutContainer();

			types = new EnumComboBox<BusinessColumnType>(BusinessColumnType.values());
			layoutContainer.add(new FieldLabel(types, "类型"));
			formPanel.setWidget(layoutContainer);
			setWidget(formPanel);
		}

		public ComboBox<BusinessColumnType> getTypes() {
			return types;
		}

		public void setTypes(ComboBox<BusinessColumnType> types) {
			this.types = types;
		}

		public GwtCallBack<BusinessColumnType> getCallBack() {
			return callBack;
		}

		public void setCallBack(GwtCallBack<BusinessColumnType> callBack) {
			this.callBack = callBack;
		}

	}
}
