package xdata.etl.web.client.gwt;

public abstract class GwtCallBack<T> {
	protected abstract void _call(T t);

	public void call(T t) {
		_call(t);
		clean();
	}

	public void clean() {

	}
}
