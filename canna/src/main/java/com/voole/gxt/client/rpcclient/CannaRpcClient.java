package com.voole.gxt.client.rpcclient;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.data.client.loader.RpcProxy;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.loader.LoadResultListStoreBinding;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.voole.gxt.client.canna.gridcontainer.paging.CannaPagingLoadConfigBean;
import com.voole.gxt.client.canna.gridcontainer.paging.CannaPagingLoader;
import com.voole.gxt.client.rpcclient.callback.DeleteCallback;
import com.voole.gxt.client.rpcclient.callback.GetCallback;
import com.voole.gxt.client.rpcclient.callback.SaveCallback;
import com.voole.gxt.client.rpcclient.callback.UpdateCallback;
import com.voole.gxt.client.service.CannaAsyncCallback;
import com.voole.gxt.client.service.CannaServiceAsync;
import com.voole.gxt.shared.entity.CannaEntity;

public abstract class CannaRpcClient<T> {
	public void save(final T t, final SaveCallback<T> callback) {
		getService().save(t, new CannaAsyncCallback<Long>() {
			@Override
			public void onSuccess(Long result) {
				((CannaEntity) t).setId(result);
				callback.postAdd(t);
			}

			@Override
			public void onFailure(Throwable caught) {
				super.onFailure(caught);
				callback.postAdd(null);
			}
		});
	}

	public void saveReturnObjct(final T t, final SaveCallback<T> callback) {
		getService().saveReturnObjct(t, new CannaAsyncCallback<T>() {
			@Override
			public void onSuccess(T result) {
				callback.postAdd(result);
			}

			@Override
			public void onFailure(Throwable caught) {
				super.onFailure(caught);
				callback.postAdd(null);
			}
		});
	}

	public void update(final T t, final UpdateCallback<T> callback) {
		getService().update(t, new CannaAsyncCallback<Void>() {
			@Override
			public void onSuccess(Void result) {
				callback.postUpdate(t);
			}

			@Override
			public void onFailure(Throwable caught) {
				super.onFailure(caught);
				callback.postUpdate(null);
			}
		});
	}

	public void delete(final List<T> list,
			final DeleteCallback<List<T>> callback) {
		List<Long> ids = new ArrayList<Long>();
		for (T cannaEntity : list) {
			ids.add(((CannaEntity) cannaEntity).getId());
		}
		getService().delete(ids, new CannaAsyncCallback<Void>() {
			@Override
			public void onSuccess(Void result) {
				callback.postDelete(list);
			}

			@Override
			public void onFailure(Throwable caught) {
				super.onFailure(caught);
				callback.postDelete(null);
			}
		});
	}

	public void delete(final T t, final DeleteCallback<T> callback) {
		getService().delete(((CannaEntity) t).getId(),
				new CannaAsyncCallback<Void>() {
					public void onSuccess(Void result) {
						callback.postDelete(t);
					};

					@Override
					public void onFailure(Throwable caught) {
						super.onFailure(caught);
						callback.postDelete(null);
					}
				});
	}

	public void get(final GetCallback<List<T>> callback) {
		getService().get(new CannaAsyncCallback<List<T>>() {
			@Override
			public void onSuccess(List<T> result) {
				callback.postGet(result);
			}
		});
	}

	public void get(final CannaPagingLoadConfigBean config,
			final GetCallback<PagingLoadResult<T>> callback) {
		getService().get(config, new CannaAsyncCallback<PagingLoadResult<T>>() {
			@Override
			public void onSuccess(PagingLoadResult<T> result) {
				callback.postGet(result);
			}
		});
	}

	public RpcProxy<CannaPagingLoadConfigBean, PagingLoadResult<T>> getProxy() {
		return new RpcProxy<CannaPagingLoadConfigBean, PagingLoadResult<T>>() {
			@Override
			public void load(CannaPagingLoadConfigBean loadConfig,
					AsyncCallback<PagingLoadResult<T>> callback) {
				getService().get(loadConfig, callback);
			}
		};
	}

	public CannaPagingLoader<T> getPagingLoader(ListStore<T> store) {
		CannaPagingLoader<T> loader = new CannaPagingLoader<T>(getProxy());
		loader.setRemoteSort(true);
		if (store != null) {
			loader.addLoadHandler(new LoadResultListStoreBinding<CannaPagingLoadConfigBean, T, PagingLoadResult<T>>(
					store));
		}
		return loader;
	}

	public abstract CannaServiceAsync<T> getService();
}
