/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.web.client.property;

import java.io.Serializable;

import xdata.etl.web.shared.entity.RpcEntity;

import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

/**
 * @author XuehuiHe
 * @date 2013年8月10日
 * 
 */
public interface RpcEntityPropertyAccess<K extends Serializable, V extends RpcEntity<K>>
		extends PropertyAccess<V> {
	@Path("id")
	ModelKeyProvider<V> key();

	ValueProvider<V, K> id();
}
