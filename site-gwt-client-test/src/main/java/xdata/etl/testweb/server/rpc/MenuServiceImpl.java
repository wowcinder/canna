package xdata.etl.testweb.server.rpc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
