package xdata.etl.web.client.common.editer;

import java.io.Serializable;

import xdata.etl.web.client.gwt.GwtCallBack;
import xdata.etl.web.client.rpc.RpcCaller;
import xdata.etl.web.shared.entity.RpcEntity;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;

public abstract class RpcEntitySimpleEditor<K extends Serializable, V extends RpcEntity<K>>
		extends EtlSimpleEditor<V> {
	private final RpcCaller<K, V> rpcCaller;

	public RpcEntitySimpleEditor(
			SimpleBeanEditorDriver<V, ? extends EtlEditor<V>> driver,
			String baseHeadingText, RpcCaller<K, V> rpcCaller) {
		super(driver, baseHeadingText);
		this.rpcCaller = rpcCaller;

	}

	@Override
	protected void save(V v, GwtCallBack<V> callback) {
		if (rpcCaller != null) {
			rpcCaller.saveAndReturn(v, callback);
		} else {
			callback.clean();
		}
	}

	@Override
	protected void update(V v, GwtCallBack<V> callback) {
		if (rpcCaller != null) {
			rpcCaller.update(v, callback);
		} else {
			callback.clean();
		}
	}

}
