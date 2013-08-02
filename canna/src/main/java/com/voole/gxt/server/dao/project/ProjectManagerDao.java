package com.voole.gxt.server.dao.project;

import java.util.List;

import com.voole.gxt.server.dao.IBaseDao;
import com.voole.gxt.shared.entity.project.ProjectManager;

public interface ProjectManagerDao extends IBaseDao<ProjectManager> {

	List<ProjectManager> getManagersFroComBox();


}
