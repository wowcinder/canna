package xdata.etl.web.client.event;

import xdata.etl.web.client.event.CenterVievChangeEvent.Hanlder;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class CenterVievChangeEvent extends GwtEvent<Hanlder> {
	public static final Type<Hanlder> HANLDER_TYPE = new Type<Hanlder>();

	public static enum EventType {
		CLICK, CLOSE
	}

	private EventType type;
	private String token;

	public CenterVievChangeEvent(String token) {
		this(EventType.CLICK, token);
	}

	public CenterVievChangeEvent(EventType type, String token) {
		this.type = type;
		this.token = token;
	}

	public interface Hanlder extends EventHandler {
		void dispatch(CenterVievChangeEvent event);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Hanlder> getAssociatedType() {
		return HANLDER_TYPE;
	}

	@Override
	protected void dispatch(Hanlder handler) {
		handler.dispatch(this);
	}

	public EventType getType() {
		return type;
	}

	public void setType(EventType type) {
		this.type = type;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
