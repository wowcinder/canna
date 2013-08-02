package com.voole.gxt.client.property.idc;

import java.util.List;

import com.sencha.gxt.core.client.ValueProvider;
import com.voole.gxt.client.property.CannaPropertyAccess;
import com.voole.gxt.shared.entity.idc.IDC;
import com.voole.gxt.shared.entity.idc.IDCAreaInfo;
import com.voole.gxt.shared.entity.idc.IDCCabinet;
import com.voole.gxt.shared.entity.idc.IDCHosting;
import com.voole.gxt.shared.entity.idc.IDCIP;
import com.voole.gxt.shared.entity.idc.IDCLeaseType;
import com.voole.gxt.shared.entity.idc.IDCNetworkInfo;
import com.voole.gxt.shared.entity.idc.IDCPartner;
import com.voole.gxt.shared.entity.idc.IDCSwitch;

public interface IDCProperties extends CannaPropertyAccess<IDC> {

	ValueProvider<IDC, Long> id();

	ValueProvider<IDC, Integer> deviceCount();

	ValueProvider<IDC, IDCLeaseType> idcLeaseType();

	ValueProvider<IDC, IDCHosting> hosting();

	ValueProvider<IDC, IDCNetworkInfo> networkInfo();

	ValueProvider<IDC, IDCAreaInfo> areaInfo();

	ValueProvider<IDC, List<IDCPartner>> partners();

	ValueProvider<IDC, List<IDCSwitch>> switchs();

	ValueProvider<IDC, List<IDCIP>> idcips();

	ValueProvider<IDC, List<IDCCabinet>> cabinets();
}
