package com.voole.gxt.client.property.idc;

import java.util.List;

import com.sencha.gxt.core.client.ValueProvider;
import com.voole.gxt.client.property.CannaPropertyAccess;
import com.voole.gxt.shared.entity.idc.IDC;
import com.voole.gxt.shared.entity.idc.IDCHosting;

public interface IDCHostingProperties extends CannaPropertyAccess<IDCHosting> {

	ValueProvider<IDCHosting, Long> id();

	ValueProvider<IDCHosting, String> name();

	ValueProvider<IDCHosting, List<IDC>> idcs();
}
