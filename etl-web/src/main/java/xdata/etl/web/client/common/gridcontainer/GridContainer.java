package xdata.etl.web.client.common.gridcontainer;

import java.util.ArrayList;
import java.util.List;

import xdata.etl.web.client.common.paging.EtlPagingLoader;
import xdata.etl.web.client.gwt.GwtCallBack;
import xdata.etl.web.client.gwt.GwtCallBack.DelayCallback;

import com.google.gwt.user.client.ui.IsWidget;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.ShowEvent;
import com.sencha.gxt.widget.core.client.event.ShowEvent.ShowHandler;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.toolbar.PagingToolBar;

public class GridContainer<M> extends VerticalLayoutContainer {
	protected Grid<M> grid;
	private final List<IsWidget> beforeGridWidgets;
	private final List<IsWidget> afterGridWidgets;
	private PagingToolBar pagingToolBar;
	private boolean refresh = false;
	private boolean isInited = false;
	private boolean isInitViewed = false;

	private DelayCallback<List<M>> initDataDelayCallback;

	public GridContainer(Grid<M> grid) {
		this.grid = grid;
		this.beforeGridWidgets = new ArrayList<IsWidget>();
		this.afterGridWidgets = new ArrayList<IsWidget>();

		this.addShowHandler(new ShowHandler() {
			@Override
			public void onShow(ShowEvent event) {
				if (!isInitViewed) {
					initView();
					isInitViewed = true;
				}
				if (!isInited || refresh) {
					initData();
					isInited = true;
				}

			}
		});
	}

	public void addToAfterGrid(IsWidget widget) {
		afterGridWidgets.add(widget);
	}

	public void addToBeforeGrid(IsWidget widget) {
		beforeGridWidgets.add(widget);
	}

	protected void initView() {
		if (this.pagingToolBar != null) {
			this.addToAfterGrid(pagingToolBar);
		}
		VerticalLayoutData vld = new VerticalLayoutData(1, -1);
		for (IsWidget widget : beforeGridWidgets) {
			this.add(widget, vld);
		}
		addGridToContainer();
		for (IsWidget widget : afterGridWidgets) {
			this.add(widget, vld);
		}
		this.forceLayout();
	}

	protected void initData() {
		getGrid().getStore().clear();
		if (getGrid().getLoader() != null) {
			getGrid().getLoader().load();
		} else if (initDataDelayCallback != null) {
			initDataDelayCallback.run(new GwtCallBack<List<M>>() {
				@Override
				protected void _call(List<M> t) {
					getGrid().getStore().addAll(t);
				}
			});
		}
	}

	protected void addGridToContainer() {
		this.add(grid, new VerticalLayoutData(1, 1));
	}

	public void setPagingLoader(EtlPagingLoader<M> loader, int pageItemCount) {
		getGrid().setLoader(loader);
		setPagingToolBar(new PagingToolBar(pageItemCount));
		getPagingToolBar().getElement().getStyle()
				.setProperty("borderBottom", "none");
		getPagingToolBar().bind(loader);
	}

	public void setInitDataDelayCallback(
			DelayCallback<List<M>> initDataDelayCallback) {
		this.initDataDelayCallback = initDataDelayCallback;
	}

	public Grid<M> getGrid() {
		return grid;
	}

	public List<IsWidget> getBeforeGridWidgets() {
		return beforeGridWidgets;
	}

	public List<IsWidget> getAfterGridWidgets() {
		return afterGridWidgets;
	}

	public PagingToolBar getPagingToolBar() {
		return pagingToolBar;
	}

	public void setPagingToolBar(PagingToolBar pagingToolBar) {
		this.pagingToolBar = pagingToolBar;
	}

	public void setRefresh(boolean refresh) {
		this.refresh = refresh;
	}

	public void setInited(boolean isInited) {
		this.isInited = isInited;
	}

}
