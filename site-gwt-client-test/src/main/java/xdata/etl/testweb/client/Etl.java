package xdata.etl.testweb.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;

public class Etl implements EntryPoint {

	public void onModuleLoad() {
		TextButton tb = new TextButton("测试");
		RootPanel.get().add(tb);
	}}
