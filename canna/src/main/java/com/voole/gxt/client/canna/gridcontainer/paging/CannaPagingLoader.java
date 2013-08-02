package com.voole.gxt.client.canna.gridcontainer.paging;

import com.sencha.gxt.data.shared.loader.DataProxy;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoader;

public class CannaPagingLoader<T> extends
		PagingLoader<CannaPagingLoadConfigBean, PagingLoadResult<T>> {
	private PagingCondition condition;

	public void setCondition(PagingCondition condition) {
		this.condition = condition;
	}

	public PagingCondition getCondition() {
		return condition;
	}

	public CannaPagingLoader(
			DataProxy<CannaPagingLoadConfigBean, PagingLoadResult<T>> proxy) {
		super(proxy);
	}

	public boolean load(CannaPagingLoadConfigBean loadConfig) {
		loadConfig.setCondition(getCondition());
		return super.load(loadConfig);
	}

	protected CannaPagingLoadConfigBean newLoadConfig() {
		return new CannaPagingLoadConfigBean();
	}
}
