package com.voole.gxt.client.property.idc;

import java.util.List;

import com.sencha.gxt.core.client.ValueProvider;
import com.voole.gxt.client.property.CannaPropertyAccess;
import com.voole.gxt.shared.entity.idc.IDC;
import com.voole.gxt.shared.entity.idc.IDCLeaseType;

public interface IDCLeaseTypeProperties extends
		CannaPropertyAccess<IDCLeaseType> {

	ValueProvider<IDCLeaseType, String> name();

	ValueProvider<IDCLeaseType, Integer> value();

	ValueProvider<IDCLeaseType, List<IDC>> idcs();
}
