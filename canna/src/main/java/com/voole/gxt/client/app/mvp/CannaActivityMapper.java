package com.voole.gxt.client.app.mvp;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.voole.gxt.client.app.activity.CenterContainerActivity;
import com.voole.gxt.client.app.place.CenterPlace;

public class CannaActivityMapper implements ActivityMapper {

	@Inject
	private Provider<CenterContainerActivity> centerContainerActivity;

	@Override
	public Activity getActivity(Place place) {
		if (place instanceof CenterPlace) {
			CenterPlace cp = (CenterPlace) place;
			CenterContainerActivity ca = centerContainerActivity.get();
			ca.setMenuToken(cp.getToken());
			return ca;
		}
		return null;
	}

}
