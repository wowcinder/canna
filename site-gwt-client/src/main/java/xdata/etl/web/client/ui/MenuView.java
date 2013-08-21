package xdata.etl.web.client.ui;

import com.google.gwt.user.client.ui.IsWidget;

public interface MenuView extends IsWidget {
	void init();
	void showMenu(String token);
}
