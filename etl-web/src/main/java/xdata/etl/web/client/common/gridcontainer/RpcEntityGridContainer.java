package xdata.etl.web.client.common.gridcontainer;

import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.toolbar.PagingToolBar;

public class RpcEntityGridContainer<V> extends GridContainer<V> {
	private GridContainerHeaderBar headerBar;
	private PagingToolBar pagingToolBar;

	public RpcEntityGridContainer(Grid<V> grid) {
		super(grid);
		this.headerBar = new GridContainerHeaderBar();
	}

	@Override
	protected void initView() {
		if (this.headerBar != null) {
			this.addToBeforeGrid(headerBar);
		}
		if (this.pagingToolBar != null) {
			this.addToAfterGrid(pagingToolBar);
		}
		super.initView();
	}

	public GridContainerHeaderBar getHeaderBar() {
		return headerBar;
	}

	public void setHeaderBar(GridContainerHeaderBar headerBar) {
		this.headerBar = headerBar;
	}

	public PagingToolBar getPagingToolBar() {
		return pagingToolBar;
	}

	public void setPagingToolBar(PagingToolBar pagingToolBar) {
		this.pagingToolBar = pagingToolBar;
	}

}
