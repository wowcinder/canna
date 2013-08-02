package com.voole.gxt.client.canna;

import javax.validation.Validator;

import com.google.gwt.validation.client.GwtValidation;
import com.voole.gxt.shared.entity.authority.AuthorityRpcGroup;
import com.voole.gxt.shared.entity.authority.AuthorityRpcMethod;
import com.voole.gxt.shared.entity.device.SuperDevice;
import com.voole.gxt.shared.entity.idc.IDC;
import com.voole.gxt.shared.entity.idc.IDCCabinet;
import com.voole.gxt.shared.entity.idc.IDCHosting;
import com.voole.gxt.shared.entity.idc.IDCIP;
import com.voole.gxt.shared.entity.idc.IDCLeaseType;
import com.voole.gxt.shared.entity.idc.IDCPartner;
import com.voole.gxt.shared.entity.idc.IDCSwitch;
import com.voole.gxt.shared.entity.menu.Menu;
import com.voole.gxt.shared.entity.menu.MenuGroup;
import com.voole.gxt.shared.entity.metadata.area.City;
import com.voole.gxt.shared.entity.metadata.area.Province;
import com.voole.gxt.shared.entity.metadata.network.NetworkType;
import com.voole.gxt.shared.entity.project.Operator;
import com.voole.gxt.shared.entity.project.Project;
import com.voole.gxt.shared.entity.project.ProjectLeader;
import com.voole.gxt.shared.entity.project.ProjectManager;
import com.voole.gxt.shared.entity.project.ProjectPartner;
import com.voole.gxt.shared.entity.project.ProjectRemark;
import com.voole.gxt.shared.entity.user.User;
import com.voole.gxt.shared.entity.user.UserGroup;

@GwtValidation(value = { AuthorityRpcGroup.class, SuperDevice.class, IDC.class,
		IDCCabinet.class, IDCHosting.class, IDCIP.class, IDCLeaseType.class,
		IDCPartner.class, IDCSwitch.class, Menu.class, MenuGroup.class,
		City.class, Province.class, NetworkType.class, Operator.class,
		Project.class, ProjectLeader.class, ProjectManager.class,
		ProjectPartner.class, ProjectRemark.class, AuthorityRpcMethod.class,
		User.class, UserGroup.class })
public interface CannaValidator extends Validator {

}
