package com.voole.gxt.client.property.project;

import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.LabelProvider;
import com.voole.gxt.client.property.CannaPropertyAccess;
import com.voole.gxt.shared.entity.project.ProjectManager;

public interface ManagerProperties extends CannaPropertyAccess<ProjectManager> {

	@Path("name")
	LabelProvider<ProjectManager> label();

	ValueProvider<ProjectManager, String> name();

	ValueProvider<ProjectManager, String> mobile();

	ValueProvider<ProjectManager, String> extNum();

	ValueProvider<ProjectManager, String> email();
}
