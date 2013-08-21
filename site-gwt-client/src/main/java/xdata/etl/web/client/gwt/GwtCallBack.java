package xdata.etl.web.client.gwt;

public abstract class GwtCallBack<T> {

	public interface DelayCallback<T> {
		void run(GwtCallBack<T> callback);
	}

	protected abstract void _call(T t);

	public void call(T t) {
		_call(t);
		clean();
	}

	public void clean() {

	}
}
