package xdata.etl.web.client.common.gridcontainer;

import com.google.gwt.widget.client.TextButton;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

public class GridContainerHeaderBar extends ToolBar {
	private TextButton addBt;
	private TextButton deleteBt;

	public GridContainerHeaderBar() {
		this(true, true);
	}

	public GridContainerHeaderBar(boolean isShowAddBt, boolean isShowDeleteBt) {
		if (isShowAddBt) {
			addBt = new TextButton("添加");
			add(addBt);
		}
		if (isShowDeleteBt) {
			deleteBt = new TextButton("删除");
			add(deleteBt);
		}
	}

	public TextButton getAddBt() {
		return addBt;
	}

	public void setAddBt(TextButton addBt) {
		this.addBt = addBt;
	}

	public TextButton getDeleteBt() {
		return deleteBt;
	}

	public void setDeleteBt(TextButton deleteBt) {
		this.deleteBt = deleteBt;
	}

}
