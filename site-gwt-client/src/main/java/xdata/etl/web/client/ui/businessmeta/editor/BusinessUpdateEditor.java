/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui.businessmeta.editor;

import xdata.etl.web.client.ServiceUtil;
import xdata.etl.web.client.common.editer.EtlEditor;
import xdata.etl.web.client.common.editer.RpcEntitySimpleEditor;
import xdata.etl.web.shared.entity.businessmeta.Business;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;

/**
 * @author XuehuiHe
 * @date 2013年9月3日
 */
public class BusinessUpdateEditor extends
		RpcEntitySimpleEditor<Integer, Business> {
	private static final BusinessDriver driver = GWT
			.create(BusinessDriver.class);

	interface BusinessDriver extends
			SimpleBeanEditorDriver<Business, BusinessUpdateEditor> {
	}

	TextField name;

	public BusinessUpdateEditor() {
		super(driver, "业务模型", ServiceUtil.BusinessRpcCaller);
	}

	public BusinessUpdateEditor(
			SimpleBeanEditorDriver<Business, ? extends EtlEditor<Business>> driver) {
		super(driver, "业务模型", ServiceUtil.BusinessRpcCaller);
	}

	@Override
	protected void initView() {
		super.initView();
		name = new TextField();
		layoutContainer.add(new FieldLabel(name, "name"), vd);
	}

	@Override
	protected Business newInstance() {
		return new Business();
	}

}
