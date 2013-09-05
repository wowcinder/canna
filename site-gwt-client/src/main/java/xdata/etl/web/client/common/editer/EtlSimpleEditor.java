/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.common.editer;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.core.client.util.Padding;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.form.FormPanel;

/**
 * @author XuehuiHe
 * @date 2013年8月10日
 * 
 */
public abstract class EtlSimpleEditor<V> extends EtlEditor<V> {
	protected static final VerticalLayoutData vd = new VerticalLayoutData(1, -1);
	@Ignore
	protected FormPanel formPanel;
	@Ignore
	protected VerticalLayoutContainer layoutContainer;
	
	public EtlSimpleEditor() {
	}

	public EtlSimpleEditor(
			SimpleBeanEditorDriver<V, ? extends EtlEditor<V>> driver,
			String baseHeadingText) {
		super(driver, baseHeadingText);
	}

	protected void initView() {
		formPanel = new FormPanel();
		layoutContainer = new VerticalLayoutContainer();

		formPanel.getElement().setPadding(new Padding(10));
		formPanel.setBorders(true);

		formPanel.setWidget(layoutContainer);

		getRoot().forceLayout();
		getRoot().setWidget(formPanel);
	}

	public FormPanel getFormPanel() {
		return formPanel;
	}

	public void setFormPanel(FormPanel formPanel) {
		this.formPanel = formPanel;
	}

	public VerticalLayoutContainer getLayoutContainer() {
		return layoutContainer;
	}

	public void setLayoutContainer(VerticalLayoutContainer layoutContainer) {
		this.layoutContainer = layoutContainer;
	}

}
