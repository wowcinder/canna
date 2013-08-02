package com.voole.gxt.client.project.combox;

import com.google.gwt.core.shared.GWT;
import com.sencha.gxt.data.shared.LabelProvider;
import com.voole.gxt.client.canna.combox.CannaComBox;
import com.voole.gxt.client.canna.editor.CannaEditor;
import com.voole.gxt.client.project.leader.LeaderEditor;
import com.voole.gxt.client.property.project.LeaderProperties;
import com.voole.gxt.client.rpcclient.project.ProjectLeaderRpcClient;
import com.voole.gxt.shared.entity.project.ProjectLeader;

public class ProjectLeaderComBox extends CannaComBox<ProjectLeader> {
	private final static LeaderProperties props = GWT
			.create(LeaderProperties.class);
	private final static ProjectLeaderRpcClient rpc = new ProjectLeaderRpcClient();

	public ProjectLeaderComBox() {
		this(true, true, props.label());
	}

	public ProjectLeaderComBox(boolean isAuto, boolean isAddabled,
			LabelProvider<? super ProjectLeader> labelProvider) {
		super(isAuto, isAddabled, labelProvider);
	}

	@Override
	public void autoGetStore() {
		rpc.getLeadersForComBox(this);
	}

	@Override
	public ProjectLeader getAddItem() {
		ProjectLeader l = new ProjectLeader();
		l.setId(-1L);
		l.setName("添加...");
		return l;
	}

	@Override
	public CannaEditor<ProjectLeader> getEditor() {
		return new LeaderEditor();
	}

	@Override
	public boolean isAddItem(ProjectLeader t) {
		return t.getId() == -1L;
	}

}
