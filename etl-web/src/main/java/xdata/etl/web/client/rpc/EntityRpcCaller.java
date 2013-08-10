/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.rpc;

import java.io.Serializable;
import java.util.List;

import xdata.etl.web.client.gwt.GwtCallBack;
import xdata.etl.web.client.service.RpcAsyncCallback;
import xdata.etl.web.client.service.RpcServiceAsync;
import xdata.etl.web.shared.Provider;
import xdata.etl.web.shared.entity.RpcEntity;

/**
 * @author XuehuiHe
 * @date 2013年8月10日
 * 
 */
public class EntityRpcCaller<K extends Serializable, V extends RpcEntity<K>> {

	private RpcServiceAsync<K, V> service;

	public EntityRpcCaller() {
	}

	public EntityRpcCaller(RpcServiceAsync<K, V> service) {
		this.service = service;

	}

	public void saveAndReturn(V v, final GwtCallBack<V> callBack) {
		service.saveAndReturn(v, new RpcAsyncCallback<V>() {
			@Override
			public void _onSuccess(V t) {
				callBack.call(t);
			}
		});
	}

	public void save(V v, final GwtCallBack<K> callBack) {
		service.save(v, new RpcAsyncCallback<K>() {
			@Override
			public void _onSuccess(K t) {
				callBack.call(t);
			}
		});
	}

	public void update(V v, final GwtCallBack<V> callBack) {
		service.update(v, new RpcAsyncCallback<V>() {
			@Override
			public void _onSuccess(V t) {
				callBack.call(t);
			}

		});
	}

	public void delete(List<K> ids, final GwtCallBack<Void> callBack) {
		service.delete(ids, new RpcAsyncCallback<Void>() {
			@Override
			public void _onSuccess(Void t) {
				callBack.call(t);
			}

		});
	}

	public void delete(K k, final GwtCallBack<Void> callBack) {
		service.delete(new Provider<K>(k), new RpcAsyncCallback<Void>() {
			@Override
			public void _onSuccess(Void t) {
				callBack.call(t);
			}

		});
	}

	public void get(final GwtCallBack<List<V>> callBack) {
		service.get(new RpcAsyncCallback<List<V>>() {
			@Override
			public void _onSuccess(List<V> t) {
				callBack.call(t);
			}

		});
	}

	public void get(K k, final GwtCallBack<V> callBack) {
		service.get(new Provider<K>(k), new RpcAsyncCallback<V>() {
			@Override
			public void _onSuccess(V t) {
				callBack.call(t);
			}

		});
	}

	public RpcServiceAsync<K, V> getService() {
		return service;
	}

	public void setService(RpcServiceAsync<K, V> service) {
		this.service = service;
	}

}