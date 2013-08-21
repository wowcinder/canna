package xdata.etl.web.client.event;

import xdata.etl.web.client.event.CenterVievChangeEvent.Hanlder;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class CenterVievChangeEvent extends GwtEvent<Hanlder> {
	public static final Type<Hanlder> TYPE = new Type<Hanlder>();

	public static enum From {
		LEFT, RIGHT
	}

	private From from;
	private String token;

	public CenterVievChangeEvent(String token) {
		this(From.LEFT, token);
	}

	public CenterVievChangeEvent(From from, String token) {
		this.from = from;
		this.token = token;
	}

	public interface Hanlder extends EventHandler {
		void dispatch(CenterVievChangeEvent event);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Hanlder> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(Hanlder handler) {
		handler.dispatch(this);
	}

	public From getFrom() {
		return from;
	}

	public void setFrom(From type) {
		this.from = type;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
