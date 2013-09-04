/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui.businessmeta.editor.c;

import xdata.etl.web.client.rpc.RpcCaller;
import xdata.etl.web.client.ui.businessmeta.combox.BusinessToHbaseTableMappingComBox;
import xdata.etl.web.client.ui.businessmeta.editor.BusinessColumnEditor;
import xdata.etl.web.shared.BusinessType;
import xdata.etl.web.shared.entity.businessmeta.c.SubMappingCTypeBusinessColumn;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.widget.core.client.form.FieldLabel;

/**
 * @author XuehuiHe
 * @date 2013年9月4日
 */
public class SubMappingCTypeBusinessColumnEditor extends
		BusinessColumnEditor<SubMappingCTypeBusinessColumn> {
	private static final SubMappingCTypeBusinessColumnDriver driver = GWT
			.create(SubMappingCTypeBusinessColumnDriver.class);

	interface SubMappingCTypeBusinessColumnDriver
			extends
			SimpleBeanEditorDriver<SubMappingCTypeBusinessColumn, SubMappingCTypeBusinessColumnEditor> {
	}

	BusinessToHbaseTableMappingComBox mappingCombox;

	public SubMappingCTypeBusinessColumnEditor(
			RpcCaller<Integer, SubMappingCTypeBusinessColumn> rpcCaller,
			BusinessType type) {
		super(driver, rpcCaller);
	}

	@Override
	protected void initView() {
		super.initView();
		mappingCombox = new BusinessToHbaseTableMappingComBox();

		layoutContainer.add(new FieldLabel(mappingCombox, "mapping"), vd);
		afterInitView();
	}

	@Override
	protected SubMappingCTypeBusinessColumn newInstance() {
		SubMappingCTypeBusinessColumn column = new SubMappingCTypeBusinessColumn();
		column.setMapping(getMapping());
		return column;
	}

	public void setType(BusinessType type) {
		mappingCombox.setType(type);
	}

}
