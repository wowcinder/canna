package com.voole.gxt.client.property.metadata;

import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.LabelProvider;
import com.voole.gxt.client.property.CannaPropertyAccess;
import com.voole.gxt.shared.entity.metadata.area.City;

public interface CityProperties extends CannaPropertyAccess<City> {
	@Path("name")
	LabelProvider<City> label();

	ValueProvider<City, String> name();

	ValueProvider<City, Integer> lat();

	ValueProvider<City, Integer> lon();
}
