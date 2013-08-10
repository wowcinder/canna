/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.common.editer;

import java.io.Serializable;

import xdata.etl.web.client.common.gridcontainer.EtlGridContainer;
import xdata.etl.web.shared.entity.RpcEntity;

import com.sencha.gxt.core.client.util.Padding;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.form.FormPanel;

/**
 * @author XuehuiHe
 * @date 2013年8月10日
 * 
 */
public abstract class EtlSimpleEditor<K extends Serializable, V extends RpcEntity<K>>
		extends EtlEditor<K, V> {
	protected static final VerticalLayoutData vd = new VerticalLayoutData(1, -1);
	@Ignore
	protected FormPanel formPanel;
	@Ignore
	protected VerticalLayoutContainer layoutContainer;

	public EtlSimpleEditor() {
		super();
	}

	public EtlSimpleEditor(EtlGridContainer<K, V> parent) {
		this();
		this.parent = parent;
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
