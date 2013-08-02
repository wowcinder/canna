package com.voole.gxt.client.canna.grid;

import com.sencha.gxt.cell.core.client.SimpleSafeHtmlCell;

public class CannaSafeHtmlCell<T> extends SimpleSafeHtmlCell<T> {

	public CannaSafeHtmlCell(AbstractCannaSafeHtmlRenderer<T> renderer) {
		super(renderer);
	}
}
