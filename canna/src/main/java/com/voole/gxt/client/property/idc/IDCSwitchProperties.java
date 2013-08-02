package com.voole.gxt.client.property.idc;

import com.sencha.gxt.core.client.ValueProvider;
import com.voole.gxt.client.property.CannaPropertyAccess;
import com.voole.gxt.shared.entity.idc.IDCSwitch;

public interface IDCSwitchProperties extends CannaPropertyAccess<IDCSwitch> {

	ValueProvider<IDCSwitch, Long> id();

	ValueProvider<IDCSwitch, Long> name();

	ValueProvider<IDCSwitch, Long> idc();

	ValueProvider<IDCSwitch, Long> idcips();
}
