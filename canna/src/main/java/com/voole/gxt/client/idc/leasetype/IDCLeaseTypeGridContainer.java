package com.voole.gxt.client.idc.leasetype;

import com.voole.gxt.client.canna.gridcontainer.CannaGridContainer;
import com.voole.gxt.client.rpcclient.idc.IDCLeaseTypeRpcClient;
import com.voole.gxt.shared.entity.idc.IDCLeaseType;

public class IDCLeaseTypeGridContainer extends CannaGridContainer<IDCLeaseType> {
	public IDCLeaseTypeGridContainer() {
		super(new IDCLeaseTypeGrid(), new IDCLeaseTypeRpcClient());
		setEditor(new IDCLeaseTypeEditor((IDCLeaseTypeRpcClient) getRpc()));
	}
}
