/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.ui.businessmeta.grid;

import xdata.etl.web.client.property.businessmeta.CTypeBusinessColumnProperty;
import xdata.etl.web.shared.entity.businessmeta.c.CTypeBusinessColumn;

/**
 * @author XuehuiHe
 * @date 2013年9月4日
 */
public class CTypeBusinessColumnGrid extends
		BusinessColumnGrid<CTypeBusinessColumn, CTypeBusinessColumnProperty> {

	/**
	 * @param props
	 */
	public CTypeBusinessColumnGrid() {
		super(CTypeBusinessColumnProperty.INSTANCE);
	}

}
