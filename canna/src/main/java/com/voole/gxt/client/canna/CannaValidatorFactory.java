package com.voole.gxt.client.canna;

import javax.validation.Validation;
import javax.validation.Validator;

import com.google.gwt.core.client.GWT;
import com.google.gwt.validation.client.AbstractGwtValidatorFactory;
import com.google.gwt.validation.client.impl.AbstractGwtValidator;

public final class CannaValidatorFactory extends AbstractGwtValidatorFactory {
	private static Validator validator;


	@Override
	public AbstractGwtValidator createValidator() {
		return GWT.create(CannaValidator.class);
	}

	public static Validator getCannaValidator() {
		if (validator == null) {
			validator = Validation.buildDefaultValidatorFactory()
					.getValidator();
		}
		return validator;
	}
}
