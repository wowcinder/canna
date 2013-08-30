/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.rpc;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoadResultBean;

import xdata.etl.web.server.annotations.AccessAuthority;
import xdata.etl.web.server.annotations.AccessAuthorityGroup;
import xdata.etl.web.server.service.menu.MenuNodeService;
import xdata.etl.web.shared.entity.menu.MenuNode;
import xdata.etl.web.shared.service.menu.MenuNodeRpcService;

/**
 * @author XuehuiHe
 * @date 2013年8月8日
 */
@Service
@AccessAuthorityGroup("菜单")
public class MenuNodeRpcServiceImpl extends
		RpcServiceImpl<Integer, MenuNode, MenuNodeService> implements
		MenuNodeRpcService {

	public MenuNodeRpcServiceImpl() {
	}

	protected HttpSession getSession() {
		if (RequestContextHolder.currentRequestAttributes() != null) {
			ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder
					.currentRequestAttributes();
			return attr.getRequest().getSession();
		}
		return null;
	}

	@Override
	@AccessAuthority("修改")
	public List<MenuNode> update(Integer parentId, Integer prevId,
			List<MenuNode> nodes) {
		return getDelegateService().update(parentId, prevId, nodes);
	}

	@Override
	@AccessAuthority("查询")
	public PagingLoadResult<MenuNode> getMenuNodes(PagingLoadConfig loadConfig) {
		PagingLoadResultBean<MenuNode> pr = new PagingLoadResultBean<MenuNode>();
		pr.setOffset(loadConfig.getOffset());
		pr.setTotalLength(1000);
		List<MenuNode> list = new ArrayList<MenuNode>();
		int id = loadConfig.getOffset();
		for (int i = 0; i < loadConfig.getLimit(); i++, id++) {
			MenuNode m = new MenuNode();
			m.setName("name" + id);
			m.setId(i);
			list.add(m);
		}
		pr.setData(list);
		System.out.println(loadConfig.getLimit());
		System.out.println(list.size());
		return pr;
	}
}
