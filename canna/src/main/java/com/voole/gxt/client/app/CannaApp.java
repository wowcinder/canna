package com.voole.gxt.client.app;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.voole.gxt.client.app.place.CenterPlace;
import com.voole.gxt.client.app.ui.CenterContainerView;
import com.voole.gxt.client.app.ui.MenuListView;
import com.voole.gxt.client.login.LoginWindow;
import com.voole.gxt.client.login.LoginWindow.LoginSucessCallBack;
import com.voole.gxt.client.service.ClientServiceFactory;
import com.voole.gxt.client.service.login.LoginService;
import com.voole.gxt.client.service.login.LoginServiceAsync;

public class CannaApp implements Presenter {
	private static final LoginServiceAsync loginService = ClientServiceFactory
			.getService(LoginService.class);

	public static final String OVERVIEW = "Overview";

	private static final Logger log = Logger
			.getLogger(CannaApp.class.getName());
	@Inject
	private EventBus eventBus;

	@Inject
	private PlaceController placeController;

	@Inject
	private ActivityMapper activityMapper;

	@Inject
	private CannaShell shell;

	@Inject
	private MenuListView listView;

	@Inject
	private CenterContainerView centerContainer;

	@Override
	public void showCenter(CenterPlace p) {
		placeController.goTo(p);
	}

	public void run() {
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

		ActivityManager activityManager = new ActivityManager(activityMapper,
				eventBus);
		activityManager.setDisplay(shell);

		listView.setPresenter(this);

		RootPanel.get().add(shell);
		refresh();
	}

	private void refresh() {
		listView.refresh();
	}
}
