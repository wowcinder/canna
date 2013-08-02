package com.voole.gxt.client.canna.grid;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.text.shared.AbstractSafeHtmlRenderer;

public abstract class AbstractCannaSafeHtmlRenderer<T> extends
		AbstractSafeHtmlRenderer<T> {

	@Override
	public SafeHtml render(T t) {
		if (t == null) {
			return null;
		}
		String str = getRenderStr(t);
		if (str == null) {
			return null;
		}
		return SafeHtmlUtils.fromString(str);
	}

	public abstract String getRenderStr(T t);

}
