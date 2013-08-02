package com.voole.gxt.client.property.project;

import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.LabelProvider;
import com.voole.gxt.client.property.CannaPropertyAccess;
import com.voole.gxt.shared.entity.project.Project;
import com.voole.gxt.shared.entity.project.ProjectPartner;
import com.voole.gxt.shared.entity.project.ProjectRemark;

public interface PartnerRemarkProperties extends
		CannaPropertyAccess<ProjectRemark> {

	@Path("remark")
	LabelProvider<ProjectRemark> label();

	ValueProvider<ProjectRemark, String> remark();

	ValueProvider<ProjectRemark, Project> project();

	ValueProvider<ProjectRemark, ProjectPartner> partner();

}
