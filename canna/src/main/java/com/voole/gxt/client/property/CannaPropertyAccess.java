package com.voole.gxt.client.property;

import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface CannaPropertyAccess<T> extends PropertyAccess<T> {
	@Path("id")
	ModelKeyProvider<T> key();

	@Path("name")
	LabelProvider<T> label();

	ValueProvider<T, Long> id();
}
