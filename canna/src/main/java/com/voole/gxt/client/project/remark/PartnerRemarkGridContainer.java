package com.voole.gxt.client.project.remark;

import java.util.ArrayList;
import java.util.List;

import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.voole.gxt.client.canna.editor.CannaEditor.EditorUpdateHanlder;
import com.voole.gxt.client.canna.gridcontainer.CannaGridContainer;
import com.voole.gxt.client.project.partner.PartnerSelectWindow;
import com.voole.gxt.client.project.partner.PartnerSelectWindow.PartnerSelectCallBack;
import com.voole.gxt.shared.entity.project.Project;
import com.voole.gxt.shared.entity.project.ProjectPartner;
import com.voole.gxt.shared.entity.project.ProjectRemark;

public class PartnerRemarkGridContainer extends
		CannaGridContainer<ProjectRemark> {

	Project project;

	private PartnerSelectCallBack callBack = new PartnerSelectCallBack() {
		@Override
		public void post(List<ProjectPartner> list) {
			add(list);
		}
	};

	public PartnerRemarkGridContainer() {
		super(new PartnerRemarkGrid(), null);

		PartnerRemarkEditor updateEditor = new PartnerRemarkEditor();
		updateEditor.setUpdateHanlder(new EditorUpdateHanlder<ProjectRemark>() {
			@Override
			public void postUpdate(ProjectRemark t) {
				getStore().update(t);
			}

		});
		setUpdateEditor(updateEditor);
		setAddHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				PartnerSelectWindow w = new PartnerSelectWindow(callBack);
				w.show();
			}
		});
	}

	private void add(List<ProjectPartner> list) {
		ListStore<ProjectRemark> store = getGrid().getStore();
		List<ProjectRemark> remarks = store.getAll();
		List<ProjectPartner> has = new ArrayList<ProjectPartner>();
		for (ProjectRemark partnerRemark : remarks) {
			has.add(partnerRemark.getPartner());
		}
		for (ProjectPartner partner : list) {
			if (!has.contains(partner)) {
				ProjectRemark pr = new ProjectRemark();
				pr.setPartner(partner);
				pr.setProject(getProject());
				store.add(0, pr);
			}
		}
	}

	protected void delete(List<ProjectRemark> list) {
		postDelete(list);
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@Override
	protected void noFoundDeleteItems() {
		// TODO Auto-generated method stub

	}

}
