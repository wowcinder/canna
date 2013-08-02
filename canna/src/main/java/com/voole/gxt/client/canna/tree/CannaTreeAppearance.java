package com.voole.gxt.client.canna.tree;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.sencha.gxt.core.client.GXT;
import com.sencha.gxt.theme.base.client.tree.TreeBaseAppearance;
import com.sencha.gxt.theme.blue.client.tree.BlueTreeAppearance.BlueTreeResources;
import com.sencha.gxt.widget.core.client.tree.Tree.CheckState;
import com.sencha.gxt.widget.core.client.tree.Tree.Joint;
import com.sencha.gxt.widget.core.client.tree.TreeStyle;
import com.sencha.gxt.widget.core.client.tree.TreeView.TreeViewRenderMode;

public class CannaTreeAppearance extends TreeBaseAppearance {
	private int leftLevel = 1;

	private static final TreeResources resources = (TreeResources) GWT
			.create(BlueTreeResources.class);
	private static final TreeBaseStyle style = resources.style();

	public CannaTreeAppearance() {
		super(resources);
	}

	@SuppressWarnings("incomplete-switch")
	@Override
	public void renderNode(SafeHtmlBuilder sb, String id, SafeHtml text,
			TreeStyle ts, ImageResource icon, boolean checkable,
			CheckState checked, Joint joint, int level,
			TreeViewRenderMode renderMode) {

		if (renderMode == TreeViewRenderMode.ALL
				|| renderMode == TreeViewRenderMode.BUFFER_WRAP) {
			if (level == getLeftLevel()) {
				sb.appendHtmlConstant("<div id=\""
						+ SafeHtmlUtils.htmlEscape(id) + "\" class=\""
						+ style.node() + "\"   style=\"float:left;\">");
			} else {
				sb.appendHtmlConstant("<div id=\""
						+ SafeHtmlUtils.htmlEscape(id) + "\" class=\""
						+ style.node() + "\"  style=\"clear: both;\" >");
			}

			sb.appendHtmlConstant("<div class=\"" + style.element() + "\">");
		}

		if (renderMode == TreeViewRenderMode.ALL
				|| renderMode == TreeViewRenderMode.BUFFER_BODY) {

			sb.appendHtmlConstant("<img src='" + GXT.getBlankImageUrl()
					+ "' style='height: 18px; width: " + (level * 18)
					+ "px;' />");

			Element jointElement = null;
			switch (joint) {
			case COLLAPSED:
				jointElement = getImage(ts.getJointCloseIcon() == null ? resources
						.jointCollapsedIcon() : ts.getJointCloseIcon());
				break;
			case EXPANDED:
				jointElement = getImage(ts.getJointOpenIcon() == null ? resources
						.jointExpandedIcon() : ts.getJointOpenIcon());
				break;
			}

			if (jointElement != null) {
				jointElement.addClassName(style.joint());
			}

			sb.appendHtmlConstant(jointElement == null ? "<img src=\""
					+ GXT.getBlankImageUrl()
					+ "\" style=\"width: 16px\" class=\"" + style.joint()
					+ "\" />" : jointElement.getString());

			// checkable
			if (checkable) {
				Element e = null;
				switch (checked) {
				case CHECKED:
					e = getImage(resources.checked());
					break;
				case UNCHECKED:
					e = getImage(resources.unchecked());
					break;
				case PARTIAL:
					e = getImage(resources.partialChecked());
					break;
				}

				e.addClassName(style.check());
				sb.appendHtmlConstant(e.getString());
			} else {
				sb.appendHtmlConstant("<span class='" + style.check()
						+ "'></span>");
			}

			if (icon != null) {
				Element e = getImage(icon);
				e.addClassName(style.icon());
				sb.appendHtmlConstant(e.getString());
			} else {
				sb.appendHtmlConstant("<span class=\"" + style.icon()
						+ "\"></span>");
			}

			sb.appendHtmlConstant("<span class=\"" + style.text() + "\">"
					+ text.asString() + "</span>");
		}

		if (renderMode == TreeViewRenderMode.ALL
				|| renderMode == TreeViewRenderMode.BUFFER_WRAP) {
			sb.appendHtmlConstant("</div>");
			sb.appendHtmlConstant("</div>");
		}

	}

	public int getLeftLevel() {
		return leftLevel;
	}

	public void setLeftLevel(int leftLevel) {
		this.leftLevel = leftLevel;
	}

	private Element getImage(ImageResource ir) {
		return AbstractImagePrototype.create(ir).createElement();
	}
}
