package com.voole.gxt.client.project.combox;

import com.google.gwt.core.shared.GWT;
import com.voole.gxt.client.canna.combox.CannaComBox;
import com.voole.gxt.client.canna.editor.CannaEditor;
import com.voole.gxt.client.project.manager.ManagerEditor;
import com.voole.gxt.client.property.project.ManagerProperties;
import com.voole.gxt.client.rpcclient.project.ProjectManagerRpcClient;
import com.voole.gxt.shared.entity.project.ProjectManager;

public class ProjectManagerComBox extends CannaComBox<ProjectManager> {
	private static final ManagerProperties props = GWT
			.create(ManagerProperties.class);
	private static final ProjectManagerRpcClient rpc = new ProjectManagerRpcClient();

	public ProjectManagerComBox() {
		this(true, true);
		getStore().clear();
	}

	public ProjectManagerComBox(boolean isAuto, boolean isAddabled) {
		super(isAuto, isAddabled, props.label());
	}

	@Override
	public void autoGetStore() {
		rpc.getManagersFroComBox(this);
	}

	@Override
	public ProjectManager getAddItem() {
		ProjectManager m = new ProjectManager();
		m.setId(-1L);
		m.setName("添加...");
		return m;
	}

	@Override
	public CannaEditor<ProjectManager> getEditor() {
		return new ManagerEditor();
	}

	@Override
	public boolean isAddItem(ProjectManager t) {
		return t.getId() == -1L;
	}

}
