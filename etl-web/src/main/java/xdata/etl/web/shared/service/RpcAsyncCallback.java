package xdata.etl.web.shared.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class RpcAsyncCallback<T> implements AsyncCallback<T> {

	public RpcAsyncCallback() {
		beforeSend();
	}

	public void beforeSend() {

	}

	public void beforeOnArrive() {

	}

	public void afterOnArrive() {

	}

	public void beforeOnFailure(Throwable caught) {

	}

	public void afterOnFailure(Throwable caught) {

	}

	public void beforeOnSuccess(T t) {

	}

	public void afterOnSuccess(T t) {

	}

	@Override
	public void onFailure(Throwable caught) {
		beforeOnArrive();
		beforeOnFailure(caught);
		_onFailure(caught);
		afterOnFailure(caught);
		afterOnArrive();
	}

	@Override
	public void onSuccess(T t) {
		beforeOnArrive();
		beforeOnSuccess(t);
		_onSuccess(t);
		afterOnSuccess(t);
		afterOnArrive();
	}

	public void _onFailure(Throwable caught) {
	}

	public abstract void _onSuccess(T t);

}
