/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.shared.entity.businessmeta.c;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

import xdata.etl.web.shared.entity.businessmeta.BusinessVersion;

/**
 * @author XuehuiHe
 * @date 2013年9月3日
 */
@Entity
@Table(name = "business_version_vertical_bar_split")
public class VerticalBarSplitBusinessVersion extends BusinessVersion {
	private static final long serialVersionUID = -8334018254059047467L;

	public VerticalBarSplitBusiness getBusiness() {
		return (VerticalBarSplitBusiness) business;
	}

	@SuppressWarnings("unchecked")
	public List<VerticalBarSplitBusinessColumn> getColumns() {
		return (List<VerticalBarSplitBusinessColumn>) super.getColumns();
	}
}
