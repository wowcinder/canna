package com.voole.gxt.client.property.project;

import com.sencha.gxt.core.client.ValueProvider;
import com.voole.gxt.client.property.CannaPropertyAccess;
import com.voole.gxt.shared.entity.project.ProjectLeader;

public interface LeaderProperties extends CannaPropertyAccess<ProjectLeader> {

	ValueProvider<ProjectLeader, String> name();

	ValueProvider<ProjectLeader, String> mobile();

	ValueProvider<ProjectLeader, String> extNum();

	ValueProvider<ProjectLeader, String> email();

}
