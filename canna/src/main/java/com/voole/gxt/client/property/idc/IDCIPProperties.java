package com.voole.gxt.client.property.idc;

import com.sencha.gxt.core.client.ValueProvider;
import com.voole.gxt.client.property.CannaPropertyAccess;
import com.voole.gxt.shared.entity.device.SuperDevice;
import com.voole.gxt.shared.entity.idc.IDC;
import com.voole.gxt.shared.entity.idc.IDCIP;
import com.voole.gxt.shared.entity.idc.IDCSwitch;
import com.voole.gxt.shared.entity.metadata.network.NetworkType;

public interface IDCIPProperties extends CannaPropertyAccess<IDCIP> {

	ValueProvider<IDCIP, Long> id();

	ValueProvider<IDCIP, String> ip();

	ValueProvider<IDCIP, String> mark();

	ValueProvider<IDCIP, NetworkType> networkType();

	ValueProvider<IDCIP, Boolean> status();

	ValueProvider<IDCIP, SuperDevice> device();

	ValueProvider<IDCIP, IDCSwitch> switzh();

	ValueProvider<IDCIP, IDC> idc();
}
