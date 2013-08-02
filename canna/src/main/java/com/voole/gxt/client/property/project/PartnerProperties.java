package com.voole.gxt.client.property.project;

import com.sencha.gxt.core.client.ValueProvider;
import com.voole.gxt.client.property.CannaPropertyAccess;
import com.voole.gxt.shared.entity.project.ProjectPartner;

public interface PartnerProperties extends CannaPropertyAccess<ProjectPartner> {

	ValueProvider<ProjectPartner, String> name();

	ValueProvider<ProjectPartner, String> position();

	ValueProvider<ProjectPartner, String> mobile();

	ValueProvider<ProjectPartner, String> email();

	ValueProvider<ProjectPartner, String> telphone();

	ValueProvider<ProjectPartner, String> remark();
}
