package com.voole.gxt.client.app;

import com.voole.gxt.client.app.ui.MenuCenterView;

public abstract class MenuViewFinder {

	protected MenuViewFinder() {
	}

	public abstract MenuCenterView findMenuView(String token);
}
