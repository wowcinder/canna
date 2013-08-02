package com.voole.gxt.client.authority.rpcgroup;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.sencha.gxt.core.client.util.Padding;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.FormPanel;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.tree.Tree.CheckState;
import com.voole.gxt.client.authority.rpcmethod.AuthorityRpcMethodTree;
import com.voole.gxt.client.canna.editor.CannaEditor;
import com.voole.gxt.client.rpcclient.authority.AuthorityRpcGroupRcpClient;
import com.voole.gxt.client.rpcclient.authority.AuthorityRpcMethodRpcClient;
import com.voole.gxt.client.rpcclient.callback.GetCallback;
import com.voole.gxt.shared.entity.CannaTreeNode;
import com.voole.gxt.shared.entity.authority.AuthorityRpcGroup;
import com.voole.gxt.shared.entity.authority.AuthorityRpcMethod;
import com.voole.gxt.shared.entity.authority.AuthorityRpcService;

public class AuthorityRpcGroupEditor extends CannaEditor<AuthorityRpcGroup>
		implements GetCallback<List<AuthorityRpcService>> {

	interface AuthorityRpcGroupEditorDriver extends
			SimpleBeanEditorDriver<AuthorityRpcGroup, AuthorityRpcGroupEditor> {
	}

	public static final AuthorityRpcMethodRpcClient methodRpc = new AuthorityRpcMethodRpcClient();
	protected VerticalLayoutContainer vc;
	protected FormPanel fp;
	private AuthorityRpcGroupEditorDriver driver = GWT
			.create(AuthorityRpcGroupEditorDriver.class);
	TextField name;
	AuthorityRpcMethodTree tree;
	private boolean treeIsRedeay = false;
	private List<CannaTreeNode> selection = new ArrayList<CannaTreeNode>();

	public AuthorityRpcGroupEditor() {
		super();
		driver.initialize(this);
		methodRpc.getService(this);
		this.rpc = new AuthorityRpcGroupRcpClient();
	}

	@Override
	protected void initView() {
		fp = new FormPanel();
		vc = new VerticalLayoutContainer();
		name = new TextField();

		fp.getElement().setPadding(new Padding(10));
		fp.setBorders(true);
		fp.setWidget(vc);

		root.forceLayout();
		root.setWidget(fp);

		root.setWidth("80%");

		vc.add(new FieldLabel(name, "权限组名称"), new VerticalLayoutData(1, -1));
		tree = new AuthorityRpcMethodTree();
		tree.setHeight(300);
		vc.add(tree, new VerticalLayoutData(1, -1));

	}

	@Override
	protected AuthorityRpcGroup flush() {
		AuthorityRpcGroup group = driver.flush();
		group.setAuthorityRpcMethods(getMethods());
		return group;
	}

	private List<AuthorityRpcMethod> getMethods() {
		List<CannaTreeNode> list = tree.getCheckedSelection();
		List<AuthorityRpcMethod> methods = new ArrayList<AuthorityRpcMethod>();
		for (CannaTreeNode node : list) {
			if (node instanceof AuthorityRpcMethod) {
				methods.add((AuthorityRpcMethod) node);
			}
		}
		return methods;
	}

	@Override
	public void toAdd() {
		clearTree();
		AuthorityRpcGroup group = new AuthorityRpcGroup();
		driver.edit(group);
	}

	private void clearTree() {
		selection.clear();
		List<CannaTreeNode> list = tree.getCheckedSelection();
		for (CannaTreeNode node : list) {
			tree.setChecked(node, CheckState.UNCHECKED);
		}
	}

	@Override
	public void doEdit(final AuthorityRpcGroup t) {
		clearTree();
		getRpc().get(t, new GetCallback<List<AuthorityRpcMethod>>() {
			@Override
			public void postGet(List<AuthorityRpcMethod> t1) {
				driver.edit(t);
				selection.addAll(t1);
				checked();
			}
		});

	}

	@Override
	public String getBaseHeadingText() {
		return "权限组";
	}

	@Override
	public void postGet(List<AuthorityRpcService> t) {
		tree.postGet(t);
		treeIsRedeay = true;
		checked();
	}

	public void checked() {
		if (treeIsRedeay && selection.size() > 0) {
			for (CannaTreeNode node : selection) {
				tree.setChecked(node, CheckState.CHECKED);
			}
		}
	}

	private AuthorityRpcGroupRcpClient getRpc() {
		return (AuthorityRpcGroupRcpClient) this.rpc;
	}

}
