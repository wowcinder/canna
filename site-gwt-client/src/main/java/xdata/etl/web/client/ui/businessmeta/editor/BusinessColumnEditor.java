/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui.businessmeta.editor;

import xdata.etl.web.client.common.editer.EtlEditor;
import xdata.etl.web.client.common.editer.RpcEntitySimpleEditor;
import xdata.etl.web.client.rpc.RpcCaller;
import xdata.etl.web.shared.entity.businessmeta.BusinessColumn;
import xdata.etl.web.shared.entity.businessmeta.BusinessToHbaseTableMapping;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.ui.TextArea;
import com.sencha.gxt.widget.core.client.form.FieldLabel;

/**
 * @author XuehuiHe
 * @date 2013年9月4日
 */
public abstract class BusinessColumnEditor<T extends BusinessColumn> extends
		RpcEntitySimpleEditor<Integer, T> {

	private BusinessToHbaseTableMapping mapping;
	TextArea desc;

	public BusinessColumnEditor(
			SimpleBeanEditorDriver<T, ? extends EtlEditor<T>> driver,
			RpcCaller<Integer, T> rpcCaller) {
		super(driver, "业务Column", rpcCaller);
	}

	@Override
	protected void initView() {
		super.initView();
	}

	protected void afterInitView() {
		desc = new TextArea();
		layoutContainer.add(new FieldLabel(desc, "描述"), vd);
	}

	public BusinessToHbaseTableMapping getMapping() {
		return mapping;
	}

	public void setMapping(BusinessToHbaseTableMapping mapping) {
		this.mapping = mapping;
	}

}
