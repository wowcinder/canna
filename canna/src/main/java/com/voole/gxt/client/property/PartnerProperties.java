package com.voole.gxt.client.property;

import com.sencha.gxt.core.client.ValueProvider;

public interface PartnerProperties<Partner> extends CannaPropertyAccess<Partner> {

	ValueProvider<Partner, String> name();

	ValueProvider<Partner, String> position();

	ValueProvider<Partner, String> mobile();

	ValueProvider<Partner, String> email();

	ValueProvider<Partner, String> telphone();

	ValueProvider<Partner, String> remark();
}
