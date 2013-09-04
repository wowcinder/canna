/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui.businessmeta.editor.json;

import xdata.etl.web.client.rpc.RpcCaller;
import xdata.etl.web.client.ui.businessmeta.combox.BusinessToHbaseTableMappingComBox;
import xdata.etl.web.client.ui.businessmeta.editor.BusinessColumnEditor;
import xdata.etl.web.shared.BusinessType;
import xdata.etl.web.shared.entity.businessmeta.json.SubMappingJsonBusinessColumn;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;

/**
 * @author XuehuiHe
 * @date 2013年9月4日
 */
public class SubMappingJsonBusinessColumnEditor extends
		BusinessColumnEditor<SubMappingJsonBusinessColumn> {

	private static final SubMappingJsonBusinessColumnDriver driver = GWT
			.create(SubMappingJsonBusinessColumnDriver.class);

	interface SubMappingJsonBusinessColumnDriver
			extends
			SimpleBeanEditorDriver<SubMappingJsonBusinessColumn, SubMappingJsonBusinessColumnEditor> {
	}

	BusinessToHbaseTableMappingComBox mappingCombox;
	TextField path;

	public SubMappingJsonBusinessColumnEditor(
			RpcCaller<Integer, SubMappingJsonBusinessColumn> rpcCaller) {
		super(driver, rpcCaller);
	}

	@Override
	protected void initView() {
		super.initView();
		mappingCombox = new BusinessToHbaseTableMappingComBox();
		path = new TextField();

		layoutContainer.add(new FieldLabel(path, "path"), vd);
		layoutContainer.add(new FieldLabel(mappingCombox, "mapping"), vd);
		afterInitView();
	}

	public void setType(BusinessType type) {
		mappingCombox.setType(type);
	}

	@Override
	protected SubMappingJsonBusinessColumn newInstance() {
		SubMappingJsonBusinessColumn column = new SubMappingJsonBusinessColumn();
		column.setMapping(getMapping());
		return column;
	}

}
