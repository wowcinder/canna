package com.voole.gxt.client.canna.gridcontainer.paging;

import com.sencha.gxt.data.shared.loader.PagingLoadConfigBean;

public class CannaPagingLoadConfigBean extends PagingLoadConfigBean {
	private static final long serialVersionUID = 8925370681075443720L;

	public CannaPagingLoadConfigBean() {
		this(0, 50);
	}

	public CannaPagingLoadConfigBean(int offset, int limit) {
		super(offset, limit);
	}

	private PagingCondition condition;

	public void setCondition(PagingCondition condition) {
		this.condition = condition;
	}

	public PagingCondition getCondition() {
		return condition;
	}
}
