/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.shared.entity.businessmeta.c;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import xdata.etl.web.shared.entity.businessmeta.BusinessToHbaseTableMapping;

/**
 * @author XuehuiHe
 * @date 2013年9月3日
 */
@Entity
@DiscriminatorValue("vertical_bar_split")
public class VerticalBarSplitBusinessToHbaseTableMapping extends
		BusinessToHbaseTableMapping {

	private static final long serialVersionUID = 3254709662221405450L;

	@SuppressWarnings("unchecked")
	public List<VerticalBarSplitBusinessColumn> getColumns() {
		return (List<VerticalBarSplitBusinessColumn>) super.getColumns();
	}
}
