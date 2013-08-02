package com.voole.gxt.server.dao.project;

import java.util.List;

import com.voole.gxt.server.dao.IBaseDao;
import com.voole.gxt.shared.entity.project.ProjectLeader;

public interface ProjectLeaderDao extends IBaseDao<ProjectLeader> {
	List<ProjectLeader> getLeadersForComBox();
}
