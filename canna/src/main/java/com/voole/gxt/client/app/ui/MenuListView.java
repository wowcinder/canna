package com.voole.gxt.client.app.ui;

import com.google.gwt.user.client.ui.IsWidget;
import com.voole.gxt.client.app.Presenter;

public interface MenuListView extends IsWidget {
	void setPresenter(Presenter presenter);
	void refresh();
}
