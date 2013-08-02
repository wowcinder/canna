package com.voole.gxt.client.canna.editor;

import com.sencha.gxt.core.client.util.Padding;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.form.FormPanel;

public abstract class CannaSimpleEditor<T> extends CannaEditor<T> {
	protected static final VerticalLayoutData vd = new VerticalLayoutData(1, -1);
	protected FormPanel fp;
	protected VerticalLayoutContainer vc;

	public CannaSimpleEditor() {
		super();
	}

	public CannaSimpleEditor(EditorAddHanlder<T> editorAddHanlder,
			EditorUpdateHanlder<T> editorUpdateHanlder) {
		this();
		if (editorAddHanlder != null) {
			setAddHanlder(editorAddHanlder);
		}
		if (editorUpdateHanlder != null) {
			setUpdateHanlder(editorUpdateHanlder);
		}
	}

	@Override
	protected void initView() {
		fp = new FormPanel();
		vc = new VerticalLayoutContainer();

		fp.getElement().setPadding(new Padding(10));
		fp.setBorders(true);

		fp.setWidget(vc);

		root.forceLayout();
		root.setWidget(fp);
	}
}
