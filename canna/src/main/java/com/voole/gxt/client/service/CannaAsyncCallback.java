package com.voole.gxt.client.service;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.widget.core.client.box.AlertMessageBox;
import com.sencha.gxt.widget.core.client.info.Info;
import com.voole.gxt.client.canna.exception.NoLoginException;
import com.voole.gxt.client.canna.exception.PermissionException;
import com.voole.gxt.client.canna.exception.JDBCException;
import com.voole.gxt.client.login.LoginWindow;

public abstract class CannaAsyncCallback<T> implements AsyncCallback<T> {
	@Override
	public void onFailure(Throwable caught) {
		if (caught instanceof PermissionException) {
			AlertMessageBox d = new AlertMessageBox("操作失败", "权限不足");
			d.show();
		} else if (caught instanceof ConstraintViolationException) {
			Set<ConstraintViolation<?>> sets = ((ConstraintViolationException) caught)
					.getConstraintViolations();
			String sb = "";
			for (ConstraintViolation<?> cv : sets) {
				if (!"".equals(sb)) {
					sb += "<br />";
				}
				sb += cv.getPropertyPath() + " " + cv.getMessage();
			}
			Info.display("操作失败", sb);
		} else if (caught instanceof JDBCException) {
			Info.display("操作失败", caught.getMessage());
		} else if (caught instanceof NoLoginException) {
			LoginWindow w = new LoginWindow();
			w.show();
		} else {
			Info.display("操作失败", "未知异常!!");
		}
	}
}
