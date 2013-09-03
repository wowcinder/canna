/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.shared.entity.businessmeta.c;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import xdata.etl.web.shared.entity.businessmeta.BusinessColumn;

/**
 * @author XuehuiHe
 * @date 2013年9月3日
 */
@Entity
@Table(name = "business_column_vertical_bar_split")
public class VerticalBarSplitBusinessColumn extends BusinessColumn {
	private static final long serialVersionUID = -8540826006059877751L;
	@Column(nullable = false)
	private int pos;

	public VerticalBarSplitBusinessColumn() {
	}

	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}

}
