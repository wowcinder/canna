package com.voole.gxt.client.canna.util;

import com.sencha.gxt.data.shared.Converter;
import com.sencha.gxt.widget.core.client.form.CheckBox;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.editing.GridRowEditing;

public class CheckBoxSelectionModelUtil<T> {
	public void fixed(GridRowEditing<T> editing, ColumnConfig<T, T> cc) {
		CheckBox checkBox = new CheckBox();
		checkBox.setReadOnly(false);
		checkBox.setEnabled(false);
		editing.addEditor(cc, new Converter<T, Boolean>() {
			@Override
			public T convertFieldValue(Boolean object) {
				return null;
			}

			@Override
			public Boolean convertModelValue(T object) {
				return Boolean.FALSE;
			}
		}, checkBox);
	}
}
