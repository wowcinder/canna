/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui.businessmeta;

import xdata.etl.web.client.ServiceUtil;
import xdata.etl.web.client.common.editer.RpcEntitySimpleEditor;
import xdata.etl.web.client.common.gridcontainer.SimpleRpcEntityGridContainer;
import xdata.etl.web.client.rpc.RpcCaller;
import xdata.etl.web.client.ui.CenterView;
import xdata.etl.web.client.ui.businessmeta.editor.BusinessAddEditor;
import xdata.etl.web.client.ui.businessmeta.editor.BusinessUpdateEditor;
import xdata.etl.web.client.ui.businessmeta.grid.BusinessGrid;
import xdata.etl.web.shared.annotations.MenuToken;
import xdata.etl.web.shared.entity.businessmeta.Business;

/**
 * @author XuehuiHe
 * @date 2013年9月3日
 */
@MenuToken(name = "业务模型", token = "business", group = "业务模型")
public class BusinessView extends
		SimpleRpcEntityGridContainer<Integer, Business> implements CenterView {

	private static final BusinessUpdateEditor updateEditor = new BusinessUpdateEditor();
	private static final BusinessAddEditor addEditor = new BusinessAddEditor();

	/**
	 * @param grid
	 */
	public BusinessView() {
		super(new BusinessGrid().create());
	}

	@Override
	protected RpcEntitySimpleEditor<Integer, Business> getAddEditor() {
		return addEditor;
	}

	@Override
	protected RpcEntitySimpleEditor<Integer, Business> getUpdateEditor() {
		return updateEditor;
	}

	@Override
	protected RpcCaller<Integer, Business> getRpcCaller() {
		return ServiceUtil.BusinessRpcCaller;
	}

}
