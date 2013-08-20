/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.app;

import java.util.logging.Level;
import java.util.logging.Logger;

import xdata.etl.web.client.ui.LoginWindow;
import xdata.etl.web.client.ui.LoginWindow.LoginSucessCallBack;
import xdata.etl.web.shared.service.login.LoginService;
import xdata.etl.web.shared.service.login.LoginServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.inject.Inject;

/**
 * @author XuehuiHe
 * @date 2013年8月13日
 */
public class EtlApp {

	private static final LoginServiceAsync loginService = GWT
			.create(LoginService.class);
	private static final Logger log = Logger.getLogger(EtlApp.class.getName());
	@Inject
	private EtlShell shell;

	public EtlApp() {
	}

	public void run() {
		// TestGrid test = new TestGrid();
		// RootPanel.get().add(test);
		// if (true) {
		// return;
		// }
		loginService.isLogin(new AsyncCallback<Boolean>() {
			@Override
			public void onSuccess(Boolean result) {
				if (result) {
					init();
				} else {
					LoginWindow w = new LoginWindow();
					w.setCallback(new LoginSucessCallBack() {
						@Override
						public void logined() {
							init();
						}
					});
					w.show();
				}
			}

			@Override
			public void onFailure(Throwable caught) {

			}
		});

	}

	private void init() {
		GWT.setUncaughtExceptionHandler(new GWT.UncaughtExceptionHandler() {
			public void onUncaughtException(Throwable e) {
				Window.alert("Error: " + e.getMessage());
				log.log(Level.SEVERE, e.getMessage(), e);
				e.printStackTrace();
			}
		});
		shell.init();
		RootPanel.get().add(shell);
	}
}
