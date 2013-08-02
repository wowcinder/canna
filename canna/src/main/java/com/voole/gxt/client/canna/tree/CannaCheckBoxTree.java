package com.voole.gxt.client.canna.tree;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.TextAlign;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.widget.core.client.tree.Tree;
import com.voole.gxt.shared.entity.CannaTreeNode;

public class CannaCheckBoxTree extends Tree<CannaTreeNode, String> {

	public CannaCheckBoxTree(ModelKeyProvider<CannaTreeNode> keyProvider) {
		super(new TreeStore<CannaTreeNode>(keyProvider),
				new ValueProvider<CannaTreeNode, String>() {
					@Override
					public String getValue(CannaTreeNode object) {
						return object.getTreeLabel();
					}

					@Override
					public void setValue(CannaTreeNode object, String value) {
						object.setTreeLabel(value);
					}

					@Override
					public String getPath() {
						return "name";
					}

				},(TreeAppearance)GWT.create(CannaTreeAppearance.class));
		
//		this.setWidth(300);
		this.setWidth("100%");
		this.getElement().getStyle().setTextAlign(TextAlign.LEFT);
		this.setCheckable(true);
		this.setCheckStyle(CheckCascade.TRI);
	}
}
