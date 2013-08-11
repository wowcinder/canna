package xdata.etl.web.client.ui;

import xdata.etl.web.client.event.CenterVievChangeEvent;

import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.event.logical.shared.AttachEvent.Handler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.web.bindery.event.shared.EventBus;
import com.sencha.gxt.widget.core.client.container.SimpleContainer;
import com.sencha.gxt.widget.core.client.container.Viewport;

public abstract class CenterView extends Composite implements IsWidget {
	private String label = "OVERVIEW";
	private boolean closeAble = true;
	private String token;
	private EventBus eventBus;

	protected SimpleContainer con;

	public CenterView() {
		createContainer();
		initWidget(con);
		doAddAttachHandler();
	}

	protected void doAddAttachHandler() {
		this.addAttachHandler(new Handler() {
			@Override
			public void onAttachOrDetach(AttachEvent event) {
				if (event.isAttached()) {
					getEventBus().fireEvent(
							new CenterVievChangeEvent(
									CenterVievChangeEvent.From.RIGHT,
									getToken()));
				}
			}
		});
	}

	public void createContainer() {
		con = new Viewport();
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public boolean isCloseAble() {
		return closeAble;
	}

	public void setCloseAble(boolean closeAble) {
		this.closeAble = closeAble;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public EventBus getEventBus() {
		return eventBus;
	}

	public void setEventBus(EventBus eventBus) {
		this.eventBus = eventBus;
	}
}
