package com.voole.gxt.client.property.idc;

import com.sencha.gxt.core.client.ValueProvider;
import com.voole.gxt.client.property.PartnerProperties;
import com.voole.gxt.shared.entity.idc.IDC;
import com.voole.gxt.shared.entity.idc.IDCPartner;

public interface IDCPartnerProperties extends PartnerProperties<IDCPartner> {
	
	ValueProvider<IDCPartner, IDC> idc();
}
