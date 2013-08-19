package xdata.etl.web.client.common.gridcontainer;

import com.google.gwt.user.client.ui.IsWidget;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

public class RpcEntityGridContainer<V> extends GridContainer<V> {
	private ToolBar headerBar;

	public RpcEntityGridContainer(Grid<V> grid) {
		super(grid);
	}

	@Override
	protected void initView() {
		if (this.headerBar != null) {
			this.addToBeforeGrid(headerBar);
		}
		super.initView();
	}

	public void addToHeaderBar(IsWidget widget) {
		if (headerBar == null) {
			headerBar = new ToolBar();
		}
		headerBar.add(widget);
	}

	public ToolBar getHeaderBar() {
		return headerBar;
	}

	public void setHeaderBar(ToolBar headerBar) {
		this.headerBar = headerBar;
	}

}
