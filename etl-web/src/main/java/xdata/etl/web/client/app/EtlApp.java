package xdata.etl.web.client.app;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.sencha.gxt.widget.core.client.info.Info;

public class EtlApp {
	@Inject
	private EventBus eventBus;

	public void run() {
		eventBus.addHandler(TestEvent.TYPE, new TestEvent.Handler() {
			@Override
			public void showMsg(String msg) {
				Info.display("test event", msg);
			}
		});
		eventBus.fireEvent(new TestEvent("kkkkkkkkkkkkkk"));

	}

	public static class TestEvent extends GwtEvent<TestEvent.Handler> {
		public static final Type<Handler> TYPE = new Type<Handler>();

		public interface Handler extends EventHandler {
			void showMsg(String msg);
		}

		private String msg;

		public TestEvent(String msg) {
			this.msg = msg;
		}

		@Override
		public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
			return TYPE;
		}

		@Override
		protected void dispatch(Handler handler) {
			handler.showMsg(msg);
		}
	}

}
