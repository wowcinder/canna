package xdata.etl.testweb.client;

import java.util.List;

import xdata.etl.testweb.shared.entity.menu.MenuNode;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;

@RemoteServiceRelativePath("rpc/menu.rpc")
public interface MenuService extends RemoteService {
	public MenuNode insert(MenuNode node);

	public List<MenuNode> get();

	public void delete(MenuNode node);

	PagingLoadResult<MenuNode> getMenuNodes(PagingLoadConfig loadConfig);
}
