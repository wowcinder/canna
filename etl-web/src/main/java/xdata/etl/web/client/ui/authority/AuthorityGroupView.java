/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui.authority;

import xdata.etl.web.client.ServiceUtil;
import xdata.etl.web.client.annotations.MenuToken;
import xdata.etl.web.client.common.editer.RpcEntitySimpleEditor;
import xdata.etl.web.client.common.gridcontainer.SimpleRpcEntityGridContainer;
import xdata.etl.web.client.rpc.EntityRpcCaller;
import xdata.etl.web.client.ui.CenterView;
import xdata.etl.web.client.ui.authority.editor.AuthorityGroupEditor;
import xdata.etl.web.client.ui.authority.grid.AuthorityGroupGrid;
import xdata.etl.web.shared.entity.authority.AuthorityGroup;

/**
 * @author XuehuiHe
 * @date 2013年8月12日
 * 
 */
@MenuToken(name = "权限组管理", token = "authority_group_manager")
public class AuthorityGroupView extends
		SimpleRpcEntityGridContainer<Integer, AuthorityGroup> implements
		CenterView {
	private static final AuthorityGroupEditor editor = new AuthorityGroupEditor();

	public AuthorityGroupView() {
		super((new AuthorityGroupGrid()).create());
	}

	@Override
	protected RpcEntitySimpleEditor<Integer, AuthorityGroup> getAddEditor() {
		return editor;
	}

	@Override
	protected RpcEntitySimpleEditor<Integer, AuthorityGroup> getUpdateEditor() {
		return editor;
	}

	@Override
	protected EntityRpcCaller<Integer, AuthorityGroup> getRpcCaller() {
		return ServiceUtil.AuthorityGroupRpcCaller;
	}

}
