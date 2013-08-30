package xdata.etl.testweb.server.rpc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoadResultBean;

import xdata.etl.testweb.client.MenuService;
import xdata.etl.testweb.server.dao.MenuDao;
import xdata.etl.testweb.shared.entity.menu.MenuNode;

@Service
public class MenuServiceImpl implements MenuService {
	@Autowired
	private MenuDao dao;

	public MenuServiceImpl() {
	}

	@Override
	public MenuNode insert(MenuNode node) {
		return dao.insert(node);
	}

	@Override
	public List<MenuNode> get() {
		return dao.get();
	}

	@Override
	public void delete(MenuNode node) {
		dao.delete(node);
	}

	@Override
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
