package com.voole.gxt.client.app.ui;

import com.google.gwt.widget.client.TextButton;

public class OverView extends CenterView {

	public OverView() {
		setCloseAble(false);
		setToken("overview");
		con.add(new TextButton("这是预览!"));
	}
}
