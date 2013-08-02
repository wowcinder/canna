package com.voole.gxt.server.dao.project;

import java.util.List;

import com.voole.gxt.server.dao.IBaseDao;
import com.voole.gxt.shared.entity.project.Project;
import com.voole.gxt.shared.entity.project.ProjectRemark;

public interface ProjectDao extends IBaseDao<Project> {

	List<ProjectRemark> getRemarks(Long id);

}
