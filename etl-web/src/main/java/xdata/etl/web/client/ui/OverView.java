package xdata.etl.web.client.ui;

import com.google.gwt.widget.client.TextButton;

public class OverView extends CenterView {

	public OverView() {
		setCloseAble(false);
		setToken("overview");
		setLabel("这是预览!");
		con.add(new TextButton("预览!"));
	}
	@Override
	protected void doAddAttachHandler() {
	}
}
