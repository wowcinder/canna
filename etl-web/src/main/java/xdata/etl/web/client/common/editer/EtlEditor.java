/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.common.editer;

import xdata.etl.web.client.gwt.GwtCallBack;

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
public abstract class EtlEditor<V> implements Editor<V> {

	@Ignore
	private final EditorWindow root;
	@Ignore
	private final TextButton saveOrUpdateBt;
	@Ignore
	private final TextButton cancelBt;
	@Ignore
	protected GwtCallBack<V> addCallBack;
	@Ignore
	protected GwtCallBack<V> updateCallBack;
	@Ignore
	private SimpleBeanEditorDriver<V, ? extends EtlEditor<V>> driver;
	@Ignore
	private String baseHeadingText = "";
	@Ignore
	private boolean isAdd = true;
	@Ignore
	private HandlerRegistration cancelBtHandlerHr;
	@Ignore
	private HandlerRegistration saveOrUpdateBtHandlerHr;

	public EtlEditor(SimpleBeanEditorDriver<V, ? extends EtlEditor<V>> driver,
			String baseHeadingText) {
		this.driver = driver;
		this.baseHeadingText = baseHeadingText;
		root = new EditorWindow();
		saveOrUpdateBt = new TextButton("Save");
		cancelBt = new TextButton("取消");

		root.setButtonAlign(BoxLayoutPack.END);
		root.setModal(true);

		root.addButton(saveOrUpdateBt);
		root.addButton(cancelBt);

		cancelBt.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				cancel();
			}
		});

		getRoot().setCancelBack(new GwtCallBack<SelectEvent>() {
			@Override
			protected void _call(SelectEvent t) {
				cancel();
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

	protected abstract void initView();

	protected abstract V newInstance();

	protected abstract void save(V v, GwtCallBack<V> callback);

	protected abstract void update(V v, GwtCallBack<V> callback);

	protected void cancel() {
		clean();
		getRoot().hide();
	}

	protected void clean() {
		final GwtCallBack<V> actionCallBack = getActionCallBack();
		if (actionCallBack != null) {
			actionCallBack.clean();
		}
	}

	protected void saveOrUpdate(V v) {
		if (isAdd()) {
			save(v, getSaveOrUpdateCallBackSwap());
		} else {
			update(v, getSaveOrUpdateCallBackSwap());
		}
	}

	protected GwtCallBack<V> getSaveOrUpdateCallBackSwap() {
		return new GwtCallBack<V>() {
			@Override
			public void _call(V t) {
				final GwtCallBack<V> actionCallBack = getActionCallBack();
				if (actionCallBack != null) {
					actionCallBack.call(t);
				}
				getRoot().hide();
			}

			@Override
			public void clean() {
				super.clean();
				EtlEditor.this.clean();
				getSaveOrUpdateBt().enable();
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

	protected void _edit(V v) {
		getDriver().edit(v);
		getRoot().show();
	}

	public void edit(V v) {
		setAdd(false);
		getRoot().setHeadingText(getHeadingText());
		getSaveOrUpdateBt().setText("修改");
		_edit(v);
	}

	public void add() {
		setAdd(true);
		getRoot().setHeadingText(getHeadingText());
		getSaveOrUpdateBt().setText("添加");
		_edit(newInstance());
	}

	protected String getHeadingText() {
		if (isAdd()) {
			return "添加  " + getBaseHeadingText();
		}
		return "修改  " + getBaseHeadingText();
	}

	protected void setSaveOrUpdateBtHandler(SelectHandler saveOrUpdateBtHandler) {
		if (this.saveOrUpdateBtHandlerHr != null) {
			this.saveOrUpdateBtHandlerHr.removeHandler();
			this.saveOrUpdateBtHandlerHr = null;
		}
		if (saveOrUpdateBtHandler != null) {
			this.saveOrUpdateBtHandlerHr = getSaveOrUpdateBt()
					.addSelectHandler(saveOrUpdateBtHandler);

		}
	}

	@SuppressWarnings("unchecked")
	public SimpleBeanEditorDriver<V, EtlEditor<V>> getDriver() {
		return (SimpleBeanEditorDriver<V, EtlEditor<V>>) driver;
	}

	public void setDriver(
			SimpleBeanEditorDriver<V, ? extends EtlEditor<V>> driver) {
		this.driver = driver;
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

	public GwtCallBack<V> getUpdateCallBack() {
		return updateCallBack;
	}

	public void setUpdateCallBack(GwtCallBack<V> updateCallBack) {
		this.updateCallBack = updateCallBack;
	}
}
