/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui.businessmeta.editor;

import xdata.etl.web.client.common.combox.EnumComboBox;
import xdata.etl.web.client.gwt.GwtCallBack;
import xdata.etl.web.shared.BusinessType;
import xdata.etl.web.shared.entity.businessmeta.Business;
import xdata.etl.web.shared.entity.businessmeta.c.VerticalBarSplitBusiness;
import xdata.etl.web.shared.entity.businessmeta.json.JsonBusiness;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.info.Info;

/**
 * @author XuehuiHe
 * @date 2013年9月3日
 */
public class BusinessAddEditor extends BusinessUpdateEditor {

	private static final BusinessDriver driver = GWT
			.create(BusinessDriver.class);

	interface BusinessDriver extends
			SimpleBeanEditorDriver<Business, BusinessAddEditor> {
	}

	@Ignore
	ComboBox<BusinessType> types;

	/**
	 * 
	 */
	public BusinessAddEditor() {
		super(driver);
	}

	@Override
	protected void initView() {
		super.initView();
		types = new EnumComboBox<BusinessType>(BusinessType.values());
		layoutContainer.add(new FieldLabel(types, "type"), vd);
	}

	@Override
	protected void save(Business v, GwtCallBack<Business> callback) {
		BusinessType type = types.getValue();
		if (type == null) {
			Info.display("出错", "请选择模型的类型!");
			callback.clean();
			return;
		}
		if (type.equals(BusinessType.C_TYPE)) {
			VerticalBarSplitBusiness newV = new VerticalBarSplitBusiness();
			newV.setName(v.getName());
			super.save(newV, callback);
		} else {
			JsonBusiness newV = new JsonBusiness();
			newV.setName(v.getName());
			super.save(newV, callback);
		}
	}

}
