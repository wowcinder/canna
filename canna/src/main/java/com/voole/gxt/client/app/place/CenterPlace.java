package com.voole.gxt.client.app.place;

import com.google.gwt.place.shared.Place;

public class CenterPlace extends Place {
	private String token;

	public CenterPlace() {
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof CenterPlace) {
			return getToken().equals(((CenterPlace) obj).getToken());
		}
		return false;
	}

	@Override
	public int hashCode() {
		return getToken().hashCode();
	}

}
