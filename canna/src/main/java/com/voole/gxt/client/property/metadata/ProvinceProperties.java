package com.voole.gxt.client.property.metadata;

import java.util.List;

import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.LabelProvider;
import com.voole.gxt.client.property.CannaPropertyAccess;
import com.voole.gxt.shared.entity.metadata.area.City;
import com.voole.gxt.shared.entity.metadata.area.Province;

public interface ProvinceProperties extends CannaPropertyAccess<Province> {
	@Path("name")
	LabelProvider<Province> label();

	ValueProvider<Province, String> name();

	ValueProvider<Province, List<City>> cities();
}
