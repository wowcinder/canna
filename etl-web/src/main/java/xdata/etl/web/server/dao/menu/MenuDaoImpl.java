/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.server.dao.menu;

import org.springframework.stereotype.Repository;

import xdata.etl.web.server.dao.RpcDao;
import xdata.etl.web.shared.entity.menu.Menu;

/**
 * @author XuehuiHe
 * @date 2013年8月8日
 */
@Repository
public class MenuDaoImpl extends RpcDao<Integer, Menu> implements MenuDao {

}
