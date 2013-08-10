/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.common.editer;

import java.io.Serializable;

import xdata.etl.web.client.common.gridcontainer.EtlGridContainer;
import xdata.etl.web.client.gwt.GwtCallBack;
import xdata.etl.web.client.rpc.EntityRpcCaller;
import xdata.etl.web.client.service.RpcServiceAsync;
import xdata.etl.web.shared.entity.RpcEntity;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.shared.HandlerRegistration;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutPack;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;

/**
 * @author XuehuiHe
 * @date 2013年8月10日
 * 
 */
public abstract class EtlEditor<K extends Serializable, V extends RpcEntity<K>>
		implements Editor<V> {

	@Ignore
	private final EditorWindow root;
	@Ignore
	private final TextButton saveOrUpdateBt;
	@Ignore
	private final TextButton cancelBt;
	@Ignore
	protected GwtCallBack<SelectEvent> cancelCallBack;
	@Ignore
	protected GwtCallBack<V> saveOrUpdateCallBack;
	@Ignore
	protected EtlGridContainer<K, V> parent;
	@Ignore
	private SimpleBeanEditorDriver<V, EtlEditor<K, V>> driver;
	@Ignore
	private String baseHeadingText = "";
	@Ignore
	private boolean isAdd = true;
	@Ignore
	private HandlerRegistration cancelBtHandlerHr;
	@Ignore
	private HandlerRegistration saveOrUpdateBtHandlerHr;

	public EtlEditor() {
		root = new EditorWindow();
		saveOrUpdateBt = new TextButton("Save");
		cancelBt = new TextButton("Cancel");

		root.setButtonAlign(BoxLayoutPack.END);
		root.setModal(true);

		root.addButton(saveOrUpdateBt);
		root.addButton(cancelBt);

		setCancelBtHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				getCancelBt().disable();
				if (getCancelCallBack() != null) {
					getCancelCallBack().call(event);
				}
				getCancelBt().enable();
				getRoot().hide();
			}
		});

		setSaveOrUpdateBtHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				getSaveOrUpdateBt().disable();
				V v = getDriver().flush();
				saveOrUpdate(v);
			}
		});

		initView();
		getDriver().initialize(this);
	}

	public EtlEditor(EtlGridContainer<K, V> parent) {
		this();
		this.parent = parent;
	}

	protected abstract void initView();

	protected void saveOrUpdate(V v) {
		if (isAdd()) {
			save(v);
		} else {
			update(v);
		}
	}

	protected void save(V v) {
		getRpcCaller().saveAndReturn(v, getSaveOrUpdateCallBackSwap());
	}

	protected void update(V v) {
		getRpcCaller().update(v, getSaveOrUpdateCallBackSwap());
	}

	protected GwtCallBack<V> getSaveOrUpdateCallBackSwap() {
		return new GwtCallBack<V>() {
			@Override
			public void call(V t) {
				if (getSaveOrUpdateCallBack() != null) {
					getSaveOrUpdateCallBack().call(t);
				}
				getSaveOrUpdateBt().enable();
				getRoot().hide();
			}
		};
	}

	public void doEdit(V v) {
		setAdd(false);
		getRoot().setHeadingText(getHeadingText());
		getSaveOrUpdateBt().setText("编辑");
		getDriver().edit(v);
		getRoot().show();
	}

	public void add() {
		setAdd(true);
		getRoot().setHeadingText(getHeadingText());
		getSaveOrUpdateBt().setText("添加");
		getDriver().edit(newInstance());
		getRoot().show();
	}

	protected abstract V newInstance();

	protected String getHeadingText() {
		if (isAdd()) {
			return "添加" + getBaseHeadingText();
		}
		return "修改" + getBaseHeadingText();
	}

	public SimpleBeanEditorDriver<V, EtlEditor<K, V>> getDriver() {
		return driver;
	}

	public void setDriver(SimpleBeanEditorDriver<V, EtlEditor<K, V>> driver) {
		this.driver = driver;
	}

	public EtlGridContainer<K, V> getParent() {
		return parent;
	}

	public void setParent(EtlGridContainer<K, V> parent) {
		this.parent = parent;
	}

	public GwtCallBack<SelectEvent> getCancelCallBack() {
		return cancelCallBack;
	}

	public void setCancelCallBack(GwtCallBack<SelectEvent> cancelCallBack) {
		this.cancelCallBack = cancelCallBack;
	}

	public GwtCallBack<V> getSaveOrUpdateCallBack() {
		return saveOrUpdateCallBack;
	}

	public void setSaveOrUpdateCallBack(GwtCallBack<V> saveOrUpdateCallBack) {
		this.saveOrUpdateCallBack = saveOrUpdateCallBack;
	}

	public String getBaseHeadingText() {
		return baseHeadingText;
	}

	public void setBaseHeadingText(String baseHeadingText) {
		this.baseHeadingText = baseHeadingText;
	}

	public boolean isAdd() {
		return isAdd;
	}

	public void setAdd(boolean isAdd) {
		this.isAdd = isAdd;
	}

	public EditorWindow getRoot() {
		return root;
	}

	public TextButton getSaveOrUpdateBt() {
		return saveOrUpdateBt;
	}

	public TextButton getCancelBt() {
		return cancelBt;
	}

	public RpcServiceAsync<K, V> getService() {
		return getParent().getService();
	}

	public EntityRpcCaller<K, V> getRpcCaller() {
		return getParent().getRpcCaller();
	}

	public void setCancelBtHandler(SelectHandler cancelBtHandler) {
		if (this.cancelBtHandlerHr != null) {
			this.cancelBtHandlerHr.removeHandler();
			this.cancelBtHandlerHr = null;
		}

		if (cancelBtHandler != null) {
			this.cancelBtHandlerHr = getCancelBt().addSelectHandler(
					cancelBtHandler);
		}
	}

	public void setSaveOrUpdateBtHandler(SelectHandler saveOrUpdateBtHandler) {
		if (this.saveOrUpdateBtHandlerHr != null) {
			this.saveOrUpdateBtHandlerHr.removeHandler();
			this.saveOrUpdateBtHandlerHr = null;
		}
		if (saveOrUpdateBtHandler != null) {
			this.saveOrUpdateBtHandlerHr = getSaveOrUpdateBt()
					.addSelectHandler(saveOrUpdateBtHandler);

		}
	}

}
