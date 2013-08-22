package xdata.etl.testweb.client;

import java.util.List;

import xdata.etl.testweb.shared.entity.menu.MenuNode;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("rpc/menu.rpc")
public interface MenuService extends RemoteService {
	public MenuNode insert(MenuNode node);

	public List<MenuNode> get();

	public void delete(MenuNode node);
}
