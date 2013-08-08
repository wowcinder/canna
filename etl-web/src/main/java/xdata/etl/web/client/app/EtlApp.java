package xdata.etl.web.client.app;

import xdata.etl.web.client.service.RpcAsyncCallback;
import xdata.etl.web.client.service.user.UserService;
import xdata.etl.web.client.service.user.UserServiceAsync;
import xdata.etl.web.shared.entity.user.User;

import com.google.gwt.core.shared.GWT;
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

		UserServiceAsync service = GWT.create(UserService.class);

		User user = new User();
		user.setEmail("kk@kk.com");
		user.setPassword("kkk");
		service.saveAndReturn(user, new RpcAsyncCallback<User>() {

			@Override
			public void _onSuccess(User t) {
				Info.display("ID", t.getId() + "");
			}
		});

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
