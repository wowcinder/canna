package xdata.etl.testweb.client;

import java.util.List;

import xdata.etl.testweb.shared.entity.menu.MenuNode;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;

public interface MenuServiceAsync {

	void delete(MenuNode node, AsyncCallback<Void> callback);

	void get(AsyncCallback<List<MenuNode>> callback);

	void insert(MenuNode node, AsyncCallback<MenuNode> callback);

	void getMenuNodes(PagingLoadConfig loadConfig,
			AsyncCallback<PagingLoadResult<MenuNode>> callback);

}
