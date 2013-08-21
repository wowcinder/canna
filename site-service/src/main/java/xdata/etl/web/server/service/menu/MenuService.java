/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.service.menu;

import java.util.List;
import xdata.etl.web.server.service.RpcDelegateService;
import xdata.etl.web.shared.entity.menu.Menu;

/**
 * @author XuehuiHe
 * @date 2013年8月21日
 */
public interface MenuService extends RpcDelegateService<Integer, Menu> {
	public List<Menu> getUserMenu(Integer userId);
}
