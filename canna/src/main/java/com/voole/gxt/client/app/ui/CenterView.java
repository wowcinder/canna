package com.voole.gxt.client.app.ui;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.IsWidget;
import com.sencha.gxt.widget.core.client.container.SimpleContainer;
import com.sencha.gxt.widget.core.client.container.Viewport;

public abstract class CenterView extends Composite implements IsWidget {
	private String label = "OVERVIEW";
	private boolean closeAble = true;
	private String token;

	protected SimpleContainer con;

	public CenterView() {
		createContainer();
		initWidget(con);
	}

	public void createContainer() {
		con = new Viewport();
	}
	public void setLabel(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public boolean isCloseAble() {
		return closeAble;
	}

	public void setCloseAble(boolean closeAble) {
		this.closeAble = closeAble;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
