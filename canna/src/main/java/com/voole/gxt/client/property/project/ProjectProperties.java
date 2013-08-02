package com.voole.gxt.client.property.project;

import java.util.List;

import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.LabelProvider;
import com.voole.gxt.client.property.CannaPropertyAccess;
import com.voole.gxt.shared.entity.project.Operator;
import com.voole.gxt.shared.entity.project.Project;
import com.voole.gxt.shared.entity.project.ProjectLeader;
import com.voole.gxt.shared.entity.project.ProjectManager;
import com.voole.gxt.shared.entity.project.ProjectRemark;

public interface ProjectProperties extends CannaPropertyAccess<Project> {

	@Path("name")
	LabelProvider<Project> label();

	ValueProvider<Project, String> name();

	ValueProvider<Project, ProjectLeader> leader();

	ValueProvider<Project, ProjectManager> manager();

	ValueProvider<Project, Operator> operator();

	ValueProvider<Project, List<ProjectRemark>> remarks();
}
