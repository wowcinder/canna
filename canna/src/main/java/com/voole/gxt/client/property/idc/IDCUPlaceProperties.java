package com.voole.gxt.client.property.idc;

import com.sencha.gxt.core.client.ValueProvider;
import com.voole.gxt.client.property.CannaPropertyAccess;
import com.voole.gxt.shared.entity.device.SuperDevice;
import com.voole.gxt.shared.entity.idc.IDCCabinet;
import com.voole.gxt.shared.entity.idc.IDCUPlace;

public interface IDCUPlaceProperties extends CannaPropertyAccess<IDCUPlace> {

	ValueProvider<IDCUPlace, Long> id();

	ValueProvider<IDCUPlace, String> uname();

	ValueProvider<IDCUPlace, IDCCabinet> cabinet();

	ValueProvider<IDCUPlace, SuperDevice> device();
}
