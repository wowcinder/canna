package com.voole.gxt.client.property.idc;

import com.sencha.gxt.core.client.ValueProvider;
import com.voole.gxt.client.property.CannaPropertyAccess;
import com.voole.gxt.shared.entity.idc.IDC;
import com.voole.gxt.shared.entity.idc.IDCCabinet;

public interface IDCCabinetProperties extends CannaPropertyAccess<IDCCabinet> {

	ValueProvider<IDCCabinet, Long> id();

	ValueProvider<IDCCabinet, String> name();

	ValueProvider<IDCCabinet, IDC> idc();
}
