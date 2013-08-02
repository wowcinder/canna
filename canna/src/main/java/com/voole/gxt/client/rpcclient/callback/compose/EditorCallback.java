package com.voole.gxt.client.rpcclient.callback.compose;

import com.voole.gxt.client.rpcclient.callback.SaveCallback;
import com.voole.gxt.client.rpcclient.callback.UpdateCallback;

public interface EditorCallback<T> extends SaveCallback<T>,
		UpdateCallback<T> {

}
