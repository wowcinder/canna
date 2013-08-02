package com.voole.gxt.client.login;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.core.client.util.Padding;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.box.AlertMessageBox;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutPack;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.FormPanel;
import com.sencha.gxt.widget.core.client.form.PasswordField;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.voole.gxt.client.service.ClientServiceFactory;
import com.voole.gxt.client.service.login.LoginService;
import com.voole.gxt.client.service.login.LoginServiceAsync;

public class LoginWindow extends Window {

	public interface LoginSucessCallBack {
		void logined();
	}

	private static final LoginServiceAsync loginService = ClientServiceFactory
			.getService(LoginService.class);
	protected static final VerticalLayoutData vd = new VerticalLayoutData(1, -1);
	private FormPanel fp = new FormPanel();
	private VerticalLayoutContainer vc = new VerticalLayoutContainer();
	private TextField email = new TextField();
	private PasswordField password = new PasswordField();
	private TextButton loginBt = new TextButton("Login");

	private LoginSucessCallBack callback;

	public LoginWindow() {
		setModal(true);
		setHeadingText("登录");

		fp.getElement().setPadding(new Padding(10));
		fp.setBorders(true);

		fp.setWidget(vc);

		forceLayout();
		setWidget(fp);

		setButtonAlign(BoxLayoutPack.CENTER);
		addButton(loginBt);

		loginBt.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				login();
			}
		});
		init();
	}

	private void init() {
		vc.add(new FieldLabel(email, "邮箱"), vd);
		vc.add(new FieldLabel(password, "密码"), vd);
	}

	private void login() {
		String emailV = email.getCurrentValue();
		String passwordV = password.getCurrentValue();

		loginService.login(emailV, passwordV, new AsyncCallback<Boolean>() {
			@Override
			public void onSuccess(Boolean result) {
				if (result) {
					if (getCallback() != null) {
						getCallback().logined();
					}
					hide();
				} else {
					AlertMessageBox d = new AlertMessageBox("失败", "邮箱或密码错误!");
					d.show();
				}
			}

			@Override
			public void onFailure(Throwable caught) {

			}
		});
	}

	public LoginSucessCallBack getCallback() {
		return callback;
	}

	public void setCallback(LoginSucessCallBack callback) {
		this.callback = callback;
	}

}
