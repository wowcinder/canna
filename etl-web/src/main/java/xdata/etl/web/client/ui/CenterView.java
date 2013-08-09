package xdata.etl.web.client.ui;

import xdata.etl.web.client.event.CenterVievChangeEvent;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.web.bindery.event.shared.EventBus;
import com.sencha.gxt.widget.core.client.container.SimpleContainer;
import com.sencha.gxt.widget.core.client.container.Viewport;
import com.sencha.gxt.widget.core.client.event.HideEvent;
import com.sencha.gxt.widget.core.client.event.HideEvent.HideHandler;
import com.sencha.gxt.widget.core.client.event.ShowEvent;
import com.sencha.gxt.widget.core.client.event.ShowEvent.ShowHandler;

public abstract class CenterView extends Composite implements IsWidget {
	private String label = "OVERVIEW";
	private boolean closeAble = true;
	private String token;
	private EventBus eventBus;
	private CenterContainer container;

	protected SimpleContainer con;

	public CenterView(EventBus eventBus, CenterContainer container) {
		this.eventBus = eventBus;
		this.container = container;
		createContainer();
		con.addShowHandler(new ShowHandler() {

			@Override
			public void onShow(ShowEvent event) {
				CenterView.this.eventBus.fireEvent(new CenterVievChangeEvent(
						CenterVievChangeEvent.From.RIGHT, getToken()));
			}
		});
		con.addHideHandler(new HideHandler() {
			@Override
			public void onHide(HideEvent event) {
				CenterView.this.container.afterClosedSubPanel();
			}
		});
		initWidget(con);
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

}
