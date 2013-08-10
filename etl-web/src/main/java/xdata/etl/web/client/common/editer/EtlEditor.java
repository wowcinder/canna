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
	protected GwtCallBack<SelectEvent> addCancelCallBack;
	@Ignore
	protected GwtCallBack<SelectEvent> updateCancelCallBack;
	@Ignore
	protected GwtCallBack<V> addCallBack;
	@Ignore
	protected GwtCallBack<V> updateCallBack;
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

	@SuppressWarnings("unchecked")
	public EtlEditor(Object driver) {
		this.driver = (SimpleBeanEditorDriver<V, EtlEditor<K, V>>) driver;
		root = new EditorWindow();
		saveOrUpdateBt = new TextButton("Save");
		cancelBt = new TextButton("取消");

		root.setButtonAlign(BoxLayoutPack.END);
		root.setModal(true);

		root.addButton(saveOrUpdateBt);
		root.addButton(cancelBt);

		setCancelBtHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				getCancelBt().disable();
				final GwtCallBack<SelectEvent> cancelCallBack = getActionCancelCallBack();
				if (cancelCallBack != null) {
					cancelCallBack.call(event);
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

	public EtlEditor(SimpleBeanEditorDriver<V, EtlEditor<K, V>> driver,
			EtlGridContainer<K, V> parent) {
		this(driver);
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
				final GwtCallBack<V> actionCallBack = getActionCallBack();
				if (actionCallBack != null) {
					actionCallBack.call(t);
				}
				getSaveOrUpdateBt().enable();
				getRoot().hide();
			}
		};
	}

	protected GwtCallBack<V> getActionCallBack() {
		if (isAdd()) {
			return getAddCallBack();
		} else {
			return getUpdateCallBack();
		}
	}

	protected GwtCallBack<SelectEvent> getActionCancelCallBack() {
		if (isAdd()) {
			return getAddCancelCallBack();
		} else {
			return getUpdateCancelCallBack();
		}
	}

	public void edit(V v) {
		setAdd(false);
		getRoot().setHeadingText(getHeadingText());
		getSaveOrUpdateBt().setText("修改");
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

	public GwtCallBack<SelectEvent> getAddCancelCallBack() {
		return addCancelCallBack;
	}

	public void setAddCancelCallBack(GwtCallBack<SelectEvent> addCancelCallBack) {
		this.addCancelCallBack = addCancelCallBack;
		root.setCancelBack(addCancelCallBack);
	}

	public GwtCallBack<V> getAddCallBack() {
		return addCallBack;
	}

	public void setAddCallBack(GwtCallBack<V> addCallBack) {
		this.addCallBack = addCallBack;
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
	@Ignore
	public TextButton getSaveOrUpdateBt() {
		return saveOrUpdateBt;
	}
	@Ignore
	public TextButton getCancelBt() {
		return cancelBt;
	}

	public GwtCallBack<SelectEvent> getUpdateCancelCallBack() {
		return updateCancelCallBack;
	}

	public void setUpdateCancelCallBack(
			GwtCallBack<SelectEvent> updateCancelCallBack) {
		this.updateCancelCallBack = updateCancelCallBack;
	}

	public GwtCallBack<V> getUpdateCallBack() {
		return updateCallBack;
	}

	public void setUpdateCallBack(GwtCallBack<V> updateCallBack) {
		this.updateCallBack = updateCallBack;
	}
}
