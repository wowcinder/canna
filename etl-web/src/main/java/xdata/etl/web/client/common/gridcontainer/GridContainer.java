package xdata.etl.web.client.common.gridcontainer;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.ui.IsWidget;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.ShowEvent;
import com.sencha.gxt.widget.core.client.event.ShowEvent.ShowHandler;
import com.sencha.gxt.widget.core.client.grid.Grid;

public class GridContainer<M> extends VerticalLayoutContainer {
	private final Grid<M> grid;
	private final List<IsWidget> beforeGridWidgets;
	private final List<IsWidget> afterGridWidgets;

	public GridContainer(Grid<M> grid) {
		this.grid = grid;
		this.beforeGridWidgets = new ArrayList<IsWidget>();
		this.afterGridWidgets = new ArrayList<IsWidget>();

		this.addShowHandler(new ShowHandler() {
			@Override
			public void onShow(ShowEvent event) {
				initView();
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

	protected void addGridToContainer() {
		this.add(grid, new VerticalLayoutData(1, 1));
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

}
