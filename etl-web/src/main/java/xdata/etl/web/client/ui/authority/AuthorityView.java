/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui.authority;

import xdata.etl.web.client.annotations.MenuToken;
import xdata.etl.web.client.common.editer.RpcEntitySimpleEditor;
import xdata.etl.web.client.common.gridcontainer.SimpleRpcEntityGridContainer;
import xdata.etl.web.client.rpc.EntityRpcCaller;
import xdata.etl.web.client.service.ServiceUtil;
import xdata.etl.web.client.ui.CenterView;
import xdata.etl.web.client.ui.authority.editor.AuthorityEditor;
import xdata.etl.web.client.ui.authority.grid.AuthorityGrid;
import xdata.etl.web.shared.entity.authority.Authority;

/**
 * @author XuehuiHe
 * @date 2013年8月12日
 * 
 */
@MenuToken(name = "权限管理", token = "authority_manager")
public class AuthorityView extends
		SimpleRpcEntityGridContainer<Integer, Authority> implements CenterView {

	private static final AuthorityEditor editor = new AuthorityEditor();

	public AuthorityView() {
		super(new AuthorityGrid().create());
	}

	@Override
	protected RpcEntitySimpleEditor<Integer, Authority> getAddEditor() {
		return editor;
	}

	@Override
	protected RpcEntitySimpleEditor<Integer, Authority> getUpdateEditor() {
		return editor;
	}

	@Override
	protected EntityRpcCaller<Integer, Authority> getRpcCaller() {
		return ServiceUtil.AuthorityRpcCaller;
	}

}
