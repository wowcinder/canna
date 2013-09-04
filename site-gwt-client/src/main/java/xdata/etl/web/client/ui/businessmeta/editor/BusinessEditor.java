/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui.businessmeta.editor;

import xdata.etl.web.client.ServiceUtil;
import xdata.etl.web.client.common.editer.RpcEntitySimpleEditor;
import xdata.etl.web.shared.BusinessType;
import xdata.etl.web.shared.entity.businessmeta.Business;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.ui.TextArea;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;

/**
 * @author XuehuiHe
 * @date 2013年9月3日
 */
public class BusinessEditor extends RpcEntitySimpleEditor<Integer, Business> {
	private static final BusinessDriver driver = GWT
			.create(BusinessDriver.class);

	interface BusinessDriver extends
			SimpleBeanEditorDriver<Business, BusinessEditor> {
	}

	@Ignore
	private BusinessType type;

	TextField name;
	TextArea desc;

	public BusinessEditor() {
		super(driver, "业务模型", ServiceUtil.BusinessRpcCaller);
	}

	@Override
	protected void initView() {
		super.initView();
		name = new TextField();
		desc = new TextArea();
		layoutContainer.add(new FieldLabel(name, "name"), vd);
		layoutContainer.add(new FieldLabel(desc, "描述"), vd);
	}

	@Override
	protected Business newInstance() {
		Business b = type.createBusiness();
		type = null;
		return b;
	}

	public void setType(BusinessType type) {
		this.type = type;
	}

}
